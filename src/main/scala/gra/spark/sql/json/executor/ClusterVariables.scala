package gra.spark.sql.json.executor

case class ClusterVariables(default: Option[Map[String, Any]]) {
  def getDefault: Map[String, Any] = default.getOrElse(Map.empty)
}
