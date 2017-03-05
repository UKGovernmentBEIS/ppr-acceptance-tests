package stepdefs

import org.jsoup.nodes.Document
import org.scalatest.{Matchers, OptionValues}

trait DocValues extends Matchers with OptionValues {

  /**
    * Turns out it is *way* faster to grab the text of the page, parse it with jsoup and
    * find elements in the resulting Document than it is to look up elements with the
    * selenium driver. This is particularly true when checking that an element is *not*
    * present on the page, as the selenium driver will wait for 1 second before deciding
    * it can't find the element.
    *
    * This implicit class adds some convenient syntax around the Document.
    */
  implicit class DocSyntax(doc: Document) {
    def elementById(id: String) = Option(doc.getElementById(id))

    def textOf(id: String) = elementById(id).value.text

    def shouldHaveElementWithId(id: String) = elementById(id).isDefined shouldBe true

    def shouldNotHaveElementWithId(id: String) = elementById(id).isDefined shouldBe false
  }

}
