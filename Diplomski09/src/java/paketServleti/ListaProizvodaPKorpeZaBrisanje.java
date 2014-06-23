package paketServleti;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import pl.Kontroler;
import pl.paketBean.*;

public class ListaProizvodaPKorpeZaBrisanje extends HttpServlet {
    
    String greska="";
    
    boolean prazno = false;

    Connection con = null;
    Statement stmt = null;
    ResultSet RS = null;
    
    ServletContext sc = null;
    RequestDispatcher rd = null;
    HttpSession session = null;
    
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DobrodosliServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DobrodosliServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally {
            out.close();
        }
    }
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
            System.out.println(kb.getKorisnickoIme());
            KorpaBean korpa = Kontroler.getInstance().potrosackaKorpa(kb);
            //System.out.println("Broj stavki: "+korpa.getStavke().size());
            session.setAttribute("korpa", korpa);
        }
        catch (Exception e){
            greska="Doslo je do greske kod povezivanja sa bazom! "+ e;
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
            rd = sc.getRequestDispatcher("/PotrosackaKorpaBrisanje.jsp");
            rd.forward(req, res);
        }
        catch (Exception e){
                greska="Nije uspelo! "+e;
                proslediGresku(greska, req, res);
                greska="";
        }
    }

}
