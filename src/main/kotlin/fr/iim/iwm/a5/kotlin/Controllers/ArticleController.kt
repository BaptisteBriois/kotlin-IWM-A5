package fr.iim.iwm.a5.kotlin.Controllers

import fr.iim.iwm.a5.kotlin.Models.Model
import fr.iim.iwm.a5.kotlin.Models.SessionProvider
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode

class ArticleController(private val model: Model) {
    fun startFM(id: Int, sessionProvider: SessionProvider): Any {
        val article = model.getArticle(id)
        val comments = model.getArticleComments(id)

        // Response FMC
        if (article != null)
            return FreeMarkerContent("article.ftl", mapOf("article" to article, "comments" to comments, "session" to sessionProvider.getSession()))
        return HttpStatusCode.NotFound
    }

    fun createArticle(title: String, text: String) {
        model.createArticle(title, text)
    }

    fun deleteArticle(id: Int) {
        model.deleteArticle(id)
    }

    fun createComment(articleId: Int, comment: String) {
        model.createComment(articleId, comment)
    }

    fun deleteComment(id: Int) {
        model.deleteComment(id)
    }
}