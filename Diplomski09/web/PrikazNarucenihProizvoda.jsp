<%@ page import="java.util.*" %>
<%@ page import="pl.paketBean.*" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Lista proizvoda</title>
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

<table width="104%" border="0" align="center">
  <tr>
    <td width="16%"><a href="Pocetna.jsp"><img src="slike/HomeButton.png" alt="HOME" width="168" height="63" border="0" /></a></td>

    <td width="84%">
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
    </td>
  </tr>
  <tr>
    <td colspan="2">
        
    <%! ArrayList naruceniProizvodi; %>

    <% 
    naruceniProizvodi=(ArrayList)session.getAttribute("korpe");
    %>

<center><h1>Naručeni proizvodi</h1></center>
<br/>
<br/>
<form name="lista" method="post">

<% for(int m=0; m<naruceniProizvodi.size();m++) { %>
<table width="700" border="0" align="center">
    <caption><h1 align="center">Podaci o korpi</h1></caption>
  
  <tr>
    <th><font size="5">Redni broj korisnika</font></th>
    <th><font size="5">Redni broj korpe</font></th>
    <th><font size="5">Datum kreiranja</font></th>
    <th><font size="5">Adresa isporuke</font></th>
    <th><font size="5">Datum narucivanja</font></th>
  </tr>

    <tr><td align="center"><font face="sans-serif" size="3" ><b><%=((KorpaBean)naruceniProizvodi.get(m)).getKorisnikId() %></b></font></td>
        <td align="center"><font face="sans-serif" size="3" ><b><%=((KorpaBean)naruceniProizvodi.get(m)).getPKID() %></b></font></td>
        <td align="center"><font face="sans-serif" size="3" ><b><%=((KorpaBean)naruceniProizvodi.get(m)).getDatumKreiranja() %></b></font></td>
        <td align="center"><font face="sans-serif" size="3" ><b><%=((KorpaBean)naruceniProizvodi.get(m)).getAdresaIsporuke() %></b></font></td>
        <td align="center"><font face="sans-serif" size="3" ><b><%=((KorpaBean)naruceniProizvodi.get(m)).getDatumNarucivanja() %></b></font></td>
    </tr>
    </table>
<br/>

    <table width="500" border="0" align ="center" >
        <caption><h1 align="center">Sadržaj potrošačke korpe</h1></caption>
        <tr>
            <th><font size="5">Rb.</font></th>
            <th><font size="5">Proizvod</font></th>
            <th><font size="5">Količina</font></th>
            <th><font size="5">Cena</font></th>

        </tr>

        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
<% double ukupnaCena=0;
KorpaBean korpa=(KorpaBean)naruceniProizvodi.get(m);
for (int i=0;i<korpa.getStavke().size();i++) {
    ukupnaCena+=((StavkaPKBean)korpa.getStavke().get(i)).getKolicina() * ((StavkaPKBean)korpa.getStavke().get(i)).getProizvod().getCena();
    %>
            <tr>
                <td  align="center"><font size="4"><%=((StavkaPKBean)korpa.getStavke().get(i)).getRB() %></font></td>
                <td  align="center"><font size="4"><%=((StavkaPKBean)korpa.getStavke().get(i)).getProizvod().getNaziv() %></font></td>
                <td  align="center"><font size="4"><%=((StavkaPKBean)korpa.getStavke().get(i)).getKolicina() %></font></td>
                <td  align="center"><font size="4"><%=((StavkaPKBean)korpa.getStavke().get(i)).getKolicina() * ((StavkaPKBean)korpa.getStavke().get(i)).getProizvod().getCena()%></font></td>
            </tr>
<% }%>

    </table>
    <br/>
    <center><h1>Ukupna cena:<%=ukupnaCena%></h1></center>
    <br/>
    <hr align="center" size="4" width="50%" noshade="noshade" color="#000000">
    <% } %>

</form>
    </td>
  </tr>
  <tr>
    <td colspan="2"> 	
    	<center><b>Milan Strahinić :: B/RT-9/09 <a href="mailto:strahinicmilan@gmail.com">kontakt</a></b></center>
    </td>
  </tr>
</table>
<script type="text/javascript">
<!--
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"SpryAssets/SpryMenuBarDownHover.gif", imgRight:"SpryAssets/SpryMenuBarRightHover.gif"});
//-->
</script>
</body>
</html>