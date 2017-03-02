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
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "org.postgresql" % "postgresql" % "9.4.1207.jre6",
  "com.codeborne" % "phantomjsdriver" % "1.2.1",
  "org.seleniumhq.selenium" % "selenium-java" % seleniumVersion,
  "org.seleniumhq.selenium" % "selenium-firefox-driver" % seleniumVersion,
  "org.scalatest" %% "scalatest" % "2.2.1",
  "org.pegdown" % "pegdown" % "1.1.0" % "test",
  "info.cukes" %% "cucumber-scala" % "1.1.8",
  "info.cukes" % "cucumber-junit" % "1.1.8",
  "info.cukes" % "cucumber-picocontainer" % "1.1.8",
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.10" % "test")

