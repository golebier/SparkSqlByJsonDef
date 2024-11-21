package gra.spark.sql.json.executor

import gra.spark.common.FileSystemUtils
import org.apache.spark.sql.SparkSession
import org.json4s.native.JsonMethods._
import org.json4s.DefaultFormats

import scala.io.Source

case class JobDefinition(parameters: Parameters)(implicit spark: SparkSession) {
  implicit val formats: DefaultFormats = DefaultFormats
  val global: Global = prepareJsonDefinitionParsed.global

  val queries: List[Query] = prepareJsonDefinitionParsed.queries

  def prepareCacheMap: Map[String, Int] = Map.empty[String, Int]

  private def fixVariables(rawJson: String, vars: Map[String, Any]): String = {
    var jsonString = rawJson
    vars.foreach{ case (k, v) => jsonString = jsonString.replaceAllLiterally("${" + k + "}", v.toString)}
    jsonString
  }

  private def clusterVariablesMap: ClusterVariables = {
    val jsonVariables = Source.fromInputStream(FileSystemUtils(parameters.variablesJsonPath).fromInputStream)
      .getLines().mkString
    val parsedStringVariables = parse(jsonVariables)
    parsedStringVariables.extract[ClusterVariables]
  }

  private def prepareJsonDefinitionParsed = {
    val jsonSqlDefinitionsString = fixVariables(Source.fromInputStream(
      FileSystemUtils(parameters.variablesJsonPath).fromInputStream).getLines().mkString
      , clusterVariablesMap.getDefault ++ parameters.paramsVariables)
    val parsedSqlDef = parse(jsonSqlDefinitionsString)
    parsedSqlDef.extract[JsonDefinitionParsed]
  }
}
