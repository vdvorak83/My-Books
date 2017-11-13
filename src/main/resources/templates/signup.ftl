<#ftl encoding='UTF-8'>
<head>
    <meta charset="UTF-8">
    <title>MyBooks</title>

    <#--<#include "../static/css/style.css">-->
    <script type="text/javascript" src="<@s.url includeParams='none' value='/bower_components/jquery/dist/jquery.min.js' />"></script>
    <script type="text/javascript" src="<@s.url includeParams='none' value='/bower_components/jquery-validation/dist/jquery.validate.min.js'/>"></script>
    <script type="text/javascript" src="<@s.url includeParams='none' value='/js/index.js'/>"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900'>
    <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Montserrat:400,700'>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="container">
    <div class="info">
        <h1>Welcome to MyBooks. Please, log in</h1>
    </div>

    <div class="thumbnail"><img src="/img/login_cat.png" alt="kitty" id="round"/></div>
</div>

<#if error??>
<div role="alert">${error}</div>
</#if>

<div class="form">
    <form class="register_form" id="js-register-form" method="post" action="/signup">
        <input type="text" name="name" placeholder="name"/>
        <input type="text" name="username" placeholder="username"/>
        <input type="password" name="password" id="password" placeholder="password"/>
        <input type="password" name="password_confirm" placeholder="repeat password"/>
        <button type="submit">register</button>
        <p class="message">Already registered? <a href="#">Sign In</a></p>
    </form>
    <form class="login-form" id="js-login-form" method="post" action="/signup">
        <input type="text" name="username" placeholder="username"/>
        <input type="password" name="password" placeholder="password"/>
        <button type="submit">username</button>
        <p class="message">Not registered? <a href="#">Create an account</a></p>
    </form>

    <script src="/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="/bower_components/jquery-validation/dist/jquery.validate.min.js"></script>
    <script src="/js/index.js"></script>
</div>
</body>