package paketServleti;

import java.sql.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import pl.Kontroler;
import pl.paketBean.ProizvodBean;

public class PretragaProizvoda extends HttpServlet{
    
    String greska="";
    String atributPretrage="";
    String kljucnaRec = "";

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

        if(!req.getParameter("kljucnaRec").equals("")){
            kljucnaRec=req.getParameter("kljucnaRec");
        }
        else{
            prazno = true;
            greska = "Ključna reč za pretragu nije uneta.<br>";
            proslediGresku(greska,req,res);
            greska="";
        }
        
       atributPretrage = (String)session.getAttribute("atributPretrage");
        
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
            int opcija=Integer.parseInt(req.getParameter("rd"));
        atributPretrage="";
        if(opcija==1){
            atributPretrage = "naziv";
        }
        if(opcija==2){
            atributPretrage = "opis";
        }
        if(opcija==3){
            atributPretrage = "kolicina";
        }
        if(opcija==4){
            atributPretrage = "cena";
        }
//req.getSession().setAttribute("atributPretrage", atributPretrage);
            ArrayList<ProizvodBean> proizvodi = Kontroler.getInstance().pretragaProizvoda(atributPretrage, kljucnaRec);
            session.setAttribute("proizvodi", proizvodi);
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
            rd = sc.getRequestDispatcher("/ListaProizvoda.jsp");
            rd.forward(req, res);
        }
        catch (Exception e){
            greska="Nije uspelo "+e;
            greska="";
        }
    }

} 


