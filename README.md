# TupBot
A Discord bot made in java using the [Discord4J api wrapper](https://github.com/austinv11/Discord4J) and [JDA-Player](https://github.com/DV8FromTheWorld/JDA-Player) for playing music. 

Allows use of commands through the text chat of discord servers that allow the bot to do things such as search Google, YouTube, Imgur, Giphy, Urban Dictionary, and returns the first post for a subreddit as well as moderator/admin tools such as mass wiping messages, vote kick, and vote mute. A full list of commands, and how to add your own, can be found below or if you added the bot to your server type #help to list all commands. 

Questions or suggestions? Message me on my [Discord server](https://discord.gg/Shus9mN).

# Add it to your server
If you don't want to add commands or set it up yourself I have my own implementation of this bot called [OniiFam](https://discordapp.com/oauth2/authorize?&client_id=172504459966939137&scope=bot&permissions=66321458&position=1). All permissions checked are needed for all commands to work as intended but some can be disabled if you want. These permissions cannot be disabled, doing so will not allow the bot to read or send messages which defeats its purpose. These permissions are send messages, read messages, and read message history. 

All other permissions will break some commands but the bot will still work, this can be used to disable certain commands. For example disabling manage messages will not allow the bot to use any message wipe commands which let it do mass removal of messages which are useful for removing spam. Kick members is needed for vote kick commands, speak, connect, and use voice activity are needed for music, and TTS for countdown. Vote mute needs the mute members and manage roles permission, manage roles does let it edit all roles below it. It needs that permission because the bot can't directly mute a member right now, instead it makes a new role that doesn’t allow someone to speak and give it to a user, I’m trying to find a better method at the moment.

Also I would not recommend giving this bot, or any bot, the administrator role since you would be giving it unrestricted access to your server which isn't a good idea.

# Set up your own bot
If you want to start writing your own bot in Java I would obviously recommend [Discord4J](https://github.com/austinv11/Discord4J). There are plenty of tutorial and examples to get you started and austinv11 and many others are always willing to help in the official Discord server linked there.

# Implementing TupBot
If you would like to use TupBot for your own bot first register an app on the [Discord api site](https://discordapp.com/developers/applications/me) and add a bot account to your application. I don’t not recommend using a normal account for your bot since they are not designed to be used by bots. Doing so will probably get that account banned since Discord no longer allows it. TupBot also only supports bot accounts and will not work as is if you try using a user account.

Use the token given there and in the Instance class set String Token equal to your token. Never tell anyone your token as it will give them access to your bot! Now follow the instructions for [JDA-Player](https://github.com/DV8FromTheWorld/JDA-Player) so it can play music, if you don’t want this you will need to remove all the music commands. 

To add the bot to your server edit this link with your client id.

https://discordapp.com/oauth2/authorize?&amp;client_id=[BOT ID GOES HERE]&scope=bot

You will also need to add the needed api keys for all search commands (Imgur, Giphy, Google, YouTube, and Reddit) I'll provide some instructions for where to get them below but if you need more information they can be found on the api sites for that service.

##Where to put API keys:

###### Google + YouTube 
You will need a client id and client secret from [Google's developer site](https://console.developers.google.com) which both go in the client_secrets.jason in OniiFam\src\main\resources. Both are found under Credentials/OAuth 2.0 client IDs, the client ID is clearly labeled next to your client name the client secret can be found by click your client's name. 

Google and YouTube both use Google's official wrapper.

###### Google search 
You will need an api key which can found in the [Google developer site](https://console.developers.google.com) found under Credentials/API keys. Set the String key in the GoogleSearch class equal to your key. The Custom Search API must also be enabled under Library/Other popular APIs.
Ex.  private static String key="1234-564_morekeystuff5655";

###### YouTube
This needs an api key, which can be the same one from Google search, which can found in the [Google developer site](https://console.developers.google.com) found under Credentials/API keys. They key goes in OniiFam\src\main\resources\youtube.properties and set youtube.apikey equal to your key.
Ex. youtube.apikey=1234-564_morekeystuff5655

###### Reddit
This needs an application id and secret as well as an account, create a new account for the bot don't use your personal account. You get this information by going to [Reddit's app page](https://www.reddit.com/prefs/apps) and clicking "are you a developer? create an app...". Give the app a reasonable name and description since you need it later.

The Reddit API wrapper I use, Jraw, requires that you have all this info, in the RedditSearch class the UserAgent and Credentials should look like this.
UserAgent myUserAgent = UserAgent.of("Description", "discordbot.botname", "version", "botname");
Credentials credentials = Credentials.script("User Name", "Password", "personal use script key", "secret");

###### Imgur 
Imgur search needs an api key which can be found at [Imgur's api site](https://api.imgur.com/oauth2/addclient). In the Command class go to the  getImgurContent method and add your key to this line.
conn.setRequestProperty("Authorization", "Client-ID " + "api key");

No third party api wrapper was used.

###### Giphy 
Lastly Giphy search needs an api key that you can get from [Giphy's api site](api.giphy.com/submit) but it should give you the public api key which is dc6zaTOxFJmzC. In the command class the Giphy giphy object should look like this, unless you have another api key then replace the public key. 
private Giphy giphy = new Giphy("dc6zaTOxFJmzC");

For Giphy I used [Giphy4J](https://github.com/keshrath/Giphy4J) which was by far the simplest and easiest to use.
###### Pandora bot
When a user mentions the bot it will make a call to a Pandora bot, you don’t need to change this but if you do find a Pandora bot you like, get its id, and replace it with the one in Test bot.
bot1 = factory.create(ChatterBotType.PANDORABOTS, "ID goes here");

## Compatibility
The code can be run on any system that can have Java installed on it. I have personally run it on both Windows 10 and Linux using a Raspberry pi 2 with Rasbian and Mate both through an IDE and with the jar file.

For anyone who wants to run this bot on a Raspberry pi I recommend using a jar file for performance as you will see some small delays with the bot on it, especially with the music command.

## Commands
My implementation of commands is done by having each command as it's own method which are put under the Command class and then listed in the Command hash map in AnnotationListener. All commands start with # so when a message is posted the bot will check if it starts with a # it will split the messages by spaces  putting the command in index 0 which then allows it to be used as a key for the hashmap. This also does two things.

It stops people from calling multiple commands per message and from calling commands in the middle of their messages which prevents the bot from being used to spam and from users calling commands in an odd fashion. It also improves the speed of calling commands, using a hash map keeps calling the commands as close to O(1) as possible, the actual commands themselves will not be of course. Many of the old tutorials used if statements for commands and when you had a lot there was a noticeable delay, I haven't used D4J's command api so I can't say how well it works or how compatible it is with mine.

The format for commands should be the # followed by the command name if it has any parameters, like for Giphy search, then it should be followed by a space.

Ex. #Command Parameter 

For any commands that need their own classes I put them in the Modules package with the exception of games and server setting which I wanted to keep separate.
If the bot is mentioned it will make a call to Pandora bot which will grab that bots response to the users message and use it as a reply. 

## Command List

About: #About, #Invite - To add bot to your server

Utility: #Countdown, #Roll [Number], #Giphy [search], #Imgur [search](WIP), #Yt [search], #Google [search], #Reddit [subreddit],

\#Urban [search], #MsgOnM, #RmvOnM

Music: #Music, #leave, #play, #play [youtube link], #pause, #stop, #skip, #nowplaying, #list, #volume [val], #restart, #repeat, #reset

Chat: @Onii Fam [message] 

Misc: #Nyan, #Gabe, #Lit, #Bern, #Papas in the house

Debug: ping, #nameHIS @user

Games: #RR [1-6], Hunger Games
Mod/Admin: #VoteKick @User, #Voteyes, #Voteno, #Poll,[#],[vote]

There are many specific commands for server settings so #helpmod/admin will show you the full list and description.

\#help[Command Type] - Will show the full description and list of commands
