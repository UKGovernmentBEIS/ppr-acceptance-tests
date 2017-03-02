package driver

import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities

object PhantomJSDriverObject {
  def apply(capabilities: DesiredCapabilities) = new PhantomJSDriver(capabilities)
}
