<%@ page import="pl.paketBean.ProizvodBean" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Dodavanje proizvoda</title>
    <script src="SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
    <link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css" />

<script language="JavaScript">
function validacijaUnosa(){
var t=true;

var imeProizvoda=Registracija.naziv.value;
var opis=Registracija.opis.value;
var kolicina=Registracija.kolicina.value;
var cena=Registracija.cena.value;
var slika=Registracija.slika.value;

if (imeProizvoda.toString()=="") { alert("Niste uneli ime proizvoda!"); t=false; }
else if (opis.toString()=="") { alert("Niste uneli opis proizvoda!"); t=false; }
else if (kolicina.toString()=="") { alert("Niste uneli kolicinu proizvoda!"); t=false; }
else if (slika.toString()=="") { alert("Niste uneli sliku!"); t=false; }
else if (!DaLiJeBrojKolicina()) { alert("Kolicina mora da sadrzi samo brojeve!"); t=false; }
else if (cena.toString()=="") { alert("Niste uneli cenu!"); t=false; }
else if (!DaLiJeBrojCena()) { alert("Cena mora da sadri samo brojeve!"); t=false; }
if (t) document.Registracija.submit();
}

function DaLiJeBrojKolicina(){
var validniZnakovi="0123456789";
var DaLiJeBr=true;
var Znak;
var kolicina=Registracija.kolicina.value;

for (i=0; i<kolicina.length && DaLiJeBr==true; i++){
	Znak=kolicina.charAt(i);
	if (validniZnakovi.indexOf(Znak) == -1)
	{
	DaLiJeBr=false;
	}
    }
    return DaLiJeBr;
}

function DaLiJeBrojCena(){
var validniZnakovi="0123456789";
var DaLiJeBr=true;
var Znak;
var cena=Registracija.cena.value;

for (i=0; i<cena.length && DaLiJeBr==true; i++){
	Znak=cena.charAt(i);
	if (validniZnakovi.indexOf(Znak) == -1)
	{
	DaLiJeBr=false;
	}
    }
    return DaLiJeBr;
}
</script>

<style type="text/css">
body {
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
 <center><h1>Dodavanje proizvoda</h1></center>

<form name="Registracija" method="post" action="DodavanjeProizvoda" >
  <br>
    <table align="center"  cellspacing="3" cellpadding="5" width="320" border="0">

	<tr>
      <td><font size = "5">Ime proizvoda:</font></td>
	  <td><input type="text" name="naziv"></td>
	  </tr>

	<tr>
      <td><font size = "5">Opis:</font></td>
	  <td><input type="text" name="opis"></td>
	  </tr>

	<tr>
      <td><font size = "5">Kolicina:</font></td>
	  <td><input type="text" name="kolicina"></td>
	</tr>

    <tr>
        <td><font size = "5">Cena:</font></td>
        <td><input type="text" name="cena"></td>
    </tr>

    <tr>
        <td><font size = "5">Slika:</font></td>
        <td><input type="text" name="slika"></td>
    </tr>

    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    
    <tr>
        <td align="right"> <input type="button"  name="prosledi" value="Dodaj" onClick="validacijaUnosa() "></td>
        <td align="left"><input type="reset" value="Obrisi" /></td>
    </tr>
    </table>
    <br/>
    
    <center>
     <%
      HttpSession sesija=request.getSession();
      String naziv=(String)sesija.getAttribute("naziv");

      if(naziv!=null){
          %>
          <b><%= "Proizvod "+naziv+" je uspesno dodat u bazu!".toString() %></b>
          <%
          }
      sesija.setAttribute("naziv", null);
      %>
     </center>
</form>
     
     <br>
    </td>
  </tr>
  <tr>
    <td colspan="2">
    	<hr align="center" size="4" width="50%" noshade="noshade" color="#000000">
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
