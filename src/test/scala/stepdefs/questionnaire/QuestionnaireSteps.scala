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
  val reasonTable: Map[String, String] = Map(
    "company.turnover.y2" -> "had a turnover of more than £36 million on its last balance sheet date",
    "company.balance.y2" -> "had more than £18 million balance sheet total on its last balance sheet date",
    "company.employees.y2" -> "had an average of more than 250 employees during its last financial year",
    "group.turnover.y2" -> "had a total turnover of at least £36 million net or £43.2 million gross on their last balance sheet date",
    "group.balance.y2" -> "had a combined balance sheet total of £18 million net or £21.6 million gross on their last balance sheet date",
    "group.employees.y2" -> "had an average of more than 250 employees during their last financial year",

    "company.turnover.y3" -> "had a turnover of more than £36 million on its last 2 balance sheet dates",
    "company.balance.y3" -> "had more than £18 million balance sheet total on its last 2 balance sheet dates",
    "company.employees.y3" -> "had an average of more than 250 employees during both of its last 2 financial years",
    "group.turnover.y3" -> "had a total turnover of at least £36 million net or £43.2 million gross on both of their last 2 balance sheet dates",
    "group.balance.y3" -> "had a combined balance sheet total of £18 million net or £21.6 million gross on both of their last 2 balance sheet dates",
    "group.employees.y3" -> "had an average of more than 250 employees during both of their last 2 financial years"
  )

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

  Then("""^I should see a reason of "(.+)"$""") { reason: String =>
    val doc = CurrentPage.doc

    doc.elementsByClass("reason").map(_.text) should contain(reason)
  }

  Then("""^I should see the reasons (.+)$""") { reasons: String =>
    val doc = CurrentPage.doc

    val expectedReasons = reasons.split("\\s+").map(reasonTable(_)).toSet
    val actualReasons = doc.elementsByClass("reason").map(_.text).toSet

    actualReasons shouldBe expectedReasons
  }

  Given("""^I give the answers (.+)$""") { answers: String =>
    answers.split("\\s+").foreach { yesNo =>
      CurrentPage.clickOn(By.id(yesNo.toLowerCase))
      CurrentPage.clickOn(By.name("Continue"))
    }
  }

}
