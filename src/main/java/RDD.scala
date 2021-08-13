import org.apache.spark.{SparkConf, SparkContext}

object RDD {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local").setAppName("test")
    val sc=new SparkContext(conf)
    val rdd1=sc.parallelize(Array("zhangsan","lisi","wangwu"))
    val rdd2=rdd1.map(name=>{
      println("*****map"+name)
      name+"~"
    })
    val rdd3=rdd2.filter(name=>{
      println("======filter"+name)
      true
    })
    rdd3.collect()
    sc.stop()
  }
}
