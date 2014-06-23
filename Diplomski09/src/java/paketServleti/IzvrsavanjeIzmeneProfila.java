package paketServleti;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import pl.Kontroler;
import pl.paketBean.KorisnikBean;

public class IzvrsavanjeIzmeneProfila extends HttpServlet {

    String greska = "";
    String ime = "";
    String prezime = "";
    String korisnickoIme = "";
    String lozinka = "";
    String potvrdaLozinke = "";
    String adresa = "";
    String telefon = "";
    String email = "";

    Connection con = null;
    Statement stmt = null;
    ResultSet RS = null;

    boolean prazno = false;

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
            out.println("<title>Servlet Registracija</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registracija at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        session = req.getSession(true);
        sc = getServletContext();

        ime = req.getParameter("ime");
        prezime = req.getParameter("prezime");
        lozinka = req.getParameter("lozinka");
        potvrdaLozinke = req.getParameter("potvrdaLozinke");
        adresa = req.getParameter("adresa");
        telefon = req.getParameter("telefon");
       
        if(!lozinka.equals(potvrdaLozinke)){
            prazno=true;
            greska = "Lozinka i potvrda lozinke nisu jednaki!<br>";
            proslediGresku(greska, req, res);
        }
        email = req.getParameter("email");

        try{
            if(!prazno){
                izvrsi(req,res);
                pozoviJSP(req,res);
            }else{
                throw new Exception();
            }

        }catch(Exception e){
            greska = "Doslo je do greske! Pokusajte ponovo.";
            proslediGresku(greska,req,res);
        }
    }
    public void izvrsi(HttpServletRequest req, HttpServletResponse res){
        try{

            KorisnikBean korisnik = (KorisnikBean)session.getAttribute("korisnik");
             if(!ime.equals("")){
                korisnik.setIme(ime);
            }
             if(!prezime.equals("")){
                korisnik.setPrezime(prezime);
            }

            korisnik.setKorisnickoIme(korisnik.getKorisnickoIme());

             if(!lozinka.equals("")){
                korisnik.setLozinka(lozinka);
            }

            if(!adresa.equals("")){
                korisnik.setAdresa(adresa);
            }
             if(!telefon.equals("")){
                korisnik.setTelefon(telefon);
            }
             if(!email.equals("")){
                korisnik.setEmail(email);
            }

            session.setAttribute("korisnik", korisnik);
            Kontroler.getInstance().izmenaProfila(korisnik);
           }
        catch(Exception e){
            e.printStackTrace();
            greska="Greska: "+e;
            proslediGresku(greska,req,res);
            greska="";
        }
    }
    
    public void proslediGresku(String g, HttpServletRequest req, HttpServletResponse res) {

        session = req.getSession(true);
        session.setAttribute("greska", greska);

        rd = sc.getRequestDispatcher("/Greska.jsp");
        try{
            rd.forward(req,res);
            greska="";
        }
        catch(Exception e){
        }
    }
    public void pozoviJSP(HttpServletRequest req, HttpServletResponse res){
		try{
            rd = sc.getRequestDispatcher("/IzmenaProfila.jsp");
            rd.forward(req, res);
        }
    catch(Exception e){
           greska="nije uspelo "+e;
           proslediGresku(greska,req,res);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
