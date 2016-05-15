name := "TDD"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "1.1.0"
libraryDependencies += "org.mockito" % "mockito-all" % "1.10.19"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.7.2" % "test")
libraryDependencies += "org.specs2" % "specs2-mock_2.11" % "3.7.3-20160413201225-0d1765e"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.4"

scalacOptions in Test ++= Seq("-Yrangepos")

coverageEnabled := true