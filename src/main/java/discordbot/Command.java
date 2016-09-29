

package discordbot;

import Games.Bettinguserbank;
import Games.Discordgamesessions;
import Games.HungerGames.HungerGames;
import Games.Slots;
import Modules.Dice;
import Modules.GoogleSearch;
import Modules.MessageOnMention;
import Modules.MusicPlayer;
import Modules.NameHistory;
import Modules.RedditSearch;
import Modules.SendPM;
import Modules.StrawPollHandle;
import Modules.UrbanSearch;
import Modules.Votehandle;
import Modules.YoutubeAPI;
import ServerSettings.Servers;
import ServerSettings.VoteKickSettings;
import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchFeed;
import at.mukprojects.giphy4j.exception.GiphyException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;
import net.dv8tion.jda.player.Playlist;
import net.dv8tion.jda.player.source.AudioInfo;
import net.dv8tion.jda.player.source.AudioSource;
import net.dv8tion.jda.player.source.AudioTimestamp;
import net.dv8tion.jda.player.source.RemoteSource;
import static sun.audio.AudioPlayer.player;
import sx.blah.discord.Discord4J;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.audio.IAudioManager;
import sx.blah.discord.handle.audio.IAudioProvider;
import sx.blah.discord.handle.audio.impl.DefaultProvider;
import sx.blah.discord.handle.impl.events.GuildCreateEvent;
import sx.blah.discord.handle.impl.events.UserUpdateEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MessageList;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.audio.AudioPlayer;

public class Command {//Command class has a method for each command, the map in
    //AnnotationListen will determine what command runs when one is called

    private static IDiscordClient api = TestBot.bot.getclient();
    private Discordgamesessions disgames = new Discordgamesessions();
    private ArrayList<IUser> users;
    private Bettinguserbank b = new Bettinguserbank();
    private Votehandle Vote = new Votehandle();
    private NameHistory Names = new NameHistory();
    private Servers ServerSettings = new Servers(api);
    private YoutubeAPI YoutubeSearch = new YoutubeAPI();
    private RedditSearch RedditSearch = new RedditSearch();
    private StrawPollHandle Poll = new StrawPollHandle();
    private Dice dice = new Dice();
    private MessageOnMention MessageOnMention = new MessageOnMention();
    private Giphy giphy = new Giphy("");
    private String s = "";
    private MusicPlayer player;
    private IAudioManager manager;
    private Slots slots = new Slots();
    public static final float DEFAULT_VOLUME = 0.35f;

