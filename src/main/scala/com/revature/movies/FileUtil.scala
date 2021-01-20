package com.revature.movies

import scala.io.BufferedSource
import scala.io.Source
import java.io.File

object FileUtil {

def movieDetails: Unit = {

    println(
         """|
         ||  Title |  Release Year |  Metacritic Score  |  IMDB Userscore  |  Director |  Genre  |
         |________________________________________________________________________________________
         |""".stripMargin
         )

    val bufferedSource = io.Source.fromFile("movies.csv")

    for (line <- bufferedSource.getLines.drop(1)) {
        val cols = line.split(",").map(_.trim)
        println(
            s"|  ${cols(0)}  |  ${cols(1)}  |  ${cols(2)}  |  ${cols(3)}  |  ${cols(4)}  |  ${cols(5)}  |")
      }
    }
}