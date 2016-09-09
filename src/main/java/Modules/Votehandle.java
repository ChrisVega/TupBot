package Modules;

import Modules.Votecommands;
import ServerSettings.SeverSettings;
import Modules.SendPM;
import static Modules.SendPM.Send;
import discordbot.AnnotationListener;
import discordbot.TestBot;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.handle.obj.Presences;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;

public class Votehandle {
    private Map<IGuild, Votecommands> currentvotes = new HashMap<IGuild, Votecommands>();
    
    public Votehandle(){

    }
    
    public void addvote(IMessage m, IUser u, SeverSettings S,int ty){
        ArrayList<IUser> users= (ArrayList<IUser>) m.getChannel().getGuild().getUsers();
        int x=0;
        for(int i=0;i<users.size();i++){
            if(users.get(i).getPresence().equals(Presences.ONLINE)){
                x=x+1;
            }
        }
        int  t = 0,p = 0;
        if(ty==0){
        t=S.getvotekick().gettimer();
        p=S.getvotekick().getpercentage();
        }else if(ty==1){
        t=S.getvotemute().gettimer();
        p=S.getvotemute().getpercentage();
        }
        currentvotes.put(m.getGuild(), new Votecommands(m,x,u,t,p,ty));
        return;
    }
    
    public void adduservote(IGuild g, IUser u){
        currentvotes.get(g).adduservote(u);
    }
    
    public boolean hasvoted(IGuild g, IUser u){
        if(currentvotes.get(g).hasvoted(u)){
            return true;
        }else{
            return false;
        }
    }
    
    public Votecommands getvotsesh(IGuild g){
        currentvotes.containsKey(g);
        return currentvotes.get(g);
    }
    
    public boolean isVoting(IGuild g){
        return currentvotes.containsKey(g);
    }
    
    public void removevote(IGuild g){
        currentvotes.remove(g);
        
    }
    
    public void castvotes(IGuild G, IChannel C, String bannie, IUser U,IDiscordClient api) throws MissingPermissionsException, HTTP429Exception, DiscordException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                try {
                    C.sendMessage(getvotsesh(G).voteresults());
                    if (getvotsesh(G).votepassed()) {
                        if(getvotsesh(G).gettype()==0){
                        G.kickUser(U);
                        }else if(getvotsesh(G).gettype()==1){
                            mute(G,U,C);
                        }
                    }
                    removevote(G);
                } catch (MissingPermissionsException | HTTP429Exception | DiscordException ex) {
                    Logger.getLogger(AnnotationListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }, getvotsesh(G).gettimer());
    }
    
    private void mute(IGuild G, IUser U,IChannel C) throws MissingPermissionsException, HTTP429Exception, DiscordException{
        List<IRole> Uroles = G.getUserByID(U.getID()).getRolesForGuild(G);
        if(!G.getRoles().toString().contains("OniiMute")){
            IRole R = G.createRole();
            R.changeName("OniiMute");
            EnumSet<Permissions> perm = EnumSet.of(Permissions.VOICE_SPEAK);
            R.changePermissions(Permissions.getDeniedPermissionsForNumber(Permissions.generatePermissionsNumber(perm)));
        }
        if(G.getRoles().toString().contains("OniiMute")){
            for(int i=0;i<G.getRoles().size();i++){
                if(G.getRoles().get(i).getName().equalsIgnoreCase("OniiMute")){
                    Uroles.add(G.getRoles().get(i));
                    i=G.getRoles().size();
                }
            }
            try {
                G.editUserRoles(U, Uroles.toArray(new IRole[Uroles.size()]));
            } catch (MissingPermissionsException ex) {
                C.sendMessage("Can not eddit roles on this server");
            } catch (HTTP429Exception ex) {
                Logger.getLogger(Votehandle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DiscordException ex) {
                Logger.getLogger(Votehandle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
