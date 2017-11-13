<%--
  Created by IntelliJ IDEA.
  User: Bulat
  Date: 10/16/2017
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>MyBooks</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900'>
    <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Montserrat:400,700'>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<div class="container">
    <div class="info">
        <h1>Welcome to MyBooks. Please, log in</h1>
    </div>

    <div class="thumbnail"><img src="img/login_cat.png" alt="Kitty" id="round"/></div>
</div>
<div class="form">
    <form class="register_form" id="js-register-form" method="post">
        <input type="text" name="name" placeholder="name"/>
        <input type="text" name="last_name" placeholder="last name"/>
        <input type="text" name="username" placeholder="username"/>
        <input type="text" name="email" placeholder="email address"/>
        <input type="password" name="password" id="password" placeholder="password"/>
        <input type="password" name="password_confirm" placeholder="repeat password"/>
        <button type="submit">register</button>
        <p class="message">Already registered? <a href="#">Sign In</a></p>
    </form>
    <form class="login-form" id="js-login-form">
        <input type="text" name="username" placeholder="username"/>
        <input type="password" name="password" placeholder="password"/>
        <button type="submit">username</button>
        <p class="message">Not registered? <a href="#">Create an account</a></p>
    </form>

    <script src="bower_components/jquery/dist/jquery.min.js"></script>
    <script src="bower_components/jquery-validation/dist/jquery.validate.min.js"></script>
    <script src="js/index.js"></script>
</div>
</body>
</html>
