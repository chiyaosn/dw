import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "dw"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "com.fasterxml.jackson.core" % "jackson-core" % "2.1.4",
    "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.4",
    "com.fasterxml.jackson.module" % "jackson-module-scala_2.9.2" % "2.1.3",
    "com.servicenow.bigdata" % "meta-data" % "1.0-SNAPSHOT"  classifier "assembly"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    resolvers += "snc-lab" at "http://10.196.32.21:8081/nexus/content/groups/public/"       
  )

}
