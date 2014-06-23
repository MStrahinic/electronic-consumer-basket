<%-- 
    Document   : Greska
    Created on : Apr 28, 2009, 8:47:44 PM
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
        <title>Desila se greska</title>
    </head>
    <body bgcolor="#000000">
        <h1>OBAVESTENJE!</h1>
        <b>
            ${greska}
        </b><br><br>
        <input type = "button" onclick="history.back()" value ="Povratak na predhodnu stranu">
    </body>
</html>
