import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object test1 
{
  def main(args: Array[String]): Unit = 
  {
    
     val sparkConf = new SparkConf().setAppName("JavaWordCount").setMaster("local[2]").set("spark.executor.memory","1g");
    val sc = new SparkContext(sparkConf)
   
   // val conf1 = new SparkConf().setAppName("golabi1").setMaster("local")
    //val sc = new SparkContext(conf1)
    def extractDateAndCompare(line: String): String=
    {
      
        val from = line.indexOf("/06/ ") + 7
        val to = line.indexOf(".html") -1
        val mydata = line.substring(from, to)
        
        println(mydata)
        mydata
    }
    val logData = sc.textFile("C:/files/file-ny-times", 2).cache()
    
   
  }
}