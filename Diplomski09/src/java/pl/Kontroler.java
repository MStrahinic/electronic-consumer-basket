package pl;

import db.DatabaseBroker;
import db.HibernateDatabaseBroker;
import db.JDBCDatabaseBroker;
import java.util.ArrayList;
import pl.paketBean.*;


public class Kontroler {
//DatabaseBroker db=new JDBCDatabaseBroker();
DatabaseBroker db=new HibernateDatabaseBroker();

private static Kontroler instance;

public static Kontroler getInstance(){
    if(instance==null){
        instance=new Kontroler();
    }
    return instance;
}
    public KorisnikBean ulogujKorisnika(KorisnikBean kb) throws Exception{
        db.poveziSaBazom();
        kb=db.ulogujKorisnika(kb);
        db.zatvoriKonekciju();
        return kb;
    }

    public void registrujKorisnika(KorisnikBean kb) throws Exception{
        db.poveziSaBazom();
        db.ubaciKorisnika(kb);
        db.zatvoriKonekciju();
        
    }

    public void promeniLozinku(KorisnikBean kb) throws Exception{
        db.poveziSaBazom();
        db.PromenaLozinke(kb);
        db.zatvoriKonekciju();
    }

    public void dodajProizvod(ProizvodBean pB) throws Exception{
        db.poveziSaBazom();
        db.DodavanjeProizvoda(pB);
        db.zatvoriKonekciju();
    }

    public void obrisiProizvod(ProizvodBean pB) throws Exception{
        db.poveziSaBazom();
        db.BrisanjeProizvoda(pB);
        db.zatvoriKonekciju();
    }

    public void obrisiProizvodIzKorpe(StavkaPKBean spk) throws Exception{
        db.poveziSaBazom();
        db.BrisanjeProizvodaIzKorpe(spk);
        db.zatvoriKonekciju();
    }

    public void azurirajProizvod(ProizvodBean p) throws Exception{
        db.poveziSaBazom();
        db.AzuriranjeProizvoda(p);
        db.zatvoriKonekciju();
    }

     public ArrayList<ProizvodBean> listaProizvoda() throws Exception{

        db.poveziSaBazom();
        ArrayList<ProizvodBean> proizvodi= db.ListaProizvoda();
        db.zatvoriKonekciju();
        return proizvodi;
    }

     public KorisnikBean podaciProfila(KorisnikBean kb) throws Exception{

        db.poveziSaBazom();
        KorisnikBean korisnik = db.PodatciProfila(kb);
        db.zatvoriKonekciju();
        return korisnik;
    }

       public void izmenaProfila(KorisnikBean kb) throws Exception{

        db.poveziSaBazom();
        db.IzmenaProfila(kb);
        db.zatvoriKonekciju();

    }

       public KorpaBean potrosackaKorpa(KorisnikBean kb) throws Exception{
       try{
        db.poveziSaBazom();
        KorpaBean korpa= db.PotrosackaKorpa(kb);
        db.zatvoriKonekciju();
        return korpa;
       }catch(Exception e){
           e.printStackTrace();
           throw e;
       }
        //return korpa;
    }

        public void naruciProizvode(KorisnikBean kb) throws Exception{

        db.poveziSaBazom();
        db.NaruciProizvode(kb);
        db.zatvoriKonekciju();

    }

        public ArrayList prikaziNaruceneProizvode(KorisnikBean kb) throws Exception{

        db.poveziSaBazom();
        ArrayList korpe=db.PrikazNarucenihProizvoda(kb);
        db.zatvoriKonekciju();
        return korpe;

    }

        public ArrayList listaKorpi() throws Exception{

        db.poveziSaBazom();
        ArrayList korpe=db.ListaKorpi();
        db.zatvoriKonekciju();
        return korpe;

    }
        public ArrayList<ProizvodBean> pretragaProizvoda(String atributPretrage, String kljucnaRec) throws Exception{

        db.poveziSaBazom();
        ArrayList<ProizvodBean> proizvodi= db.PretragaProizvoda(atributPretrage, kljucnaRec);
        db.zatvoriKonekciju();
        return proizvodi;
    }
      
        public void punjenjeKorpe(ProizvodBean pb, KorisnikBean kb, int kolicina) throws Exception{

        db.poveziSaBazom();
        db.PunjenjeKorpe(pb, kb, kolicina);
        db.zatvoriKonekciju();

    }

}
