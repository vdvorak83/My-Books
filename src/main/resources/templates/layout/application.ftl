<#macro layout title>
    <!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>${title}</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/user/profile">Profile</a> </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Browse <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/books/classics">Classics</a></li>
                        <li><a href="/books/historical-fiction">Historical Fiction</a></li>
                        <li><a href="/books/mystery">Mystery</a></li>
                        <li><a href="/books/philosophy">Philosophy</a></li>
                        <li><a href="/books/science-fiction">Science Fiction</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/books/genres">All Genres</a></li>
                    </ul>
                </li>
                <li><a href="/authors">Authors</a> </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<#nested >
</body>
</html>
</#macro>