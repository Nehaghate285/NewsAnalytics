    package action

import domain.Link

import scala.io.Source

    /**
     *  Singleton parser designed to take a Source and pull all
     *  links from the text of the url.
     *
     *  This is rather simplistic and finds any html anchor references
     *  with an href attribute.
     */
    object LinkCleaner {

        val hrefRegex = """\<a.*?href=\"(.*?)\".*?\>.*?\</a>""".r



        /**
            Parses the contents of a Source object and finds all links that match
            the specified regular expression. The links that are found are then
            organized.
        */
        def parse(sourceUrl: String, source: Source) : List[Link] = {
            val links : List[Link] = Nil
            organize(hrefRegex.findAllIn(source.mkString).toList)(links)(sourceUrl)
        }

        /**
            A recursive method that builds a list of Link objects from a List of
            Strings. Only items in the list that match the hrefRegex will be added
        */
        def organize (listOfMatches : List[String]) (listOfLinks : List[Link]) (sourceUrl : String)
            : List[Link] = listOfMatches match {

            case head :: tail => {
                head match {
                    case hrefRegex(url) => {
                        organize(tail)(new Link(sourceUrl, url, "") :: listOfLinks)(sourceUrl)
                    }
                    case _ => {
                        // skip the item
                        organize(tail)(listOfLinks)(sourceUrl)
                    }
                }
            }
            case _ => listOfLinks
        }
    }