import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object ScalaTest {
  def main(args: Array[String]): Unit = {
//    val conf=new SparkConf()
//    conf.setMaster("local").setAppName("sqltest")
//    val sc=new SparkContext(conf)
//    val sqlContext=new SQLContext(sc=sc)
    val spark=SparkSession.builder().appName("test").master("local").getOrCreate()

    val df: DataFrame = spark.read.format("json").load("D:\\SparkCode\\SparkCodeTest\\src\\data\\json")
//    df.show()
//    df.printSchema()
//    df.take(10)
//    val rdd: RDD[Row] = df.rdd
//    rdd.foreach(println)
//    rdd.foreach(row=>{
//      val name=row.getAs[String]("name")
//      val age=row.getAs[Long]("age")
//      println(s"name=$name,age=$age")
//    })
//    val df1: Dataset[Row] = df.select(df.col("name")).filter("age>18")
//    df1.show()
//
//    val df2 = df.select("name", "age").filter("name like 'zhangsan%'")
//    df2.show()
    df.createTempView("t1")
    df.createOrReplaceTempView("t1")
    df.createGlobalTempView("t1")
    val df1: DataFrame = spark.sql("select name,age from t1")
    df1.show()
  }
}
