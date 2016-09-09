package Modules;

import java.io.Serializable;
import java.util.Arrays;
import sx.blah.discord.Discord4J;
import sx.blah.discord.handle.obj.IUser;

public class Names implements Serializable{
    private String UserID;
    private String[] Names = new String[1];
    
    public Names(IUser u){
        UserID=u.getID();
        Names[0]=u.getName();
    }

    public String getnames(){
        return Arrays.toString(Names);
    }
    
    public String getID(){
        return UserID;
    }
    
    public void addname(String N){
        if(!contains(N)&&N!=null){
        String[] tmp = new String[Names.length+1];
        tmp[0]=N; 
        if(Names.length>=1){
        for(int i=0;i<Names.length;i++){
            tmp[i+1]=Names[i];
        }
        }
        Names=tmp;
        }
    }
    
    public boolean contains(String N){
        for(int i=0; i<Names.length;i++){
            if(N!=null&&!N.equalsIgnoreCase("null")&&Names[i]!=null){
            if(Names[i].equalsIgnoreCase(N)){
                return true;
            }
            }else{
                Discord4J.LOGGER.info(N +" did some null shit " + UserID);
            }
        }
        return false;
    }
    
    public void updateuser(IUser u){
        UserID=u.getID();
    }
    
}
