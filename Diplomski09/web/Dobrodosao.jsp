<%-- 
    Document   : dobrodosaoAdmine
    Created on : Apr 28, 2009, 8:49:19 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>

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

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Opsti podaci</title>
    </head>
    <body bgcolor ="#000000">
        <form name="opstiPodaci" method="post" action = "DobrodosliServlet">
           <center><h1>Dobrodošao ${titula}</h1>
               <hr align="center" size="4" width="50%" noshade>

               <h2>Vaše korisničko ime je:</h2>
               <h1><strong>${korisnik.korisnickoIme}</strong></h1>

               <h2>Pritisnite dugme da pređete na početnu stranicu.</h2>
               <input type="submit" name="pocetak" value="Prelazak na pocetnu stranicu"/>

            </center>
        </form>
        <br>
        <hr align="center" width="50%" size = "4" noshade>
          <center><b>Milan Strahinić :: B/RT-9/09 <a href="mailto:strahinicmilan@gmail.com">kontakt</a></b></center>
    </body>
</html>

