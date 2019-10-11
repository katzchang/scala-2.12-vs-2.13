lazy val scala212 = "2.12.10"
lazy val scala213 = "2.13.1"
lazy val supportedScalaVersions = List(scala212, scala213)

ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := scala212
ThisBuild / version := "0.1.0-SNAPSHOT"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
crossScalaVersions := supportedScalaVersions
