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
