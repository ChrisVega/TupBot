package Games;

import Games.Betting.Bettinggame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class Bettinguserbank {

    private Map<IUser, Float> useracc;
    private Map<IChannel, Bettinggame> currentbets;

    public Bettinguserbank() {
        useracc = new HashMap<IUser, Float>();
        currentbets = new HashMap<IChannel, Bettinggame>();
    }

    public void newbetgame(IMessage m, int n, float b) throws MissingPermissionsException, DiscordException, RateLimitException {
        if (!currentbets.containsKey(m.getChannel())) {
            if (!useracc.containsKey(m.getAuthor())) {
                adduser(m);
            }
            if(n>6){
                n=6;
            }
            currentbets.put(m.getChannel(), new Bettinggame(n, m, b));
            start(m.getChannel());
            m.reply("Starting! Number of bullets: " + n);
        } else {
            m.reply("There is already a bet going on in this channel please wait");
        }
    }

    public void removebetgame(IChannel c) {
        currentbets.remove(c);
    }

    public void getbetgame() {

    }

    public void adduser(IMessage m) throws MissingPermissionsException, DiscordException {
        if (!useracc.containsKey(m.getAuthor())) {
            useracc.put(m.getAuthor(), 1000.0f);
        } else {
        }
    }

    public void getuser(IMessage m) throws MissingPermissionsException, DiscordException, RateLimitException {
        if (useracc.containsKey(m.getAuthor())) {
            m.reply("$" + useracc.get(m.getAuthor()));
        } else {
            m.reply("No account found");
        }
    }

    public void removeuser() {

    }

    public void addbet(IMessage m, float f, boolean s) throws MissingPermissionsException, DiscordException, RateLimitException {
        if (currentbets.containsKey(m.getChannel())) {
            if (!useracc.containsKey(m.getAuthor())) {
                adduser(m);
            }
            if (!s && f <= useracc.get(m.getAuthor())) {
                currentbets.get(m.getChannel()).addbetfor(m.getAuthor(), f);
                return;
            }
            if (s && f <= useracc.get(m.getAuthor())) {
                currentbets.get(m.getChannel()).addbetagainst(m.getAuthor(), f);
                return;
            }
        } else {
            m.reply("No active bets");
        }
    }

    public void start(IChannel c) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                ArrayList<better> betters = currentbets.get(c).getbetters();
                try {
                    currentbets.get(c).calcearnings();
                } catch (MissingPermissionsException | DiscordException ex) {
                    Logger.getLogger(Bettinguserbank.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RateLimitException ex) {
                    Logger.getLogger(Bettinguserbank.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < betters.size(); i++) {
                    float temp = useracc.get(betters.get(i).getuser());
                    useracc.replace(betters.get(i).getuser(), temp + betters.get(i).getreturns());
                }
                if (currentbets.get(c).getoutcome()) {
                    try {
                        c.sendMessage(currentbets.get(c).getuser().mention() + " lost as has been kicked.");
                    } catch (MissingPermissionsException | DiscordException ex) {
                        Logger.getLogger(Bettinguserbank.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RateLimitException ex) {
                        Logger.getLogger(Bettinguserbank.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        c.sendMessage(currentbets.get(c).getuser().mention() + " won as was not kicked");
                    } catch (MissingPermissionsException | DiscordException | RateLimitException ex) {
                        Logger.getLogger(Bettinguserbank.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                removebetgame(c);
            }

        }, 30000);
    }

}
