package gra.spark.sql.json.executor

import org.json4s.native.JsonMethods._
import org.json4s.DefaultFormats

object Parameters {
  implicit val formats: DefaultFormats.type = DefaultFormats
  def prepare(args: Array[String]): Parameters = args.toList match {
    // TODO make it better
    case "-c" :: clusterSettingsYmlPath :: "-s" :: variablesJsonPath :: "-e" :: jsonSqlDefinitions
      :: "-v" :: paramsVariables :: Nil =>
      Parameters(clusterSettingsYmlPath, variablesJsonPath, jsonSqlDefinitions, parse(paramsVariables)
        .extract[Map[String, Any]])
    case _ => throw new Exception("Wrong parameters!")
  }
}

case class Parameters(clusterSettingsYmlPath: String, variablesJsonPath: String, jsonSqlDefinitions: String
                      , paramsVariables: Map[String, Any])
