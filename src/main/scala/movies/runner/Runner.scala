package movies.runner

import movies.cli.Cli
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Connection
import scala.util.Using

object Runner extends App {

    val cli = new Cli
    
    cli.menu
}