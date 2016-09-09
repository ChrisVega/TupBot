package Modules;

import discordbot.Instance;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sx.blah.discord.Discord4J;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class MessageOnMention implements Serializable{

    private ArrayList<String> Users = new ArrayList<String>();
    
    public MessageOnMention(){
        Load();
    }
    
    public void adduser(IMessage m, IUser u,IDiscordClient api) throws MissingPermissionsException, RateLimitException, DiscordException, HTTP429Exception{
        int x = search(u.getID());
        if(x==-1){
            Users.add(u.getID());
            sort();
            Discord4J.LOGGER.info("User added "+ u.getName());
            SendPM.Send(api, u.getID(), "You have been added to the Message On Mention list");
        }else{
            m.reply("You are already on the Message on Mention list");
            return;
        }
        Save();
    }

    public void sort(){
        if(Users.size()>1){
       Users = sorthelper(Users, Users.size()-1); 
        }
    }
    
    public void removeuser(IMessage m,IDiscordClient api) throws MissingPermissionsException, RateLimitException, DiscordException, HTTP429Exception{
        if(Users.contains(m.getAuthor().getID())){
            Users.remove(m.getAuthor().getID());
            SendPM.Send(api, m.getAuthor().getID(), "You have been removed from the Message On Mention list");
        }else{
            m.reply("You are not on the Message on Mention list");
        }
        Save();
    }
    
    public void sendPMs(List<IUser> MO,IDiscordClient api, IMessage m) throws DiscordException, HTTP429Exception, MissingPermissionsException{
        String s="";
        if(m.mentionsEveryone()){
            s="Everyone was mentioned in "+m.getGuild().getName();
        }else{
            s="You were mentioned in "+m.getGuild().getName();
        }
        for(int i=0;i<MO.size();i++){
            if(Users.contains(MO.get(i).getID())){
                SendPM.Send(api, MO.get(i).getID(), s);
            }
        }
    }
    
    private ArrayList<String> sorthelper(ArrayList<String> m, int n){
        if(n==0){
            return m;
        }
        for(int i=0;i<n;i++){
            int compare = m.get(i).compareTo(m.get(i+1));
            if(compare>0){
                String tmp = m.get(i);
                m.set(i, m.get(i+1));
                m.set(i+1, tmp);
            }
        }
        return sorthelper(m,n-1);
    }
    
    public int search(String elt){
        if(Users.size()==1&&Users.get(0).equals(elt)){
            return 0;
        }
        if(Users!=null&&Users.size()!=0){
        return recsearch(Users,0,Users.size()-1,elt);
        }
        return -1;
    }
    
    private int recsearch(ArrayList<String> ar,int start, int end, String key){
        int mid = (start + end)/2;
        int compare = key.compareTo(ar.get(mid));
        if(start > end){
            return -1;
        }
        if(compare == 0){
            return mid;
        }
        else if(compare > 0){
            return recsearch(ar,mid+1,end,key);
        }
        else{
            return recsearch(ar,start,mid-1,key);
        }
    }
    
    public void Load(){
    try {
        FileInputStream fis = new FileInputStream("MOMarray.dat");
        ObjectInputStream in = new ObjectInputStream(fis);
        Users = (ArrayList<String>)in.readObject();
        in.close();
      }
      catch (IOException | ClassNotFoundException e) {
          Logger.getLogger(Instance.class.getName()).log(Level.SEVERE, "No file found, creating new array", e);
          Users = new ArrayList<String>();
      }
}
    
    public void Save(){
        try{
        FileOutputStream fos = new FileOutputStream("MOMarray.dat");
         ObjectOutputStream out = new ObjectOutputStream(fos);
         out.writeObject(Users);
         out.flush();
         out.close();
        }catch (IOException e) {
          Logger.getLogger(Instance.class.getName()).log(Level.SEVERE, "File could not be saved", e);
      }
    }
}