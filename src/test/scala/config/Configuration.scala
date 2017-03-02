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


