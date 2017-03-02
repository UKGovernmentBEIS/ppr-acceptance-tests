package driver

import java.util.concurrent.TimeUnit

import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.{Dimension, WebDriver}
import org.scalatest.selenium.{Chrome, Firefox, HtmlUnit}

import scala.util.Try

object Driver extends Driver

class Driver {

  val systemProperties = System.getProperties

  val implicitWait = 200

  private def withWaitTime[T](time: Long)(body: => T) = {
    webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS)
    val t: T = body
    webDriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.MILLISECONDS)
    t
  }

  def withoutDelay[T](body: => T) = withWaitTime(0)(body)

  val webDriver: WebDriver = {

    var selectedDriver: WebDriver = null

    sys addShutdownHook {
      Try(webDriver.quit())
    }

    if (!StringUtils.isEmpty(systemProperties.getProperty("browser"))) {
      val targetBrowser = systemProperties.getProperty("browser")
      if (targetBrowser.equalsIgnoreCase("firefox")) {
        selectedDriver = Firefox.webDriver
        selectedDriver.manage().window().maximize()
      } else if (targetBrowser.equalsIgnoreCase("phone_mock")) {
        val d = new Dimension(320, 480)
        selectedDriver = Firefox.webDriver
        selectedDriver.manage().window().setSize(d)
      } else if (targetBrowser.equalsIgnoreCase("tablet_mock")) {
        val d = new Dimension(768, 1024)
        selectedDriver = Firefox.webDriver
        selectedDriver.manage().window().setSize(d)
      } else if (targetBrowser.equalsIgnoreCase("htmlunit")) {
        selectedDriver = HtmlUnit.webDriver
      } else if (targetBrowser.equalsIgnoreCase("chrome")) {
        val cap = new DesiredCapabilities()
        cap.setJavascriptEnabled(true)
        selectedDriver.manage().window().maximize()
        selectedDriver = Chrome.webDriver
      } else if (targetBrowser.equalsIgnoreCase("phantomjs")) {
        val cap = new DesiredCapabilities()
        cap.setJavascriptEnabled(true)
        selectedDriver = PhantomJSDriverObject(cap)
        selectedDriver.manage().window().maximize()
      }
    }

    if (selectedDriver == null) {
      Firefox.firefoxProfile.setAcceptUntrustedCertificates(true)
      selectedDriver = Firefox.webDriver
    }

    selectedDriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.MILLISECONDS)
    selectedDriver
  }
}
