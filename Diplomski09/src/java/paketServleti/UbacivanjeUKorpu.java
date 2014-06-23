package paketServleti;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import pl.Kontroler;
import pl.paketBean.KorisnikBean;
import pl.paketBean.ProizvodBean;

public class UbacivanjeUKorpu extends HttpServlet{
    
    String greska="";
    String naziv="";
    int kolicina=0;
    double cena=0;
    
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
        KorisnikBean kb=(KorisnikBean)session.getAttribute("korisnik");
        try{

            ArrayList<ProizvodBean> proizvodi=(ArrayList<ProizvodBean>)session.getAttribute("proizvodi");

            for(int i=0;i<proizvodi.size();i++){
                if(req.getParameter("cb"+proizvodi.get(i).getProizvodID())!=null){
                    ProizvodBean pb=proizvodi.get(i);
                    int kolicina=Integer.parseInt(""+req.getParameter(proizvodi.get(i).getNaziv()));

                    if(pb.getKolicina()-kolicina<0){
                         greska="Trazena kolicina za proizvod "+pb.getNaziv()+" je veca od zaliha! ";
                         proslediGresku(greska, req, res);
                    }
                }
            }
            for(int i=0;i<proizvodi.size();i++){
                if(req.getParameter("cb"+proizvodi.get(i).getProizvodID())!=null){
                    ProizvodBean pb=proizvodi.get(i);
                    int kolicina=Integer.parseInt(""+req.getParameter(proizvodi.get(i).getNaziv()));

                    if(!(pb.getKolicina()-kolicina<0)){
                        Kontroler.getInstance().punjenjeKorpe(pb, kb, kolicina);
                    }
                }
            }

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
            rd = sc.getRequestDispatcher("/PotrosackaKorpa");
            rd.forward(req, res);
        }
        catch (Exception e){
                greska="Nije uspelo! "+e;
                proslediGresku(greska, req, res);
                greska="";
        }
    }
            
            
            
        
        

}
