package paketServleti;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import pl.Kontroler;
import pl.paketBean.KorisnikBean;

public class PromenaLozinke extends HttpServlet {

    String upit = "";
    String greska = "";
    String korisnickoIme = "";
    String lozinka = "";
    String novaLozinka = "";
    String potvrdaLozinke = "";

    Connection con = null;
    Statement stmt = null;
    ResultSet RS = null;
    PreparedStatement prosirenje=null;

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
            out.println("<title>Servlet Logovanje</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Logovanje at " + request.getContextPath () + "</h1>");
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
         if(!req.getParameter("novaLozinka").equals("")){
             novaLozinka = req.getParameter("novaLozinka");
        }
         else{
            prazno = true;
            greska = "Nova lozinka nije prosledjena.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }
        if(!req.getParameter("potvrdaLozinke").equals("")){
             potvrdaLozinke = req.getParameter("potvrdaLozinke");
        }
         else{
            prazno = true;
            greska = "Potvrda nove lozinke nije prosledjena.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }
        if(!novaLozinka.equals(potvrdaLozinke)){
            greska = "Nova lozinka i potvrda lozinke nisu jednaki!<br>";
            proslediGresku(greska, req, res);
            greska = "";
        }
        try{
            if(!prazno){
                izvrsi(req,res);
                pozoviJSP(req,res);
            }else{
                throw new Exception();
            }
            
        }catch(Exception e){
            greska = "Doslo je do greske! Pokusajte ponovo";
            proslediGresku(greska,req,res);
        }
    }

    public void izvrsi(HttpServletRequest req, HttpServletResponse res){
        try{

            KorisnikBean kB = new KorisnikBean();
            kB.setKorisnickoIme(korisnickoIme);
            kB.setLozinka(lozinka);
            kB.setNovaLozinka(novaLozinka);
            session.setAttribute("korisnik", kB);
            Kontroler.getInstance().promeniLozinku(kB);

        }
        catch(Exception e){
            e.printStackTrace();
            greska="Konekcija sa bazom podataka nije uspostavljena!";
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
        rd = sc.getRequestDispatcher("/Logovanje.html");
        rd.forward(req,res);
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
