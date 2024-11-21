package gra.spark.common

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession

import java.io.InputStream

case class FileSystemUtils(path: String)(implicit spark: SparkSession) {
  // TODO we need cluster like definition - dummy for now
  def fromInputStream: InputStream = fromFileSystem.open(new Path(path))
  def fromFileSystem: FileSystem = FileSystem.getLocal(getConfiguration)
  def getConfiguration: Configuration = spark.sparkContext.hadoopConfiguration
}
