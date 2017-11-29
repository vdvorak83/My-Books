<#ftl encoding='UTF-8'>
<#import "layout/application.ftl" as layout>
<@layout.layout title="${model.user.username}'s Profile Page">
</@layout.layout>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <ul class="nav nav-tabs nav-justified">
                <li class="active"><a data-toggle="tab" href="#reading">Currently Reading</a></li>
                <li><a data-toggle="tab" href="#going-to">Going to Read</a></li>
                <li><a data-toggle="tab" href="#stopped">Stopped Reading</a></li>
                <li><a data-toggle="tab" href="#read">Read</a></li>
            </ul>

            <div class="tab-content">
                <div id="reading" class="tab-pane fade in active">
                    <h3>Currently Reading</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                </div>
                <div id="going-to" class="tab-pane fade">
                    <h3>Going to Read</h3>
                    <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                </div>
                <div id="stopped" class="tab-pane fade">
                    <h3>Stopped Reading</h3>
                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
                </div>
                <div id="read" class="tab-pane fade">
                    <h3>Read</h3>
                    <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <h1 class="text-center">${model.user.username}</h1>
            <img src="https://i.imgur.com/I87f7fI.png" alt="profile image" class="img-thumbnail center-block">
        </div>
    </div>
</div>


<#--<img src="/static/img/user_default_profile_img.png" alt="profile image"/>-->
</body>
