package com.nefariouszhen.alert

import javax.net.ssl.{HostnameVerifier, SSLSession}

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.setup.{Bootstrap, Environment}
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jxmpp.jid.impl.JidCreate

object NoopHostnameVerifier extends HostnameVerifier {
  override def verify(s: String, sslSession: SSLSession): Boolean = true
}

class TalkConfiguration {
  @JsonProperty
  var host: String = ""

  @JsonProperty
  var serviceName: String = ""

  @JsonProperty
  var port: Int = 0

  @JsonProperty
  var user: String = ""

  @JsonProperty
  var password: String = ""

  def config: XMPPTCPConnectionConfiguration = {
    XMPPTCPConnectionConfiguration.builder()
      .setHost(host)
      .setPort(port)
      .setServiceName(JidCreate.domainBareFrom(serviceName))
      .setHostnameVerifier(NoopHostnameVerifier)
      .build()
  }
}

class AlertConfiguration extends Configuration {
  @JsonProperty
  var talk = new TalkConfiguration

  @JsonProperty
  var user = ""

  @JsonProperty
  var msg = ""
}

object AlertApplication extends ScalaApplication[AlertConfiguration] {
  override def initialize(bootstrap: Bootstrap[AlertConfiguration]): Unit = {
    bootstrap.addCommand(new AlertCommand)
  }

  override def run(configuration: AlertConfiguration, environment: Environment): Unit = {
    System.err.println("Please run the 'alert' command (instead of 'server').")
    System.exit(1)
  }
}
