package crawler

import java.net.MalformedURLException

import org.scalatest.{Assertions, Matchers, FlatSpec}

import scala.util.{Success, Failure}

/**
  * Created by Leandra on 4/18/2016.
  *
  */
class WebCrawlerSpec extends FlatSpec with Matchers {


  "success url" should "succeed with http://www.theatlantic.com/" in {
    val listOfLinks = WebCrawler.passURL("http://www.theatlantic.com/")
    val count = listOfLinks.length
    Assertions.assert(count > 1)
println(count)
  }

  "failed url 1 : Malformed URL " should "fail with abcd" in {
    try {
      val listOfLinks = WebCrawler.passURL("abcd")
    }
    catch {
      case _: MalformedURLException => // Expected, so continue
    }
  }


  "failed url 2: URL does not exist" should "fail with rwuwuwu.com" in {
    try {
      val listOfLinks = WebCrawler.passURL("rwuwuwu.com")
    }
    catch {
      case _: MalformedURLException => // Expected, so continue
    }
  }

  "failed url 3: empty URL" should "fail with empty URL" in {
    try {
      val listOfLinks = WebCrawler.passURL(" ")
    }
    catch {
      case _: MalformedURLException => // Expected, so continue
    }
  }



}