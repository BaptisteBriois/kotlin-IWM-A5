package fr.iim.iwm.a5.kotlin

import fr.iim.iwm.a5.kotlin.Models.MysqlModel
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ModelTest {
    val model = MysqlModel("jdbc:h2:mem:cms;MODE=MYSQL", null, null)

    @Before
    fun initDB() {
        model.connectionPool.use { connection ->
            connection.prepareStatement("""
                DROP TABLE IF EXISTS `articles`;
                CREATE TABLE `articles` (
                  `id` int(11) NOT NULL AUTO_INCREMENT,
                  `title` varchar(255) NOT NULL,
                  `text` text NOT NULL,
                  PRIMARY KEY (`id`)
                );
                INSERT INTO `articles` VALUES
                  (1, 'Premier article', 'Lorem ipsum le premier article'),
                  (2, 'Deuxième article', 'Lorem ipsum le 2ème article')
            """).use { stmt ->
                stmt.execute()
            }
        }

        model.connectionPool.use { connection ->
            connection.prepareStatement("""
                DROP TABLE IF EXISTS `comments`;
                CREATE TABLE `comments` (
                  `id` int(11) NOT NULL AUTO_INCREMENT,
                  `article_id` int(11) NOT NULL,
                  `text` text NOT NULL,
                  PRIMARY KEY (`id`),
                  CONSTRAINT comments_article_id_foreign FOREIGN KEY (`article_id`) REFERENCES articles(id) ON DELETE CASCADE
                );
                INSERT INTO `comments` VALUES
                  (1, 1, 'Commentaire 1.1'),
                  (2, 1, 'Commentaire 1.2'),
                  (3, 2, 'Commentaire 2.1'),
                  (4, 2, 'Commentaire 2.2')
            """).use { stmt ->
                stmt.execute()
            }
        }
    }

    @Test
    fun testArticleInDB() {
        val article = model.getArticle(1)
        assertNotNull(article)
        assertEquals(1, article.id)
        assertEquals("Premier article", article.title)
        assertTrue(article.text!!.startsWith("Lorem ipsum"))
    }
}