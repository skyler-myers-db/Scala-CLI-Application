package movies.cli

import movies.dao.MovieDao
import movies.model.Movie
import movies.utils.FileUtil
import scala.io.StdIn
import scala.util.matching.Regex

class Cli {

    // Makes sure our user CLI inputs are valid
    val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

    // Introduction to and explanation of the application
    def intro = println(
        """
        |#############################################################################################################################################
        |
        |You are interacting with a list of highly rated yet lesser known films.
        |The purpose of this application is for you to discover quality movies that you otherwise may have never heard of.
        |If you want more information on the movies such as the director and review scores, there is a command for that, too.
        |You can choose to look at the entire list or to filter it by genre. You can even add your own movies that I may have missed!""".stripMargin
         )

    // Ways users can sort through movies
    def cliOptions = println(
        """
        |#############################################################################################################################################
        |
        |Retrieval options (Example - "genre thriller"):
        |------------------------------------------------
        |All movies          ***selects all movies***
        |Genre [genre]  ---  (action, drama, or thriller)
        |Random movie        ***picks random movie***
        |A order             ***alphabetical order***
        |Add movie           ***add your own movie***
        |Delete movie        ***delete seen movies***
        |More details        ***more movie details***
        |------------------------------------------------
        |Input "quit" or "exit" to leave the application.
        |------------------------------------------------""".stripMargin
        )
    
    def menu: Unit = {

    intro

    var runMenu = true

    while (runMenu) {

      cliOptions

      val input = StdIn.readLine()

      input match {
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("quit") || cmd.equalsIgnoreCase("exit") => runMenu = false
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("all") && arg.equalsIgnoreCase("movies") => allMovies
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("genre") => byGenre(arg)
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("random") && arg.equalsIgnoreCase("movie") => randomMovie
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("a") && arg.equalsIgnoreCase("order") => aSort
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("add") && arg.equalsIgnoreCase("movie") => addMovie
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("delete") && arg.equalsIgnoreCase("movie") => deleteMovie
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("more") && arg.equalsIgnoreCase("details") => FileUtil.movieDetails
        case commandArgPattern(cmd, arg) => println(s"""unrecognized command: "$cmd" or: "$arg". Please follow menu instructions exactly.""")
        case _ =>  println("Please insert a command consistent with the menu options.")
      }
    }

    println("Enjoy your film!")
  }

  def allMovies = {
    println("------------------------------------------------")
    MovieDao.getAll.foreach(println)
  }

  def byGenre(genre: String) = {
    println("------------------------------------------------")
    MovieDao.byGenre(genre).foreach(println)
  }

  def aSort = {
    println("------------------------------------------------")
    MovieDao.alphSort.foreach(println)
  }

  def randomMovie = {
    println("------------------------------------------------")
    MovieDao.movieGenerator.foreach(println)
    println("------------------------------------------------")
  }

  def addMovie: Unit = {

    println("What movie would you like to add?")
    
    val titleInput = StdIn.readLine()

    try {
      if (MovieDao.saveNew(Movie(titleInput))) println(s"Added $titleInput to the movie database.")
    } catch {
      case e: Exception => println("Failed to add movie.")
    }
  }

  def deleteMovie: Unit = {

    println("What movie would you like to delete?")

    val titleInput = StdIn.readLine()

    try {
      if (MovieDao.deleteMovie(Movie(titleInput))) println(s"Deleted $titleInput from movie database.")
    } catch {
      case e: Exception => println("Failed to delete movie.")
    }
  }
}