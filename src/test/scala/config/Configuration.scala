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

case class Configuration(ROOT: String, PAGE_TIMEOUT_SECS: Int)

object Configuration {

  lazy val environment: Environment.Name = {
    val environmentProperty = System.getProperty("environment", "dev").toLowerCase
    environmentProperty match {
      case "dev" => Environment.Dev
      case "local" => Environment.Local
      case "acceptance" => Environment.Acceptance
      case _ => throw new IllegalArgumentException(s"Environment '$environmentProperty' not known")
    }
  }

  lazy val settings: Configuration = create()

  private def create(): Configuration = {
    environment match {
      // dev - running in sbt
      case Environment.Dev =>
        new Configuration(
          ROOT = "http://localhost:9000",
          PAGE_TIMEOUT_SECS = 10
        )

      case Environment.Local =>
        new Configuration(
          ROOT = ???,
          PAGE_TIMEOUT_SECS = 10
        )

      case Environment.Acceptance =>
        new Configuration(
          ROOT = ???,
          PAGE_TIMEOUT_SECS = 10
        )

      case _ => throw new IllegalArgumentException(s"Environment '$environment' not known")
    }
  }
}

object Environment extends Enumeration {
  type Name = Value
  val Dev, Local, Acceptance = Value
}


