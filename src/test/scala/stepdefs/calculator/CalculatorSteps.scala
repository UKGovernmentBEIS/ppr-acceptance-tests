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

package stepdefs.calculator

import cucumber.api.scala.{EN, ScalaDsl}
import driver.StartUpTearDown
import org.scalatest.{Matchers, OptionValues}
import pages.CurrentPage

class CalculatorSteps extends ScalaDsl with EN with Matchers with StartUpTearDown with OptionValues {
  implicit val driver = CurrentPage.webDriver

  Then("""^I should see ([0-9]+) calculated periods$""") { count: Int =>
    (1 to count).foreach { i =>
      CurrentPage.IdQuery(s"period-start-$i").findElement shouldBe a[Some[_]]
      CurrentPage.IdQuery(s"period-end-$i").findElement shouldBe a[Some[_]]
      CurrentPage.IdQuery(s"deadline-$i").findElement shouldBe a[Some[_]]
    }

    CurrentPage.IdQuery(s"period-start-${count + 1}").findElement shouldBe None
    CurrentPage.IdQuery(s"period-end-${count + 1}").findElement shouldBe None
    CurrentPage.IdQuery(s"deadline-${count + 1}").findElement shouldBe None
  }

  Then("""^Period ([0-9]+) should run from (.+) to (.+) with deadline (.+)$""") {
    (i: Int, start: String, end: String, deadline: String) =>
      CurrentPage.IdQuery(s"period-start-$i").webElement.getText shouldBe start
      CurrentPage.IdQuery(s"period-end-$i").webElement.getText shouldBe end
      CurrentPage.IdQuery(s"deadline-$i").webElement.getText shouldBe deadline
  }


}
