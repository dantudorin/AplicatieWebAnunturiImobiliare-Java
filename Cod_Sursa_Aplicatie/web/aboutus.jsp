<%--
  Created by IntelliJ IDEA.
  User: tudorindan
  Date: 11.12.2018
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MainAppPage</title>
    <link rel="stylesheet" href="aboutUs.css">
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

<div class="mesaj">
    <h3>Scurt istoric:</h3>

    <p>Îți mai amintești perioada în care trebuia să cumperi un ziar cu anunțuri imobiliare atunci când îți căutai un apartament de închiriat sau o casă de vânzare? Limita de cuvinte impusă de fiecare publicație în parte și lipsa fotografiilor atașate anunțurilor făceau ca proprietarii de locuințe sau cei care se aflau în căutarea unui loc pe care să îl numească „acasă” să aibă mult de pierdut, mai cu seamă timpul pe care îl investeau în vizionarea caselor care li se păreau a fi atractive dintr-o simplă descriere și care, de fapt, nu erau pe placul lor. Astăzi, Internetul a eliminat această problemă, astfel încât acum ai la dispoziție site-uri de specialitate care îți prezintă ofertele de pe piața imobiliară pe larg. Mai mult decât atât, fiecare anunț este însoțit de fotografii, unele chiar la 360 de grade, astfel încât poți avea senzația că deja vizitezi un apartament pe care ai pus ochii. Cum trebuie să filtrezi anunțurile astfel încât rezultatele afișate să fie cât mai aproape de locuința ta ideală?
    </p>

    <h3>Stabilește o marjă de preț</h3>

    <p>Piața imobiliară, mai ales cea din marile orașe ale țării, este una dezvoltată, așa încât există variante pentru fiecare categorie de cumpărători sau chiriași, în funcție de bugetul acestora. E foarte important să începi căutările cu stabilirea unui prag pe care ți-l poți permite. Astfel, eviți posibilele situații în care te-ai putea îndrăgosti de un apartament sau un cartier din orașul tău, ca mai apoi să îți dai seama că nu îți poți permite să locuiești acolo. Ca să îți faci o idee despre care ar putea fi prețul maxim potrivit pentru tine, ideal ar fi ca rata sau chiria să nu depășească valoarea de 30% din veniturile tale sau ale familiei tale.</p>

    <h3>Alege cartierul</h3>

    <p>După ce ai aflat care este suma maximă pe care ți-ai putea-o permite lunar, e momentul să îți alegi un cartier. Cu siguranță vei avea mai mult de două opțiuni, așa că trebuie să stabilești care dintre acestea te avantajează mai mult, în funcție de activitățile tale și de necesități. De exemplu, dacă ești o persoană activă, care are un job ce presupune întâlniri dese, sau pur și simplu îți place să îți petreci serile alături de prieteni în oraș, ar fi mai indicat să eviți locuințele de la periferia orașului din simplul motiv că ai cheltui foarte mulți bani și timp pentru deplasările pe care trebuie să le faci.
        E foarte important ca zona în care urmează să te muți să aibă acces la facilitățile de care ai nevoie, cum ar fi hypermarketuri, magazine, școli sau parcuri. O greutate mare în alegerea cartierului potrivit este și în funcție de stilul de viață pe care îl ai. Dacă îți place agitația, centrul orașului este ideal pentru tine. Dacă, însă, îți place mai mult liniștea, fugi cât poți de bulevarde, zonele cu parcuri (care în weekend se aglomerează până la refuz), patinoare sau multe magazine și restaurante, care la finalul săptămânii se blochează cu șoferi nervoși, care uită mâinile pe claxoane.</p>

    <h3>Gândește-te la spațiu</h3>

    <p>Nu în ultimul rând, e necesar să stabilești care este suprafața ideală pentru tine, în funcție de numărul de persoane cu care urmează să locuiești și de activitățile pe care le faci. De exemplu, dacă ai o familie cu copii, e clar că o garsonieră nu va cuprinde spațiul necesar pentru voi, ci mai degrabă v-ar folosi un apartament cu trei sau patru camere. Dacă membrii familiei numără doar două persoane, adică tu și jumătatea ta, atunci vă puteți arunca atenția și către locuințe mai mici, cu o suprafață de până la 60 de metri pătrați.
        Ai grijă ca apartamentului pe care urmează să îl cumperi sau să îl închiriezi să nu îi lipsească spațiile de depozitare, atât de importante atunci când lipsesc! Un balcon unde să poți usca natural hainele proaspăt spălate sau să-ți depozitezi bicicleta este necesar într-o locuință, la fel cum și un dressing sau un loc de parcare asigurat fac diferența.
        Acum că ai setat filtrele, poți găsi locuința ideală pentru tine! Spor la cumpărături!</p>

    <p>„Vechiul trend de afişare prin bannere stradale sau în ziare tinde să se piardă în noul mediu digital“, spune Flavius Pop, business development manager la storia.ro şi olx.ro.
        Cele mai cunoscute site-uri de anunţuri imobiliare - storia.ro, imobiliare.ro şi anuntul.ro - au avut anul trecut afaceri cumulate de peste 100 de milioane de lei, potrivit calculelor ZF făcute pe baza datelor publicate pe site-ul Ministerului de Finanţe.</p>

</div>

</body>
</html>
