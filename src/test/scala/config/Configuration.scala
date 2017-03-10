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

package config

import enumeratum.EnumEntry.Lowercase
import enumeratum._

case class Configuration(ROOT: String, PAGE_TIMEOUT_SECS: Int)

object Configuration {

  lazy val environment: Environment = {
    val environmentProperty = System.getProperty("environment", "local").toLowerCase
    Environment.withNameOption(environmentProperty).getOrElse(throw new IllegalArgumentException(s"Environment '$environmentProperty' not known"))
  }

  lazy val settings: Configuration = create()

  private def create(): Configuration = {
    environment match {
      // dev - running in sbt on local machine
      case Environment.Local =>
        new Configuration(
          ROOT = "http://localhost:9000",
          PAGE_TIMEOUT_SECS = 10
        )

      case Environment.Dev =>
        new Configuration(
          ROOT = "https://beis-ppr-dev.herokuapp.com",
          PAGE_TIMEOUT_SECS = 10
        )

      case Environment.UR =>
        new Configuration(
          ROOT = "https://beis-ppr-ur.herokuapp.com",
          PAGE_TIMEOUT_SECS = 10
        )

      case Environment.Staging =>
        new Configuration(
          ROOT = "https://beis-ppr-staging.herokuapp.com",
          PAGE_TIMEOUT_SECS = 10
        )

      case _ => throw new IllegalArgumentException(s"Environment '$environment' not known")
    }
  }
}

sealed trait Environment extends EnumEntry with Lowercase

object Environment extends Enum[Environment] {
  override def values = findValues

  case object Local extends Environment

  case object Dev extends Environment

  case object UR extends Environment

  case object Staging extends Environment

}


