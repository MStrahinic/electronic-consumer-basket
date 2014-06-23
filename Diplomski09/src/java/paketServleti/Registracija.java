package paketServleti;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import pl.Kontroler;
import pl.paketBean.KorisnikBean;

public class Registracija extends HttpServlet {

    int admin = 0;
    String upit = "";
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

        prazno=false;
        if(!req.getParameter("ime").equals("")){
            ime = req.getParameter("ime");
        }
        else{
            prazno = true;
            greska = "Ime nije prosledjeno.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }
        if(!req.getParameter("prezime").equals("")){
            prezime = req.getParameter("prezime");
        }
        else{
            prazno = true;
            greska = "Prezime nije prosledjeno.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }
        if(!req.getParameter("korisnickoIme").equals("")){
            korisnickoIme = req.getParameter("korisnickoIme");
        }
        else{
            prazno = true;
            greska = "Korisnicko ime nije prosledjeno.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }
        if(!req.getParameter("lozinka").equals("")){
             lozinka = req.getParameter("lozinka");
        }
         else{
            prazno = true;
            greska = "Lozinka nije prosledjena.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }
        if(!req.getParameter("potvrdaLozinke").equals("")){
            potvrdaLozinke = req.getParameter("potvrdaLozinke");
        }
        else{
            prazno = true;
            greska = "Potvrda lozinke nije prosledjena.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }
        if(!req.getParameter("adresa").equals("")){
            adresa = req.getParameter("adresa");
        }
        else{
            prazno = true;
            greska = "Adresa nije prosledjena.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }
        if(!req.getParameter("telefon").equals("")){
            telefon = req.getParameter("telefon");
        }
        else{
            prazno = true;
            greska = "Telefon nije prosledjen.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }

        if(!lozinka.equals(potvrdaLozinke)){
            prazno=true;
            greska = "Lozinka i potvrda lozinke nisu jednaki!<br>";
            proslediGresku(greska, req, res);
        }
        if(!req.getParameter("email").equals("")){
            email = req.getParameter("email");
        }
        else{
            prazno = true;
            greska = "Email nije prosledjen.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }
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

            //admin=RS.getInt("Administrator");
            KorisnikBean kB = new KorisnikBean();
            kB.setIme(ime);
            kB.setPrezime(prezime);
            kB.setKorisnickoIme(korisnickoIme);
            kB.setLozinka(lozinka);
            kB.setAdresa(adresa);
            kB.setTelefon(telefon);
            kB.setEmail(email);
            session.setAttribute("korisnik", kB);
            Kontroler.getInstance().registrujKorisnika(kB);
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
            String titula="korisnice";
            session.setAttribute("titula", titula);
            rd = sc.getRequestDispatcher("/Dobrodosao.jsp");
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
