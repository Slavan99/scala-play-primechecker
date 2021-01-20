lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-scala-starter-example""",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      "mysql" % "mysql-connector-java" % "5.1.12",
      "com.typesafe.slick" %% "slick-codegen" % "3.3.2",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "org.mockito" % "mockito-core" % "2.22.0"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
