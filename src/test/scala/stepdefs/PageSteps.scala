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
        page.waitForLoad()
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
