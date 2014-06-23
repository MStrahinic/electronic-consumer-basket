<%@ page import="java.util.*" %>
<%@ page import="pl.paketBean.KorisnikBean" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Izmena profila</title>
    <script src="SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
    <link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript">
 function validacijaUnosa()
        {
        var lozinka=Registracija.lozinka.value;
        var potvrdaLozinke=Registracija.potvrdaLozinke.value;
        var email=Registracija.email.value;
        var tekst=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

        if(!email.toString()=="" && !tekst.test(email)){
            alert("Email nije uredu!");
            return false;
        }
        else if(lozinka!=potvrdaLozinke){
            alert("Lozinka i potvrda lozinke nisu isti!!!");
            return false;
        }
        else{
            return true;
        }
    }

    </script>
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
    <%! KorisnikBean korisnik; %>

    <%
    korisnik=(KorisnikBean)session.getAttribute("korisnik");
    %>
      	<form name="Registracija" method="post" action="IzvrsavanjeIzmeneProfila" onsubmit="return validacijaUnosa();">
        <table align="center" cellspacing="3" cellpadding="5" width="600" border="0">
        <center><h1>Izmena profila</h1></center>
        <br/>
        <tr>
             <td>&nbsp;</td>
             <td align="center"><font size="5"><b>Stari podaci</b></font></td>
             <td align="center"><font size="5"><b>Novi podaci</b></font></td>
        </tr>
        <tr>
            <td><font size = "5">Ime:</font></td>
            <th><font size="4"><%=korisnik.getIme() %></font></th>
            <th><input type = "text" name = "ime" ></th>

        </tr>
        <tr>
             <td><font size = "5">Prezime:</font></td>
             <th><font size="4"><%=korisnik.getPrezime() %></font></th>
             <th><input type="text" name="prezime" ></th>
        </tr>
        <tr>
             <td><font size = "5">Korisnicko ime:</font></td>
             <th><font size="4"><%=korisnik.getKorisnickoIme() %></font></th>
             <th><input type="text" name="korisnickoIme" value = "<%=korisnik.getKorisnickoIme() %>" disabled="true"></th>

        </tr>
        <tr>
             <td><font size = "5">Lozinka:</font></td>
             <th><font size="4"><%=korisnik.getLozinka() %></font></th>
             <th><input type="password" name="lozinka" ></th>
        </tr>
        <tr>
             <td><font size = "5">Potvrda lozinke:</font></td>
             <td>&nbsp;</td>
             <th><input type="password" name="potvrdaLozinke" ></th>
        </tr>
        <tr>
             <td><font size = "5">Adresa:</font></td>
             <th><font size="4"><%=korisnik.getAdresa() %></font></th>
             <th><input type="text" name="adresa" ></th>
        </tr>
        <tr>
             <td><font size = "5">Telefon:</font></td>
             <th><font size="4"><%=korisnik.getTelefon() %></font></th>
             <th><input type="text" name="telefon"></th>
        </tr>
        <tr>
             <td><font size = "5">E-mail:</font></td>
             <th><font size="4"><%=korisnik.getEmail() %></font></th>
             <th><input type="text" name="email" ></th>
        </tr>
	  </table>
      <br/>
      <center>
          <input type="submit" name="Submit" value="Prosledi"/>
          <input type="reset" name="odustani" value="Obrisi"/>
      </center>
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
