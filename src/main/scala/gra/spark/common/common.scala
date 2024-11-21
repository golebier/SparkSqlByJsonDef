package gra.spark

import org.apache.spark.sql.SparkSession

package object common {
  /**
   * Creates Spark Session with modification of the Spark Session Settings
   * @param appName The application name
   * @return SparkSession
   */
  def prepareSparkSession(appName: String): SparkSession = {
    val spark = prepareSparkSessionBuilder(appName)
    prepareSparkSessionSettings(spark)
    spark
  }

  /**
   * Modifies settings on Spark Session to match any app on a context
   * @param spark SparkSession impacted by the settings
   */
  def prepareSparkSessionSettings(implicit spark: SparkSession): Unit = {
    spark.conf.set("hive.exec.dynamic.partition", "true")
    spark.conf.set("hive.exec.dynamic.partition.mode", "nonstrict")
    spark.conf.set("spark.exec.dynamic.partition.mode", "nonstrict")
    spark.conf.set("spark.sql.source.partitionOverwriteMode", "dynamic")
  }

  /**
   * Builds Spark Session. Hive support is enabled.
   * @param appName the name of app
   * @return SparkSession
   */
  private def prepareSparkSessionBuilder(appName: String): SparkSession = SparkSession.builder
    .appName(appName).enableHiveSupport()
    .getOrCreate()
}
