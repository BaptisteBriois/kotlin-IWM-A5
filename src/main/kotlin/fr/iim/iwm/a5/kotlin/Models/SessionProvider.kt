package fr.iim.iwm.a5.kotlin.Models

interface SessionProvider {
    fun getSession(): UserSession?
}