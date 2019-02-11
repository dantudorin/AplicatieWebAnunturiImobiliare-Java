<%@ page import="java.sql.SQLException" %>
<%@ page import="webapp.SQLConnection" %><%--
  Created by IntelliJ IDEA.
  User: tudorindan
  Date: 29.11.2018
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MainAppPage</title>
    <link rel="stylesheet" href="mainApp.css">
</head>
<body>
<div class = "header">

</div>

<div class="secondheader">
    <img src="htmlpictures/user.png">
    <p1>Username: ${username}</p1>
    <p2>UserId: ${userId}</p2>
    <div class="homeZZ">
        <h3>Houmzzz punct RO</h3>
        <img src="htmlpictures/home.png">
    </div>
</div>

<div class="logOut">
    <form action="index.jsp" method="post">
        <input type="submit" name="logout" value="Log out">
    </form>
</div>

<form action="/prelucrareproces" method="post">
    <input style="border: none;outline: none;background: transparent;color: transparent" type="text" name="username" value=${username}>
    <input style="border: none;outline: none;background: transparent;color: transparent" type="text" name="userId" value=${userId}>
    <input style="border: none;outline: none;background: transparent;color: transparent" type="text" name="picture" value = ${picture}>
    <div class="processBar">
    </div>

    <div class="adaugaAnunt">
        <input type="submit" name="action" value="+ ADAUGA ANUNT">
    </div>

    <div class="anunturileMele">
        <input type="submit" name="action" value="Anunturile Mele">
    </div>

    <div class="aboutUs">
        <input type="submit" name="action" value="Despre noi">
    </div>

    <div class="sorteazaCuvant">
        <input type="submit" name="action" value="Sorteaza">
    </div>

    <div class="search">
        <input type="text" name="searchString" placeholder="Cauta anunt...">
        <button name="action" value="Search">
            <img src="/htmlpictures/search.png">
        </button>
    </div>

    <div class="sortBar">
        <select name="sorteaza">
            <option value="toateanunturile" selected ="default">Toate anunturile</option>
            <option value="pretcrescator">Pret crescator</option>
            <option value="pretdescrescator">Pret descrescator</option>
            <option value="datapublicarii">Data publicarii</option>
        </select>
    </div>
</form>
<div class="listBorders"></div>
<nav>
    <form action="/displayanunt" method="post">
        <input style="border: none;outline: none;background: transparent;color: transparent" type="text" name="username" value=${username}>
        <input style="border: none;outline: none;background: transparent;color: transparent" type="text" name="userId" value=${userId}>
        <ul>
            ${lista}
            <p style="color: #fb0b00;font-size: 20px;position: absolute;left: 40%">${errorlog}</p>
        </ul>
    </form>
</nav>


<div class="pointAnimation">
    <img src="htmlpictures/homeZZ.png">
</div>
<div class="placeImage">
    <img src="htmlpictures/grass.png">
</div>
</body>
</html>
