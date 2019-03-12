package fr.iim.iwm.a5.kotlin.Models

interface Model {
    // ARTICLES
    fun getArticlesList(): List<Article>
    fun getArticle(id: Int): Article?
    fun createArticle(title: String, text: String)
    fun deleteArticle(id: Int)

    // COMMENTS
    fun getArticleComments(id: Int): List<Comment>
    fun createComment(articleId: Int, comment: String)
    fun deleteComment(id: Int)

    // SESSION
    fun getUser(username: String?): User?
}