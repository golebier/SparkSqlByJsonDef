package gra.tests.common

import gra.spark.common.prepareSparkSessionSettings
import org.apache.spark.sql.SparkSession

trait TestSparkSessionWrapper extends TestBeforeAndAfter {
  implicit lazy val spark: SparkSession = {
    val spark = SparkSession.builder().master("local[4]").enableHiveSupport().getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    prepareSparkSessionSettings(spark)
    spark
  }
}
