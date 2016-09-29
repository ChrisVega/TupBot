package Games.Betting;

import Games.better;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class Bettinggame {//firs users can enter game to roll for dubs if a user wants to bet the start off with 1000

    private IUser user;
    private float betfor; //for user winning false
    private float betagainst; //for user losing true
    private IChannel betchan;
    private ArrayList<better> users;
    private float percentage;
    private final float winnerper = 0.2f;
    private float winnercut;
    private boolean outcome;//true losses kicked false wins stays
    private Random rand = new Random();
    private int num;

    public Bettinggame(int n, IMessage m, float bet) {
        num = n;
        user = m.getAuthor();
        betfor = bet;
        users = new ArrayList<better>();
        users.add(new better(m.getAuthor(), bet, false));
        betchan = m.getChannel();
        spin();
    }

    public IUser getuser() {
        return user;
    }

    public ArrayList<better> getbetters() {
        return users;
    }

    public void addbetfor(IUser u, float f) throws MissingPermissionsException, DiscordException, RateLimitException {
        if (search(u) == -1) {
            users.add(new better(u, 0, false));
        }
        if (!users.get(search(u)).getside()) {
            users.get(search(u)).setbet(f);
            betfor = betfor + f;
            betchan.sendMessage("Bet added");
        } else {
            betchan.sendMessage("Sorry you already voted against " + user.getName());
        }
    }

    public void addbetagainst(IUser u, float f) throws MissingPermissionsException, DiscordException, RateLimitException {
        if (search(u) == -1) {
            users.add(new better(u, 0, true));
        }
        if (users.get(search(u)).getside()) {
            users.get(search(u)).setbet(f);
            betagainst = betagainst + f;
            betchan.sendMessage("Bet added");
        } else {
            betchan.sendMessage("Sorry you already voted for " + user.getName());
        }
    }

    public int search(IUser u) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getuser().equals(u)) {
                return i;
            }
        }
        return -1;
    }

    public void spin() {
        int x = rand.nextInt(6) + 1;
        if (x <= num) {
            outcome = true;
        } else {
            outcome = false;
        }
    }

    public boolean getoutcome() {
        return outcome;
    }

    public float getfor() {
        return betfor;
    }

    public float getagainst() {
        return betagainst;
    }

    public void calcearnings() throws MissingPermissionsException, DiscordException, RateLimitException {
        IUser cuser;
        if (outcome) {//user loses
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getside()) {
                    percentage = 0;
                    percentage = users.get(i).getbet() / betagainst;
                    betchan.sendMessage(users.get(i).getuser().mention() + ", " + percentage + ", " + betfor + ", " + betagainst + ", " + users.get(i).getbet() + ", " + betfor * percentage);
                    users.get(i).setreturns(betfor * percentage);
                    percentage = 0;
                } else {
                    users.get(i).setreturns(users.get(i).getbet() * -1);
                }
            }
            betchan.getGuild().kickUser(user);
        } else if (!outcome) {
            winnercut = betagainst * winnerper;
            betagainst = betagainst - winnercut;
            percentage = users.get(search(user)).getbet() / betfor;
            users.get(search(user)).setreturns(winnercut + betagainst * percentage);
            for (int i = 0; i < users.size(); i++) {
                if (i != search(user)) {
                    if (!users.get(i).getside()) {
                        percentage = users.get(i).getbet() / betfor;
                        users.get(i).setreturns(betagainst * percentage);
                        percentage = 0;
                    } else {
                        users.get(i).setreturns(users.get(i).getbet() * -1);
                    }
                }
            }
        }
    }

}
