import org.apache.spark.{SparkConf, SparkContext}

object BroadCast {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
    conf.setMaster("local")
    conf.setAppName("broadcast")
    val sc=new SparkContext(conf)
    val list=List("zhangsan","lisi")
    val bcList=sc.broadcast(list)
    val nameRDD=sc.parallelize(List[String]("zhangsan","lisi","wangwu"))

    val result=nameRDD.filter(name=>{
      val innerList=bcList.value
      innerList.contains(name)
    }).foreach(println)

    sc.stop()
  }
}
