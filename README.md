ShortIt
If you own your own domain(eg. http://begr.im), and you want to host your own URL shortener, then this is the plugin for you!

About

ShortIt is a plugin where it automatically shortens links when you post them into the chat. The difference between this plugin and many others is that it focuses on the YOURLS API. YOURLS stands for Your Own URL Shortener and is very easy to install on a webserver. See yourls.org for more info.

Commands/Permissions

Commands

/link [url]
Lets you make a link without anybody else seeing it but you
/link about
Lists the information about the plugin
/link reload
Reloads the config
NOTE: THIS CURRENTLY DOES NOT WORK
/link help
Lists the current commands
Permissions

shortit.*
Gives access to all commands
shortit.admin
Gives access to /reload
shortit.short*
Gives access to /link [url] and the ability to auto short links
shortit.autoshort
Only gives the ability to auto shorten links in the chat
shortit.getlink
Only gives the ability to shorten links via the command /link [url]
Setup

Visit this page here on how to setup YOURLS and the config.

Config

Currently, there is only one item on the config and that is the link for the YOURLS API.

To-Do

The ability to choose the colour of a shortened link
See if the server is running a version of Bukkit that does not support the new chat event and use the old chat event
A command that halts the ability to auto shorten links
Blacklist and whitelist certain links
Fix /link reload
Suggestions?
