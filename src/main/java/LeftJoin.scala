import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
object LeftJoin {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local").setAppName("test")
    val sc=new SparkContext(conf)
    val rdd1 = sc.makeRDD(Array(
      ("zhangsan",18),("lisi",19),("wangwu",20),("zhangsan",18),("lisi",19),("wangwu",20),("maliu",21)),3)
rdd1.foreachPartition(iter=>{
  iter.foreach(println)
})
    //    rdd1.mapPartitions(iter=>{
//      println("插入数据库")
//      iter
//    },true).collect()
    sc.stop()
  }

}
