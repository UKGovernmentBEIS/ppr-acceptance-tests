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

package stepdefs.report

import cucumber.api.DataTable
import cucumber.api.scala._
import driver.{FixedDelay, StartUpTearDown}
import org.openqa.selenium.By
import org.scalatest.Matchers
import pages.CurrentPage
import pages.report._
import stepdefs.DocValues

import scala.collection.JavaConversions._
import scala.util.Try

class ReportSteps extends ScalaDsl with EN with Matchers with StartUpTearDown with DocValues {
  var fileReportPage: Option[FileReport] = None

  val companyMap = Map(
    "The Testing Company" -> "000000001"
  )

  When("^I start to prepare a report for (.+)$") { companyName: String =>
    val companyId = companyMap(companyName)

    val start = new ReportStart(companyName, companyId)
    start.open()
    start.clickOn(By.id("start-button"))

    val preLogin = new PreLogin(companyId)
    preLogin.waitForLoad()
    preLogin.clickOn(By.id("account-yes"))
    preLogin.clickOn(By.id("continue-button"))

    val login = new MockCompaniesHouseLogin(companyId)
    login.waitForLoad()
    login.clickOn(By.id("submit"))

    val code = new MockCompaniesHouseCode(companyId)
    code.waitForLoad()
    code.clickOn(By.id("submit"))

    fileReportPage = Some(new FileReport(companyName, companyId))
    fileReportPage.value.waitForLoad()
  }

  Then("""^the error for (.+) should be '(.+)'$""") { (fieldName: String, errorText: String) =>
    val errorSpanId = s"error-$fieldName"
    CurrentPage.IdQuery(errorSpanId).findElement(CurrentPage.webDriver).value.text shouldBe errorText
  }

  Then("""^the js validation should give these errors for the numeric fields:""") {
    values: DataTable =>
      val dataRows: List[List[String]] = values.asLists(classOf[String]).toList.map(_.toList)
      implicit val driver = CurrentPage.webDriver

      dataRows.foreach {
        case fieldName :: fieldValue :: expectedErrorText :: Nil =>
          fileReportPage.value.open()

          val errorSpanId = s"error-$fieldName"

          // There's a flaky issue where sometimes the error doesn't trigger. I think it's because the test is
          // entering the data in the field and pressing tab before the javascript has run to set up the validations
          // on the field. This hack of a small delay might mitigate that, but it would be better to have some
          // positive indication that the js has finished. Maybe by some js that runs after the validation wiring
          // that sets the content of an invisible element?
          FixedDelay(100)
          CurrentPage.clickOn(By.name(fieldName))
          CurrentPage.numberField(fieldName).value = fieldValue
          CurrentPage.pressKeys("\t")

          def condition(): Boolean = {
            CurrentPage.IdQuery(errorSpanId).findElement(CurrentPage.webDriver).map(_.text.trim).contains(expectedErrorText)
          }

          Try {
            fileReportPage.value.waitForPageToBeLoaded(condition(), s"Timed out waiting for expected message in $fieldName", 5)
          }

          withClue(s"$fieldName with value $fieldValue") {
            val actualErrorText = CurrentPage.IdQuery(errorSpanId).findElement(CurrentPage.webDriver).value.text.trim
            actualErrorText shouldBe expectedErrorText
          }

        case row =>
          fail("Data row is malformed: $row")
      }
  }

  Then("""^I should see the following errors for the fields:""") { values: DataTable =>
    val dataRows: List[List[String]] = values.asLists(classOf[String]).toList.map(_.toList)
    val doc = CurrentPage.doc

    dataRows.foreach {
      case fieldName :: expectedErrorText :: Nil =>
        val errorSpanId = s"error-$fieldName"

        withClue(s"Expected error for $fieldName is $expectedErrorText") {
          val actualErrorText = Option(doc.getElementById(errorSpanId)).value.text.trim
          actualErrorText shouldBe expectedErrorText
        }

      case row =>
        fail("Data row is malformed: $row")
    }
  }

  Then("""^there should be no error for these fields:$""") { values: DataTable =>
    val dataRows: List[List[String]] = values.asLists(classOf[String]).toList.map(_.toList)
    val doc = CurrentPage.doc

    dataRows.foreach {
      case fieldName :: Nil =>
        val errorSpanId = s"error-$fieldName"

        withClue(s"Expected no error for $fieldName") {
          val actualErrorText = Option(doc.getElementById(errorSpanId)).value.text.trim
          actualErrorText shouldBe ""
        }


      case row =>
        fail("Data row is malformed: $row")
    }

  }
}
