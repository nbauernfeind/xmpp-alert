# A command line tool to send XMPP IMs.

This github repository contains a tool that can be run to send IMs.

Usage:
```bash
java -jar xmpp-alert.jar alert npc-cred.yml -Ddw.user=alertedUser -Ddw.msg="Hello There"
```

Sample Config (`npc-cred.yml`):
```yml
talk:
  host: talk.google.com
  serviceName: gmail.com # The domain of the user account if using gtalk.
  port: 5222
  user: alert-bot        # Do not include the @domain portion of user.
  password: MY_PASSWORD  # This is not really my password.

user: person-to-notify@gmail.com
msg: Hello Person.
```

Note the `-Ddw.user` and `-Ddw.msg` flags overwrite what exists in the config file.
