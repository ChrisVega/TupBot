package Modules;

import discordbot.AnnotationListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class StrawPollHandle {
     private Map<IGuild, StrawPoll> currentpolls = new HashMap<IGuild, StrawPoll>();
     
     public StrawPollHandle(){
         currentpolls = new HashMap<IGuild, StrawPoll>();
     }
     
     public void addpoll(IMessage message, String poll, int size) throws MissingPermissionsException, DiscordException, RateLimitException{
         if(currentpolls.containsKey(message.getGuild())){
             message.reply("There is already a poll in progress.");
         }else{
             currentpolls.put(message.getGuild(), new StrawPoll(poll, message, size));
             message.getChannel().sendMessage("__**New Poll**__\n\n"
             +"```"+poll+"```\n"
             +"Begining poll, voting time is 2 min.");
             castvotes(message.getGuild(),message.getChannel());
         }
     }
     
     public void addvote(IGuild G, int vote, IMessage M) throws MissingPermissionsException, DiscordException, RateLimitException{
         if(currentpolls.containsKey(G)){
             currentpolls.get(G).addvote(M, vote);
         }else{
             M.reply("There are no current polls.");
         }
     }
     
     public void removepoll(IGuild G){
         currentpolls.remove(G);
     }
     
     public StrawPoll getpoll(IGuild G){
         return currentpolls.get(G);
     }
     
     public void castvotes(IGuild G, IChannel C) throws MissingPermissionsException, DiscordException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                try {
                    C.sendMessage(getpoll(G).getresults());
                    removepoll(G);
                } catch (MissingPermissionsException | DiscordException ex) {
                    Logger.getLogger(AnnotationListener.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RateLimitException ex) {
                    Logger.getLogger(StrawPollHandle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }, 120000);
    }
}
