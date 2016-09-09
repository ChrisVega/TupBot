package ServerSettings;

import java.io.Serializable;

public class SeverSettings implements Serializable{
    
    private String server;
    private VoteKickSettings votekick;
    private votemute votemute;
    
    public SeverSettings(String s){
        server = s;
        votekick = new VoteKickSettings();
        votemute = new votemute();
    }
    
    public String getserver(){
        return server;
    }
    
    public void setserver(String s){
        server=s;
    }
    
    public VoteKickSettings getvotekick(){
        return votekick;
    }
    
    public votemute getvotemute(){
        return votemute;
    }
}
