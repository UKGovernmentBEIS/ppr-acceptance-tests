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

package stepdefs

import cucumber.api.scala.{EN, ScalaDsl}
import driver.StartUpTearDown
import org.joda.time.format.DateTimeFormat
import org.openqa.selenium.By
import org.scalatest.Matchers
import pages.CurrentPage

class CurrentPageSteps extends ScalaDsl with EN with Matchers with StartUpTearDown {
  import collection.JavaConversions._
  implicit val driver = CurrentPage.webDriver
  var suppliedValues: Map[String, String] = Map()

  Given("""^I set these field values:$""") { table: java.util.Map[String, String] =>
    // stash the map so we can refer to the values in later steps
    suppliedValues = table.toMap

    suppliedValues.foreach {
      case (name, v) => CurrentPage.textField(name).value = v
    }
  }

  val df = DateTimeFormat.forPattern("d MMMM YYYY")

  Given("""^I set these date field values:$""") { table: java.util.Map[String, String] =>
    // stash the map so we can refer to the values in later steps
    suppliedValues = table.toMap

    suppliedValues.foreach {
      case (name, v) => CurrentPage.setDateField(name, df.parseLocalDate(v))
    }
  }

  Given("""^I clear field (.+)$""") { fieldName: String => CurrentPage.textField(fieldName).clear() }

  Given("""^I set field (.+) to '(.*)'$""") { (name: String, value: String) => CurrentPage.textField(name).value = value }

  Given("""^I set password (.+) to '(.*)'$""") { (name: String, value: String) => CurrentPage.pwdField(name).value = value }

  Given("""^I set dropdown (.+) to '(.*)'$""") { (name: String, value: String) => CurrentPage.singleSel(name).value = value }

  Given("""^I click on '(.+)'$""") { buttonName: String => CurrentPage.clickOn(By.name(buttonName)) }

  Then("""^the fields should be populated with the values I set$""") { () =>
    suppliedValues.foreach {
      case (name, v) => CurrentPage.textField(name).value shouldBe v
    }
  }

  Given("""^I click on the '(.+)' link$"""){ linkText :String=>
    driver.findElements(By.tagName("a")).find(_.getText == linkText) match {
      case None => fail(s"Could not find a link with text $linkText")
      case Some(e) => e.click()
    }
  }

  Then("""^I should see an alert with the text '(.+)'""") { expectedText:String =>
    val p = driver.findElement(By.className("alert")).findElement(By.tagName("p"))
    p.getText shouldBe expectedText
  }
}
