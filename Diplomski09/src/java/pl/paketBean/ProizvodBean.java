package pl.paketBean;

import java.io.Serializable;

public class ProizvodBean implements Serializable{

    private String naziv;
    private String opis;
    private int kolicina;
    private double cena;
    private String datumPoslednjeIzmene;
    private String adminPoslednjeIzmene;
    private String slika;
    private int proizvodID;


    public int getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(int proizvodID) {
        this.proizvodID = proizvodID;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }


    public String getAdminPoslednjeIzmene() {
        return adminPoslednjeIzmene;
    }

    public void setAdminPoslednjeIzmene(String adminPoslednjeIzmene) {
        this.adminPoslednjeIzmene = adminPoslednjeIzmene;
    }


    public String getDatumPoslednjeIzmene() {
        return datumPoslednjeIzmene;
    }

    public void setDatumPoslednjeIzmene(String datumPoslednjeIzmene) {
        this.datumPoslednjeIzmene = datumPoslednjeIzmene;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

}
