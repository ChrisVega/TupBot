package Games;

import java.util.HashMap;
import java.util.Map;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;

public class Discordgamesessions {

    private Map<String, Games> currentgames;

    public Discordgamesessions() {
        currentgames = new HashMap<String, Games>();
    }

    public Discordgamesessions(String userid, Games game) {
        currentgames = new HashMap<String, Games>();
        currentgames.put(userid, game);
    }

    public Games getgame(String userid) {
        return currentgames.get(userid);
    }

    public void removegame(String userid) {
        currentgames.remove(userid);
    }

    public void addgame(String userid, Games game, IMessage message) throws MissingPermissionsException, HTTP429Exception, DiscordException {

        if (currentgames.containsKey(userid) && !currentgames.get(userid).gamedone()) {
            //if the user already has a game running the will have to overwrite their last game to continue
            //unless the last game has ended(last turn was displayed) then it will overwrite it
            message.reply("You currently have a game in session\nUse #Cleargames to remove current game");
        } else {
            currentgames.put(userid, game);
            game.gamesession();
            message.reply(usernextturn(message.getAuthor().getID()) + "");
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }
    }

    public String getcurrentgame(String userid) {
        String s = "";
        if (currentgames.containsKey(userid)) {
            s = "You are currently playing " + currentgames.get(userid).getgamename();
        } else {
            s = "You have no acive game.";
        }
        return s;
    }

    public boolean hasgame(String userid) {
        if (currentgames.containsKey(userid)) {
            return true;
        } else {
            return false;
        }
    }

    public String usernextturn(String userid) {
        return currentgames.get(userid).showcurrentturn();
    }

    public String userprevturn(String userid) {
        return currentgames.get(userid).showprevturn();
    }
}
