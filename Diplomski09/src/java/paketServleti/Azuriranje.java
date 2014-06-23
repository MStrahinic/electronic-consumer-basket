package paketServleti;

import java.io.*;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import javax.servlet.*;
import javax.servlet.http.*;
import pl.Kontroler;
import pl.paketBean.ProizvodBean;

public class Azuriranje extends HttpServlet{

    String greska="";
    String naziv="";
    String opis="";
    int kolicina=0;
    double cena=0;
    String datumPoslednjeIzmene="";
    String adminPoslednjeIzmene="";
    String slika="";
   
    Connection con = null;
    Statement stmt = null;
    ResultSet RS = null;

    boolean prazno = false;
    
    ServletContext sc = null;
    RequestDispatcher rd = null;
    HttpSession session = null;
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        doPost(req, res);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        session=req.getSession(true);
        sc=getServletContext();

        int selected=Integer.parseInt(req.getParameter("rd"));
        ArrayList proizvodi=(ArrayList)session.getAttribute("proizvodi");
        ProizvodBean p=(ProizvodBean)proizvodi.get(selected);

        naziv=req.getParameter("naziv");
        opis=req.getParameter("opis");
        kolicina=0;
        cena=0;
        try{

            kolicina=Integer.parseInt(req.getParameter("kolicina"));

        }catch (Exception e){}
        try{

            cena=Double.parseDouble(req.getParameter("cena"));

        }catch (Exception e){}
        datumPoslednjeIzmene=DateFormat.getDateInstance(DateFormat.MEDIUM,new Locale("SR")).format(new Date());
        adminPoslednjeIzmene = req.getParameter("adminPoslednjeIzmene");
        slika=req.getParameter("slika");

        try{
            izvrsi(req, res, p);
            pozoviJSP(req, res);
        }
        catch (Exception e){
            greska="Doslo je do greske! Pokusajte ponovo!";
            proslediGresku(greska, req, res);
            greska="";
        }
    }
    
    public void izvrsi(HttpServletRequest req, HttpServletResponse res,  ProizvodBean p){
        try{
            if(!naziv.equals("")){
                p.setNaziv(naziv);
            }
            if(!opis.equals("")){
                p.setOpis(opis);
            }
            if(kolicina!=0){
                p.setKolicina(kolicina);
            }
            if(cena!=0){
                p.setCena(cena);
            }
            if(!slika.equals("")){
                p.setSlika(slika);
            }
            if(!adminPoslednjeIzmene.equals("")){
                p.setAdminPoslednjeIzmene(adminPoslednjeIzmene);
            }
            //if(!datumPoslednjeIzmene.equals("")){
                p.setDatumPoslednjeIzmene(datumPoslednjeIzmene);
            //}

            Kontroler.getInstance().azurirajProizvod(p);
        }
        catch (Exception e) {
            e.printStackTrace();
            greska="Konekcija sa bazom podataka nije uspostavljena!"+e;
            proslediGresku(greska,req,res);
            greska="";
        }       
    }
    
    public void proslediGresku(String g, HttpServletRequest req, HttpServletResponse res){
        session=req.getSession(true);
        session.setAttribute("greska", greska);
        rd = sc.getRequestDispatcher("/Greska.jsp");
        try {
            rd.forward(req, res);
            greska="";
        }catch (Exception e){
        }
    }

    public void pozoviJSP(HttpServletRequest req, HttpServletResponse res){
    try{
        rd = sc.getRequestDispatcher("/ListaProizvodaZaAzuriranje");
        rd.forward(req, res);
    }
    catch (Exception e){
        greska="nije uspelo "+e;
        greska="";
    }
}
    
}
