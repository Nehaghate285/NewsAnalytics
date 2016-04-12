import java.net.URL
import scala.io.Source

import action.LinkParser
import java.io._

/**
 * A main function that boostraps crawling efforts
 */
object Scrawler {
    def main(args : Array[String]) : Unit = {
        //val sourceUrl = "http://192.168.1.105/~rynmrtn/"
        val sourceUrl = "http://www.boston.com//"
        val source = Source.fromURL(new URL(sourceUrl))
        val lst = LinkParser.parse(sourceUrl, source)

// FileWriter
val file = new File("/C:/files/file-bostonnews.txt")
val bw = new BufferedWriter(new FileWriter(file))



        println("\n" + lst.size + " links were found.\n")
        lst.foreach { link => bw.write(link.toString) }
        bw.close()
    }
}
