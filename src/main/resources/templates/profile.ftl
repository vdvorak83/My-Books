<#ftl encoding='UTF-8'>
<@layout title="${model.user.username} Profile Page">
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <ul class="nav nav-tabs nav-justified">
                <li role="presentation">Currently Reading</li>
                <li role="presentation"><a href="#">Going to Read</a> </li>
                <li role="presentation"><a href="#">Stopped Reading</a> </li>
                <li role="presentation"><a href="#">Read</a> </li>
            </ul>
        </div>
        <div class="col-md-4">
            <h1 class="text-center">${model.user.username}</h1>
            <img src="https://i.imgur.com/I87f7fI.png" alt="profile image" class="img-thumbnail center-block">
        </div>
    </div>
</div>


<#--<img src="/static/img/user_default_profile_img.png" alt="profile image"/>-->
</body>
</@layout>