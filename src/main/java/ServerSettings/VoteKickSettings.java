package ServerSettings;

import java.io.Serializable;

public class VoteKickSettings implements Serializable{
    
    private boolean votekick;
    private int timer;
    private int percentage;
    
    public VoteKickSettings(){
    votekick=true;
    timer=60000;
    percentage=30;
}
    
    public void settimer(int x){
        timer=x*1000;
    }
    
    public void setpercentage(int x){
        percentage=x;
    }
    
    public void setvotekick(boolean b){
        votekick=b;
    }
    
    public int gettimer(){
        return timer;
    }
    
    public int getpercentage(){
        return percentage;
    }
    
    public boolean getvotekick(){
        return votekick;
    }
    
}
