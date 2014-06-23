
package paketServleti;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DobrodosliServlet extends HttpServlet {

    String greska = "";
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        session = req.getSession(true);
        sc = getServletContext();
            pozoviJSP(req,res);
    }
     public void proslediGresku(String greska, HttpServletRequest req, HttpServletResponse res){
        session=req.getSession(true);
        session.setAttribute("greska", greska);
        try {
            rd = sc.getRequestDispatcher("/Greska.jsp");
            rd.forward(req, res);
        }
        catch (Exception e){
        }
    }
    public void pozoviJSP(HttpServletRequest req, HttpServletResponse res){
		try {
            rd = sc.getRequestDispatcher("/Pocetna.jsp");
            rd.forward(req, res);
        }
        catch (Exception e){
                greska="Nije uspelo! " + e;
                proslediGresku(greska, req, res);
                greska="";
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
