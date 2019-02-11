<%--
  Created by IntelliJ IDEA.
  User: tudorindan
  Date: 29.11.2018
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

  <head>

    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="style.css">

  </head>

  <body>

      <div class = "loginBox">
          <img src="htmlpictures/Goodfeelingalt_avatar.png" class="avatar">
          <h1>Login Here</h1>
            <form action="/login" method = "post">
                <p>Username: </p>
                <input type="text" name = "username" placeholder="EnterUsername">
                <p>Password: </p>
                <input type="password" name = "password" placeholder="EnterPassword">
                <input type="submit" name="action" value="Login">
                <input type="submit" name="action" value="Register">
                <p1>${errorMessage}</p1>
            </form>
      </div>

  </body>


</html>
