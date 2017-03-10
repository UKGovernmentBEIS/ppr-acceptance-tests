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
import driver.StartUpTearDown
import org.openqa.selenium.{By, Keys}
import org.scalatest.Matchers
import pages.CurrentPage
import pages.report._
import stepdefs.DocValues

import scala.collection.JavaConversions._

class ReportSteps extends ScalaDsl with EN with Matchers with StartUpTearDown with DocValues {

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

    val file = new FileReport(companyName, companyId)
    file.waitForLoad()
  }

  Then("""^the error for (.+) should be '(.+)'$""") { (fieldName: String, errorText: String) =>
    val errorSpanId = s"error-$fieldName"
    CurrentPage.IdQuery(errorSpanId).findElement(CurrentPage.webDriver).value.text shouldBe errorText
  }

  Then("""^the js validation should give these errors for the numeric fields:""") { values: DataTable =>
    val dataRows: List[List[String]] = values.asLists(classOf[String]).toList.map(_.toList)
    implicit val driver = CurrentPage.webDriver

    dataRows.foreach { row =>
      val fieldName = row(0)
      val value = row(1)
      val expectedErrorText = row(2)
      val errorSpanId = s"error-$fieldName"

      CurrentPage.clickOn(By.name(fieldName))
      CurrentPage.numberField(fieldName).value = value
      CurrentPage.pressKeys("\t")
      CurrentPage.IdQuery(errorSpanId).findElement(CurrentPage.webDriver).value.text.trim shouldBe expectedErrorText
    }
  }

}
