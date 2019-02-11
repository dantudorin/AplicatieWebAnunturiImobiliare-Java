<%--
  Created by IntelliJ IDEA.
  User: tudorindan
  Date: 30.11.2018
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="styleRegisterForm.css">
</head>
<body>

    <div class = "message">
        <img src="/htmlpictures/arrow.png">
        <h3>Join Us</h3>
        <p>Please register to benefit of all our features</p>
        <p>Thank you for choosing us!</p>
    </div>

    <div class = "registerFoarm">
        <h1>Register Here</h1>
        <form action="/register" method="post">
            <p></p>
            <input type = "text" name = "firstname" placeholder="firstname" >
            <p></p>
            <input type = "text" name = "lastname" placeholder="lastname" >
            <p></p>
            <input type = "text" name = "username" placeholder="username" >
            <p></p>
            <input type = "password" name = "password" placeholder="password" >
            <p></p>
            <input type = "text" name = "email" placeholder="email" >
            <p></p>
            <input type="submit" name="action" value="Register">
            <p style="color: #fb0b00;text-align:center">${errormessage}</p>
        </form>
    </div>

</body>
</html>
