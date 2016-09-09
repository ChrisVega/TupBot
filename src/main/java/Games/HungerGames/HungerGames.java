package Games.HungerGames;

import Games.Games;
import Games.HungerGames.Reaping;
import java.util.ArrayList;

public class HungerGames extends Games {

    private EventHandle ED;
    private Reaping Rep;
    private ArrayList<String> Allturns;
    private int turn = 0;
    private String name = "Hunger Games";

    public HungerGames() {
        ED = new EventHandle();
        Rep = new Reaping();
        Allturns = new ArrayList<String>();
    }

    public HungerGames(String s) {
        ED = new EventHandle();
        Rep = new Reaping(s);
        Allturns = new ArrayList<String>();
    }

    public String nextturn() {
        String s = ED.eventhandle(Rep.gettrib());
        Rep.removefallen();
        return s;
    }

    public void gamesession() {
        String s = "";
        do {
            Allturns.add(nextturn());
        } while (!Rep.winner());
        s = "__**" + Rep.getwinner() + "**__\n\n";
        s = s.concat("__**Fallen tributes**__\n\n");
        for (int i = 0; i < Rep.getfall().size(); i++) {
            s = s.concat(Rep.getfall().get(i).getname() + "\n");
        }
        Allturns.add(s);
    }

    public String showcurrentturn() {
        String s = "";
        if (turn == Allturns.size()) {
            s = s = Allturns.get(Allturns.size() - 1);
        } else {
            s = Allturns.get(turn);
            turn = turn + 1;
        }
        return s;
    }

    public String showprevturn() {
        turn = turn - 1;
        return showcurrentturn();
    }

    @Override
    public boolean gamedone() {
        if (turn == Allturns.size()) {
            return true;
        }
        return false;
    }

    @Override
    public String getgamename() {
        return name;
    }

}
