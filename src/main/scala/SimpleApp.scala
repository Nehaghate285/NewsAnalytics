/* SimpleApp.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import java.io._

 
import java.sql.DriverManager
import java.sql.Connection

object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "C:/files/file-ny-times.txt" // Should be some file on your system
   val cleanfile = "C:/files/cleandNYtimes/part-00001" // Should be some file on your system
   
    // val conf = new SparkConf().setAppName("Simple Application")
    //val sc = new SparkContext(conf)
   val sparkConf = new SparkConf().setAppName("JavaWordCount").setMaster("local[2]").set("spark.executor.memory","1g");
    val sc = new SparkContext(sparkConf)
    val logData = sc.textFile(logFile, 2).cache()
    val cleanData = sc.textFile(cleanfile, 2).cache()
    
    
    //val file = new File("/C:/files/cleandNYtimes.txt")
//val bw = new BufferedWriter(new FileWriter(file))
    
     val numAs = logData.filter(line => line.contains("2016"))
    
    //numAs.foreach { link => bw.write(link.toString) }
    
   

   // val b = logData.filter( xx => xx.contains("2016"))
  //  val wordCount = b.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
  // b.saveAsTextFile("/C:/files/cleandNYtimes")
     //bw.close();
     
    //val b1 = logData.filter( xx => xx.contains("2016"))
   // val numBs = logData.filter(line => line.contains("b")).count()
    //val spliturl = logData.split("/")
    


    val splitRdd = cleanData.map( line => line.split("/2016/") )
    
// RDD[ Array[ String ]
val yourRdd = splitRdd.flatMap( arr => {
  val title = arr( 0 )
  val text = arr( 1 )
  val words = text.split( "-" )
  words.map( word => ( word, title ) )
} )


val wordCount = cleanData.flatMap(line => line.split("-")).map(word => (word, 1)).reduceByKey(_ + _)
  

/* val yourRddcount = splitRdd.flatMap( 
    arr => {
  val title = arr( 0 )
  val text = arr( 1 )
  val words = text.split( "-" ).map( word => ( word, 1 ).reduceByKey(_ + _) )
} )*/
// RDD[ ( String, String ) ]
// Now, if you want to print this...
yourRdd.foreach( { case ( word, text ) => println( s"{ $word, $text }" ) } )




// if you want to count ( this count is for non-unique words), 
val countRdd = yourRdd
  .groupBy( { case ( word, text ) => text } )  // group by title
  .map( { case ( text, iter ) => ( text, iter.size ) } ) // count for every title
    
   
    println("count is:%s ".format(countRdd))
    
   
    
    val wordCounts = logData.map(line => line.split(" ").size).reduce((a, b) => if (a > b) a else b)
  //  val wordCount = logData.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)
   val printCount = wordCount.collect()
   
    //println("Lines with a: %s, Lines with b: %s,Lines a: %s".format(numAs,printCount))
  
  //db conn
    
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/news_analytics"
    val username = "root"
    val password = "root"
 
    // there's probably a better way to do this
    var connection:Connection = null
 
    try {
      // make the connection
       Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
 
      
      
         val prep = connection.prepareStatement("INSERT INTO urls (url) VALUES (?)")
         prep.setString(1, "Nothing great was ever achieved without enthusiasm.")
         prep.executeUpdate
      
  println("Thus,is a new List.")
  
      
    } catch {
      case e => e.printStackTrace
    }
    connection.close()
  
  
  
  
  }
}