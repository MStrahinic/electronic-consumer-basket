/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import db.hibernateUtility.HibernateUtility;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import pl.paketBean.KorisnikBean;
import pl.paketBean.KorpaBean;
import pl.paketBean.ProizvodBean;
import pl.paketBean.StavkaPKBean;


public class HibernateDatabaseBroker implements DatabaseBroker{

    Session session;

    @Override
    public void AzuriranjeProizvoda(ProizvodBean pB) throws Exception {
        session.saveOrUpdate(pB);

    }

    @Override
    public void BrisanjeProizvoda(ProizvodBean pB) throws Exception {
        session.delete(pB);
    }

    @Override
    public void BrisanjeProizvodaIzKorpe(StavkaPKBean spk) throws Exception {
        try{
            ProizvodBean pb=spk.getProizvod();
        String q="Delete from Stavkapk where korisnikID="+spk.getKorisnikId()+" and pkid="+spk.getPKID()+" and RB="+spk.getRB();
       SQLQuery sqlq=session.createSQLQuery(q);
       sqlq.executeUpdate();
        session.getTransaction().commit();
        session.beginTransaction();
        pb.setKolicina(pb.getKolicina()+spk.getKolicina());
        session.saveOrUpdate(pb);
        }catch(Exception e){
            System.out.println("greska: "+e);
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void DodavanjeProizvoda(ProizvodBean pB) throws Exception {
        ProizvodBean primerProizvoda=new ProizvodBean();
        primerProizvoda.setNaziv(pB.getNaziv());
    	Criteria crit = session.createCriteria(ProizvodBean.class);
    	crit.add(Example.create(primerProizvoda));
    	primerProizvoda = (ProizvodBean) crit.uniqueResult();
        if(primerProizvoda==null){
            session.saveOrUpdate(pB);
        }else{
            throw new Exception();
        }
    }

    @Override
    public void IzmenaProfila(KorisnikBean kb) throws Exception {
        session.saveOrUpdate(kb);
    }

    @Override
    public ArrayList ListaKorpi() throws Exception {
        try{
        org.hibernate.Query queryK=session.createQuery("from KorisnikBean");
        List listaKorisnika=queryK.list();
        ArrayList zbirnaLista=new ArrayList();
        for(int i=0;i<listaKorisnika.size();i++){
            zbirnaLista.addAll(PrikazNarucenihProizvoda((KorisnikBean)listaKorisnika.get(i)));
        }
        return zbirnaLista;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public ArrayList<ProizvodBean> ListaProizvoda() throws Exception {
        org.hibernate.Query query=session.createQuery("from ProizvodBean");
        List lista=query.list();
        return (ArrayList<ProizvodBean>)lista;
    }

    @Override
    public void NaruciProizvode(KorisnikBean kb) throws Exception {
        org.hibernate.Query query=session.createQuery("from KorpaBean where KorisnikId="+kb.getKorisnikId()+" and status=1");
        KorpaBean korpa=(KorpaBean)query.uniqueResult();
        korpa.setStatus(0);
        korpa.setDatumNarucivanja(DateFormat.getDateInstance(DateFormat.MEDIUM,new Locale("SR")).format(new Date()));
        session.saveOrUpdate(korpa);

    }

    @Override
    public KorisnikBean PodatciProfila(KorisnikBean kbn) throws Exception {
        Query q=session.createQuery("from KorisnikBean kb where kb.korisnickoIme='"+kbn.getKorisnickoIme()+"'");
        KorisnikBean kb=(KorisnikBean)q.uniqueResult();
        if(kb==null){
            throw new Exception("Korisnik sa unetim korisnickim imenom i lozinkom ne postoji!");
        }
        return kb;
    }

    @Override
    public KorpaBean PotrosackaKorpa(KorisnikBean kb) throws Exception {
        Query q=session.createQuery("from KorisnikBean kb where kb.korisnickoIme='"+kb.getKorisnickoIme()+"'");
        KorisnikBean kbn=(KorisnikBean)q.uniqueResult();
        KorpaBean korpa;
        String sql= "select {potrosackaKorpa.*} from potrosackaKorpa where potrosackaKorpa.korisnikId="+kbn.getKorisnikId()+" and potrosackaKorpa.status="+1;
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity("potrosackaKorpa", KorpaBean.class);
        List lista=query.list();
        
        if(lista.size()==0){
            try{
                session.beginTransaction();
                KorpaBean newKb=new KorpaBean();
                newKb.setKorisnikId(kbn.getKorisnikId());
                newKb.setDatumKreiranja(DateFormat.getDateInstance(DateFormat.MEDIUM,new Locale("SR")).format(new Date()));
                newKb.setAdresaIsporuke(kbn.getAdresa());
                newKb.setStatus(1);
                int max=0;
                try{
                    String sql1= "select max(PKID) as maxPK from potrosackakorpa";
                    SQLQuery sqlq=session.createSQLQuery(sql1);
                     max=((Integer)sqlq.uniqueResult()).intValue();
                }catch(Exception e){System.out.println("GRESKAAAAA: "+e);}
                max+=1;
                newKb.setPKID(max);
                session.saveOrUpdate(newKb);
                korpa=newKb; 
            }catch(Exception e){
                e.printStackTrace();
                throw e;
            }
        }else{
            korpa=(KorpaBean)lista.get(0);
        }
        System.out.println(korpa);
        return korpa;
    }

    @Override
    public ArrayList<ProizvodBean> PretragaProizvoda(String atributPretrage, String kljucnaRec) throws Exception {
        String sql = "select {proizvod.*} from Proizvod proizvod where ("+atributPretrage+" like '%"+kljucnaRec+"%')";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity("proizvod", ProizvodBean.class);
        List lista = query.list();
        return (ArrayList<ProizvodBean>)lista;
    }

    @Override
    public ArrayList PrikazNarucenihProizvoda(KorisnikBean kb) throws Exception {
        Query q=session.createQuery("from KorisnikBean kb where kb.korisnickoIme='"+kb.getKorisnickoIme()+"'");
        KorisnikBean kbn=(KorisnikBean)q.uniqueResult();
        Query qKorpe=session.createQuery("from KorpaBean kb where kb.korisnikId="+kbn.getKorisnikId()+" and status="+0);
        List korpe=qKorpe.list();
        return new ArrayList(korpe);
    }

    @Override
    public void PromenaLozinke(KorisnikBean kbn) throws Exception {
        KorisnikBean kb=new KorisnikBean();
        kb.setKorisnickoIme(kbn.getKorisnickoIme());
    	Criteria crit = session.createCriteria(KorisnikBean.class);
    	crit.add(Example.create(kb));
    	kb = (KorisnikBean) crit.uniqueResult();
        kb.setLozinka(kbn.getLozinka());
        session.saveOrUpdate(kb);
    }

    @Override
    public void PunjenjeKorpe(ProizvodBean pb, KorisnikBean kob, int kolicina) throws Exception {
        try{
            
        System.out.println("ulazim u punjenje");
        KorpaBean kb=PotrosackaKorpa(kob);
        int maxStavka=0;
        if(kb.getStavke()!=null){
            if(kb.getStavke().size()>0){
                maxStavka=((StavkaPKBean)(kb.getStavke().get(kb.getStavke().size()-1))).getRB();
            }else{
                maxStavka=0;
            }     
        }
        maxStavka+=1;
        pb.setKolicina(pb.getKolicina()-kolicina);
        session.beginTransaction();
         System.out.println( pb.getKolicina());
        String sql="UPDATE proizvod SET proizvod.kolicina="+pb.getKolicina()+" WHERE proizvod.proizvodID="+pb.getProizvodID()+"";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity("proizvod", ProizvodBean.class);
        System.out.println("za proizvod "+ query.executeUpdate());
        String sql1="Insert INTO StavkaPK (korisnikID, PKID, RB, proizvodID, Kolicina) VALUES ("+kb.getKorisnikId()+","+kb.getPKID()+","+maxStavka+","+pb.getProizvodID()+", "+kolicina+")";
        SQLQuery query1 = session.createSQLQuery(sql1);
        System.out.println( "za stavku "+query1.executeUpdate());
         
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProizvodBean getProizvodByID(int id) {
        ProizvodBean pb=new ProizvodBean();
        pb.setProizvodID(id);
    	Criteria crit = session.createCriteria(ProizvodBean.class);
    	crit.add(Example.create(pb));
    	pb = (ProizvodBean) crit.uniqueResult();
        return pb;
    }

    @Override
    public void poveziSaBazom() throws Exception {
        session=HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
    }

    @Override
    public void ubaciKorisnika(KorisnikBean kbn) throws Exception {
        KorisnikBean kb=new KorisnikBean();
        kb.setKorisnickoIme(kbn.getKorisnickoIme());
    	Criteria crit = session.createCriteria(KorisnikBean.class);
    	crit.add(Example.create(kb));
    	kb = (KorisnikBean) crit.uniqueResult();
        if(kb==null){
            session.saveOrUpdate(kbn);
        }else{
            throw new Exception();
        }

    }

    @Override
    public KorisnikBean ulogujKorisnika(KorisnikBean kbn) throws Exception {
        Query q=session.createQuery("from KorisnikBean kb where kb.korisnickoIme='"+kbn.getKorisnickoIme()+"' AND kb.lozinka='"+kbn.getLozinka()+"'");

        KorisnikBean kb=(KorisnikBean)q.uniqueResult();
       
        if(kb==null){
            throw new Exception("Korisnik sa unetim korisnickim imenom i lozinkom ne postoji!");
        }
        return kb;
    }

    @Override
    public void zatvoriKonekciju() throws Exception {
        session.getTransaction().commit();
        HibernateUtility.getSessionFactory().close();
    }

}
