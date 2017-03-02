package driver

import org.openqa.selenium.WebDriver

trait StartUpTearDown {

  val webDriver: WebDriver = Driver.webDriver

}
