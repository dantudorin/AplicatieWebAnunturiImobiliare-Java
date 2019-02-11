<%--
  Created by IntelliJ IDEA.
  User: tudorindan
  Date: 13.12.2018
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ModificaAnunt</title>
    <link rel="stylesheet" href="modificaAnunt.css">
    <script src="https://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,400,300,600&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
</head>
<body>
    <form action="/modifica" method="post">
        <input style="border: none;outline: none;background: transparent;color: transparent" type="text" name="username" value=${username}>
        <input style="border: none;outline: none;background: transparent;color: transparent" type="text" name="userId" value=${userId}>
        <input style="border: none;outline: none;background: transparent;color: transparent" type="text" name="anuntId" value=${anuntId}>

        <div class = "fullbox">

            <div class="mpozaImobil">
                <img src=${pozaImobil}>
                <p>Poza Imobil</p>
                <input type="text" name="pozamodificat" value=${pozaImobil}>
            </div>

            <div class="mtitlu">
                <p>Titlu</p>
                <input type="text" name="titlumodificat" maxlength="100" value="${titlu}">
            </div>

            <div class="mtelefon">
                <img src="htmlpictures/telephone-handle-silhouette.png">
                <input type="text" maxlength="10" name="telefonmodificat" value=${telefon}>
            </div>

             <div class="selectCategorie">
                 <p>Categorie</p>
                <div class="center">
                    <select name="sources" id="sources" class="custom-select sources" placeholder ="choose">
                        ${categorieImobil}
                    </select>
                </div>
                 <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
                 <script src="selectBeautify.js"></script>
             </div>

            <div class="mnrcamere">
                <p>Numar camere</p>
                <input type="text" name="nrcameremodificat" maxlength="2" value=${nrCamere}>
            </div>

            <div class="mlocatie">
                <img src="htmlpictures/iconfinder_advantage_nearby_1034361.png">
                <input type="text" name="locatiemodificat" value="${locatie}">
            </div>

            <div class="mpret">
                <p>Pret in &#8364</p>
                <input type="text" name="pretmodificat" value=${pret}>
            </div>


            <div class = "msuprafata">
                <p>Suprafata in mp</p>
                <input type="text" name="suprafatamodificat" value=${suprafata}>
            </div>

            <div class="mdotari">
                <h3>Dotari:</h3>
              <div class="listadotari">
                ${dotari}
              </div>
            </div>

            <div class="mactiune">
                <h3>Actiune</h3>
                <div class="radioposition">
                    ${actiune}
                </div>
            </div>

            <div class="submitbutton">
                <input type="submit" name="submit" value="Modify">
            </div>

        </div>

    </form>
</body>
</html>
