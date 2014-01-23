import android.Keys._

android.Plugin.androidBuild

name := "DateCalculator"

scalaVersion := "2.10.3"

proguardCache in Android ++= Seq(
    ProguardCache("org.scaloid") % "org.scaloid"
      )

proguardOptions in Android ++= Seq("-dontobfuscate", "-dontoptimize")

resolvers += "Local android extras" at "file:///" + System.getenv("ANDROID_HOME") + "/extras/android/m2repository"

libraryDependencies ++= Seq(
	"org.scaloid" %% "scaloid" % "3.1-8-RC1",
	"com.android.support" % "support-v13" % "19.0.1"
	)

scalacOptions in Compile += "-feature"

run <<= run in Android

install <<= install in Android
