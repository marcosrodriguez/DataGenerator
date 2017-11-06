import sbt.Path

name := "DataGenerator"

version := "0.1"

scalaVersion := "2.12.4"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

libraryDependencies += "joda-time" % "joda-time" % "2.9.9"
        