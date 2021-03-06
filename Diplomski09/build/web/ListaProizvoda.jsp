<%@ page import="java.util.*" %>
<%@ page import="pl.paketBean.ProizvodBean" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Lista proizvoda</title>
<script src="SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css" />

<script language="JavaScript">
    function validacijaUnosa(){
        var kljucnaRec = Pogodak.kljucnaRec.value;

    if (kljucnaRec.toString()=="") {
        alert("Niste uneli ključnu reč za pretragu!!!");
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
        
    <%! ArrayList proizvodi; %>
    

    <% 
    proizvodi=(ArrayList)session.getAttribute("proizvodi");
    %>

<center><h1>Lista proizvoda</h1></center>
<br/>
<form name="Pogodak" method="post" action="PretragaProizvoda" onSubmit="return validacijaUnosa();">

    <h2 align="center">Odaberite opciju pretrage</h2>
    <table border="0" align="center">
        <tr>
            <td><font size="4">Ime proizvoda</font></td>
            <th><input type="radio" name="rd" value="<%=1%>" checked="true"></th>
        </tr>
        <tr>
            <td><font size="4">Opis</font></td>
            <th><input type="radio" name="rd" value="<%=2%>"></th>
        </tr>
        <tr>
            <td><font size="4">Količina</font></td>
            <th><input type="radio" name="rd" value="<%=3%>"></th>
        </tr>
        <tr>
            <td><font size="4">Cena</font></td>
            <th><input type="radio" name="rd" value="<%=4%>"></th>
        </tr>
         <tr>
            <td><input type="text" name="kljucnaRec"></td>
            <th><input type="submit" name="pretraga" value="Pretraga"></th>
        </tr>
    </table>
</form>
    <br/>
    <br/>

<form name="lista" method="post" action ="UbacivanjeUKorpu">   
<table width="800" border="0"  align="center">
  
  <tr>
    <th><font size="5">Ime proizvoda</font></th>
    <th><font size="5">Opis</font></th>
    <th><font size="5">Količina</font></th>
    <th><font size="5">Cena</font></th>
    <th><font size="5">Slika</font></th>
    <th><font size="5">Komada</font></th>
    <th><font size="5">Odaberi</font></th>
  </tr>

    <% for(int m=0; m<proizvodi.size();m++) { %>
    <tr>
        <td width="200" align="center"><font face="sans-serif" size="3" ><b><%=((ProizvodBean)proizvodi.get(m)).getNaziv() %></b></font></td>
        <td width="200" align="center"><font face="sans-serif" size="3" ><b><%=((ProizvodBean)proizvodi.get(m)).getOpis() %></b></font></td>
        <td width="100" align="center"><font face="sans-serif" size="3" ><b><%=((ProizvodBean)proizvodi.get(m)).getKolicina() %></b></font></td>
        <td width="100" align="center"><font face="sans-serif" size="3" ><b><%=((ProizvodBean)proizvodi.get(m)).getCena() %></b></font></td>
        <td align="center"><img src="<%=((ProizvodBean)proizvodi.get(m)).getSlika() %>" width="130" height="180"></td>
        <td width="100" align="center"><input type="text" value="1" size="5" name="<%=((ProizvodBean)proizvodi.get(m)).getNaziv() %>"></td>
        <td align="center"><input type="checkbox" name="cb<%=((ProizvodBean)proizvodi.get(m)).getProizvodID() %>"</td>
   </tr>
    <% } %>

</table>

<br/>

<center><input type="submit" name="DodajUKorpu" value="Dodaj u korpu"></center>

</form>
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