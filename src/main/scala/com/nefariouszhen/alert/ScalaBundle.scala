package com.nefariouszhen.alert

import com.fasterxml.jackson.module.scala.DefaultScalaModule
import io.dropwizard.Bundle
import io.dropwizard.setup.{Environment, Bootstrap}

class ScalaBundle extends Bundle {
  def initialize(bootstrap: Bootstrap[_]): Unit = {
    bootstrap.getObjectMapper.registerModule(DefaultScalaModule)
  }

  def run(environment: Environment): Unit = {
    // Do Nothing
  }
}
