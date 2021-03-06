﻿# Bun Bot

Bun is a discord bot created by `ralf#0987` with the `JDA4` Library, built on the Official Discord API. (Migrated from `Discord.py` in 09/2020)


## Goal
Bun's goal is to provide quality of life improvements to servers, as well as execute tasks for the sake of convenience. 


## Features
Bun's functions can be accessed with a server independent prefix placed before a valid command. Bun's default prefix is `!`. The following is a list of commands at Bun's disposal, which can also be accessed with the `help` command.


### General
`greet`: Bun will send you a greeting message, mentioning you in a **text channel**.

`ping`: Bun will respond with the round-trip latency it took to handle your message. A helpful diagnosis tool!


### Moderation
`clear`: users with the `Manage Messages` permission are able to clear up to 99 messages within a **text channel**.

`mute`: users with the `Manage Permissions` permission may mute any server member to prevent them from sending messages in the server.

`ban`: users with the `Manage Members` permission may indefinitely ban a member from the server.

`kick`: users with the `Manage Members` permission may remove a member from the server.

### Fun
`coinflip`: Bun will "flip a coin" and return either Heads or Tails!

`roll`: By default Bun will return a random number from 1-10, or you can specify any number after `roll` for a larger or smaller range!

### Info
`serverinfo`: Bun will return information about the current server it is in.

`userinfo`: When followed by the mention of another user who is present in the current server, it will return that user's information.

## More features coming soon!
