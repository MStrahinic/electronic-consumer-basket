package paketServleti;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import pl.Kontroler;
import pl.paketBean.KorisnikBean;

public class Logovanje extends HttpServlet {

    int admin = 0;
    String upit = "";
    String greska = "";
    String korisnickoIme = "";
    String lozinka = "";

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
            kB.setKorisnickoIme(korisnickoIme);
            kB.setLozinka(lozinka);
            kB=Kontroler.getInstance().ulogujKorisnika(kB);
            
                admin=kB.getAdmin();

            session.setAttribute("korisnik", kB);

        }catch(Exception e){
            e.printStackTrace();
            greska="Greska u logovanju!";
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
             System.out.println("Admin="+admin);
        if (admin == 1){
            titula="administratore";
           
            }
            session.setAttribute("titula", titula);
            rd = sc.getRequestDispatcher("/Dobrodosao.jsp");
            rd.forward(req, res);

        }
    catch(Exception e){
           greska="nije uspelo "+e;
           proslediGresku(greska,req,res);
           greska="";
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
