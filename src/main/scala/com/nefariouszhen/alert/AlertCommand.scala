package com.nefariouszhen.alert

import io.dropwizard.cli.ConfiguredCommand
import io.dropwizard.setup.Bootstrap
import net.sourceforge.argparse4j.inf.Namespace
import org.jivesoftware.smack.chat.ChatManager
import org.jivesoftware.smack.java7.Java7SmackInitializer
import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jxmpp.jid.impl.JidCreate

/** This is the command that does all of the work. */
class AlertCommand extends ConfiguredCommand[AlertConfiguration]("alert", "Send Message to User") {
  override def run(bootstrap: Bootstrap[AlertConfiguration], namespace: Namespace, cfg: AlertConfiguration): Unit = {
    new Java7SmackInitializer().initialize()

    val conn = new XMPPTCPConnection(cfg.talk.config)
    conn.connect()
    conn.login(cfg.talk.user, cfg.talk.password)

    val receiver = JidCreate.bareFrom(cfg.user)

    val roster = Roster.getInstanceFor(conn)
    if (roster.getEntry(receiver) == null) {
      roster.createEntry(receiver, cfg.user, Array())
    }

    val chatman = ChatManager.getInstanceFor(conn)
    val chat = chatman.createChat(receiver)

    chat.sendMessage(cfg.msg)

    conn.disconnect()
  }
}
