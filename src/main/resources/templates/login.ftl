<#include "layout/header.ftl">

<form action="/login" method="post">
    <div class="form-group row">
        <label for="username">Nom d'utilisateur : </label>
        <input type="text" class="form-control" name="username" required>
    </div>
    <div class="form-group row">
        <label for="password">Mot de passe : </label>
        <input type="password" class="form-control" name="password" required>
    </div>
    <button type="submit" class="btn btn-primary">Connexion</button>
</form>

<#include "layout/footer.ftl">
