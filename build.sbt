import Dependencies._

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

inThisBuild(
  List(
    scalaVersion := "2.13.8",
    organization := "com.worekleszczy",
    organizationName := "worekleszczy",
    scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.5.0",
    scalafixScalaBinaryVersion := "2.13",
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalacOptions += "-Wconf:cat=unused:info",
    scalacOptions ++= Seq(
      "-Wconf:cat=unused:info",
      "-encoding",
      "utf8",
      "-feature",
      "-unchecked",
      "-language:existentials",
      "-language:experimental.macros",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-Xcheckinit",
      "-Xlint:adapted-args",
      "-Xlint:constant",
      "-Xlint:delayedinit-select",
      "-Xlint:deprecation",
      "-Xlint:doc-detached",
      "-Xlint:implicit-recursion",
      "-Xlint:implicit-not-found",
      "-Xlint:inaccessible",
      "-Xlint:infer-any",
      "-Xlint:missing-interpolator",
      "-Xlint:nullary-unit",
      "-Xlint:option-implicit",
      "-Xlint:package-object-classes",
      "-Xlint:poly-implicit-overload",
      "-Xlint:private-shadow",
      "-Xlint:stars-align",
      "-Xlint:strict-unsealed-patmat",
      "-Xlint:type-parameter-shadow",
      "-Xlint:-byname-implicit",
      "-Wdead-code",
      "-Wextra-implicit",
      "-Wnumeric-widen",
      "-Wvalue-discard",
      "-Wunused:nowarn",
      "-Wunused:implicits",
      "-Wunused:explicits",
      "-Wunused:imports",
      "-Wunused:locals",
      "-Wunused:params",
      "-Wunused:patvars",
      "-Wunused:privates",
      "-Werror"
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaCheck, "-s", "4"),
    libraryDependencies ++= Seq(
      compilerPlugin("com.olegpy"   %% "better-monadic-for" % "0.3.1"),
      compilerPlugin("org.typelevel" % "kind-projector"     % "0.13.2" cross CrossVersion.full)
    )
  )
)

lazy val root = (project in file("."))
  .aggregate(`day-1`, `day-2`, utils)

lazy val `day-1` = (project in file("day-1"))
  .settings(
    libraryDependencies ++= munit ++ cats ++ fs2
  )

lazy val `day-2` = (project in file("day-2"))
  .settings(
    libraryDependencies ++= munit ++ cats ++ fs2
  )

lazy val `day-3` = (project in file("day-3"))
  .settings(
    libraryDependencies ++= munit ++ cats ++ fs2
  ).dependsOn(utils)

lazy val utils = (project in file("utils"))
  .settings(
    libraryDependencies ++= munit ++ cats ++ fs2
  )
