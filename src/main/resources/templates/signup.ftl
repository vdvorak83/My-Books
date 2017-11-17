<#ftl encoding='UTF-8'>
<head>
    <meta charset="UTF-8">
    <title>MyBooks</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="container">
    <div class="info">
        <h1>Welcome to MyBooks. Please, sign up</h1>
    </div>

    <div class="thumbnail"><img src="https://image.ibb.co/ncfJCm/login_cat.png" alt="kitty" id="round"/></div>
</div>

<#if error??>
<div role="alert">${error}</div>
</#if>

<div class="form">
    <form method="post" action="/signup">
        <input type="text" name="name" placeholder="name"/>
        <input type="text" name="username" placeholder="username"/>
        <input type="password" name="password" id="password" placeholder="password"/>
        <input type="password" name="password_confirm" placeholder="repeat password"/>
        <button type="submit">sign up</button>
        <p class="message">Already registered? <a href="/login">Sign nn</a></p>
    </form>
</div>
</body>