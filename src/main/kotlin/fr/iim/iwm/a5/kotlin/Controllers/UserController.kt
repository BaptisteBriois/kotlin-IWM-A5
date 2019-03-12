package fr.iim.iwm.a5.kotlin.Controllers

import fr.iim.iwm.a5.kotlin.Models.Model
import fr.iim.iwm.a5.kotlin.Models.SessionProvider
import fr.iim.iwm.a5.kotlin.Models.UserSession
import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.sessions.clear
import io.ktor.sessions.sessions
import org.mindrot.jbcrypt.BCrypt

class UserController(private val model: Model) {
    fun startFM(sessionProvider: SessionProvider): Any {
        return FreeMarkerContent("login.ftl", mapOf("session" to sessionProvider.getSession()), "e")
    }

    fun login(username: String?, password: String?, context: ApplicationCall): String {
        val user = model.getUser(username)

        if (user != null) {

            if (BCrypt.checkpw(password, user.password)) {
                val userSession = UserSession(user.username, user.id)
                context.sessions.set("user", userSession)
                return "/"
            }

        }
        return "/login"
    }

    fun logout(context: ApplicationCall): String {
        context.sessions.clear<UserSession>()
        return "/"
    }
}