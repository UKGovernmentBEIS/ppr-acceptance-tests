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
import org.openqa.selenium.By
import org.scalatest.Matchers
import pages.{CurrentPage, PageTable}

class PageSteps extends ScalaDsl with EN with Matchers with StartUpTearDown {

  Given("""^I navigate to the (.+) page$""") { pageName: String =>
    PageTable.lookupPage(pageName) match {
      case None => fail(s"could not find a page with name $pageName")
      case Some(page) => page.open()
    }
  }

  Then("""^I should see the (.+) page with (.+)$""") { (pageName: String, withError: String) =>
    PageTable.lookupPage(pageName) match {
      case None => fail(s"could not find a page with name $pageName (did you forget to add the page object to the PageTable?)")
      case Some(page) =>
        withError match {
          case "an error" | "errors" => CurrentPage.hasErrors shouldBe true
          case "a success message" => CurrentPage.elementDisplayed(By.className("success")) shouldBe true
          case "no errors" => CurrentPage.hasErrors shouldBe false
          case "no alert" => CurrentPage.hasAlert shouldBe false
          case _ => // no test
        }
    }
  }

  Then("""^I should see the (.+) page$""") { pageName: String =>
    PageTable.lookupPage(pageName) match {
      case None => fail(s"could not find a page with name $pageName (did you forget to add the page object to the PageTable?)")
      case Some(page) => page.waitForLoad()
    }
  }

}
