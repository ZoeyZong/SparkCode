import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object parationMap {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("lianxiMapandMappartitions")
    val sc = new SparkContext(conf)
    println("map=====================================================================")
    val aa: RDD[Int] = sc.parallelize(1 to 9,2)
    def doubleMap(a:Int):(Int,Int) = {(a , a * 2)}
    val bb: RDD[(Int, Int)] = aa.map(doubleMap)
    println(aa.getNumPartitions)
    println(bb.collect().mkString)
    println("mappartition=============================================================")
    val cc: RDD[Int] = sc.makeRDD(1 to 9,2)
    val dd: RDD[Int] = cc.mapPartitions(x => {
      var result: List[Int] = List[Int]()
      var i = 0
      while (x.hasNext) {
        val i1: Int = x.next()
        result.::=(i1 * 2)
      }
      result.iterator
    })
    dd.foreach(println)
sc.stop()
  }
}

/**
 * package test
/**
 * scala中的:: , +:, :+, :::, +++, 等操作;
  */
object listTest {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3)
    // :: 用于的是向队列的头部追加数据,产生新的列表, x::list,x就会添加到list的头部
    println(4 :: list)  //输出: List(4, 1, 2, 3)
    // .:: 这个是list的一个方法;作用和上面的一样,把元素添加到头部位置; list.::(x);
    println( list.:: (5)) //输出: List(5, 1, 2, 3)
    // :+ 用于在list尾部追加元素; list :+ x;
    println(list :+ 6)  //输出: List(1, 2, 3, 6)
    // +: 用于在list的头部添加元素;
    val list2 = "A"+:"B"+:Nil //Nil Nil是一个空的List,定义为List[Nothing]
    println(list2)  //输出: List(A, B)
    // ::: 用于连接两个List类型的集合 list ::: list2
    println(list ::: list2) //输出: List(1, 2, 3, A, B)
    // ++ 用于连接两个集合，list ++ list2
    println(list ++ list2) //输出: List(1, 2, 3, A, B)
  }
}
 */