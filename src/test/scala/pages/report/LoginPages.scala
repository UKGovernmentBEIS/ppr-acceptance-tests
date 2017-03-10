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

package pages.report

import pages.{Page, PageLoading}

class PreLogin(companyId: String) extends Page with PageLoading {
  override def pageName: String = "Pre-login"

  override def pageURL: String = s"/report-payment-practices/company/$companyId/pre-login"

  override def titleString: String = s"Sign in using your Companies House account"
}

class MockCompaniesHouseLogin(companyId: String) extends Page with PageLoading {
  override def pageName: String = "Mock CoHo Login"

  override def pageURL: String = s"/report-payment-practices/coho/login/$companyId"

  override def titleString: String = s"Sign in to Companies House"
}

class MockCompaniesHouseCode(companyId: String) extends Page with PageLoading {
  override def pageName: String = "Mock CoHo Code"

  override def pageURL: String = s"/report-payment-practices/coho/code/$companyId"

  override def titleString: String = s"Company authentication"
}
