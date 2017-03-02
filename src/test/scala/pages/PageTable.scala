package pages

import pages.calculator.Calculator

object PageTable {

  val pages =
    Seq(Calculator)

  def lookupPage(pageName: String): Option[Page with PageLoading] = pages.find(_.pageName == pageName)

}
