package action

import java.net.{UnknownHostException, MalformedURLException, URL}

import domain.Link
import org.scalatest.{Assertions, Matchers, FlatSpec}

import scala.io.Source

/**
  * Created by Leandra on 4/18/2016.
  */
class LinkCleanerSpec  extends FlatSpec with Matchers{

  "parse url succeed" should "succeed with http://nytimes.com/" in {

    val src = "http://nytimes.com/"
    val list = LinkCleaner.parse(src,Source.fromURL(new URL(src)))
    Assertions.assert(list.length > 1)
  }


  "failed url 2: URL HOSTNAME does not exist" should "fail with http://rwuwuwu.com/" in {
    try {
      val src = "http://rwuwuwu.com/"
      val list = LinkCleaner.parse(src,Source.fromURL(new URL(src)))

    }
    catch {
      case _: UnknownHostException => // Expected, so continue
    }
  }


  "organize url fail 2: invalid parse regex" should "fail with URL not in href" in {

   // LinkParser.organize(List("http://nytimes.com/","http://nytimes.com/")
    val sourceUrl = "https://raw.githubusercontent.com/lakshlumba/CSYE7200_Big-Data-Sys-Engr-Using-Scala/master/Rossmann_Sales_Prediction_SBT/src/test/scala/com/neu/test/TestBaseSpec.scala"
    val hrefRegex = """\<a.*?href=\"(.*?)\".*?\>.*?\</a>""".r
    val links : List[Link] = Nil
   val list = LinkCleaner.organize(hrefRegex.findAllIn(Source.fromURL(new URL(sourceUrl)).mkString).toList)(links)(sourceUrl)
    Assertions.assert(list.length == 0)

  }


  "organize url succeed : valid parse regex" should "pass with URL in href" in {

    val sourceUrl = "https://www.washingtonpost.com/"
    val hrefRegex = """\<a.*?href=\"(.*?)\".*?\>.*?\</a>""".r
    val links : List[Link] = Nil
    val list = LinkCleaner.organize(hrefRegex.findAllIn(Source.fromURL(new URL(sourceUrl)).mkString).toList)(links)(sourceUrl)
    Assertions.assert(list.length > 1)

  }




}
