import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.apache.spark.{SparkConf, SparkContext}

object makeString {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("makeString")
    val sc = new SparkContext(conf)
    val rdd1: RDD[String] = sc.textFile(path = "D:\\SparkCode\\SparkCodeTest\\src\\data\\pvuvdata")
    val rdd2 = rdd1.map(row => Row(row(0), row.mkString("", "|", "")))
    rdd2.take(10).foreach(println)
    sc.stop()
  }
}
