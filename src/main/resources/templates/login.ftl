<#ftl encoding='UTF-8'>
<head>
    <title>Login</title>

    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="container">
    <div class="info">
        <h1>Welcome to MyBooks. Please, sign in</h1>
    </div>

    <div class="thumbnail"><img src="https://image.ibb.co/ncfJCm/login_cat.png" alt="kitty" id="round"/></div>
</div>

<#if error??>
<div role="alert">${error}</div>
</#if>

<div class="form">
    <form class="login-form" method="post" action="/login">
        <input type="text" name="login" placeholder="username"/>
        <input type="password" name="password" placeholder="password"/>
        <button type="submit">log in</button>
        <p class="message">Not registered? <a href="/signup">Create an account</a></p>
    </form>
</div>
</body>