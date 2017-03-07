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

package stepdefs.questionnaire

import cucumber.api.scala.{EN, ScalaDsl}
import driver.StartUpTearDown
import org.scalatest.Matchers
import pages.CurrentPage
import stepdefs.DocValues

class QuestionnaireSteps extends ScalaDsl with EN with Matchers with StartUpTearDown with DocValues {

  Then("""^the reason should be "(.+)"$""") { reason: String =>
    CurrentPage.doc.elementById("reason").value.text shouldBe reason
  }

}
