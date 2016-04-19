  package action

  import java.nio.file.{Files, Paths}
  import domain.Link
  import org.apache.spark.rdd.RDD
  import Array._
  import scala.io.Source
  import org.apache.spark.SparkContext
  import org.apache.spark.SparkContext._
  import org.apache.spark.SparkConf
  import java.io._
  import scala.util.Success


  /**
    *  Singleton parser designed to take a Source and pull all
   *  links from the text of the url.
   *
   *  This is rather simplistic and finds any html anchor references
   *  with an href attribute.
   */
  object FilterNewsURL {

    // Getting all the URL with href

      val hrefRegex = """\<a.*?href=\"(.*?)\".*?\>.*?\</a>""".r

      /**
          *Parses the contents of a Source object and finds all links that match
          *the specified regular expression. The links that are found are then
          *organized.
      */;
      def hello(firstname : String ) = {
          println("\n" + firstname)

      }


      def dataFiltering(filePath : String): RDD[(String,String,String)] ={

          val logFile =  filePath // Should be some file on your system
          val cleanfile = "C:/files/cleandNYtimes/part-00001" // File path to store cleaned data

          // val conf = new SparkConf().setAppName("Simple Application")
          //val sc = new SparkContext(conf)
          val sparkConf = new SparkConf().setAppName("JavaWordCount").setMaster("local[2]").set("spark.executor.memory","1g").set("spark.driver.allowMultipleContexts", "true") ;
          val sc = new SparkContext(sparkConf)
          val logData = sc.textFile(logFile, 2).cache()
          val cleanData = sc.textFile(cleanfile, 2).cache()

          // filtering out news urls which contain only 2016
          val numAs = logData.filter(line => line.contains("2016"))

          // for getting just 2016 url links
          val removedata2016 = logData.filter( xx => xx.contains("2016"))
          hello("removed 2016 data:")
           //11.25  removedata2016.foreach(println)

          //splitting the news url with 2016 keyword
          val splitRdd = removedata2016.map( line => line.split("/2016/") )

          // segregating the url into 3 parts
          // RDD[ Array[ String ] to [String,String,String]
          val yourRdd = splitRdd.flatMap(
            arr => {
              try {
                val title = arr( 0 )
                val text = arr( 1 )
                val words = text.split( "-|_" )
                val url = title + "/2016/" + text
                words.map(word => (word, text, url)) // appending word, text and url
              } catch {
                case e : Exception => None
              }
          } )

          //filtering the news with stopwords
          val  newrdd = yourRdd.filter(!_._1.contentEquals("how")).filter(!_._1.contentEquals("the")).filter(!_._1.contentEquals("a")).filter(!_._1.contentEquals("is"))
            .filter(!_._1.contentEquals("on")).filter(!_._1.contentEquals("in")).filter(!_._1.contentEquals("as")).filter(!_._1.contentEquals("for"))
            .filter(!_._1.contentEquals("i")).filter(!_._1.contentEquals("and")).filter(!_._1.contentEquals("was")).filter(!_._1.contentEquals("all"))
            .filter(!_._1.contentEquals("am")).filter(!_._1.contentEquals("an")).filter(!_._1.contentEquals("are")).filter(!_._1.contentEquals("at"))
            .filter(!_._1.contentEquals("be")).filter(!_._1.contentEquals("but")).filter(!_._1.contentEquals("by")).filter(!_._1.contentEquals("do"))
            .filter(!_._1.contentEquals("from")).filter(!_._1.contentEquals("has")).filter(!_._1.contentEquals("have")).filter(!_._1.contentEquals("had"))
            .filter(!_._1.contentEquals("is")).filter(!_._1.contentEquals("her")).filter(!_._1.contentEquals("if")).filter(!_._1.contentEquals("he"))
            .filter(!_._1.contentEquals("or")).filter(!_._1.contentEquals("it")).filter(!_._1.contentEquals("so")).filter(!_._1.contentEquals("that"))
            .filter(!_._1.contentEquals("to")).filter(!_._1.contentEquals("too")).filter(!_._1.contentEquals("you")).filter(!_._1.contentEquals("we"))
            .filter(!_._1.contentEquals("your")).filter(!_._1.contentEquals("into")).filter(!_._1.contentEquals("why")).filter(!_._1.contentEquals("with"))
            .filter(!_._1.contentEquals("my")).filter(!_._1.contentEquals("of"))


           //5.44  newrdd.foreach( { case ( word, text ,url) => println( s"{ $word, $text ,$url}" ) } )

           //5.41 newrdd.map(x => x._1 + "," + x._2 + "," + x._3).repartition(1).saveAsTextFile("urlCleaned7")
           hello("leandra")



           val rddinString: RDD[Array[String]] = newrdd.map(x => Array(x._1, x._2,x._3))
           // rddinString.flatMap()
          //dislaying the cleaned rdd
          //4.26   newrdd.foreach( { case ( word, text ,url) => println( s"{ $word, $text ,$url}" ) } )
          hello("end of print")

        if(Files.exists(Paths.get("urlCleaned8/part-00000"))) {
            hello("if loop")


                //8.01 val fw = new FileWriter("cleanedNews/part-00000", true)
            val data = sc.textFile("urlCleaned8/part-00000") //RDD[String]
            val prevrdd = data.map(line => line.split(",").map(elem => elem.trim))
            val prevInFormat = prevrdd.map( arr => {
            val word = arr( 0 )
            //val word = arr( 0 ).substring(1)
            val text = arr( 1 )
            val url = arr( 2 )
           // val url = arr( 2 ).substring(0,arr(2).length - 1 )
            ( word, text ,url )
          } )
          hello("prev rdd")
          //10.28    prevInFormat.foreach( { case ( word, text ,url) => println( s"{ $word, $text ,$url}" ) } )



           val rddAll = newrdd.union(prevInFormat) // RDD[String,String,String]
           // rddAll.foreach( { case ( word, text ,url) => println( s"{ $word, $text ,$url}" ) } )

               rddAll.map(x => x._1 + "," + x._2 + "," + x._3).repartition(1).saveAsTextFile("urlCleaned9.1")

          }

        newrdd
      }

      /**
          *A recursive method that builds a list of Link objects from a List of
          *Strings. Only items in the list that match the hrefRegex will be added
      */
  }