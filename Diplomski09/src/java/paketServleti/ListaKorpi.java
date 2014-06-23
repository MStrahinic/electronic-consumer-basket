package paketServleti;

import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import pl.Kontroler;
import pl.paketBean.*;

public class ListaKorpi extends HttpServlet{
    
    String greska="";
    
    Connection con = null;
    Statement stmt = null;
    ResultSet RS = null;
    
    ServletContext sc = null;
    RequestDispatcher rd = null;
    HttpSession session = null;
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        doPost(req, res);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        session = req.getSession(true);
        sc=getServletContext();

        try {
            izvrsi(req, res);
            pozoviJSP(req, res);
        }
        catch (Exception e){
            proslediGresku("Doslo je do greske! Pokusajte ponovo. "+e, req, res);
        }
    }
    public void izvrsi(HttpServletRequest req, HttpServletResponse res){
        try{

            KorisnikBean kb=(KorisnikBean)session.getAttribute("korisnik");
            ArrayList korpe=Kontroler.getInstance().listaKorpi();
            session.setAttribute("korpe", korpe);
        }
        catch (Exception e){
            greska="Doslo je do greske kod povezivanja sa bazom! "+e;
            proslediGresku(greska, req, res);
        }
    }

     public void proslediGresku(String g, HttpServletRequest req, HttpServletResponse res){
        session=req.getSession(true);
        session.setAttribute("greska", g);
        try {
            rd = sc.getRequestDispatcher("/Greska.jsp");
            rd.forward(req, res);
        }
        catch (Exception e){
        }
    }
             public void pozoviJSP(HttpServletRequest req, HttpServletResponse res){
        try {
            rd = sc.getRequestDispatcher("/PrikazNarucenihProizvoda.jsp");
            rd.forward(req, res);
        }
        catch (Exception e){
                greska="Nije uspelo! "+e;
                proslediGresku(greska, req, res);
                greska="";
        }
    }


}
