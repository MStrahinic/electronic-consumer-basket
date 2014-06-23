<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Prodaja</title>
<script src="SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">
    function validacijaUnosa(){
        var t=true;
        var kolicina=frm.kolicina.value;

        if (kolicina.toString()==""){alert("Niste uneli kolicinu!"); t=false;}
        else if (!DaLiJeBrojKolicina()) { alert("Dodaje se samo broj"); t=false; }
        if (t) document.frm.submit();
    }
    function DaLiJeBrojKolicina(){
        var validniZnakovi="0123456789";
        var DaLiJeBr=true;
        var Znak;
        var kolicina=frm.kolicina.value;

        for (i=0; i<kolicina.length && DaLiJeBr==true; i++){
            Znak=kolicina.charAt(i);
            if (validniZnakovi.indexOf(Znak) == -1)
            {
            DaLiJeBr=false;
            }
            }
            return DaLiJeBr;
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
                </ul>
              </li>
              <li><a href="#" class="MenuBarItemSubmenu">Prodaja</a>
                <ul>
                  <% if(admin.equals("administratore")){ %>
                  <li><a href="ListaProdaje.jsp">Lista prodaje</a></li>
                  <li><a href="NaruceneKorpe">Moje naručene korpe</a></li>
                  <%}%>
                </ul>
              </li>
            </ul>
    </td>
  </tr>
  <tr>
    <td colspan="2">
    <%! int broj_proizvoda; %>
    <%! Vector proizvodi; %>


    <% broj_proizvoda=(new Integer(session.getAttribute("broj_proizvoda").toString())).intValue();
    proizvodi=(Vector)session.getAttribute("proizvodi");
    %>

<center><h1>Prodaja</h1></center>
<form name="frm" method="post" action="PotvrdiProdaju">
<table border="0" align="center">
    <tr>
    <td><h3>Datum prodaje:</h3></td>
    <td><select name="datumOdDan" >
      <option value="01" selected="selected">01</option>
      <option value="02">02</option>
      <option value="03">03</option>
      <option value="04">04</option>
      <option value="05">05</option>
      <option value="06">06</option>
      <option value="07">07</option>
      <option value="09">09</option>
      <option value="10">10</option>
      <option value="11">11</option>
      <option value="12">12</option>
      <option value="13">13</option>
      <option value="14">14</option>
      <option value="15">15</option>
      <option value="16">16</option>
      <option value="17">17</option>
      <option value="18">18</option>
      <option value="19">19</option>
      <option value="20">20</option>
      <option value="21">21</option>
      <option value="22">22</option>
      <option value="23">23</option>
      <option value="24">24</option>
      <option value="25">25</option>
      <option value="26">26</option>
      <option value="27">27</option>
      <option value="28">28</option>
      <option value="29">29</option>
      <option value="30">30</option>
      <option value="31">31</option>
    </select>
      <select name="datumOdMes">
        <option value="01" selected="selected">01</option>
        <option value="02">02</option>
        <option value="03">03</option>
        <option value="04">04</option>
        <option value="05">05</option>
        <option value="06">06</option>
        <option value="07">07</option>
        <option value="09">09</option>
        <option value="10">10</option>
        <option value="11">11</option>
        <option value="12">12</option>
      </select>
      <select name="datumOdGod">
        <option value="2007" selected="selected">2007</option>
        <option value="2008">2008</option>
        <option value="2009">2009</option>
        <option value="2010">2010</option>
      </select></td>
    </tr>
  <tr>
    <td><h3>Ime proizvoda:</h3></td>
    <td>
        <select name="proizvod">
            <% for (int i = 0; i < proizvodi.size(); i++) { %>
            <option > <%=proizvodi.get(i)%> </option>
            <% } %>
        </select>
    </td>
  </tr>
  <tr>
    <td><h3>Kolicina:</h3></td>
    <td><input name="kolicina" type="text" size="5"></td>
  </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td><input  type="reset" name="Reset" value="Obrisi" ></td>
        <td><input  name="potvrda" value="Potvrdi" onClick="validacijaUnosa()" type="submit"></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
</table>
</form>
    </td>
  </tr>
  <tr>
    <td colspan="2">
    	<hr align="center" size="4" width="50%" noshade="noshade" color="#000000">
    	<center><b>Milan Strahinić :: rt-102/06 <a href="mailto:strahinicmilan@gmail.com">kontakt</a></b></center>    </td>
  </tr>
</table>
<script type="text/javascript">
<!--
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"SpryAssets/SpryMenuBarDownHover.gif", imgRight:"SpryAssets/SpryMenuBarRightHover.gif"});
//-->
</script>
</body>
</html>
