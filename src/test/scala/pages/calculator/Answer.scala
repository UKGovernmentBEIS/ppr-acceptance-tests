package pages.calculator

import pages.{Page, PageLoading}

object Answer extends Page with PageLoading {
  override def pageName: String = "Answer"

  override def pageURL: String = "/calculate-reporting-deadlines/deadlines"

  override def titleString: String = "Reporting periods and deadlines"
}
