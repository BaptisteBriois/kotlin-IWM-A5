<#include "layout/header.ftl">

<h1>Liste des articles</h1>
<#list articles?reverse as article>
    <div class="col-sm-6">
        <a href="/article/${article.id}" style="color: black; text-decoration-color: black;">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${article.title}</h5>
                </div>
            </div>
        </a>
    </div>
</#list>

<#include "layout/footer.ftl">
