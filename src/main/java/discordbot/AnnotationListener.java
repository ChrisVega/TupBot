package discordbot;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.GuildCreateEvent;
import sx.blah.discord.handle.impl.events.MentionEvent;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.UserUpdateEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import Modules.MessageOnMention;
import sx.blah.discord.Discord4J;
import sx.blah.discord.handle.impl.events.DiscordDisconnectedEvent;

public class AnnotationListener {

    final private static IDiscordClient api = TestBot.bot.getclient();
    private Command Command = new Command();
    private MessageOnMention MOnMention = new MessageOnMention();
    private IMessage message;

    @EventSubscriber
    public void onMessage(MessageReceivedEvent event) throws MissingPermissionsException, RateLimitException, DiscordException{
        message = event.getMessage();
        if (message.toString().startsWith("#")||message.toString().toLowerCase().equals("ping")) {
            String[] arr = message.toString().split(" ");
            if(hmap.containsKey(arr[0].toLowerCase())) {
                hmap.get(arr[0].toLowerCase()).run();
            }
        }else if(!message.getMentions().isEmpty()){
            MOnMention.sendPMs(message.getMentions(), api, message);
        }

    }

    @EventSubscriber
    public void onUserUpdate(UserUpdateEvent event) {
        Command.onUserUpdate(event);
    }

    @EventSubscriber
    public void onGuildCreate(GuildCreateEvent event) {
        Command.joinGuild(event);
    }

    @EventSubscriber
    public void onMention(MentionEvent event) throws MissingPermissionsException, RateLimitException, DiscordException, Exception {
        if (!event.getMessage().mentionsEveryone()) {
                String s = "";
                s = event.getMessage().toString();
                
                s = s.replace("<@172505688512135168>", "");
                System.out.println(s);
                System.out.println("bot1> " + s);
                

                    s = TestBot.bot1session.think(s);

                System.out.println("bot1> " + s);
                event.getMessage().getChannel().sendMessage(s);
        }
    }

    HashMap<String, Runnable> hmap;

    public AnnotationListener() {
        this.hmap = new HashMap<String, Runnable>() {
            {
                put("ping", () -> Command.ping(message));
                put("#help", () -> Command.help(message));
                put("#helputility", () -> Command.helpUtility(message));
                put("#helchat", () -> Command.helpChat(message));
                put("#helmisc", () -> Command.helpMisc(message));
                put("#helpdebug", () -> Command.helpDebug(message));
                put("#helpgames", () -> Command.helpGames(message));
                put("#hghelp", () -> Command.HGHelp(message));
                put("#helpmusic", () -> Command.helpMusic(message));
                put("#mod", () -> Command.helpMod(message));
                put("#helpmod/admin", () -> Command.helpMod(message));
                put("#about", () -> Command.about(message));
                put("#invite", () -> Command.invite(message));
                put("#countdown", () -> Command.countdown(message));
                put("#giphy", () -> Command.Giphy(message));
                put("#imgur", () -> Command.Imgur(message));
                put("#yt", () -> Command.Youtube(message));
                put("#google", () -> Command.Google(message));
                put("#reddit", () -> Command.Reddit(message));
                put("#urban", () -> Command.UrbanDictionary(message));
                put("#poll,", () -> Command.Poll(message));
                put("#vote", () -> Command.vote(message));
                put("#roll", () -> Command.roll(message));
                put("#msgonm", () -> Command.MsgOnM(message));
                put("#rmvonm", () -> Command.RmvOnM(message));
                put("#nyan", () -> Command.Nyan(message));
                put("#gabe", () -> Command.Gabe(message));
                put("#lit", () -> Command.lit(message));
                put("#bern", () -> Command.Bern(message));
                put("#papas", () -> Command.PapaJ(message));
                put("#pres", () -> Command.Presence(message));
                put("#getdis", () -> Command.getDiscriminator(message));
                put("#getid", () -> Command.GetID(message));
                put("#getperm", () -> Command.GetPermission(message));
                put("#smtm", () -> Command.SMTM(message));
                put("#finduserinfo", () -> Command.findUserInfo(message));
                put("#namehis", () -> Command.nameHistory(message));
                put("#rr", () -> Command.RussianR(message));
                put("#cleargames", () -> Command.ClearGames(message));
                put("#hg", () -> Command.HungerGames(message));
                put("#hgcustom", () -> Command.HGCustom(message));
                put("#hgusers", () -> Command.HGUsers(message));
                put("#nextturn", () -> Command.nextTurn(message));
                put("#betfor", () -> Command.betFor(message));
                put("#betagainst", () -> Command.betAgainst(message));
                put("#acc", () -> Command.Account(message));
                put("#votekick", () -> Command.voteKick(message));
                put("#votemute", () -> Command.voteMute(message));
                put("#voteyes", () -> Command.voteYes(message));
                put("#voteno", () -> Command.voteNo(message));
                put("#vke", () -> Command.voteKickEnable(message));
                put("#vkt", () -> Command.voteKickTimer(message));
                put("#vkp", () -> Command.voteKickPercentage(message));
                put("#vkk", () -> Command.voteKickSettings(message));
                put("#msgwipe", () -> Command.messageWipe(message));
                put("#music", () -> Command.music(message));
                put("#play", () -> Command.play(message));
                put("#volume", () -> Command.volume(message));
                put("#stop", () -> Command.stop(message));
                put("#leave", () -> Command.leave(message));
                put("#pause", () -> Command.pause(message));
                put("#stop", () -> Command.stop(message));
                put("#skip", () -> Command.skip(message));
                put("#nowplaying", () -> Command.nowplaying(message));
                put("#list", () -> Command.list(message));
                put("#restart", () -> Command.restart(message));
                put("#repeat", () -> Command.repeat(message));
                put("#reset", () -> Command.reset(message));
                put("#shuffle", () -> Command.shuffle(message));
                put("#slots", () -> Command.slots(message));
            }
        };
    }

}
