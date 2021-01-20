import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "Underrated Movies",
    libraryDependencies += scalaTest % Test
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.

// https://mvnrepository.com/artifact/org.postgresql/postgresql
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.18"

// https://mvnrepository.com/artifact/org.scala-lang/scala-library
libraryDependencies += "org.scala-lang" % "scala-library" % "2.13.4"

// https://mvnrepository.com/artifact/org.scala-lang/scala-reflect
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.13.4"

// https://mvnrepository.com/artifact/org.scala-lang/scala-compiler
libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.13.4"

// https://mvnrepository.com/artifact/org.scala-lang.modules/scala-parser-combinators
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.2.0-M1"

scalacOptions ++= Seq("-deprecation", "-feature")