package paketServleti;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import pl.Kontroler;
import pl.paketBean.ProizvodBean;

public class DodavanjeProizvoda extends HttpServlet{
    
    String greska="";
    String naziv="";
    String opis="";
    int kolicina=0;
    double cena=0;
    String datumPoslednjeIzmene="";
    String adminPoslednjeIzmene="";
    String slika="";

    boolean prazno = false;

    Connection con = null;
    Statement stmt = null;
    ResultSet RS = null;

    ServletContext sc = null;
    RequestDispatcher rd = null;
    HttpSession session = null;
    
    @Override
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        doPost(req, res);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        session = req.getSession(true);
        sc=getServletContext();

        if(!req.getParameter("naziv").equals("")){
            naziv=req.getParameter("naziv");
        }
        else{
            prazno = true;
            greska = "Ime proizvoda nije uneto.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }

        if(!req.getParameter("opis").equals("")){
             opis=req.getParameter("opis");
        }
        else{
            prazno = true;
            greska = "Opis nije prosleđen.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }

        if(!req.getParameter("kolicina").equals("")){
             kolicina=Integer.parseInt(req.getParameter("kolicina"));
        }
        else{
            prazno = true;
            greska = "Kolicina nije prosleđena.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }

        if(!req.getParameter("cena").equals("")){
            cena=Double.parseDouble(req.getParameter("cena"));
        }
        else{
            prazno = true;
            greska = "Cena nije prosleđena.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }

//         if(!req.getParameter("datumPoslednjeIzmene").equals("")){
//            datumPoslednjeIzmene=req.getParameter("datumPoslednjeIzmene");
//        }
//        else{
//            prazno = true;
//            greska = "Datum poslednje izmene nije prosleđen.<br>";
//            proslediGresku(greska,req,res);
//            greska="";
//        }
//
//        if(!req.getParameter("adminPoslednjeIzmene").equals("")){
//            adminPoslednjeIzmene = req.getParameter("adminPoslednjeIzmene");
//        }
//        else{
//            prazno = true;
//            greska = "Naziv administratorske izmene nije prosledjen.<br>";
//            proslediGresku(greska,req,res);
//            greska="";
//        }

        if(!req.getParameter("slika").equals("")){
             slika=req.getParameter("slika");
        }
        else{
            prazno = true;
            greska = "Slika proizvoda nije prosledjena.<br>";
            proslediGresku(greska,req,res);
            greska="";
            }
        
        try {
            if(!prazno){
                izvrsi(req,res);
                pozoviJSP(req,res);
            }else{
                throw new Exception();
            }
        }
        catch (Exception e){
            greska="Doslo je do greske! Pokusajte ponovo!";
            proslediGresku(greska, req, res);
            greska="";
        }            
    }
    public void izvrsi(HttpServletRequest req, HttpServletResponse res){
        try{
            ProizvodBean pB = new ProizvodBean();
            pB.setNaziv(naziv);
            pB.setOpis(opis);
            pB.setKolicina(kolicina);
            pB.setCena(cena);
//            pB.setDatumPoslednjeIzmene(datumPoslednjeIzmene);
//            pB.setAdminPoslednjeIzmene(adminPoslednjeIzmene);
            pB.setSlika(slika);
            Kontroler.getInstance().dodajProizvod(pB);
            session.setAttribute("proizvod", pB);
            session.setAttribute("naziv", naziv);
            
           }
        catch(Exception e){
            e.printStackTrace();
            greska="Konekcija sa bazom podataka nije uspostavljena!\n"+e;
            proslediGresku(greska, req, res);
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
        }
        catch (Exception e){
        }
    }
    public void pozoviJSP(HttpServletRequest req, HttpServletResponse res){
        try{
            rd = sc.getRequestDispatcher("/DodavanjeProizvoda.jsp");
            rd.forward(req, res);
        }
        catch (Exception e){
            greska="Nije uspelo "+e;
            greska="";
        }
    }

} 


