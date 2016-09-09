package Games;

import sx.blah.discord.handle.obj.IUser;

public class better {

    private IUser user;
    private float bet;
    private boolean side;
    private float returns;

    public better(IUser u, float b, boolean s) {
        user = u;
        bet = b;
        side = s;
        returns = 0;
    }

    public void setbet(float b) {
        bet = bet + b;
    }

    public void setreturns(float r) {
        returns = r;
    }

    public IUser getuser() {
        return user;
    }

    public float getbet() {
        return bet;
    }

    public float getreturns() {
        return returns;
    }

    public boolean getside() {
        return side;
    }
}
