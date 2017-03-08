name := "ppr-acceptance-tests"

organization in ThisBuild := "uk.gov.beis"

scalaVersion := "2.11.8"

git.useGitDescribe in ThisBuild := true

scalacOptions in ThisBuild ++= Seq("-deprecation", "-feature", "-language:reflectiveCalls", "-language:postfixOps", "-Xfatal-warnings")

parallelExecution in Test := false

enablePlugins(GitVersioning)
enablePlugins(GitBranchPrompt)

val seleniumVersion = "2.47.1"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.7",
  "org.joda" % "joda-convert" % "1.8.1",
  "com.github.nscala-time" %% "nscala-time" % "2.16.0",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "org.postgresql" % "postgresql" % "9.4.1207.jre6",
  "com.codeborne" % "phantomjsdriver" % "1.2.1",
  "org.seleniumhq.selenium" % "selenium-java" % seleniumVersion,
  "org.seleniumhq.selenium" % "selenium-firefox-driver" % seleniumVersion,
  "org.scalatest" %% "scalatest" % "2.2.1",
  //"org.pegdown" % "pegdown" % "1.1.0" % "test",
  "org.jsoup" % "jsoup" % "1.10.2",
  "info.cukes" %% "cucumber-scala" % "1.1.8",
  "info.cukes" % "cucumber-junit" % "1.1.8",
  "info.cukes" % "cucumber-picocontainer" % "1.1.8",
  "com.beachape" %% "enumeratum" % "1.5.2",

  "junit" % "junit" % "4.11" % Test,
  "com.novocode" % "junit-interface" % "0.10" % Test)

