<#ftl encoding='UTF-8'>
<head>
    <title>Login</title>
</head>
<body>
<h1>Sign In. Want to <a href="templates/signup.ftl">sign up</a>?</h1>
<#if model.error.isPresent()>
<div class="alert alert-danger" role="alert">Логин или пароль введены неверно</div>
</#if>
<form action="/login" method="post">
    <input name="login" placeholder="username">
    <input name="password" placeholder="password">
    <input type="submit">
</form>
</body>