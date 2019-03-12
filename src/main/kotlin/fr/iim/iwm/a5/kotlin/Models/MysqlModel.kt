package fr.iim.iwm.a5.kotlin.Models

class MysqlModel(url: String, user: String?, password: String?) : Model {
    val connectionPool = ConnectionPool(url, user, password)

    // Get all articles
    override fun getArticlesList(): List<Article> {
        val articles = ArrayList<Article>()

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM articles").use { stmt ->
                val results = stmt.executeQuery()

                while(results.next()){
                    articles.add(Article(results.getInt("id"), results.getString("title"), results.getString("text")))
                }

                /* val str = buildString {
                    while (results.next()) {
                        val id = results.getInt("id")
                        val title = results.getString("title")
                        append("<p><a href='/article/$id'>$title</a></p>")
                    }
                }*/
            }
        }

        return articles
    }

    // Get selected article
    override fun getArticle(id: Int): Article? {
        connectionPool.use { connection ->

            connection.prepareStatement("SELECT * FROM articles WHERE id = ?").use { stmt ->
                stmt.setInt(1, id)
                val result = stmt.executeQuery()
                val found = result.next()

                if (found) {
                    return Article(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("text")
                    )
                }
            }
        }

        return null
    }

    override fun createArticle(title: String, text: String) {
        connectionPool.use { connection ->

            connection.prepareStatement("INSERT INTO articles VALUES (NULL, ?, ?)").use { stmt ->
                stmt.setString(1, title)
                stmt.setString(2, text)
                stmt.executeUpdate()
            }
        }
    }

    // Get selected article comments
    override fun getArticleComments(id: Int): List<Comment> {
        val comments = ArrayList<Comment>()
        connectionPool.use { connection ->

            connection.prepareStatement("SELECT * FROM comments WHERE article_id = ?").use { stmt ->
                stmt.setInt(1, id)
                val results = stmt.executeQuery()

                while(results.next()){
                    comments.add(
                        Comment(
                            results.getInt("id"),
                            results.getInt("article_id"),
                            results.getString("text")
                        )
                    )
                }
            }
        }

        return comments
    }

    // Post comment
    override fun createComment(articleId: Int, comment: String) {
        connectionPool.use { connection ->

            connection.prepareStatement("INSERT INTO comments VALUES (NULL, ?, ?)").use { stmt ->
                stmt.setInt(1, articleId)
                stmt.setString(2, comment)
                stmt.executeUpdate()
            }
        }
    }

    // Get user by username
    override fun getUser(username: String?): User? {
        connectionPool.use { connection ->

            connection.prepareStatement("SELECT * FROM users WHERE username = ?").use { stmt ->
                stmt.setString(1, username)
                val result = stmt.executeQuery()
                val found = result.next()

                if (found) {
                    return User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password")
                    )
                }
            }
        }

        return null
    }
}