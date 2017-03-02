import org.openqa.selenium.{By, WebDriver, WebElement}

package object stepdefs {
  implicit class ElementOps(e: WebElement) {

    import collection.JavaConversions._

    def hasChild(by: By, cond: WebElement => Boolean): Boolean = e.findElements(by).exists(cond)

    def findChildren(by: By): List[WebElement] = e.findElements(by).toList

    def findChildren(by: By, cond: WebElement => Boolean): List[WebElement] = findChildren(by).filter(cond)
  }

  implicit class WebDriverOps(wd: WebDriver) {

    import collection.JavaConversions._

    def findChildren(by: By): List[WebElement] = wd.findElements(by).toList
  }
}
