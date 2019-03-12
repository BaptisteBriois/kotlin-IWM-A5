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
            <p>${comment.text}</p>
            <hr>
        </#list>
        <div class="container">
            <form action="/article/${article.id}/createComment" method="post">
                <div class="form-group row">
                    <input type="text" class="form-control col-md-10" name="comment" placeholder="Je commente" required>
                    <button type="submit" class="btn btn-success col-md-2">Commenter</button>
                </div>
            </form>
        </div>
    </div>
</div>

<#include "layout/footer.ftl">
