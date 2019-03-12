<#include "layout/header.ftl">

<div class="card">
    <div class="card-body">
        <h5 class="card-title">${article.title}</h5>
        <p class="card-text">${article.text}</p>
    </div>
    <hr>
    <div class="card-body">
        <h5>Commentaires</h5>
        <#list comments?reverse as comment>
            <div class="d-flex justify-content-between"">
                <p>${comment.text}</p>
                <#if session??>
                    <a href="/article/${article.id}/comment/${comment.id}/delete" style="color: black; text-decoration-color: black;">X</a>
                </#if>
            </div>
            <hr>
        </#list>
        <div class="container">
            <form action="/article/${article.id}/comment/create" method="post">
                <div class="form-group row">
                    <input type="text" class="form-control col-md-10" name="comment" placeholder="Je commente" required>
                    <button type="submit" class="btn btn-success col-md-2">Commenter</button>
                </div>
            </form>
        </div>
    </div>
</div>

<#include "layout/footer.ftl">
