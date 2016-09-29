package Modules;


import java.util.ArrayList;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class StrawPoll {

    private String poll;
    private int[] votes;
    private IMessage message;
    private ArrayList<IUser> users;

    public StrawPoll(String p, IMessage m, int size) {
        poll = p;
        message = m;
        users = new ArrayList<IUser>();
        votes=new int[size];
    }
    
    public void addvote(IMessage message, int index) throws MissingPermissionsException, DiscordException, RateLimitException{
        if(index>votes.length){
            message.reply(index+" is not a valid poll option.");
            return;
        }
        if(!hasvoted(message.getAuthor())){
        votes[index-1]=votes[index-1]+1;
        users.add(message.getAuthor());
        }else{
            message.reply("You can only vote once.");
        }
    }
    
    public boolean hasvoted(IUser U){
        if(users.contains(U)){
            return true;
        }else{
            return false;
        }
    }
    
    public String getpoll(){
        return poll;
    }
    
    public String getresults(){
        String s="__**Poll Results**__\n\n";
        s=s.concat("```"+poll+"```\n");
        for(int i=0;i<votes.length;i++){
            s=s.concat((i+1)+": "+votes[i]+" votes\n");
        }
        return s;
    }

}
