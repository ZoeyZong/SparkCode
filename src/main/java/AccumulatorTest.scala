import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object AccumulatorTest {
  def main(args: Array[String]): Unit = {
    val spark=SparkSession.builder().appName("test").master("local").getOrCreate()
    val sc=spark.sparkContext
    val rdd1: RDD[String] = sc.textFile("D:\\SparkCode\\SparkCodeTest\\src\\data\\word.txt")
    val accumulator=sc.longAccumulator
    val rdd2=rdd1.map(one=>{
      accumulator.add(1)
      one
    })
    rdd2.collect()
    println(s"accumulator=${accumulator.value}")
    sc.stop()
  }
}
