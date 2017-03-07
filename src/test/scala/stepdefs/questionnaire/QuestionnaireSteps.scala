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

package stepdefs.questionnaire

import cucumber.api.scala.{EN, ScalaDsl}
import driver.StartUpTearDown
import org.openqa.selenium.By
import org.scalatest.Matchers
import pages.{CurrentPage, PageTable}
import stepdefs.DocValues

class QuestionnaireSteps extends ScalaDsl with EN with Matchers with StartUpTearDown with DocValues {
  val With = new Step("With")

  Then("""^the message should be "(.+)"$""") { reason: String =>
    CurrentPage.doc.elementById("reason").value.text shouldBe reason
  }

  Given("""I select '(.+)' and continue$""") { checkboxId: String =>
    CurrentPage.clickOn(By.id(checkboxId.toLowerCase))
    CurrentPage.clickOn(By.name("Continue"))
  }

  Given("""I select '(.+)' and continue to the (.+) page$""") { (checkboxId: String, pageName: String) =>
    CurrentPage.clickOn(By.id(checkboxId.toLowerCase))
    CurrentPage.clickOn(By.name("Continue"))
    PageTable.lookupPage(pageName) match {
      case None => fail(s"could not find a page with name $pageName (did you forget to add the page object to the PageTable?)")
      case Some(page) => page.waitForLoad()
    }
  }

  With("""^I should see a reason of "(.+)"$""") { reason:String =>
    val doc = CurrentPage.doc

    doc.elementsByClass("reason").map(_.text) should contain(reason)
  }

}