    public void ping(IMessage message) {//Method reffering to the command
        try {
            //reply to message
            message.reply("pong");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //**********************  HELP COMMANDS ***************************
    //Help commands list commands and give descriptions, formatting, and instructions
    public void help(IMessage message) {
        try {
            message.getChannel().sendMessage("__**General Commands**__\n"
                    + "\n"
                    + "```About: #About, #Invite - To add bot to your server\n"
                    + "Utility: #Countdown, #Roll [Number], #Giphy [search], #Imgur [search](WIP), #Yt [search], #Google [search], #Reddit [subreddit], #Urban [search], #MsgOnM, #RmvOnM\n"
                    + "Music: #Music, #leave, #play, #play [youtube link], #pause, #stop, #skip, #nowplaying, #list, #volume [val], #restart, #repeat, #reset\n"
                    + "Chat: @Onii Fam [message] \n"
                    + "Misc: #Nyan, #Gabe, #Lit, #Bern, #Papas in the house\n"
                    + "Debug: ping, #nameHIS @user\n"
                    + "Games: #RR [1-6], Hunger Games\n"
                    + "Mod/Admin: #VoteKick @User, #Voteyes, #Voteno, #Poll,[#],[vote], #Mod - for full list\n"
                    + "#help[Command Type] - Will show the full description and list of commands"
                    + "\nCommands that do many things at once may be subjected to rate limits there's not much I can do```"
            );
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void helpUtility(IMessage message) {
        try {
            message.getChannel().sendMessage("__**Utility**__\n\n"
                    + "```#countdown - counts down from 5, the 5, 2, 3, 2, 1 is a joke, Jesus\n"
                    + "#Roll [# of rolls] D[# of faces]- Rolls a dice up to 20 times, can add up to 10 dice, ex. #roll 5 d20, 10 d4\n"
                    + "#Giphy [search] – Returns the first search result from Giphy\n"
                    + "#Imgur [search] – Returns the first search result from Imgur, a bit buggy\n"
                    + "#Yt [search] – Returns the first search result from YouTube\n"
                    + "#Google [search] – Returns the first search result from Google\n"
                    + "#Reddit [subreddit] – Returns the first post from a subreddit\n"
                    + "#Urban [search] – Returns the first search result from UrbanDictionary\n"
                    + "#MsgOnM - Will messages you when you are mentioned in a Guild, will exclude at everyone\n"
                    + "#RmvOnM - Will stop messaging you when you are mentioned```");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void helpMusic(IMessage message) {
        try {
            message.getChannel().sendMessage("__**Music**__\n\n"
                    + "#music - Joins the channel labled 'Music Room'\n"
                    + "#leave - Leaves the 'Music Room'\n"
                    + "#play - Will play the current queue, if pause it will start again\n"
                    + "#play [youtube link] - Will play a song from youtube, if the queue id empty it will start playing\n"
                    + "#pause - Pauses the queue\n"
                    + "#stop - Stops all audio playback\n"
                    + "#skip - Skips the current song\n"
                    + "#shuffle - Shuffles the playlist\n"
                    + "#nowplaying - Prints info about the current song\n"
                    + "#list - Lists all the songs in the queue\n"
                    + "#volume [val] - Sets the volume 0.0 - 1.0\n"
                    + "#restart - Restarts the current song, or the previous if none are playing\n"
                    + "#repeat - Repeats the current song"
                    + "#reset - Resets the player, used to fix errors");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void helpChat(IMessage message) {
        try {
            message.getChannel().sendMessage("__**Chat**__\n\n"
                    + "```To chat with Onii Fam just mention it\n"
                    + "As of writing this giving Onii Fam a nickname will break this```");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void helpMisc(IMessage message) {
        try {
            message.getChannel().sendMessage("__**Misc**__\n\n"
                    + "```#Nyan - Does some weeb stuff, I really don't get it\n"
                    + "#Gabe - Posts a 20min mega mix of Gabe the dog\n"
                    + "#Lit - Posts a random video from the Lit play list\n"
                    + "#Bern - Do you feel it?\n"
                    + "#Papas in the house - PAPAS IN THE HOUSE!```");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void helpDebug(IMessage message) {
        try {
            message.getChannel().sendMessage("__**Debug**__\n\n"
                    + "```Mostly for dev purposes\n"
                    + "Ping - pings the bot to see if it is online\n"
                    + "#nameHIS @User - shows all previously known names for that user```");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void helpGames(IMessage message) {
        try {
            message.getChannel().sendMessage("__**Games(WIP)**__\n\n"
                    + "```#RR [1-6] - Roll a 6 sided die to see if you'll be kicked, great fun!\n"
                    + "#HGhelp - Commands for Hunger Games\n"
                    + "#Nextturn – Shows the next turn for your current game\n"
                    + "#Cleargames – Removes active game\n"
                    + "Each user can only run one game at a time```");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void HGHelp(IMessage message) {
        try {
            message.getChannel().sendMessage("__**Hunger Games**__\n\n"
                    + "```Based off of https://brantsteele.net/hungergames/disclaimer.php\n"
                    + "24 tributes will enter the arean one comes out\n"
                    + "You can use the default list of tibutes, use members from the server or enter them manualy\n"
                    + "If you enter less than 24 tributes then default tributes will fill the gaps\n"
                    + "If you enter more than 24 then 24 random tributes will be taken from your list\n"
                    + "#HG - Starts the game with the default list of tributes\n"
                    + "#HGUsers - Starts the game with a list of random users in the server\n"
                    + "#HGCustom,[name1],[name2],ect. - Starts the game with a list of chosen names, do not put spaces after commas\n"
                    + "#Nextturn - Shows next turn\n"
                    + "#Cleargames - delete active game```");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void helpMod(IMessage message) {
        try {
            message.getChannel().sendMessage("__**Mod/Admin Tools and Settings**__\n\n"
                    + "__**Vote Kick**__\n\n"
                    + "```#Votekick @User\n"
                    + "Once voting has started use these commands to vote\n"
                    + "#voteyes - for yes\n"
                    + "#voteno – for no\n"
                    + "Percentage of votes to kick can be set by server owners, default is 30%```\n"
                    + "__**Straw Poll**__\n\n"
                    + "```#Poll,[how many options],[List of options] - Do not put commas in [List of options], formmatting is on you\n"
                    + "Ex: #Poll, 2, 1)For something 2)Against something\n"
                    + "#Vote [option] - adds a vote for the option you choose```\n"
                    + "__**Vote Kick Settings**__\n\n"
                    + "```To use these commands you need the manage server permission\n"
                    + "VKE [on/off] - enable or disable vote kick, default off\n"
                    + "VKT [time sec] - how may seconds vote kick lasts\n"
                    + "VKP [int] - percentage needed to kick a user, enter as an integer\n"
                    + "VK show - show current settings```\n"
                    + "\n__**Messages**__\n\n"
                    + "```#MSGcache - show current size of message chache for that channel\n"
                    + "#MSGwipe [min]- deletes all cached messages for the time specified, max time 60 min, is subjected to rate limits"
                    + "\nEx. #MSGwipe 5 will delete all messages for the past 5 minuets"
                    + "\n#MSGwipe [start-end] - Deletes all messages in that time range in minuets ex. 1-5 inbetween 1 and 5 minuets ago"
                    + "\n#MSGwipe @User [time] - Only delets messages for a specific user, can use either time format```");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //********************** ABOUT COMMANDS ***************************
    public void about(IMessage message) {//Gives info about this bot and the github
        try {
            message.getChannel().sendMessage("Written in java using the Discor4J "
                    + "library, previously Javacord.\nSource: https://github.com/ChrisVega/TupBot"
                    + "\n\nHave a suggestion or found a bug? PM @Franku unchained");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void invite(IMessage message) {//Link to add bot to server
        try {
            message.getChannel().sendMessage("Use this URL to add me to your server, you need to have Manage roles permission to do so\n"
                    + "https://discordapp.com/oauth2/authorize?&client_id=172504459966939137&scope=bot&permissions=66321458&position=1");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //********************** MUSIC COMMANDS ***************************
    //All music commands, JDA-Player was not well documented all commands, minus #music, are taken from the JDA-Player
    //github and rewritten to work with my command structure. Because of this I don't have the best idea of how this works
    public void music(IMessage message) {//joins the voice server with the name Music Room
        List<IVoiceChannel> voicechannel = message.getGuild().getVoiceChannelsByName("Music Room");
        if (!voicechannel.isEmpty() && voicechannel.size() == 1) {
            try {
                voicechannel.get(0).join();
            } catch (MissingPermissionsException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                message.getChannel().sendMessage("Joined `" + voicechannel.get(0).getName() + "`.");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                message.getChannel().sendMessage("Could not join voice channel, make sure there is "
                        + "one titled 'Music Room' with the capital letters.");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void volume(IMessage msg) {
        try {
            String message = msg.getContent();
            float volume = Float.parseFloat(message.substring("#volume ".length()));
            volume = Math.min(1F, Math.max(0F, volume));
            player.setVolume(volume);
            msg.getChannel().sendMessage("Volume was changed to: " + volume);
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void list(IMessage msg) {
        try {
            List<AudioSource> queue = player.getAudioQueue();
            if (queue.isEmpty()) {
                try {
                    msg.getChannel().sendMessage("The queue is currently empty!");
                    return;
                } catch (MissingPermissionsException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RateLimitException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DiscordException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            MessageBuilder builder = new MessageBuilder(msg.getClient());
            builder.appendContent("__Current Queue.  Entries: " + queue.size() + "__\n");
            for (int i = 0; i < queue.size() && i < 10; i++) {
                AudioInfo info = queue.get(i).getInfo();
//                builder.appendString("**(" + (i + 1) + ")** ");
                if (info == null) {
                    builder.appendContent("*Could not get info for this song.*");
                } else {
                    AudioTimestamp duration = info.getDuration();
                    builder.appendContent("`[");
                    if (duration == null) {
                        builder.appendContent("N/A");
                    } else {
                        builder.appendContent(duration.getTimestamp());
                    }
                    builder.appendContent("]` " + info.getTitle() + "\n");
                }
            }

            boolean error = false;
            int totalSeconds = 0;
            for (AudioSource source : queue) {
                AudioInfo info = source.getInfo();
                if (info == null || info.getDuration() == null) {
                    error = true;
                    continue;
                }
                totalSeconds += info.getDuration().getTotalSeconds();
            }

            builder.appendContent("\nTotal Queue Time Length: " + AudioTimestamp.fromSeconds(totalSeconds).getTimestamp());
            if (error) {
                builder.appendContent("`An error occured calculating total time. Might not be completely valid.");
            }
            builder.withChannel(msg.getChannel()).build();
        } catch (RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MissingPermissionsException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nowplaying(IMessage msg) {
        if (player.isPlaying()) {
            AudioTimestamp currentTime = player.getCurrentTimestamp();
            AudioInfo info = player.getCurrentAudioSource().getInfo();
            if (info.getError() == null) {
                try {
                    msg.getChannel().sendMessage(
                            "**Playing:** " + info.getTitle() + "\n"
                            + "**Time:**    [" + currentTime.getTimestamp() + " / " + info.getDuration().getTimestamp() + "]");
                } catch (MissingPermissionsException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RateLimitException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DiscordException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    msg.getChannel().sendMessage(
                            "**Playing:** Info Error. Known source: " + player.getCurrentAudioSource().getSource() + "\n"
                            + "**Time:**    [" + currentTime.getTimestamp() + " / (N/A)]");
                } catch (MissingPermissionsException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RateLimitException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DiscordException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                msg.getChannel().sendMessage("The player is not currently playing anything!");
            } catch (MissingPermissionsException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RateLimitException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void leave(IMessage msg) {
        IDiscordClient client = msg.getClient();
        IVoiceChannel chan = client.getConnectedVoiceChannels()
                .stream().filter(c -> c.getGuild() == msg.getGuild()).findFirst().orElse(null);
        if (chan != null) {
            chan.leave();
        }
    }

    public void skip(IMessage msg) {
        try {
            player.skipToNext();
            msg.getChannel().sendMessage("Skipped the current song.");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void repeat(IMessage msg) {
        if (player.isRepeat()) {
            try {
                player.setRepeat(false);
                msg.getChannel().sendMessage("The player has been set to **not** repeat.");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                player.setRepeat(true);
                msg.getChannel().sendMessage("The player been set to repeat.");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void shuffle(IMessage msg) {
        if (player.isShuffle()) {
            try {
                player.setShuffle(false);
                msg.getChannel().sendMessage("The player has been set to **not** shuffle.");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                player.setShuffle(true);
                msg.getChannel().sendMessage("The player been set to shuffle.");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void reset(IMessage msg) {
        player.stop();
        player = new MusicPlayer();
        player.setVolume(DEFAULT_VOLUME);
        manager.setAudioProvider(player);
        try {
            msg.getChannel().sendMessage("Music player has been completely reset.");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void play(IMessage msg) {//pretty much have no idea how this works since JDA-player has no documentation at all
        manager = msg.getGuild().getAudioManager();
        if (manager.getAudioProvider() instanceof DefaultProvider) {
            player = new MusicPlayer();
            player.setVolume(DEFAULT_VOLUME);
            manager.setAudioProvider(player);
        } else {

        }
        String message = msg.getContent();
        if (message.equals("#play")) {
            if (player.isPlaying()) {
                try {
                    msg.getChannel().sendMessage("Player is already playing!");
                    return;
                } catch (MissingPermissionsException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RateLimitException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DiscordException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (player.isPaused()) {
                try {
                    player.play();
                    msg.getChannel().sendMessage("Playback as been resumed.");
                } catch (MissingPermissionsException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RateLimitException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DiscordException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (player.getAudioQueue().isEmpty()) {
                    try {
                        msg.getChannel().sendMessage("The current audio queue is empty! Add something to the queue first!");
                    } catch (MissingPermissionsException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RateLimitException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DiscordException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    player.play();
                    try {
                        msg.getChannel().sendMessage("The current audio queue is empty! Add something to the queue first!");
                    } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            String infoMsg = "";
            String url = message.substring("#play ".length());
            System.out.println(url);
            Playlist playlist = Playlist.getPlaylist(url);
            List<AudioSource> sources = new LinkedList(playlist.getSources());
            if (sources.size() > 1) {
                try {
                    msg.getChannel().sendMessage("Found a playlist with **" + sources.size() + "** entries.\n"
                            + "Proceeding to gather information and queue sources. This may take some time...");
                    final MusicPlayer fPlayer = player;
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            for (Iterator<AudioSource> it = sources.iterator(); it.hasNext();) {
                                AudioSource source = it.next();
                                AudioInfo info = source.getInfo();
                                List<AudioSource> queue = fPlayer.getAudioQueue();
                                if (info.getError() == null) {
                                    queue.add(source);
                                    if (fPlayer.isStopped()) {
                                        fPlayer.play();
                                    }
                                } else {
                                    try {
                                        msg.getChannel().sendMessage("Error detected, skipping source. Error:\n" + info.getError());
                                    } catch (MissingPermissionsException e) {
                                        e.printStackTrace();
                                    } catch (RateLimitException e) {
                                        e.printStackTrace();
                                    } catch (DiscordException e) {
                                        e.printStackTrace();
                                    }
                                    it.remove();
                                }
                            }
                            try {
                                msg.getChannel().sendMessage("Finished queuing provided playlist. Successfully queued **" + sources.size() + "** sources");
                            } catch (MissingPermissionsException e) {
                                e.printStackTrace();
                            } catch (RateLimitException e) {
                                e.printStackTrace();
                            } catch (DiscordException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                } catch (MissingPermissionsException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RateLimitException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DiscordException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                AudioSource source = sources.get(0);
                AudioInfo info = source.getInfo();
                if (info.getError() == null) {
                    try {
                        player.getAudioQueue().add(source);
                        infoMsg += "The provided URL has been added the to queue";
                        if (player.isStopped()) {
                            player.play();
                            infoMsg += " and the player has started playing";
                        }
                        msg.getChannel().sendMessage(infoMsg + ".");
                    } catch (MissingPermissionsException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RateLimitException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DiscordException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        msg.getChannel().sendMessage("There was an error while loading the provided URL.\n"
                                + "Error: " + info.getError());
                    } catch (MissingPermissionsException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RateLimitException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DiscordException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public void pause(IMessage msg) {
        player.pause();
        try {
            msg.getChannel().sendMessage("Playback has been paused.");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stop(IMessage msg) {
        player.stop();
        try {
            msg.getChannel().sendMessage("Playback has been completely stopped.");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void restart(IMessage msg) {
        if (player.isStopped()) {
            if (player.getPreviousAudioSource() != null) {
                player.reload(true);
                try {
                    msg.getChannel().sendMessage("The previous song has been restarted.");
                } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    msg.getChannel().sendMessage("The player has never played a song, so it cannot restart a song.");
                } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            player.reload(true);
            try {
                msg.getChannel().sendMessage("The currently playing song has been restarted!");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //********************** UTILITY COMMANDS ***************************

    public void countdown(IMessage message) {//Counts down using tts
        try {
            message.getChannel().sendMessage("5.....2.....1...2...3....go ahead", true);
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Giphy(IMessage message) {//Giphy Search
        try {
            s = (message.getContent().toLowerCase().replace("#giphy ".toLowerCase(), ""));
            SearchFeed feed = giphy.search(s, 1, 0);
            try {
                message.getChannel().sendMessage(feed.getDataList().get(0).getImages().getOriginal().getUrl());
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (GiphyException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Imgur(IMessage message) {//Imgur Search
        try {
            s = (message.getContent().toLowerCase().replace("#Imgur ".toLowerCase(), ""));
            message.reply(getImgurContent(s));
        } catch (Exception ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Youtube(IMessage message) {//Youtube search
        try {
            s = (message.getContent().toLowerCase().replace("#Yt ".toLowerCase(), ""));
            try {
                message.reply(YoutubeSearch.search(s));
            } catch (IOException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Google(IMessage message) {//Google Search
        try {
            s = (message.getContent().toLowerCase().replace("#Google ".toLowerCase(), ""));
            s = s.replace(" ", "+");
            try {
                message.reply(GoogleSearch.search(s));
            } catch (IOException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Reddit(IMessage message) {//Returns first post for searched subreddit
        try {
            s = (message.getContent().toLowerCase().replace("#Reddit ".toLowerCase(), ""));
            RedditSearch = new RedditSearch();
            message.reply(RedditSearch.top(s));
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UrbanDictionary(IMessage message) {//Urbandictionary search
        try {
            s = (message.getContent().toLowerCase().replace("#Urban ".toLowerCase(), ""));
            message.reply(UrbanSearch.search(s));
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Poll(IMessage message) {//Creates a straw poll
        String[] pollsplit = message.getContent().toString().split("\\s*,\\s*");
        if (pollsplit.length < 3) {//if poll split is <3 then a component, [0] = #poll, [1] = number of options,
            // [2] = poll options
            try {
                message.reply("Please check your formatting, something is missing. " + pollsplit.length + "\n"
                        + pollsplit.toString());
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (pollsplit.length == 3) {
            try {
                Poll.addpoll(message, pollsplit[2], Integer.parseInt(pollsplit[1]));
            } catch (MissingPermissionsException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RateLimitException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void vote(IMessage message) {//which poll option you are voting for
        try {
            s = (message.getContent().toLowerCase().replace("#vote ".toLowerCase(), ""));
            Poll.addvote(message.getGuild(), Integer.parseInt(s), message);
        } catch (MissingPermissionsException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void roll(IMessage message) {//dice roll
        try {
            s = (message.getContent().toLowerCase().replace("#Roll ".toLowerCase(), ""));
            message.reply(dice.roll(s));
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MsgOnM(IMessage message) {//sends a message when you are mentioned
        try {
            MessageOnMention.adduser(message, message.getAuthor(), api);
        } catch (MissingPermissionsException | DiscordException | RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RmvOnM(IMessage message) {//stops the above
        try {
            MessageOnMention.removeuser(message, api);
        } catch (MissingPermissionsException | DiscordException | RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Nyan(IMessage message) {
        try {
            message.reply("Nyan nyan " + message.getAuthor().getName() + " onii-chan!");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Gabe(IMessage message) {
        try {
            message.getChannel().sendMessage("https://www.youtube.com/watch?v=BdmcYIGIqr0");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lit(IMessage message) {
        try {
            message.getChannel().sendMessage(TestBot.af.litaf());
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Bern(IMessage message) {
        try {
            message.getChannel().sendMessage("http://dowefeelthebern.com/");
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PapaJ(IMessage message) {
        try {
            message.getChannel().sendMessage(TestBot.John.pith());
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //********************** DEBUG COMMANDS ***************************

    public void Presence(IMessage message) {
        try {
            message.reply(message.getAuthor().getPresence().toString());
            message.reply(message.getAuthor().getRolesForGuild(message.getGuild()).toString().toUpperCase());
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDiscriminator(IMessage message) {
        try {
            message.getChannel().sendMessage(message.getAuthor().getDiscriminator());
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void GetID(IMessage message) {
        try {
            message.getChannel().sendMessage(message.getAuthor().getID());
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void GetPermission(IMessage message) {
        try {
            message.reply(message.getGuild().getRoles().toString());
                SendPM.Send(api, TestBot.authorID, s);
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SMTM(IMessage message) {
        try {
            users = (ArrayList<IUser>) message.getChannel().getGuild().getUsers();
            for (int i = 0; i < users.size(); i++) {
                s = s.concat("Name :" + users.get(i).getName() + " ID :" + users.get(i).getID() + ", ");
            }
            message.getChannel().sendMessage(s);
            message.getChannel().sendMessage(message.getChannel().getGuild().getRoles().toString());
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void findUserInfo(IMessage message) {
        s = (message.getContent().toLowerCase().replace("#find user info: ".toLowerCase(), ""));
        users = (ArrayList<IUser>) message.getChannel().getGuild().getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().toLowerCase().contains(s)) {
                try {
                    message.getChannel().sendMessage("Name: " + users.get(i).getName() + ", ID: " + users.get(i).getID());
                } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void nameHistory(IMessage message) {
        try {
            Names.getnames(message);
        } catch (MissingPermissionsException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //********************** GAMES ***************************
    public void slots(IMessage message){
        try {
            message.getChannel().sendMessage(slots.pull());
        } catch (MissingPermissionsException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        } catch (DiscordException e) {
            e.printStackTrace();
        }
    }
    public void RussianR(IMessage message) {
        try {
            String[] RR = message.getContent().split("\\s+");
            float f = 100;
            if (RR.length >= 3) {
                f = Float.parseFloat(RR[2]);
            }
            b.newbetgame(message, Integer.parseInt(RR[1]), f);
        } catch (MissingPermissionsException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ClearGames(IMessage message) {
        if (disgames.hasgame(message.getAuthor().getID())) {
            try {
                disgames.removegame(message.getAuthor().getID());
                message.getChannel().sendMessage("Current game deleted");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                message.getChannel().sendMessage("You have no active game.");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void HungerGames(IMessage message) {
        try {
            disgames.addgame(message.getAuthor().getID(), new HungerGames(), message);
        } catch (MissingPermissionsException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void HGCustom(IMessage message) {
        s = (message.getContent().toLowerCase().replace("#HGCustom ".toLowerCase(), ""));
        try {
            disgames.addgame(message.getAuthor().getID(), new HungerGames(s), message);
        } catch (MissingPermissionsException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void HGUsers(IMessage message) {
        try {
            users = new ArrayList<>(message.getChannel().getGuild().getUsers());
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i) != null) {
                    if (i == users.size() - 1) {
                        s = s.concat(users.get(i).getName());
                    } else {
                        s = s.concat(users.get(i).getName() + ",");
                    }
                }
            }
            disgames.addgame(message.getAuthor().getID(), new HungerGames(s), message);
        } catch (MissingPermissionsException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nextTurn(IMessage message) {
        String nt = "";
        if (disgames.hasgame(message.getAuthor().getID())) {
            try {
                nt = disgames.usernextturn(message.getAuthor().getID());
                message.getChannel().sendMessage(nt);
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                message.getChannel().sendMessage("You have no acive game.");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void betFor(IMessage message) {
        String[] RR = message.getContent().split("\\s+");
        try {
            b.addbet(message, Float.parseFloat(RR[1]), false);
        } catch (MissingPermissionsException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void betAgainst(IMessage message) {
        String[] RR = message.getContent().split("\\s+");
        try {
            b.addbet(message, Float.parseFloat(RR[1]), true);
        } catch (MissingPermissionsException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Account(IMessage message) {
        try {
            b.getuser(message);
        } catch (MissingPermissionsException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RateLimitException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //********************** MOD/ADMIN COMMANDS ***************************
    public void voteKick(IMessage message) {
        if (ServerSettings.getserver(message.getGuild().getID()).getvotekick().getvotekick() && permissionKick(message)) {
            if (!Vote.isVoting(message.getGuild())) {
                ArrayList<IUser> mUser = (ArrayList<IUser>) message.getMentions();
                users = (ArrayList<IUser>) message.getChannel().getGuild().getUsers();
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).equals(mUser.get(0))) {
                        try {
                            int t = ServerSettings.getserver(message.getGuild().getID()).getvotekick().gettimer() / 1000;
                            Vote.addvote(message, users.get(i), ServerSettings.getserver(message.getGuild().getID()), 0);
                            message.getChannel().sendMessage("Begin voting to kick " + users.get(i).mention()
                                    + "\nTime, " + t + " sec");//#votekick
                                Vote.castvotes(message.getGuild(), message.getChannel(), s, users.get(i), api);
                            break;
                        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        } else {
            try {
                message.getChannel().sendMessage("Vote kick is not enabled on this server");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void voteMute(IMessage message) {
        if (ServerSettings.getserver(message.getGuild().getID()).getvotemute().getvotemute() && permissionRole(message)) {
            if (!Vote.isVoting(message.getGuild())) {
                ArrayList<IUser> mUser = (ArrayList<IUser>) message.getMentions();
                users = (ArrayList<IUser>) message.getChannel().getGuild().getUsers();
                for (IUser user : users) {
                    if (user.equals(mUser.get(0))) {
                        try {
                            int t = ServerSettings.getserver(message.getGuild().getID()).getvotemute().gettimer() / 1000;
                            Vote.addvote(message, user, ServerSettings.getserver(message.getGuild().getID()), 1);
                            message.getChannel().sendMessage("Begin voting to mute " + user.mention() + "\nTime, " + t + " sec"); //#
                                Vote.castvotes(message.getGuild(), message.getChannel(), s, user, api);
                            break;
                        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        } else {
            try {
                message.getChannel().sendMessage("Vote mute is not enabled on this server");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void voteYes(IMessage message) {
        if (Vote.isVoting(message.getGuild())) {
            if (!Vote.hasvoted(message.getGuild(), message.getAuthor())) {
                Vote.getvotsesh(message.getGuild()).addfor();
                Vote.adduservote(message.getGuild(), message.getAuthor());
            } else {
                try {
                    message.getChannel().sendMessage("You can only vote once.");
                } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                message.getChannel().sendMessage("There are no current votes.");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void voteNo(IMessage message) {
        if (Vote.isVoting(message.getGuild())) {
            if (!Vote.hasvoted(message.getGuild(), message.getAuthor())) {
                Vote.getvotsesh(message.getGuild()).addnay();
                Vote.adduservote(message.getGuild(), message.getAuthor());
            } else {
                try {
                    message.getChannel().sendMessage("You can only vote once.");
                } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                message.getChannel().sendMessage("There are no current votes.");
            } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void voteKickEnable(IMessage message) {
        if (permission(message)) {
            VoteKickSettings VK = ServerSettings.getserver(message.getGuild().getID()).getvotekick();
            if (message.getContent().toLowerCase().contains("#VKE on".toLowerCase())) {
                try {
                    VK.setvotekick(true);
                    SendPM.Send(api, message.getAuthor().getID(), "Vote kick enabled");
                    ServerSettings.Save();
                } catch (DiscordException | MissingPermissionsException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RateLimitException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (message.getContent().toLowerCase().contains("#VKE off".toLowerCase())) {
                try {
                    VK.setvotekick(false);
                    SendPM.Send(api, message.getAuthor().getID(), "Vote kick disabled");
                    ServerSettings.Save();
                } catch (DiscordException | MissingPermissionsException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RateLimitException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            try {
                SendPM.Send(api, message.getAuthor().getID(), "You don't have permission to do this");
            } catch (DiscordException | MissingPermissionsException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RateLimitException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void voteKickTimer(IMessage message) {
        if (permission(message)) {
            try {
                VoteKickSettings VK = ServerSettings.getserver(message.getGuild().getID()).getvotekick();
                s = (message.getContent().toLowerCase().replace("#VKT ".toLowerCase(), ""));
                VK.settimer(Integer.parseInt(s));
                SendPM.Send(api, message.getAuthor().getID(), "Vote kick timer set to " + s + " sec");
                ServerSettings.Save();
            } catch (DiscordException | MissingPermissionsException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RateLimitException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                SendPM.Send(api, message.getAuthor().getID(), "You don't have permission to do this");
            } catch (DiscordException | MissingPermissionsException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RateLimitException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void voteKickPercentage(IMessage message) {
        if (permission(message)) {
            try {
                VoteKickSettings VK = ServerSettings.getserver(message.getGuild().getID()).getvotekick();
                s = (message.getContent().toLowerCase().replace("#VKP ".toLowerCase(), ""));
                VK.setpercentage(Integer.parseInt(s));
                SendPM.Send(api, message.getAuthor().getID(), "Vote kick percentage set to " + s + "%");
                ServerSettings.Save();
            } catch (DiscordException | MissingPermissionsException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RateLimitException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                SendPM.Send(api, message.getAuthor().getID(), "You don't have permission to do this");
            } catch (DiscordException | MissingPermissionsException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RateLimitException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void voteKickSettings(IMessage message) {
        if (permission(message)) {
            try {
                VoteKickSettings VK = ServerSettings.getserver(message.getGuild().getID()).getvotekick();
                SendPM.Send(api, message.getAuthor().getID(), "Voke Kick Settings for " + message.getGuild().getName()
                        + "\nVote kick enabled: " + VK.getvotekick() + "\nVote kick time: " + VK.gettimer() / 1000 + " sec"
                        + "\nVote kick percentage: " + VK.getpercentage() + "%");
            } catch (DiscordException | MissingPermissionsException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RateLimitException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                SendPM.Send(api, message.getAuthor().getID(), "You don't have permission to do this");
            } catch (DiscordException | MissingPermissionsException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RateLimitException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void messageCache(IMessage message) {
        if (permission(message)) {
            try {
                MessageList msg = message.getChannel().getMessages();
                SendPM.Send(api, message.getAuthor().getID(), msg.getCacheCapacity() + "");
            } catch (DiscordException | MissingPermissionsException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RateLimitException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void messageWipe(IMessage message) {
        if (permission(message)) {
            LocalDateTime x;
            LocalDateTime y;
            List<IUser> mentions = null;
            List<IMessage> bulkdel = new ArrayList<>();
            int timeframe = 0;
            int timeframesrt = 0;
            String split[];
            s = (message.getContent().toLowerCase().replace("#MSGwipe ".toLowerCase(), ""));
            if (!message.getMentions().isEmpty()) {
                mentions = message.getMentions();
                s = s.replaceAll("<@\\d+> ", "");
            }
            MessageList msg = message.getChannel().getMessages();
            if (s.contains("-")) {
                split = s.split("-");
                timeframe = Integer.parseInt(split[1]);
                timeframesrt = Integer.parseInt(split[0]);
            } else {
                try {
                    timeframe = Integer.parseInt(s);
                    timeframesrt = 0;
                }catch(NumberFormatException ex){
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    try {
                        message.reply("No time entered!");
                    } catch (MissingPermissionsException | RateLimitException | DiscordException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (timeframe <= 60) {
                x = message.getTimestamp().minusMinutes(timeframe);
                y = message.getTimestamp().minusMinutes(timeframesrt);
                bulkdel.add(message);
                for (IMessage msg1 : msg) {
                    if (msg1.getTimestamp().isAfter(x) && msg1.getTimestamp().isBefore(y) && bulkdel.size() < 101) {
                        if (mentions != null) {
                            if (!mentions.isEmpty() && mentions.contains(msg1.getAuthor())) {
                                bulkdel.add(msg1);
                            }
                        } else {
                            bulkdel.add(msg1);
                        }
                    }
                }
                if (bulkdel.size() > 1) {
                    try {
                        msg.bulkDelete(bulkdel);
                    } catch (DiscordException | RateLimitException | MissingPermissionsException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        message.delete();
                    } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
                        Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                try {
                    SendPM.Send(api, message.getAuthor().getID(), "Max timeframe is 60 min");
                } catch (DiscordException | MissingPermissionsException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RateLimitException ex) {
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                SendPM.Send(api, message.getAuthor().getID(), "You don't have permission to do this");
            } catch (DiscordException | MissingPermissionsException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RateLimitException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //********************** OTHER COMMANDS ***************************
    public void phone(IMessage message) {
        try {
            s = (message.getContent().toLowerCase().replace("phone ".toLowerCase(), ""));
            api.getChannelByID("130925206091857922").sendMessage(s);
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void joinGuild(GuildCreateEvent event) {
        if (ServerSettings.search(event.getGuild().getID()) == -1) {
            ServerSettings.add(event.getGuild().getID());
            Discord4J.LOGGER.debug("ADD");
        }
    }

    public void onUserUpdate(UserUpdateEvent event) {
        IUser old = event.getOldUser();
        IUser newu = event.getNewUser();
        Names.addname(old);
        Names.addname(newu);
    }

    public String getImgurContent(String q) throws Exception {
        String qurey = q;
        qurey = qurey.replaceAll(" ", "+");
        URL url;
        url = new URL("https://api.imgur.com/3/gallery/search/hot/all/0/?q=" + qurey);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Client-ID " + "0d6fe9cb2fca42c");

        conn.connect();
        StringBuilder stb = new StringBuilder();

        // Get the response
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            stb.append(line).append("\n");
        }
        rd.close();
        String split = stb.toString();
        split = split.replaceAll("[^A-Za-z0-9 ]", " ");
        String ID;
        String baseURL = "http://i.imgur.com/";
        List<String> items = Arrays.asList(split.split("\\s+"));
        ID = items.get(items.indexOf("id") + 1);
        String adrs = baseURL + ID;
        if (adrs.equalsIgnoreCase(baseURL) || ID == null) {
            return "No image found";
        }
        return adrs;
    }

    public boolean permission(IMessage message) {
        List<IRole> roles = message.getAuthor().getRolesForGuild(message.getGuild());
        boolean perm = false;
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getPermissions().contains(Permissions.MANAGE_SERVER)) {
                return true;
            }
        }
        return false;
    }

    public boolean permissionRole(IMessage message) {
        List<IRole> roles = api.getOurUser().getRolesForGuild(message.getGuild());
        boolean perm = false;
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getPermissions().contains(Permissions.MANAGE_ROLES)) {
                return true;
            }
        }
        return false;
    }

    public boolean permissionKick(IMessage message) {
        List<IRole> roles = api.getOurUser().getRolesForGuild(message.getGuild());
        boolean perm = false;
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getPermissions().contains(Permissions.KICK)) {
                return true;
            }
        }
        return false;
    }
}
