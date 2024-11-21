package gra.spark.sql.json.executor

import org.apache.spark.storage.StorageLevel

/**
 * "global": {
 * "sparkCache": "MEMORY_ONLY",
 * "mode": "batch",
 * "cacheAutomatically": true
 * }
 *
 * @param
 */
case class Global(sparkCache: Option[String], cacheAutomatically: Option[Boolean], mode: Option[String]) {
  def prepareSparkTypeCachePolicy: StorageLevel = StorageLevel.fromString(sparkCache.getOrElse("MEMORY_AND_DISK"))
  def getCacheAutomatically: Boolean = cacheAutomatically.getOrElse(false)
}
