package com.nefariouszhen.alert

import io.dropwizard.Configuration
import io.dropwizard.Application

abstract class ScalaApplication[T <: Configuration] extends Application[T] {
  final def main(args: Array[String]) {
    run(args: _*)
  }
}
