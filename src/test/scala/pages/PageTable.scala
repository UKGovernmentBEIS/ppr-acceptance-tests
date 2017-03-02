package pages

import pages.calculator.{Answer, Calculator}

object PageTable {

  val pages = Seq(Calculator, Answer)

  def lookupPage(pageName: String): Option[Page with PageLoading] = pages.find(_.pageName == pageName)

}
