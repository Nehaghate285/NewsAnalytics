package action

import java.net.MalformedURLException

import org.apache.hadoop.mapred.InvalidInputException
import org.scalatest.{Assertions, Matchers, FlatSpec}

/**
  * Created by Leandra on 4/18/2016.
  */
class FilterNewsURLSpec extends FlatSpec with Matchers{

  "success file with data filtered with 2016" should "succeed with C:/files/newsURLs2.txt" in {

    val rddData = FilterNewsURL.dataFiltering("C:/files/newsURLs2.txt")
    val datawith2016 = rddData.filter( xx => xx._3.contains("/2016/"))
    val count = datawith2016.count()
    Assertions.assert(count > 1)
    print(count)

      }


  "fail with file for data filtered with 2014" should "fail with C:/files/newsURLs2.txt" in {

    val rddData = FilterNewsURL.dataFiltering("C:/files/newsURLs2.txt")
    val datawith2014 = rddData.filter( xx => xx._3.contains("/2014/"))
    val count = datawith2014.count()
    Assertions.assert(count ==  0)
    print(count)

  }


  "fail with file not found" should "fail with C:/files/news.txt" in {

    try {
      val rddData = FilterNewsURL.dataFiltering("C:/files/news.txt")
      val datawith2016 = rddData.filter( xx => xx._3.contains("/2016/"))
      val count = datawith2016.count()
      print(count)

    }
    catch {
      case _: InvalidInputException => // Expected, so continue
    }

  }
}
