<%-- 
    Document   : PotrosackaKorpa
    Created on : Jul 28, 2009, 10:25:43 PM
    Author     : Administrator
--%>
<%@ page import="java.util.*" %>
<%@ page import="pl.paketBean.*" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Potrosacka korpa</title>
    <script src="SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
    <link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css" />
        
<style type="text/css">
	body
	{
		background: white url(slike/pozadina01.jpg) repeat-x;
		height: auto;
		line-height: normal;
		text-decoration: none;
		letter-spacing: normal;
		background-attachment: fixed
	}
</style>
</head>

<body>
    <%! String admin; %>
    <% admin = session.getAttribute("titula").toString(); %>

<table width="100%" border="0" align="center">
  <tr>
    <td width="16%"><a href="Pocetna.jsp"><img src="slike/HomeButton.png" alt="HOME" width="168" height="63" border="0" /></a></td>
    
    <td>      
      
      <div align="center">
            <ul id="MenuBar1" class="MenuBarHorizontal">
              <li><a class="MenuBarItemSubmenu" href="#">Profil</a>
                <ul>
                  <li><a href="LogOff">Log off</a></li>
                  <li><a href="IzmenaProfila">Izmena profila</a></li>
                </ul>
              </li>
              <li><a href="#" class="MenuBarItemSubmenu">Proizvodi</a>
                <ul>
                  <li><a href="ListaProizvoda">Lista proizvoda</a></li>
                  <% if(admin.equals("administratore")){ %>
                  <li><a href="ListaProizvodaZaAzuriranje">Ažuriranje</a></li>
                  <li><a href="DodavanjeProizvoda.jsp">Dodaj proizvod u listu</a></li>
                  <li><a href="ListaProizvodaZaBrisanje">Obriši proizvod iz liste</a></li>
                  <%}%>
                </ul>
              </li>
              <li><a href="#" class="MenuBarItemSubmenu">Potrošačka korpa</a>
                <ul>
                  <li><a href="PotrosackaKorpa">Prikaz korpe</a></li>
                  <li><a href="ListaProizvodaPKorpeZaBrisanje">Obriši proizvod iz korpe</a></li>
                </ul>
              </li>
              <li><a href="#" class="MenuBarItemSubmenu">Prodaja</a>
                <ul>
                  <li><a href="NaruceneKorpe">Moje naručene korpe</a></li>
                  <% if(admin.equals("administratore")){ %>
                  <li><a href="ListaKorpi">Lista prodaje</a></li>
                  <%}%>
                </ul>
              </li>
            </ul>
    </div></td>
  </tr>
  <tr>
    <%! KorpaBean korpa; %>

    <%
    korpa=(KorpaBean)session.getAttribute("korpa");
    %>
    <form name="PKBrisanje" method="post" action ="BrisanjeProizvodaIzKorpe" >

    <table width="500" border="0" align ="center" >
    <caption><h1>Sadržaj potrošačke korpe</h1></caption>
        <tr>
            <th><font size="5">Rb.</font></th>
            <th><font size="5">Proizvod</font></th>
            <th><font size="5">Količina</font></th>
            <th><font size="5">Cena</font></th>
            <th><font size="5">Brisanje</font></th>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
<% for (int i=0;i<korpa.getStavke().size();i++) {
if(korpa.getStavke().get(i)!=null){
    %>
            <tr>
                <td  align="center"><font size="4"><%=((StavkaPKBean)korpa.getStavke().get(i)).getRB() %></font></td>
                <td  align="center"><font size="4"><%=((StavkaPKBean)korpa.getStavke().get(i)).getProizvod().getNaziv() %></font></td>
                <td  align="center"><font size="4"><%=((StavkaPKBean)korpa.getStavke().get(i)).getKolicina() %></font></td>
                <td  align="center"><font size="4"><%=((StavkaPKBean)korpa.getStavke().get(i)).getKolicina() * ((StavkaPKBean)korpa.getStavke().get(i)).getProizvod().getCena()%></font></td>
                <td align="center"><input type="radio" name="rd" value="<%=i%>" <%if(i==0){%>checked="true"<%}%>></td>
            </tr>
<% }}%>

    </table>
    <br/>
     <center><input type="submit" name="brs" value="Brisanje"/></center>
</form>
    <br/>
  </tr>
  <tr>
    <td colspan="2">
    	<hr align="center" size="4" width="60%" noshade="noshade" color="#000000">
    	<center><b>Milan Strahinić :: B/RT-9/09 <a href="mailto:strahinicmilan@gmail.com">kontakt</a></b></center>    </td>
  </tr>
</table>
<script type="text/javascript">
<!--
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"SpryAssets/SpryMenuBarDownHover.gif", imgRight:"SpryAssets/SpryMenuBarRightHover.gif"});
//-->
</script>
</body>
</html>
