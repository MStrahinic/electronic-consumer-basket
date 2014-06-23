package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.paketBean.*;

public class JDBCDatabaseBroker implements DatabaseBroker {
    Connection con;
    Statement stmt;
    ResultSet RS;

    public void poveziSaBazom() throws Exception{
          try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/korpa?autoReconnect=true";
            con = DriverManager.getConnection(url, "root", "root");
          }catch(Exception e){
              throw new Exception("Greska pri konekciji sa bazom!");
          }
      }
      
      public void zatvoriKonekciju() throws Exception{
          con.close();
      }

      public KorisnikBean ulogujKorisnika(KorisnikBean kb1) throws Exception{
          //boolean admin=false;
          KorisnikBean kb=null;
           try{
            stmt=con.createStatement();
            String query1 = "select * from korisnik where korisnickoIme='"+kb1.getKorisnickoIme()+"' and lozinka='"+kb1.getLozinka()+"'";
        	RS=stmt.executeQuery(query1);
            
        if(!RS.next()) {
            throw new Exception("Ne postoji korisnik sa unetim korisnickim imenom ili sifrom!");
        }else{

                kb=new KorisnikBean();
                kb.setIme(RS.getString("ime"));
                kb.setPrezime(RS.getString("prezime"));
                kb.setKorisnickoIme(RS.getString("korisnickoIme"));
                kb.setLozinka(RS.getString("lozinka"));
                kb.setAdresa(RS.getString("adresa"));
                kb.setTelefon(RS.getString("telefon"));
                kb.setEmail(RS.getString("email"));
                kb.setAdmin(RS.getInt("Administrator"));
                kb.setKorisnikId(RS.getInt("korisnikId"));

        }

      }catch(Exception e){
              throw new Exception("Greska pri logovanju korisnika korisnika! "+e);
          }
          return kb;
      }

      public void ubaciKorisnika(KorisnikBean kb) throws Exception{
          try{
            stmt=con.createStatement();
            String query1 = "select * from korisnik where (korisnickoIme='"+kb.getKorisnickoIme()+"')";
            RS=stmt.executeQuery(query1);

        if(RS.next()) {
            throw new Exception("Postoji korisnik sa unetim korisnickim imenom!");
        }
        else {
            String query2 = "insert into korisnik (ime, prezime, korisnickoIme, lozinka, adresa, telefon, email) values('"+kb.getIme()+"','"+kb.getPrezime()+"','"+kb.getKorisnickoIme()+"','"+kb.getLozinka()+"', '"+kb.getAdresa()+"', '"+kb.getTelefon()+"','"+kb.getEmail()+"')";
            stmt.executeUpdate(query2);
        }
      }catch(Exception e){
              throw new Exception("Greska pri ubacivanju korisnika! "+e);
          }
      }

      public void PromenaLozinke(KorisnikBean kb) throws Exception{
          try{
            stmt=con.createStatement();
            String query1 = "select * from korisnik where (korisnickoIme='"+kb.getKorisnickoIme()+"' and lozinka='"+kb.getLozinka()+"')";
            RS=stmt.executeQuery(query1);
        if(!RS.next()) {
            throw new Exception("Ne postoji korisnik sa unetim korisnickim imenom ili lozinkom!");
        }
        else {
            String query2 = "update korisnik set lozinka='"+kb.getNovaLozinka()+"' where (korisnickoIme='"+kb.getKorisnickoIme()+"')";
            stmt.executeUpdate(query2);
        }
      }catch(Exception e){
              throw new Exception("Greska pri promeni lozinke! "+e);
          }
      }

      public void DodavanjeProizvoda(ProizvodBean pB) throws Exception{
          try{
            stmt=con.createStatement();
            String query1 = "select * from proizvod where (naziv='"+pB.getNaziv()+"')";
            RS=stmt.executeQuery(query1);
        if(RS.next()) {
            throw new Exception("Postoji proizvod sa unetim nazivom!");
        }
        else {
            String query2 = "INSERT INTO proizvod (Naziv, Opis, Kolicina, Cena, slika) VALUES ('"+pB.getNaziv()+"','"+pB.getOpis()+"','"+pB.getKolicina()+"','"+pB.getCena()+"','"+pB.getSlika()+"')";
            stmt.executeUpdate(query2);
        }
      }catch(Exception e){
              throw new Exception("Greska prilikom ubacivanja novog proizvoda! "+e);
          }
      }

      public void BrisanjeProizvoda(ProizvodBean pB) throws Exception{
          try{
            stmt=con.createStatement();
            String query1 = "DELETE FROM proizvod WHERE proizvodID="+pB.getProizvodID();
            stmt.executeUpdate(query1);
      
          }catch(Exception e){
              throw new Exception("Greska prilikom brisanja proizvoda! "+e);
          }
      }

      public void BrisanjeProizvodaIzKorpe(StavkaPKBean spk) throws Exception{
          try{
            stmt=con.createStatement();
            String query1 = "DELETE FROM stavkapk WHERE KorisnikId = "+spk.getKorisnikId()+" AND PKID = "+spk.getPKID()+" AND rb="+spk.getRB() ;
            stmt.executeUpdate(query1);

            String query2 = "UPDATE proizvod SET kolicina = kolicina + "+spk.getKolicina()+" WHERE proizvodID="+spk.getProizvod().getProizvodID() ;
            stmt.executeUpdate(query2);

          }catch(Exception e){
              throw new Exception("Greska prilikom brisanja proizvoda! "+e);
          }
      }

       public void AzuriranjeProizvoda(ProizvodBean pB) throws Exception{
           
          try{
            stmt=con.createStatement();
            String query1 = "UPDATE proizvod SET naziv='"+pB.getNaziv()+"', opis='"+pB.getOpis()+"', kolicina="+pB.getKolicina()+", cena="+pB.getCena()+",datumPoslednjeIzmene = '"+pB.getDatumPoslednjeIzmene()+"', adminPoslednjeIzmene='"+pB.getAdminPoslednjeIzmene()+"', slika='"+pB.getSlika()+"' WHERE proizvodID="+pB.getProizvodID();
            stmt.executeUpdate(query1);

         }catch(Exception e){
              throw new Exception("Greska prilikom azuriranja proizvoda! "+e);
         }

      }

      public ArrayList<ProizvodBean> ListaProizvoda() throws Exception{
          ArrayList<ProizvodBean> proizvodi=new ArrayList<ProizvodBean>();
          try{
            stmt=con.createStatement();
            String query1 = "SELECT * From proizvod";
            RS=stmt.executeQuery(query1);
        
                while(RS.next()){
                    ProizvodBean p=new ProizvodBean();
                    p.setNaziv(RS.getString("naziv"));
                    p.setKolicina(RS.getInt("kolicina"));
                    p.setCena(RS.getDouble("cena"));
                    p.setSlika(RS.getString("slika"));
                    p.setOpis(RS.getString("opis"));
                    p.setDatumPoslednjeIzmene(RS.getString("datumPoslednjeIzmene"));
                    p.setAdminPoslednjeIzmene(RS.getString("adminPoslednjeIzmene"));
                    p.setProizvodID(RS.getInt("proizvodID"));
                    proizvodi.add(p);
                }
        if(proizvodi.size()==0) {
            throw new Exception("Nema unetih proizvoda!");
        }
        
      }catch(Exception e){
              throw new Exception("Greska prilikom prikaza proizvoda! "+e);
          }
          return proizvodi;
      }

      public KorisnikBean PodatciProfila(KorisnikBean kbn) throws Exception{
          KorisnikBean kb=null;
         
          try{
            stmt=con.createStatement();
            String query1 = "select * from korisnik where (korisnickoIme='"+kbn.getKorisnickoIme()+"')";
            RS=stmt.executeQuery(query1);
        
            while(RS.next()){
                    kb=new KorisnikBean();
                    kb.setIme(RS.getString("ime"));
                    kb.setPrezime(RS.getString("prezime"));
                    kb.setKorisnickoIme(RS.getString("korisnickoIme"));
                    kb.setLozinka(RS.getString("lozinka"));
                    kb.setAdresa(RS.getString("adresa"));
                    kb.setTelefon(RS.getString("telefon"));
                    kb.setEmail(RS.getString("email"));
                    
                }
            
      }catch(Exception e){
              throw new Exception("Greska pri ubacivanju korisnika! "+e);
          }
          return kb;
      }
      
      public void IzmenaProfila(KorisnikBean kb) throws Exception{
          try{
            stmt=con.createStatement();
            String query2 = "UPDATE korisnik SET ime = '"+kb.getIme()+"' , prezime = '"+kb.getPrezime()+"' , korisnickoIme = '"+kb.getKorisnickoIme()+"' , lozinka = '"+kb.getLozinka()+"' , adresa = '"+kb.getAdresa()+"' , telefon = '"+kb.getTelefon()+"' , email = '"+kb.getEmail()+"' WHERE korisnickoIme = '"+kb.getKorisnickoIme()+"' ";
            stmt.executeUpdate(query2);

      }catch(Exception e){
              throw new Exception("Greska prilikom izmene profila korisnika! "+e);
          }
      }

      public KorpaBean PotrosackaKorpa(KorisnikBean kb) throws Exception{

          KorpaBean korpa = null;
          try{
            stmt=con.createStatement();
            String query1 = "select * from potrosackakorpa where korisnikID=(select KorisnikId from korisnik where korisnickoIme = '"+kb.getKorisnickoIme()+"' ) AND status="+1;
            RS=stmt.executeQuery(query1);
            //int br=0;
            if(RS.next()){
               // br++;
                    KorpaBean k = new KorpaBean();
                    k.setKorisnikId(RS.getInt("KorisnikID"));
                    k.setPKID(RS.getInt("PKID"));
                    k.setDatumNarucivanja(RS.getString("DatumNarucivanja"));
                    k.setDatumKreiranja(RS.getString("DatumKreiranja"));
                    k.setStatus(RS.getInt("Status"));
                    k.setAdresaIsporuke(RS.getString("AdresaIsporuke"));
                    k.setStatus(RS.getInt("Status"));
                    korpa=k;
                }else{
                Statement st=con.createStatement();
                String queryMax="Select MAX(PKID) as MPK from potrosackakorpa where korisnikID=(select KorisnikId from korisnik where korisnickoIme = '"+kb.getKorisnickoIme()+"' )";
                ResultSet rs=st.executeQuery(queryMax);
                int mpk=1;
                    if(rs.next()){
                        mpk=rs.getInt("MPK")+1;
                    }

                    String query="Insert Into potrosackakorpa (KorisnikID, PKID, DatumKreiranja, AdresaIsporuke, Status) values ((select KorisnikId from korisnik where korisnickoIme = '"+kb.getKorisnickoIme()+"'), "+mpk+", '"+DateFormat.getDateInstance(DateFormat.MEDIUM,new Locale("SR")).format(new Date())+"', (select adresa from korisnik where korisnickoIme = '"+kb.getKorisnickoIme()+"'),"+1+")";
                    Statement st1=con.createStatement();
                    st1.executeUpdate(query);


                    stmt=con.createStatement();
            String query111 = "select * from potrosackakorpa where korisnikID=(select KorisnikId from korisnik where korisnickoIme = '"+kb.getKorisnickoIme()+"' ) AND status="+1;
            RS=stmt.executeQuery(query111);
            //int br=0;
            if(RS.next()){
               // br++;
                    KorpaBean k = new KorpaBean();
                    k.setKorisnikId(RS.getInt("KorisnikID"));
                    k.setPKID(RS.getInt("PKID"));
                    k.setDatumNarucivanja(RS.getString("DatumNarucivanja"));
                    k.setDatumKreiranja(RS.getString("DatumKreiranja"));
                    k.setStatus(RS.getInt("Status"));
                    k.setAdresaIsporuke(RS.getString("AdresaIsporuke"));
                    k.setStatus(RS.getInt("Status"));
                    korpa=k;
                }
                }
            Statement stmt1=con.createStatement();
            String query2 = "select * from stavkapk where KorisnikID="+korpa.getKorisnikId()+" AND PKID="+korpa.getPKID();
            ResultSet RS=stmt1.executeQuery(query2);

            while(RS.next()){
                StavkaPKBean spk=new StavkaPKBean();
                spk.setKorisnikId(korpa.getKorisnikId());
                spk.setPKID(korpa.getPKID());
                spk.setRB(RS.getInt("RB"));
                spk.setProizvod(getProizvodByID(RS.getInt("proizvodID")));
                spk.setKolicina(RS.getInt("kolicina"));
                korpa.getStavke().add(spk);
            }
        //if(br==0) {
          //  throw new Exception("Nema unetih proizvoda!");
        //}
      }catch(Exception e){
              throw new Exception("Greska pri pregledu potrosacke korpe! "+e);
          }
          return korpa;
      }

      public void NaruciProizvode(KorisnikBean kb) throws Exception{
          try{
            stmt=con.createStatement();
            String query1 = "UPDATE potrosackakorpa SET status = "+0+" , datumNarucivanja='"+DateFormat.getDateInstance(DateFormat.MEDIUM,new Locale("SR")).format(new Date())+"' WHERE korisnikId = "+kb.getKorisnikId()+" AND status = 1";
            stmt.executeUpdate(query1);

      }catch(Exception e){
              throw new Exception("Greska prilikom narucivanja proizvoda! "+e);
          }
      }
       public ArrayList PrikazNarucenihProizvoda(KorisnikBean kb) throws Exception{
           ArrayList korpe=new ArrayList();

          try{
            stmt=con.createStatement();
            String query1 = "SELECT * FROM potrosackakorpa WHERE status = "+0+" AND korisnikID="+kb.getKorisnikId();
            RS=stmt.executeQuery(query1);

            while(RS.next()){
                KorpaBean korpa=new KorpaBean();
                korpa.setPKID(RS.getInt("PKID"));
                korpa.setDatumKreiranja(RS.getString("DatumKreiranja"));
                korpa.setAdresaIsporuke(RS.getString("AdresaIsporuke"));
                korpa.setDatumNarucivanja(RS.getString("DatumNarucivanja"));
                korpa.setKorisnikId(kb.getKorisnikId());

                korpe.add(korpa);
            }

              for (Iterator it = korpe.iterator(); it.hasNext();) {
                  KorpaBean korpa = (KorpaBean)it.next();

                Statement st=con.createStatement();
                String upit="SELECT * FROM StavkaPK WHERE korisnikID="+kb.getKorisnikId()+" AND pkid="+korpa.getPKID();
                ResultSet rs=st.executeQuery(upit);
                while(rs.next()){
                    StavkaPKBean spk=new StavkaPKBean();
                    spk.setRB(rs.getInt("rb"));
                    spk.setProizvod(getProizvodByID(rs.getInt("proizvodID")));
                    spk.setKolicina(rs.getInt("kolicina"));
                    spk.setPKID(korpa.getPKID());
                    spk.setKorisnikId(kb.getKorisnikId());
                    korpa.getStavke().add(spk);
                }

              }

      }catch(Exception e){
          e.printStackTrace();
              throw new Exception("Greska prilikom narucivanja proizvoda! "+e);
          }
           return korpe;
      }

       public ArrayList ListaKorpi() throws Exception{
           ArrayList korpe=new ArrayList();

          try{
            stmt=con.createStatement();
            String query1 = "SELECT * FROM potrosackakorpa WHERE status = "+0;
            RS=stmt.executeQuery(query1);

            while(RS.next()){
                KorpaBean korpa=new KorpaBean();
                korpa.setPKID(RS.getInt("PKID"));
                korpa.setDatumKreiranja(RS.getString("DatumKreiranja"));
                korpa.setAdresaIsporuke(RS.getString("AdresaIsporuke"));
                korpa.setDatumNarucivanja(RS.getString("DatumNarucivanja"));
                korpa.setKorisnikId(RS.getInt("KorisnikId"));

                //System.out.println("PKID="+korpa.getPKID());

                korpe.add(korpa);
            }

              for (Iterator it = korpe.iterator(); it.hasNext();) {
                  KorpaBean korpa = (KorpaBean)it.next();

                Statement st=con.createStatement();
                String upit="SELECT * FROM StavkaPK WHERE pkid="+korpa.getPKID()+" AND korisnikID="+korpa.getKorisnikId();
                ResultSet rs=st.executeQuery(upit);
                while(rs.next()){
                    StavkaPKBean spk=new StavkaPKBean();
                    spk.setRB(rs.getInt("rb"));
                    spk.setProizvod(getProizvodByID(rs.getInt("proizvodID")));
                    spk.setKolicina(rs.getInt("kolicina"));
                    spk.setPKID(korpa.getPKID());
                    spk.setKorisnikId(korpa.getKorisnikId());
                    korpa.getStavke().add(spk);
                }

              }

      }catch(Exception e){
          e.printStackTrace();
              throw new Exception("Greska prilikom narucivanja proizvoda! "+e);
          }
           return korpe;
      }
        public ArrayList<ProizvodBean> PretragaProizvoda(String atributPretrage, String kljucnaRec) throws Exception{
          ArrayList<ProizvodBean> proizvodi=new ArrayList<ProizvodBean>();
          try{
            stmt=con.createStatement();
            String query1 = "SELECT * FROM proizvod WHERE ("+atributPretrage+" like '%"+kljucnaRec+"%')";//082660260//069366800
            RS=stmt.executeQuery(query1);

                while(RS.next()){
                    ProizvodBean p=new ProizvodBean();
                    p.setNaziv(RS.getString("naziv"));
                    p.setKolicina(RS.getInt("kolicina"));
                    p.setCena(RS.getDouble("cena"));
                    p.setSlika(RS.getString("slika"));
                    p.setOpis(RS.getString("opis"));
                    p.setDatumPoslednjeIzmene(RS.getString("datumPoslednjeIzmene"));
                    p.setAdminPoslednjeIzmene(RS.getString("adminPoslednjeIzmene"));
                    p.setProizvodID(RS.getInt("proizvodID"));
                    proizvodi.add(p);
                }
        if(proizvodi.size()==0) {
            throw new Exception("Nema unetih proizvoda!");
        }

      }catch(Exception e){
             throw new Exception("Greska prilikom pretrage proizvoda! "+e);
          }
          return proizvodi;
      }

       public void PunjenjeKorpe(ProizvodBean pb, KorisnikBean kob, int kolicina) throws Exception{

           KorpaBean kb=PotrosackaKorpa(kob);
           try{
            stmt=con.createStatement();
             String query1 = "SELECT MAX(rb) AS MRB FROM stavkaPK WHERE KorisnikID="+kb.getKorisnikId()+" AND PKID="+kb.getPKID();
             ResultSet rs=stmt.executeQuery(query1);
             int rb=1;
              if (rs.next()) {
                  rb=rs.getInt("MRB")+1;
              }
             String query2="Insert INTO StavkaPK (korisnikID, PKID, RB, proizvodID, Kolicina) VALUES ("+kb.getKorisnikId()+","+kb.getPKID()+","+rb+","+pb.getProizvodID()+", "+kolicina+")";
             Statement st=con.createStatement();
             st.executeUpdate(query2);

            Statement st1=con.createStatement();
            String query3 = "UPDATE proizvod SET kolicina=kolicina - "+kolicina+" WHERE naziv='"+pb.getNaziv()+"'";
            st1.executeUpdate(query3);

      }catch(Exception e){
              throw new Exception("Greska prilikom ubacivanja u korpu! "+e);
          }
      }

     public ProizvodBean getProizvodByID(int id){
         ProizvodBean proizvod=null;
        try {
            String query = "select * from proizvod where proizvodID=" + id;
            Statement stmt=con.createStatement();
            ResultSet RS = stmt.executeQuery(query);
            if(RS.next()){
                proizvod=new ProizvodBean();
                proizvod.setProizvodID(id);
                proizvod.setNaziv(RS.getString("naziv"));
                proizvod.setOpis(RS.getString("opis"));
                proizvod.setCena(RS.getDouble("cena"));
                proizvod.setKolicina(RS.getInt("kolicina"));
                proizvod.setSlika(RS.getString("slika"));
                proizvod.setAdminPoslednjeIzmene(RS.getString("AdminPoslednjeIzmene"));
                proizvod.setDatumPoslednjeIzmene(RS.getString("DatumPoslednjeIzmene"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
            return proizvod;
     }
}
