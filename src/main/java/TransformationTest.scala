import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object TransformationTest {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
    conf.setMaster("local").setAppName("test")
    val sc=new SparkContext(conf)
    val lines: RDD[String] = sc.textFile("D:\\SparkCode\\SparkCodeTest\\src\\datas\\words.txt")
    val Array: Array[String] = lines.collect
    //lines.take(5)意思是取前五行，first取第一个
    Array.foreach(println)
    val howmany: Long = lines.count
    println(howmany)
    lines.filter(s=>{
      s.equals("hello scala")
    }).foreach(println)
    /**
     * sample抽样
     * true代表有放回抽样
     * seed:Long类型的种子，针对同一批数据只要种子相同，每次抽样结果一样
     */
    val result = lines.sample(false, 0.2)
    result.foreach(println)
    sc.stop()
  }
}
