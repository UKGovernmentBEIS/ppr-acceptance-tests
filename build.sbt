name := "ppr-acceptance-tests"

organization in ThisBuild := "uk.gov.beis"

scalaVersion := "2.11.8"

git.useGitDescribe in ThisBuild := true

scalacOptions in ThisBuild ++= Seq("-deprecation", "-feature", "-language:reflectiveCalls", "-language:postfixOps", "-Xfatal-warnings")

parallelExecution in Test := false

enablePlugins(GitVersioning)
enablePlugins(GitBranchPrompt)

val seleniumVersion = "2.49.0"
val cucumberVersion = "1.2.5"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.7",
  "org.joda" % "joda-convert" % "1.8.1",
  "com.github.nscala-time" %% "nscala-time" % "2.16.0",
  "com.codeborne" % "phantomjsdriver" % "1.2.1",
  "org.seleniumhq.selenium" % "selenium-java" % seleniumVersion,
  "org.seleniumhq.selenium" % "selenium-firefox-driver" % seleniumVersion,
  "org.seleniumhq.selenium" % "selenium-remote-driver" % seleniumVersion,
  "org.scalatest" %% "scalatest" % "3.0.1",
  "org.jsoup" % "jsoup" % "1.10.2",
  "info.cukes" %% "cucumber-scala" % cucumberVersion,
  "info.cukes" % "cucumber-junit" % cucumberVersion,
  "info.cukes" % "cucumber-picocontainer" % cucumberVersion,
  "com.beachape" %% "enumeratum" % "1.5.2",

  "junit" % "junit" % "4.11" % Test,
  "com.novocode" % "junit-interface" % "0.10" % Test)

