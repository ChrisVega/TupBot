package Games.HungerGames;

public class Tribute {

    private String Name = "";
    private boolean Dead = false;
    private int kills = 0;

    public Tribute(String n) {
        Name = n;
    }

    public String getname() {
        return Name;
    }

    public boolean vitals() {
        return Dead;
    }

    public void nextturn(EventObj e) {
        if (e.getDeath()) {
            Dead = true;
            return;
        }
    }

}
