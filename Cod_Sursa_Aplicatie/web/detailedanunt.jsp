<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
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
    <link rel="stylesheet" href="detailedAnunt.css">
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
    <div class="boddyOfContent">
        <div class="titluLabel">
            <h3>${titlu}</h3>
        </div>

        <div class="pret">
            <h3>${pret} &#8364</h3>
        </div>

        <div class="dataPublicarii">
            <h3>${datapostare}</h3>
        </div>

        <div class="actiuneLabel">
            <p>${actiune}</p>
        </div>
        <div class="categorieLabel">
            <p>${categorie}</p>
        </div>

        <div class="imagine">
            <img src=${pozaapartament}>
        </div>

        <div class="userBox">
            <img src="htmlpictures/585e4bf3cb11b227491c339a.png">
            <p style="text-align: center">${nume} ${prenume}</p>
            <button name="telefonbtn">
                <img src="htmlpictures/telephone.png">
                <p style="text-align: center">${nrtelefon}</p>
            </button>
            <button name="emailbtn">
                <img src="htmlpictures/close-envelope.png">
                <p style="text-align: center">${email}</p>
            </button>
        </div>


        <button name="locatie">
                <img src="/htmlpictures/icons8-marker-100.png">
                <p>${locatie}</p>
        </button>


        <div class="numarCamere">
            <p>Numar camere ${numarcamere}</p>
        </div>

        <div class="suprafata">
            <p>Suprafata ${suprafata}</p>
        </div>

        <div class="dotari">
            <p1>Dotari</p1>
            <p>${dotari}</p>
        </div>

    </div>


    <div class="pointAnimation">
        <img src="htmlpictures/homeZZ.png">
    </div>
    <div class="placeImage">
        <img src="htmlpictures/grass.png">
    </div>
</body>
</html>
