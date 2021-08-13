import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object SparkPVUV {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local")
      conf.setAppName("test")
    val sc=new SparkContext(conf)
    val lines: RDD[String] = sc.textFile(path = "D:\\SparkCode\\SparkCodeTest\\src\\data\\pvuvdata")
    //pv
    lines.map(line=>{(line.split("\t")(5),1)}).reduceByKey((v1:Int,v2:Int)=>{
      v1+v2
    }).sortBy(tp=>{tp._2},false).foreach(println)

println("********************************")

    //uv要去重
    lines.map(line=>{line.split("\t")(0)+"_"+line.split("\t")(5)}).
      distinct().map(one=>
    {(one.split("_")(1),1)})
      .reduceByKey(_+_)
      .sortBy(_._2,false)
      .foreach(println)

    println("********************************")
    //每个网址的每个地区访问量从大到小
val site_local: RDD[(String, String)] = lines.map(line=>{(line.split("\t")(5),line.split("\t")(1))})
    val site_localIterable: RDD[(String, Iterable[String])] = site_local.groupByKey()
    site_localIterable.map(one=>{
      val localMap=mutable.Map[String,Int]()
      val site: String = one._1
      val localIter = one._2.iterator
      while(localIter.hasNext){
        val local=localIter.next()
        if(localMap.contains(local)){
          localMap.put(local,localMap.get(local).get+1)
        }else{
          localMap.put(local,1)
        }
      }
      val tuples: List[(String, Int)] = localMap.toList.sortBy(one => {-one._2})
      if(tuples.size>3){
        val returnList=new ListBuffer[(String,Int)]()
        for(i<-0 to 2){
          returnList.append(tuples(i))
        }

        (site,returnList)
      }else{
        (site,tuples)
      }


    }).foreach(println)

    sc.stop()
  }
}
