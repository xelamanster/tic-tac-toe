name := "TDD"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.7.2" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")

coverageEnabled := true