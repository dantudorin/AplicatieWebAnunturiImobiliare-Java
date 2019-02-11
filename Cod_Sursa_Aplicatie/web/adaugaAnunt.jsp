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
    <link rel="stylesheet" href="adaugaAnunt.css">
    <script src="https://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,400,300,600&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
</head>
<body>
    <form action="/adauga" method="post">
        <input style="border: none;outline: none;background: transparent;color: transparent" type="text" name="username" value=${username}>
        <input style="border: none;outline: none;background: transparent;color: transparent" type="text" name="userId" value=${userId}>
        <input style="border: none;outline: none;background: transparent;color: transparent" type="text" name="anuntId" value=${anuntId}>

        <p style="color: #fb0b00;font-size: 19px;text-align: center;z-index: 5;font-family: sans-serif">${errorLog}</p>
        <div class = "fullbox">

            <div class="mpozaImobil">
                <img src= "htmlpictures/image.png">
                <p>Poza Imobil</p>
                <input type="text" name="poza" value=${pozaImobil}>
            </div>

            <div class="mtitlu">
                <p>Titlu</p>
                <input type="text" name="titlu" maxlength="100" value="${titlu}">
            </div>

            <div class="mtelefon">
                <img src="htmlpictures/telephone-handle-silhouette.png">
                <input type="text" maxlength="10" name="telefon" value=${telefon}>
            </div>

            <div class="selectCategorie">
                <p>Categorie</p>
                <div class="center">
                    <select name="sources" id="sources" class="custom-select sources" placeholder ="choose">
                        <option value="Apartament">Apartament</option>
                        <option value="Garsoniera">Garsoniera</option>
                        <option value="Teren">Teren</option>
                        <option value="Casa">Casa</option>
                        <option value="Altceva">Altceva</option>
                    </select>
                </div>
                <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
                <script src="selectBeautify.js"></script>
            </div>

            <div class="mnrcamere">
                <p>Numar camere</p>
                <input type="text" name="nrcamere" maxlength="2" value=${nrCamere}>
            </div>

            <div class="mlocatie">
                <img src="htmlpictures/iconfinder_advantage_nearby_1034361.png">
                <input type="text" name="locatie" value="${locatie}">
            </div>

            <div class="mpret">
                <p>Pret in &#8364</p>
                <input type="text" name="pret" value=${pret}>
            </div>


            <div class = "msuprafata">
                <p>Suprafata in mp</p>
                <input type="text" name="suprafata" value=${suprafata}>
            </div>

            <div class="mdotari">
                <h3>Dotari:</h3>
                <div class="listadotari">
                    <p>
                    <input type="checkbox" id="test1" name="aer" value="Aer conditionat"/>
                    <label for="test1">Aer conditionat</label>
                    </p>


                    <p>
                    <input type="checkbox" id="test2" name="centrala" value="Centrala termica"/>
                    <label for="test2">Centrala termica</label>
                    </p>
                    <p>

                    <input type="checkbox" id="test3" name="izoe" value="Izolatie termica exterioara"/>
                    <label for="test3">Izolatie termica exterioara</label>
                    </p>

                    <p>
                    <input type="checkbox" id="test4" name="izoi" value="Izolatie termica interioara"/>
                    <label for="test4">Izolatie termica interioara</label>
                    </p>

                    <p>
                    <input type="checkbox" id="test5" name="loc" value="Loc de parcare"/>
                    <label for="test5">Loc de parcare</label>
                    </p>

                    <p>
                    <input type="checkbox" id="test6" name="mobi" value="Mobilat"/>
                    <label for="test6">Mobilat</label>
                    </p>

                    <p>
                    <input type="checkbox" id="test7" name="obi" value="Obiecte Sanitare"/>
                    <label for="test7">Obiecte Sanitare</label>
                    </p>

                    <p>
                    <input type="checkbox" id="test8" name="tamp" value="Tamplarie termopan"/>
                    <label for="test8">Tamplarie termopan</label>
                    </p>
                </div>
            </div>

            <div class="mactiune">
                <h3>Actiune</h3>
                <div class="radioposition">
                    <label class="container">Vanzare
                        <input type="radio" name="radio" value="Vanzare">
                        <span class="checkmark"></span>
                        </label>
                    <label class="container">Inchiriere
                    <input type="radio" name="radio" value="Inchiriere">
                    <span class="checkmark"></span>
                    </label>
                </div>
            </div>

            <div class="submitbutton">
                <input type="submit" name="submit" value="+ ADAUGA ANUNT">
            </div>

        </div>

    </form>


</body>
</html>