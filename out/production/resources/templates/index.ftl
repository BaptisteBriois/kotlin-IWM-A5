<#include "layout/header.ftl">

<h1>Liste des articles</h1>
<div class="mb-5">
    <#list articles?reverse as article>
        <div class="mb-3">
            <div class="card">
                <div class="card-body d-flex justify-content-between">
                    <a href="/article/${article.id}" style="color: black; text-decoration-color: black;">
                        <h5 class="card-title">${article.title}</h5>
                    </a>
                    <#if session??>
                        <a href="/article/${article.id}/delete" style="color: black; text-decoration-color: black;">X</a>
                    </#if>
                </div>
            </div>
        </div>
    </#list>
</div>

<#if session??>
    <h2>Nouvel article</h2>
    <form action="/article/create" method="post">
        <div class="form-group">
            <input type="text" class="form-control mb-2" name="title" placeholder="Titre de l'article" required>
            <textarea class="form-control mb-2" name="text" placeholder="Article" required></textarea>
            <button type="submit" class="btn btn-success col-md-3">Poster</button>
        </div>
    </form>
</#if>

<#include "layout/footer.ftl">
