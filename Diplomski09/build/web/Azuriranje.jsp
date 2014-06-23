<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="pl.paketBean.ProizvodBean" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Ažuriranje</title>
    <script src="SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
    <link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css" />

    <script language="JavaScript">
    function validacijaUnosa(){

    var naziv=frm.naziv.value;
    var opis=frm.opis.value;
    var kolicina=frm.kolicina.value;
    var cena=frm.cena.value;
    var slika=frm.slika.value;
    var adminPoslednjeIzmene=frm.adminPoslednjeIzmene.value;

        if(naziv.toString()=="" && opis.toString()=="" && kolicina.toString()=="" && cena.toString()=="" && adminPoslednjeIzmene.toString()=="" && slika.toString()==""){
                alert("Niste uneli nijedan podatak za azuriranje!!!");
                return false;
            }
        else if( adminPoslednjeIzmene.toString()=="" ){
                alert("Niste uneli podatak o administratoru koji je nacinio izmenu!!!")
                return false;
            }
        else if (!DaLiJeBrojKolicina()) {
            alert("Dodaje se samo broj za kolicinu!");
            return false;
        }
        else if(!DaLiJeBrojCena()){
            alert("Dodaje se samo broj za cenu!");
            return false;
        }
        else{
            return true;
        }
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
    function DaLiJeBrojCena(){
        var validniZnakovi="0123456789";
        var DaLiJeBr=true;
        var Znak;
        var cena=frm.cena.value;

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

<body bgcolor="white" text="black" link="blue" vlink="blue">
    <%! String admin; %>
    <%! ArrayList proizvodi; %>
    
    <% admin = session.getAttribute("titula").toString(); %>
    <% proizvodi=(ArrayList)session.getAttribute("proizvodi"); %>

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
    <center>
    <form name="frm" method="post" action="Azuriranje" onsubmit="validacijaUnosa();">
    
    <table width="800" border="0" align ="center">
    <caption><h1>Ažuriranje</h1></caption>

        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <th><font size="5">Ime proizvoda</font></th>
            <th><font size="5">Opis</font></th>
            <th><font size="5">Količina</font></th>
            <th><font size="5">Cena</font></th>
            <th><font size="5">Administrator izmene</font></th>
            <th><font size="5">Slika</font></th>
            <th><font size="5">Datum<br/>izmene</font></th>
            <th><font size="5">Ažuriranje</font></th>
      </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        
        <% for (int m = 0; m < proizvodi.size(); m++) { %>
            <tr>
                <td align="center"><font size="4"><%=((ProizvodBean)proizvodi.get(m)).getNaziv() %></font></td>
                <td align="center"><font size="4"><%=((ProizvodBean)proizvodi.get(m)).getOpis() %></font></td>
                <td align="center"><font size="4"><%=((ProizvodBean)proizvodi.get(m)).getKolicina() %></font></td>
                <td align="center"><font size="4"><%=((ProizvodBean)proizvodi.get(m)).getCena() %></font></td>
                <td align="center"><font size="4"><%=((ProizvodBean)proizvodi.get(m)).getAdminPoslednjeIzmene() %></font></td>
                <td align="center"><img src="<%=((ProizvodBean)proizvodi.get(m)).getSlika() %>" width="130" height="180"></td>
                <td align="center"><font size="4"><%=((ProizvodBean)proizvodi.get(m)).getDatumPoslednjeIzmene() %></font></td>
                <td align="center"><input type="radio" name="rd" value="<%=m%>" <% if(m==0){%>checked="true"<%}%>></td>
            </tr>
        <% } %>
     <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>

        </tr>
     <tr>
         <td align="center"><input type="text" name="naziv"/></td>
         <td align="center"><input type="text" name="opis"/></td>
         <td align="center"><input type="text" name="kolicina"/></td>
         <td align="center"><input type="text" name="cena"/></td>
         <td align="center"><input type="text" name="adminPoslednjeIzmene"/></td>
         <td align="center"><input type="text" name="slika"/></td>
         <td align="center"><%=DateFormat.getDateInstance(DateFormat.MEDIUM,new Locale("SR")).format(new Date())%></td>
         <td align="center"><input type="submit" name="azuriraj" value="Azuriraj"/></td>
     </tr>
    </table>
    <br/>
</form>
</center>
    </td>
  </tr>
  
  <tr>
    <td colspan="2">
    	<hr align="center" size="4" width="50%" noshade="noshade" color="#000000">
    	<center><b>Milan Strahinić :: B/RT-9/09 <a href="mailto:strahinicmilan@gmail.com">kontakt</a></b></center></td>
  </tr>
</table>
<script type="text/javascript">
<!--
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"SpryAssets/SpryMenuBarDownHover.gif", imgRight:"SpryAssets/SpryMenuBarRightHover.gif"});
//-->
</script>
</body>
</html>

