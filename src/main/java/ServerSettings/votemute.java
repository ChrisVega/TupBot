package ServerSettings;

import java.io.Serializable;

public class votemute implements Serializable{
    private boolean votemute;
    private int timer;
    private int percentage;
    
    public votemute(){
    votemute=true;
    timer=5000;
    percentage=5;
}
    
    public void settimer(int x){
        timer=x*1000;
    }
    
    public void setpercentage(int x){
        percentage=x;
    }
    
    public void setvotemute(boolean b){
        votemute=b;
    }
    
    public int gettimer(){
        return timer;
    }
    
    public int getpercentage(){
        return percentage;
    }
    
    public boolean getvotemute(){
        return votemute;
    }
}
