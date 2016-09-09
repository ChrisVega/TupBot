package Games.HungerGames;

import java.util.ArrayList;

public class EventObj {

    private String occ = "";
    private boolean Death = false;
    private int numTrib = 0; //how many tributes are affected by this event
    private EventObj followup = null;
    private int type = 0;//number past which players are killed ex p1[0] killes p2[1] =1 p1[0] and p2[1] kill p3[2] = 2

    public EventObj(String e, boolean d, int nt, int t, EventObj fw) {
        occ = e;
        Death = d;
        numTrib = nt;
        type = t;
        followup = fw;
    }

    public EventObj(boolean d) {
        Death = d;
    }

    public String getocc() {
        return occ;
    }

    public boolean getDeath() {
        return Death;
    }

    public int gettribnum() {
        return numTrib;
    }

    public EventObj getfollowup() {
        return followup;
    }

    public int gettype() {
        return type;
    }

}
