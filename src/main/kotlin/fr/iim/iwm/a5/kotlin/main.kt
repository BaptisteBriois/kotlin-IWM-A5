package fr.iim.iwm.a5.kotlin

import fr.iim.iwm.a5.kotlin.Controllers.ArticleController
import fr.iim.iwm.a5.kotlin.Controllers.ArticleListController
import fr.iim.iwm.a5.kotlin.Controllers.UserController
import fr.iim.iwm.a5.kotlin.Models.KtorSessionProvider
import fr.iim.iwm.a5.kotlin.Models.MysqlModel
import fr.iim.iwm.a5.kotlin.Models.UserSession
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.http.Parameters
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*
import org.mindrot.jbcrypt.BCrypt

class App

fun main() {
    val model = MysqlModel("jdbc:mysql://localhost:3306/cms", "root", "root")

    val articleListController = ArticleListController(model)
    val articleController = ArticleController(model)
    val userController = UserController(model)

    embeddedServer(Netty, 8080) {
        install(Sessions) {
            cookie<UserSession>("user") {
                cookie.path = "/"
            }
        }

        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
        }

        routing {
            get("/") {
                val content = articleListController.startFM(KtorSessionProvider(call))
                call.respond(content)
            }

            // ARTICLES
            get("/article/{id}") {
                val id = call.parameters["id"] !! . toInt()

                val content = articleController.startFM(id, KtorSessionProvider(call))
                call.respond(content)
            }

            post("article/create") {
                val params = call.receive<Parameters>()

                articleController.createArticle(params["title"] !!, params["text"] !!)

                call.respondRedirect("/")
            }

            post("/article/{id}/createComment") {
                val articleId = call.parameters["id"] !! . toInt()
                val comment = call.receive<Parameters>()["comment"] !!

                articleController.createComment(articleId, comment)

                call.respondRedirect("/article/$articleId")
            }

            // SESSION
            get("/login") {
                val content = userController.startFM(KtorSessionProvider(call))
                call.respond(content)
            }

            post("/login") {
                val params = call.receive<Parameters>()
                val username = params["username"]
                val password = params["password"]
                val content = userController.login(username, password, context)
                call.respondRedirect(content)
            }

            get("/logout") {
                val content = userController.logout(context)
                call.respondRedirect(content)
            }
        }
    }.start(true)
}
