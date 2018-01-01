<#--<#ftl encoding='UTF-8'>
<#import "layout/application.ftl" as layout>
<@layout.layout title="${model.user.username}'s Profile Page">
</@layout.layout>-->
<body>
<form action="/sendsms" method="post">
    <input name="phone" placeholder="phone"/>
    <button type="submit">Send me</button>
</form>
</body>