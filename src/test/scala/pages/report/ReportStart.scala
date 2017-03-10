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

class ReportStart(companyName: String, companyId: String) extends Page with PageLoading {
  override def pageName: String = "Start reporting"

  override def pageURL: String = s"/report-payment-practices/company/$companyId/start"

  override def titleString: String = s"Publish a report for $companyName"
}

class FileReport(companyName: String, companyId: String) extends Page with PageLoading {
  override def pageName: String = "File a report"

  override def pageURL: String = s"/report-payment-practices/company/$companyId/file"

  override def titleString: String = s"Publish a report for $companyName"
}
