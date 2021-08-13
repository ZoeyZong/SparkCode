import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
 *1. cache默认缓存再内存中，是懒执行算子=persist()=persist(StorageLevel.MEMORY_ONLY)
 * 2.persist(StorageLevel.MEMORY_ONLY)可以手动指定持久化节点
 * 3，cache和persist都是懒执行，需要触发执行
 * 4.对一个rdd，cache或者persist之后可以赋值给一个变量，下次直接使用持久化的rdd
 * 5.如果赋值给一个变量，那么cache和persist之后不能跟持久化的算子
 * 6.checkpoint将数据存在磁盘中
 * chche,persist,checkpiont,持久化单位都是partition
 */
object Cache {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local").setAppName("cacheTest")
    val sc=new SparkContext(conf)
    sc.setCheckpointDir("./checkpoint")
//    val rdd = sc.textFile(path = "D:\\SparkCode\\SparkCodeTest\\src\\datas\\NASA_access_log_Aug95")
//    rdd.cache()
val rdd: RDD[String] = sc.textFile("D:\\SparkCode\\SparkCodeTest\\src\\datas\\words.txt")
//    rdd.cache
    rdd.checkpoint()
    rdd.collect()
    println("...")
//    val startime=System.currentTimeMillis()
//    val result1: Long = rdd.count()
//    println(result1)
//    val endtime=System.currentTimeMillis()
//    println(endtime-startime)
//    val startime1=System.currentTimeMillis()
//    val result2: Long = rdd.count()
//    println(result2)
//    val endtime1=System.currentTimeMillis()
//    println(endtime1-startime1)
//

    sc.stop()
  }
}
