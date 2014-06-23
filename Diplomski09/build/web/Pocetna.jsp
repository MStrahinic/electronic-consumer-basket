<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Pocetna</title>
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
.style3 {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-weight: bold;
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
    <td>&nbsp;</td>
    <td><table width="950" border="0">
      <tr>
        <td colspan="5">&nbsp;</td>
      </tr>
      <tr>
        <td width="111" rowspan="2">&nbsp;</td>
        <td width="217"><a href="ListaProizvoda" ><img src="slike/irska.jpg" width="180" height="230" border="0" /></a></td>
        <td width="214"><a href="ListaProizvoda" ><img src="slike/engleska.jpg" width="180" height="230" border="0" /></a></td>
        <td width="196"><a href="ListaProizvoda" ><img src="slike/australija.jpg" width="180" height="230" border="0" /></a></td>
        <td width="190" rowspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center">
          <p class="style3">KOOGA</p>
          <p class="style3">-Dres Irske-</p>
        </div></td>
        <td width="214"><div align="center">
          <p class="style3">KOOGA </p>
          <p class="style3">-Dres Engleske-</p>
        </div></td>
        <td width="196"><div align="center">
          <p class="style3">KOOGA</p>
          <p class="style3">-Dres Australije-</p>
        </div></td>
      </tr>
      <tr>
        <td rowspan="2">&nbsp;</td>
        <td><a href="ListaProizvoda"><img src="slike/skotska.jpg" width="180" height="230" border="0" /></a></td>
        <td><a href="ListaProizvoda"><img src="slike/vels.jpg" width="180" height="230" border="0" /></a></td>
        <td><a href="ListaProizvoda"><img src="slike/novizeland.jpg" width="180" height="230" border="0" /></a></td>
        <td rowspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center">
          <p class="style3">KOOGA </p>
          <p class="style3">-Dres Škotske-</p>
        </div></td>
        <td><div align="center">
          <p class="style3">KOOGA </p>
          <p class="style3">-Dres Velsa-</p>
        </div></td>
        <td><div align="center">
          <p class="style3">KOOGA </p>
          <p class="style3">-Dres Novog Zelanda-</p>
        </div></td>
      </tr>
      <tr>
        <td colspan="5"><table width="950" border="0">
          <tr>
            <td width="290">&nbsp;</td>
            <td width="234"><a href="ListaProizvoda"><img src="slike/banner.gif" alt="" width="234" height="60" /></a></td>
            <td width="412">&nbsp;</td>
          </tr>
        </table></td>
        </tr>
    </table></td>
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
