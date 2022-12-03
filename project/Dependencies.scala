import sbt._

object Dependencies {

  object v {
    val scalacheckEffect = "1.0.4"
    val catsEffectMunit  = "2.0.0-M3"
    val munit            = "1.0.0-M6"
    val cats             = "2.7.0"
    val fs2 = "3.4.0"
  }

  val munit = Seq(
    "org.scalameta" %% "munit"                   % v.munit            % Test,
    "org.typelevel" %% "munit-cats-effect"       % v.catsEffectMunit  % Test,
    "org.typelevel" %% "scalacheck-effect-munit" % v.scalacheckEffect % Test
  )

  val cats = Seq(
    "org.typelevel" %% "cats-laws"              % v.cats,
    "org.typelevel" %% "cats-core"              % v.cats,
    "org.typelevel" %% "cats-free"              % v.cats,
    "org.typelevel" %% "cats-testkit-scalatest" % "2.1.5" % Test
  )

  val fs2 = Seq(
    "co.fs2" %% "fs2-core"   % v.fs2,
    "co.fs2" %% "fs2-io"     % v.fs2,
    "co.fs2" %% "fs2-scodec" % v.fs2
  )

}
