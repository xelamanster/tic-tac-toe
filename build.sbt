name := "TDD"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "junit" % "junit" % "4.12"

coverageEnabled := true