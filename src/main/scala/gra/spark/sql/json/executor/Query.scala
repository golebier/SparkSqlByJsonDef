package gra.spark.sql.json.executor

case class Query(alias: String, query: Option[String], udf: Option[String], config: Option[Map[String, Any]])
