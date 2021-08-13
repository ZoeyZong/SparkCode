import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Transformation {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local").setAppName("test")
    val sc=new SparkContext(conf)

    //    val rdd: RDD[Int] = sc.parallelize(Array(1, 2, 3, 4),3)
    val rdd: Any = sc.makeRDD(Array((1, 2), (3, 4), (5, 7)), 3)
    //sc.textfile也可以变成rdd



    sc.stop()
  }
}
