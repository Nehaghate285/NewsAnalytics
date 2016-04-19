  package userInput

  import java.io.{File, BufferedWriter, FileWriter}

  import action.FilterNewsURL
  import org.apache.spark.rdd.RDD
  import org.apache.spark.{SparkContext, SparkConf}

  /**
    * Created by Leandra on 4/19/2016.
    */
  object displayNews {




    def getAllData(): RDD[(String, String, String)] = {
      val sparkConf = new SparkConf().setAppName("JavaWordCount").setMaster("local[2]").set("spark.executor.memory", "1g").set("spark.driver.allowMultipleContexts", "true") ;
      val sc = new SparkContext(sparkConf)
      val fileData = sc.textFile("urlCleaned9/part-00000")
      val dist1 = fileData.map(line => line.split(",").map(elem => elem.trim))

      val dist2 = dist1.map(arr => {
        val word = arr(0)
        val text = arr(1)
        val url = arr(2)
        (word, text, url)

      })
      dist2

    }

    def showNewsURL(): Unit = {

      val sparkConf = new SparkConf().setAppName("JavaWordCount").setMaster("local[2]").set("spark.executor.memory", "1g").set("spark.driver.allowMultipleContexts", "true") ;
      val sc = new SparkContext(sparkConf)


      val fileData = sc.textFile("urlCleaned9/part-00000")

      //println("not dist data")
      // fileData.foreach(println)


      val dist = fileData.distinct()

      //println("dist data")
      //12.05 dist.foreach(println)


      val dist1 = dist.map(line => line.split(",").map(elem => elem.trim))

      val dist2 = dist1.map(arr => {
        val word = arr(0)
        val text = arr(1)
        val url = arr(2)
        (word, text, url)

      })


      val nytimesRdd = dist2.filter(xx => xx._3.contains("nytimes.com"))
      // nytimesRdd.foreach( { case ( word, text ,url) => println( s"{ $word, $text ,$url}" ) } )

      val washingtonRdd = dist2.filter(xx => xx._3.contains("washingtonpost.com"))
      //  washingtonRdd.foreach( { case ( word, text ,url) => println( s"{ $word, $text ,$url}" ) } )


//      val file1 = fileData.map(line => line.split(",").map(elem => elem.trim))
//
//      val file2All = file1.map(arr => {
//        val word = arr(0)
//        val text = arr(1)
//        val url = arr(2)
//        (word, text, url)
//
//      })


      println("NY Times articles : ");

       nytimesRdd.take(15).foreach( { case ( word, text ,url) => println( s"{$url}" ) } )


      println("Washington post articles : ");

      washingtonRdd.take(10).foreach( { case ( word, text ,url) => println( s"{$url}" ) } )

    }


    def userURL(url: String): Unit = {

      //val url = "http://www.nytimes.com/2016/04/19/sports/football/nfl-concussion-lawsuit.html"
      //val url = "http://www.nytimes.com/2016/04/18/us/politics/hillary-bill-clinton-crime-bill.html"
     //4.10 val url = "https://www.washingtonpost.com/news/morning-mix/wp/2016/04/18/uc-berkeley-student-removed-from-southwest-flight-after-speaking-arabic-on-plane/"
      val urlSplit = url.split("/2016/")
      val file = new File("files/user2.txt")
      val bw = new BufferedWriter(new FileWriter(file,true))

      FilterNewsURL.hello(url)

//      try {
        val text = urlSplit(1)
        val words = text.split("-|_")


/*
        val rdd1 = sc.parallelize(words)

        val myWords = rdd1.flatMap(x => x.split(",").filter(x => !x.contains("and")))

         val cleanWords= myWords.collect()

        cleanWords.foreach(println)

*/
        FilterNewsURL.hello("all news")

//      Array[RDD[(String,String,String)]]
        val getNews = checkInput(words,url)

      println("The topics of interests are :")
      words.foreach(println)


      FilterNewsURL.hello("News of your interests are as follows : ")
        for(i <- getNews) yield i.distinct().foreach({ case (word, text, url) =>println(s">> : $url")})
        val count = for(i <- getNews) yield i.distinct().count()
        FilterNewsURL.hello("Number of distinct interested News articles is "+count.size.toString)

//      }
//      catch {
//        case e: Exception => None
//
//      }
    }


    def checkInput(interests: Array[String],url : String): Array[RDD[(String,String,String)]] = {


      val masterData = getAllData()

      //remove stopwords


      val usernews = for(interest <- interests) yield{

        val news = masterData.filter(_._1.contentEquals(interest))
        val news1 = news.filter(!_._3.contains(url))
      //2.48  news1.foreach({ case (word, text, url) => println(s"{ $word, $text ,$url}") })

        // FileWriter
//        news1.foreach({ case (word, text, url) => bw.write(word)})

        news1  //RDD[(String,String,String)]

    }
     //3.40 for(i <- usernews) yield i.foreach({ case (word, text, url) =>println(s"{ $word, $text ,$url}")})

      //usernews Array[RDD[(String,String,String)]]



      usernews


    }

  }


