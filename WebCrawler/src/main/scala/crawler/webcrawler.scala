    package crawler

    import java.io._
    import java.net.URL
    import action.{LinkCleaner, FilterNewsURL}
    import userInput.displayNews
    import scala.io.Source
    import scala.util.{Try, Failure}


    /**
     * Main function for webcrawler
     */
    object WebCrawler {
      def main(args: Array[String]): Unit = {

        // source url on which web crawler will crawl
        //val sourceUrl = "http://192.168.1.105/~rynmrtn/"
        //   val sourceUrl = "http://www.foxnews.com/"

       // val lst = passURL("https://www.bostonglobe.com/")
      //  val lst = passURL("http://www.freep.com/")


        /*
        val lst = passURL("http://nypost.com/")


        // FileWriter
        val file = new File("C:/files/newsURLs2.txt")
        // to append multiple entries
        val bw = new BufferedWriter(new FileWriter(file,true))



        println("\n" + lst.size + " links were found.\n")
        lst.foreach { link => bw.write(link.toString) }
        bw.close()
        */



      }

      def takeInput(): String ={

        //  readLine lets you prompt the user and read their input as a String
        println("Please copy paste news URL of your interest:  ")
        val url = readLine()


        // you can also print output with printf
        println(s"You chose : $url ."+"\n Please wait for related news articles !")
        url
      }


      def passURL(url: String) = {

        val sourceUrl = url
        //val sourceUrl = "https://www.washingtonpost.com/"

        // val sourceUrl = "http://www.theblaze.com/"
        //  val sourceUrl = "http://www.thedailybeast.com/"
        // val sourceUrl = "http://www.slate.com/"
        //val sourceUrl = "http://www.nytimes.com/"

        val source = Source.fromURL(new URL(sourceUrl))
        val lst = LinkCleaner.parse(sourceUrl, source)

        lst

      }



        //FilterNewsURL.hello("neha")
        //FilterNewsURL.dataFiltering("C:/files/newsURLs2.txt")
      displayNews.showNewsURL()
      val userURL =  takeInput()
      displayNews.userURL(userURL)

    }