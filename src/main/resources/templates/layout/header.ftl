<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>CMS</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Liste des articles</a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item">
                <#if !session??>
                    <a class="nav-link" href="/login">Connexion</a>
                </#if>

                <#if session??>
                    <a class="nav-link" href="/logout">Déconnexion</a>
                </#if>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-5">