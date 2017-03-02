package pages.calculator

import pages.{Page, PageLoading}

object Calculator extends Page with PageLoading {
  override def pageName: String = "Calculator"

  override def pageURL: String = "/calculate-reporting-deadlines"

  override def titleString: String = "Calculate reporting periods and deadlines"
}
