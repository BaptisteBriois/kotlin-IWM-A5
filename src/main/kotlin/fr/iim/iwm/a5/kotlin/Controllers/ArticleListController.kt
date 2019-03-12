package fr.iim.iwm.a5.kotlin.Controllers

import fr.iim.iwm.a5.kotlin.Models.IndexArticlesData
import fr.iim.iwm.a5.kotlin.Models.Model
import fr.iim.iwm.a5.kotlin.Models.SessionProvider
import io.ktor.freemarker.FreeMarkerContent

class ArticleListController(private val model: Model) {
    fun startFM(sessionProvider: SessionProvider): Any {
        val articles = model.getArticlesList()

        // Response FMC
        return FreeMarkerContent("index.ftl", mapOf("articles" to articles, "session" to sessionProvider.getSession()))
    }
}