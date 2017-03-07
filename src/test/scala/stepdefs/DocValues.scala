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

package stepdefs

import org.jsoup.nodes.{Document, Element}
import org.scalatest.{Matchers, OptionValues}

import scala.collection.JavaConversions._

trait DocValues extends Matchers with OptionValues {

  /**
    * Turns out it is *way* faster to grab the text of the page, parse it with jsoup and
    * find elements in the resulting Document than it is to look up elements with the
    * selenium driver. This is particularly true when checking that an element is *not*
    * present on the page, as the selenium driver will wait for 1 second before deciding
    * it can't find the element.
    *
    * This implicit class adds some convenient syntax around the Document.
    */
  implicit class DocSyntax(doc: Document) {
    def elementById(id: String) = Option(doc.getElementById(id))

    def elementsByClass(className: String): List[Element] = doc.getElementsByClass(className).toList

    def textOf(id: String) = elementById(id).value.text

    def shouldHaveElementWithId(id: String) = elementById(id).isDefined shouldBe true

    def shouldNotHaveElementWithId(id: String) = elementById(id).isDefined shouldBe false
  }

}
