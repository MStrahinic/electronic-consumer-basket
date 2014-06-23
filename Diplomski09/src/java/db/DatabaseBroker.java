/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.util.ArrayList;
import pl.paketBean.KorisnikBean;
import pl.paketBean.KorpaBean;
import pl.paketBean.ProizvodBean;
import pl.paketBean.StavkaPKBean;


public interface DatabaseBroker {

    void AzuriranjeProizvoda(ProizvodBean pB) throws Exception;

    void BrisanjeProizvoda(ProizvodBean pB) throws Exception;

    void BrisanjeProizvodaIzKorpe(StavkaPKBean spk) throws Exception;

    void DodavanjeProizvoda(ProizvodBean pB) throws Exception;

    void IzmenaProfila(KorisnikBean kb) throws Exception;

    ArrayList ListaKorpi() throws Exception;

    ArrayList<ProizvodBean> ListaProizvoda() throws Exception;

    void NaruciProizvode(KorisnikBean kb) throws Exception;

    KorisnikBean PodatciProfila(KorisnikBean kbn) throws Exception;

    KorpaBean PotrosackaKorpa(KorisnikBean kb) throws Exception;

    ArrayList<ProizvodBean> PretragaProizvoda(String atributPretrage, String kljucnaRec) throws Exception;

    ArrayList PrikazNarucenihProizvoda(KorisnikBean kb) throws Exception;

    void PromenaLozinke(KorisnikBean kb) throws Exception;

    void PunjenjeKorpe(ProizvodBean pb, KorisnikBean kob, int kolicina) throws Exception;

    ProizvodBean getProizvodByID(int id);

    void poveziSaBazom() throws Exception;

    void ubaciKorisnika(KorisnikBean kb) throws Exception;

    KorisnikBean ulogujKorisnika(KorisnikBean kb1) throws Exception;

    void zatvoriKonekciju() throws Exception;

}
