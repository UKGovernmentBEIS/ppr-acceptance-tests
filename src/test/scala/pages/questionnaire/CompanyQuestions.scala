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

package pages.questionnaire

import pages.{Page, PageLoading}

object CompanyTurnoverQuestionY2 extends Page with PageLoading {
  override def pageName: String = "Company Turnover Question year 2"

  override def pageURL: String = "/check-if-you-need-to-report/questions"

  override def titleString: String = "Did your business have a turnover of more than £36 million on its last balance sheet date?"
}

object CompanyBalanceSheetQuestionY2 extends Page with PageLoading {
  override def pageName: String = "Company Balance Sheet Question year 2"

  override def pageURL: String = "/check-if-you-need-to-report/questions"

  override def titleString: String = "Did your business have a balance sheet total greater than £18 million on its last balance sheet date?"
}

object CompanyEmployeeQuestionY2 extends Page with PageLoading {
  override def pageName: String = "Company Employee Question year 2"

  override def pageURL: String = "/check-if-you-need-to-report/questions"

  override def titleString: String = "Did your business have an average of at least 250 employees during its last financial year?"
}

object CompanyTurnoverQuestionY3 extends Page with PageLoading {
  override def pageName: String = "Company Turnover Question year 3"

  override def pageURL: String = "/check-if-you-need-to-report/questions"

  override def titleString: String = "Did your business have a turnover of more than £36 million on its last 2 balance sheet dates?"
}

object CompanyBalanceSheetQuestionY3 extends Page with PageLoading {
  override def pageName: String = "Company Balance Sheet Question year 3"

  override def pageURL: String = "/check-if-you-need-to-report/questions"

  override def titleString: String = "Did your business have a balance sheet total greater than £18 million at its last 2 balance sheet dates?"
}

object CompanyEmployeeQuestionY3 extends Page with PageLoading {
  override def pageName: String = "Company Employee Question year 3"

  override def pageURL: String = "/check-if-you-need-to-report/questions"

  override def titleString: String = "Did your business have an average of at least 250 employees during its last 2 financial years?"
}

object SubsidiariesQuestion extends Page with PageLoading {
  override def pageName: String = "Subsidiaries Question"

  override def pageURL: String = "/check-if-you-need-to-report/questions"

  override def titleString: String = "Does your business have subsidiaries?"
}
