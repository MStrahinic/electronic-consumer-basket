package pl.paketBean;

import java.util.ArrayList;
import java.util.List;

public class KorpaBean {

    private int korisnikId;
    private int PKID;
    private String datumKreiranja;
    private String adresaIsporuke;
    private int status;
    private String datumNarucivanja;
    private List stavke;//=new ArrayList();

    public List getStavke() {
        return stavke;
    }

    public void setStavke(List stavke) {
        this.stavke = stavke;
    }

    public int getPKID() {
        return PKID;
    }

    public void setPKID(int PKID) {
        this.PKID = PKID;
    }

    public String getAdresaIsporuke() {
        return adresaIsporuke;
    }

    public void setAdresaIsporuke(String adresaIsporuke) {
        this.adresaIsporuke = adresaIsporuke;
    }

    public String getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(String datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public String getDatumNarucivanja() {
        return datumNarucivanja;
    }

    public void setDatumNarucivanja(String datumNarucivanja) {
        this.datumNarucivanja = datumNarucivanja;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String toString(){
        return "PKID="+PKID;
    }
    
}
