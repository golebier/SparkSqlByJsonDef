package gra.spark.sql.json.executor

import gra.spark.common.prepareSparkSession
import org.apache.spark.sql.SparkSession


/**
 * Main object of the app
 * Executed all the steps to achieve ap goal
 */
object Main {
  def main(args: Array[String]): Unit = {
    val startTime = System.currentTimeMillis()
    implicit val spark: SparkSession = prepareSparkSession("SparkSqlByJsonDef")
    run(args)
    println(s"All queries processing time: ${System.currentTimeMillis() - startTime}")
  }

  def run(args: Array[String])(implicit spark: SparkSession): Unit = {
    val parameters = Parameters.prepare(args)
    val jobDefinition = JobDefinition(parameters)
    SparkSql(jobDefinition.global)
      .process(jobDefinition.queries, jobDefinition.prepareCacheMap, System.currentTimeMillis())
  }
}
