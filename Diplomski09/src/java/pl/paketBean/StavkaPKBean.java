package pl.paketBean;


public class StavkaPKBean {

    private int korisnikId;
    private int PKID;
    private int RB;
    private int kolicina;
    private ProizvodBean proizvod;

    public ProizvodBean getProizvod() {
        return proizvod;
    }

    public void setProizvod(ProizvodBean proizvod) {
        this.proizvod = proizvod;
    }
    
    public int getPKID() {
        return PKID;
    }

    public void setPKID(int PKID) {
        this.PKID = PKID;
    }

    public int getRB() {
        return RB;
    }

    public void setRB(int RB) {
        this.RB = RB;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }



    
}
