package Modules;

import java.util.ArrayList;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class Votecommands {
    private int votingType = 0; //0 for kick 1 for mute
    private int votesfor;
    private int votesnay;
    private int timer;
    private int percentage;
    private String name;
    private int onlineusers;
    private IUser user;
    private IGuild server;
    private IChannel channel;
    private ArrayList<IUser> uservote = new ArrayList<IUser>();
    
    public Votecommands(IMessage message, int o, IUser u, int t, int p, int ty){
        votingType = ty;
        name = u.getName();
        onlineusers = o;
        user = u;
        server = message.getGuild();
        channel = message.getChannel();
        timer=t;
        percentage=p;
    }
    
    public void addfor(){
        votesfor=votesfor+1;
    }
    
    public void addnay(){
        votesnay=votesnay+1;
    }
    
    public int gettimer(){
        return timer;
    }
    
    public boolean votepassed(){
        if(onlineusers==0){
            onlineusers=1;
        }
        float f = (float) votesfor/onlineusers;
        if(votesfor>votesnay && (f*100)>percentage){
            return true;
        }else{
            return false;
        }
    }
    
    public String voteresults(){
        String ty = "";
        if(votingType==1){
            ty="mute";
        }else if(votingType==0){
            ty="kick";
        }
        String s="";
            s="Votes to "+ty+" "+user.mention()+"\n"; 
        s=s.concat("Votes for: "+votesfor+"\nVotes against:"+votesnay+"\n");
        if(votepassed()){
            s=s.concat("Vote passed!");
        }else{
            s=s.concat("Vote failed!");
        }
        return s;
    }
    
    public void adduservote(IUser u){
        uservote.add(u);
    }
    
    public boolean hasvoted(IUser u){
        if(uservote.contains(u)){
            return true;
        }else{
            return false;
        }
    }
    
    public int gettype(){
        return votingType;
    } 
}
