/*
 * Copyright (C) 2017  Department for Business, Energy and Industrial Strategy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package stepdefs.calculator

import cucumber.api.scala.{EN, ScalaDsl}
import driver.StartUpTearDown
import org.joda.time.format.DateTimeFormat
import org.jsoup.nodes.Document
import org.scalatest.{Matchers, OptionValues}
import pages.CurrentPage
import pages.calculator.Answer

class CalculatorSteps extends ScalaDsl with EN with Matchers with StartUpTearDown with OptionValues {
  implicit val driver = CurrentPage.webDriver

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

    def shouldBePresent(id: String) = elementById(id).isDefined shouldBe true

    def shouldNotBePresent(id: String) = elementById(id).isDefined shouldBe false
  }

  val df = DateTimeFormat.forPattern("d MMMM YYYY")

  Then("""^I should see ([0-9]+) calculated periods$""") { count: Int =>
    val doc = Answer.doc

    (1 to count).foreach { i =>
      doc.shouldBePresent(s"period-start-$i")
      doc.shouldBePresent(s"period-end-$i")
      doc.shouldBePresent(s"deadline-$i")
    }

    doc.shouldNotBePresent(s"period-start-${count + 1}")
    doc.shouldNotBePresent(s"period-end-${count + 1}")
    doc.shouldNotBePresent(s"deadline-${count + 1}")
  }

  Then("""^Period ([0-9]+) should run from (.+) to (.+) with deadline (.+)$""") {
    (i: Int, start: String, end: String, deadline: String) =>
      val doc = Answer.doc

      doc.textOf(s"period-start-$i") shouldBe start
      doc.textOf(s"period-end-$i") shouldBe end
      doc.textOf(s"deadline-$i") shouldBe deadline
  }

  Then("""it should show that the financial year runs from (.+) to (.+)""") { (startDate: String, endDate: String) =>
    val doc = Answer.doc
    doc.textOf("financial-year-start") shouldBe startDate
    doc.textOf("financial-year-end") shouldBe endDate
  }

  Then("""if the (.+) is before (.+) then I should see a message about that""") { (endDate: String, cutoffDate: String) =>
    val doc = Answer.doc
    val d = df.parseLocalDate(endDate)
    val cutoff = df.parseLocalDate(cutoffDate)

    if (d.isBefore(cutoff)) {
      doc.shouldBePresent("no-need-to-report")
    }
  }

  Then("""form error should be '(.+)'""") { s: String =>
    val doc = Answer.doc
    doc.textOf("form-errors") shouldBe s
  }

  Then("""field error for (.+) should be '(.+)'""") { (fieldName: String, s: String) =>
    val doc = Answer.doc
    doc.textOf(s"error-$fieldName") shouldBe s
  }

}
