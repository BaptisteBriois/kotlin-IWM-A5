<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Kotlin</title>
</head>
<body>
<h1>${article.title}</h1>
<h1>${article.text}</h1>
<#list comments as comment>
    <p>${comment.text}</p>
</#list>
<form action="/articles/${article.id}/postComment" method="post">
    <input type="text" name="text">
    <input type="submit" value="Commenter">
</form>
</body>
</html>
