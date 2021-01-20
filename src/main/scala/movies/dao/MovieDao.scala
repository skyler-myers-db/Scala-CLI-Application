package movies.dao

import movies.model.Movie
import movies.utils.ConnectionUtil
import scala.util.Using
import scala.collection.mutable.ArrayBuffer
import scala.util.{Try, Using}
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Connection

object MovieDao {

    def getAll: Seq[Movie] = {

        val conn = ConnectionUtil.getConnection

        Using.Manager { use =>
            val stmt = use(conn.prepareStatement("SELECT * FROM project0.movies;"))
            stmt.execute

            val rs = use(stmt.getResultSet)
            val allMovies: ArrayBuffer[Movie] = ArrayBuffer()

            while (rs.next) {
                allMovies.addOne(Movie.produceResult(rs))
            }
            allMovies.toList
            }.get
    }

    def byGenre(genre: String): Seq[Movie] = {

        val conn = ConnectionUtil.getConnection

        Using.Manager { use =>
            val stmt = use(conn.prepareStatement("SELECT * FROM project0.movies WHERE project0.movies.genre = ?"))
            stmt.setString(1, genre)
            stmt.execute

            val rs = use(stmt.getResultSet)
            val moviesWithGenre: ArrayBuffer[Movie] = ArrayBuffer()

            while (rs.next) {
                moviesWithGenre.addOne(Movie.produceResult(rs))
            }
            moviesWithGenre.toList
            }.get
    }

    def alphSort: Seq[Movie] = {

        val conn = ConnectionUtil.getConnection

        Using.Manager { use =>
            val stmt = use(conn.prepareStatement("SELECT * FROM project0.movies ORDER BY title ASC;"))
            stmt.execute

            val rs = use(stmt.getResultSet)
            val movie: ArrayBuffer[Movie] = ArrayBuffer()

            while (rs.next) {
                movie.addOne(Movie.produceResult(rs))
            }
            movie.toList
            }.get
    }
    
    def movieGenerator: Seq[Movie] = {

        val conn = ConnectionUtil.getConnection

        Using.Manager { use =>
            val stmt = use(conn.prepareStatement("SELECT title FROM project0.movies ORDER BY RANDOM() LIMIT 1;"))
            stmt.execute

            val rs = use(stmt.getResultSet)
            val movie: ArrayBuffer[Movie] = ArrayBuffer()

            while (rs.next) {
                movie.addOne(Movie.produceResult(rs))
            }
            movie.toList
            }.get
    }

    def saveNew(movie: Movie): Boolean = {

        val conn = ConnectionUtil.getConnection

        Using.Manager { use =>
            val stmt = use(conn.prepareStatement("INSERT INTO project0.movies VALUES (?);"))
            stmt.setString(1, movie.title)
            stmt.execute
            stmt.getUpdateCount > 0
            }.getOrElse(false)
    }

    def deleteMovie(movie: Movie): Boolean = {

        val conn = ConnectionUtil.getConnection

        Using.Manager { use =>
            val stmt = use(conn.prepareStatement("DELETE FROM project0.movies WHERE title = ?;"))
            stmt.setString(1, movie.title)
            stmt.execute
            stmt.getUpdateCount > 0
            }.getOrElse(false)
    }
}