package gra.spark.sql.json.executor

/**
 *
 * @param global
 */
case class SparkSql(global: Global) {
  def process(queries: List[Query], cacheMap: Map[String, Int], startTime: Long): Unit = {
  }
}
