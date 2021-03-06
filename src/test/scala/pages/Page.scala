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

package pages

import java.text.SimpleDateFormat
import java.util.{Calendar, TimeZone}

import config.{Configuration, Environment}
import driver.exceptions.{Server500ResponseException, Server502ResponseException}
import driver.{Driver, FixedDelay}
import org.joda.time.LocalDate
import org.jsoup.nodes.Document
import org.jsoup.parser.Parser
import org.openqa.selenium.{By, NoSuchElementException, WebDriver}
import org.scalatest.concurrent.Eventually
import org.scalatest.selenium

trait PageLoading {
  self: Page =>

  def isOnPage: Boolean = getPageTitle.contains(titleString)

  def navigateToPage(url: String): Unit = {
    go to url
  }

  def pageName: String

  def titleString: String

  def isAdmin: Boolean = true

  def isFiling: Boolean = !isAdmin

  def urlRoot = Configuration.settings.ROOT

  def open(): Unit = {
    navigateToPage(s"$urlRoot$pageURL")
    waitForLoad()
  }

  def waitForLoad(): Unit = waitForPageToBeLoaded(isOnPage, s"$pageName page failed to load")

  def waitForPageToBeLoaded(condition: => Boolean, exceptionMessage: String, timeoutInSeconds: Int = Configuration.settings.PAGE_TIMEOUT_SECS): Unit = {
    val endTime = System.currentTimeMillis + timeoutInSeconds * 1000
    while (System.currentTimeMillis < endTime) {
      try {
        if (condition) {
          return
        }
      } catch {
        case _: RuntimeException =>
        // ignore exceptions during the timeout period because the condition
        // is throwing exceptions and we DO want to try the condition again until the timeout expires
      }
      // The following is to avoid to wait until timeout in case of well known errors
      val source: String = webDriver.getPageSource
      // 502
      if (source.contains("502 Bad Gateway")) {
        throw new Server502ResponseException
      }
      // GENERIC PLAY ERROR
      if (source.contains("play-error-page")) {
        throw new Server500ResponseException("ERROR OCCURRED \n" + webDriver.findElement(By.cssSelector("#detail")).getText)
      }
      //IDA LOGIN UNEXPECTED ERROR
      if (source.contains("An unexpected problem occurred during authentication.")) {
        throw new Server500ResponseException("IDA Login Error")
      }
      FixedDelay(100)
    }
    throw new HmrcPageWaitException(exceptionMessage + "\nThe current page was:\n" + webDriver.getPageSource)
  }

  class HmrcPageWaitException(exceptionMessage: String) extends Exception(exceptionMessage)

}

// A handy hook for steps that are valid on any page
object CurrentPage extends Page {
  def hasErrors: Boolean = elementDisplayed(By.className("error-message"))

  def hasAlert: Boolean = elementDisplayed(By.className("alert"))

  def setDateField(name: String, d: LocalDate) = {
    numberField(s"$name.day").value = d.getDayOfMonth.toString
    numberField(s"$name.month").value = d.getMonthOfYear.toString
    numberField(s"$name.year").value = d.getYear.toString
  }

  override val pageURL = ""
}

trait Page extends selenium.WebBrowser with Eventually {

  implicit val webDriver: WebDriver = Driver.webDriver

  val localCalendar = Calendar.getInstance(TimeZone.getDefault)
  val currentYear = localCalendar.get(Calendar.YEAR)
  val previousYear = currentYear - 1
  val today = Calendar.getInstance().getTime
  val yearFormat = new SimpleDateFormat("yy")
  val currentYearAsTwoDigitString = yearFormat.format(today)
  val environmentProperty = System.getProperty("environment", "local")
  val backToOnlineServicesLink = By.linkText("Back to HMRC Online Services")

  def isLocal = Configuration.environment.equals(Environment.Dev)

  def clearSession(): Unit = webDriver.manage().deleteAllCookies()

  def elementDisplayed(by: By): Boolean = {
    try {
      webDriver.findElement(by)
      true
    } catch {
      case e: NoSuchElementException => false
    }
  }

  def clickOn(selector: By) = webDriver.findElement(selector).click()

  def textFromElement(selector: By) = webDriver.findElement(selector).getText

  def pageText = webDriver.getPageSource

  def pageURL: String

  def getPageSource = webDriver.getPageSource

  def getPageHeadingTitle = webDriver.findElement(By.tagName("h1")).getText

  def getPageTitle = webDriver.getTitle

  def getCurrentUrlInBrowser: String = webDriver.getCurrentUrl

  def doc: Document = Parser.parse(pageText, pageURL)
}
