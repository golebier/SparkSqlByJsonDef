import sbtassembly.AssemblyPlugin.autoImport._
import sbt.Keys._

lazy val root = (project in file("."))
  .settings(
    name := "SparkSqlJsonDefExecutor",
    version := "1.0",
    scalaVersion := "2.12.20",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-library" % "2.13.8",
      "org.scala-lang" % "scala-reflect" % "2.13.8",
      "org.scala-lang" % "scala-compiler" % "2.13.8",

      "org.json4s" %% "json4s-core" % "3.7.0-M11",
      "org.json4s" %% "json4s-native" % "3.7.0-M11",
      "org.json4s" %% "json4s-jackson" % "3.7.0-M11",
      "org.json4s" %% "json4s-ast" % "3.7.0-M11",

      "org.apache.spark" %% "spark-core" % "3.5.3" % Provided,
      "org.apache.spark" %% "spark-sql" % "3.5.3" % Provided,
      "org.apache.spark" %% "spark-hive" % "3.5.3" % Provided,

      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "org.scalacheck" %% "scalacheck" % "1.18.1" % Test
    ),
    assembly / test := {},
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", xs @_*) => MergeStrategy.discard
      case _ => MergeStrategy.first
    }
  )
enablePlugins(AssemblyPlugin)
