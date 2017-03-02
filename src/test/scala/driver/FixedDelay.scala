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

package driver

object FixedDelay {

  import scala.concurrent.duration._

  def apply(duration: Long) {
    val delayFor = Duration(duration, MILLISECONDS)
    val deadline = delayFor.fromNow
    while (deadline.hasTimeLeft()) { /* just wasting some cycles for a fixed period */ }
  }
}
