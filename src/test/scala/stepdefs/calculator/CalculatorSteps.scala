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
import org.scalatest.Matchers
import pages.CurrentPage
import pages.calculator.Answer
import stepdefs.DocValues

class CalculatorSteps extends ScalaDsl with EN with Matchers with StartUpTearDown with DocValues {
  implicit val driver = CurrentPage.webDriver

  val df = DateTimeFormat.forPattern("d MMMM YYYY")

  Then("""^I should see ([0-9]+) calculated periods$""") { count: Int =>
    val doc = Answer.doc

    (1 to count).foreach { i =>
      doc shouldHaveElementWithId s"period-start-$i"
      doc shouldHaveElementWithId s"period-end-$i"
      doc shouldHaveElementWithId s"deadline-$i"
    }

    doc shouldNotHaveElementWithId s"period-start-${count + 1}"
    doc shouldNotHaveElementWithId s"period-end-${count + 1}"
    doc shouldNotHaveElementWithId s"deadline-${count + 1}"
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
      doc shouldHaveElementWithId "no-need-to-report"
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
