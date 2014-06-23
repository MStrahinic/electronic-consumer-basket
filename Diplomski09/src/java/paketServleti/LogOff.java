package paketServleti;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class LogOff extends HttpServlet{

    Connection con = null;
    Statement stmt = null;
    ResultSet RS = null;

    String greska = "";

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


        try{
            session.invalidate();
            pozoviJSP(req, res);
        }
        catch (Exception e){
            greska="Doslo je do greske! Pokusajte ponovo!";
            proslediGresku(greska, req, res);
            greska="";
        }
    } //doPost


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
        rd = sc.getRequestDispatcher("/Logovanje.html");
        rd.forward(req, res);
    }
    catch (Exception e){
        greska="nije uspelo "+e;
        greska="";
    }
}

}
