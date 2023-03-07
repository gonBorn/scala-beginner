ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "scala-beginner"
  )

libraryDependencies += "org.typelevel" %% "cats-core" % "2.2.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "2.5.3"
