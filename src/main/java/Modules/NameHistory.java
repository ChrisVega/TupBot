package Modules;

import discordbot.Instance;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sx.blah.discord.Discord4J;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class NameHistory implements Serializable{

    private ArrayList<Names> Users = new ArrayList<Names>();
    
    public NameHistory(){
        Load();
    }
    
    public void adduser(IUser u){
        int x = search(u.getID());
        if(x==-1){
            Users.add(new Names(u));
            sort();
            Discord4J.LOGGER.info("User added "+ u.getName());
        }else{
            Users.get(x).addname(u.getName());
        }
        Save();
    }

    public void sort(){
        if(Users.size()>1){
       Users = sorthelper(Users, Users.size()-1); 
        }
    }
    
    public void getnames(IMessage m) throws MissingPermissionsException, DiscordException, RateLimitException{
        IUser mUser = m.getMentions().get(0);
        int x = search(mUser.getID());
        if(x!=-1){
            m.getChannel().sendMessage(Users.get(x).getnames());
        }else{
        adduser(mUser);
        x = search(mUser.getID());
        m.getChannel().sendMessage(Users.get(x).getnames());
        }
    }
    
    public void addname(IUser u){
        int x = search(u.getID());
        if(x>-1){
            Users.get(x).addname(u.getName());
            Users.get(x).updateuser(u);
            sort();
        }else{
            adduser(u);
        }
    }
    
    private ArrayList<Names> sorthelper(ArrayList<Names> m, int n){
        if(n==0){
            return m;
        }
        for(int i=0;i<n;i++){
            int compare = m.get(i).getID().compareTo(m.get(i+1).getID());
            if(compare>0){
                Names tmp = m.get(i);
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
    
    private int recsearch(ArrayList<Names> ar,int start, int end, String key){
        int mid = (start + end)/2;
        int compare = key.compareTo(ar.get(mid).getID());
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
        FileInputStream fis = new FileInputStream("Namesarray.dat");
        ObjectInputStream in = new ObjectInputStream(fis);
        Users = (ArrayList<Names>)in.readObject();
        in.close();
      }
      catch (IOException | ClassNotFoundException e) {
          Logger.getLogger(Instance.class.getName()).log(Level.SEVERE, "No file found, creating new array", e);
          Users = new ArrayList<Names>();
      }
}
    
    public void Save(){
        try{
        FileOutputStream fos = new FileOutputStream("Namesarray.dat");
         ObjectOutputStream out = new ObjectOutputStream(fos);
         out.writeObject(Users);
         out.flush();
         out.close();
        }catch (IOException e) {
          Logger.getLogger(Instance.class.getName()).log(Level.SEVERE, "File could not be saved", e);
      }
    }
}
