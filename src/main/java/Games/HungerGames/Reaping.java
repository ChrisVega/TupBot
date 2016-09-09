package Games.HungerGames;

import Games.HungerGames.Tribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Reaping {

    //string.split("\\s*(=>|,|\\s)\\s*");

    private ArrayList<Tribute> tributes;
    private ArrayList<Tribute> fallen;
    private Random rand = new Random();

    public Reaping() {
        tributes = new ArrayList<Tribute>(
                Arrays.asList(new Tribute("Marvel"),
                        new Tribute("Glimmer"),
                        new Tribute("Cato"),
                        new Tribute("Clove"),
                        new Tribute("Distric 3 Male"),
                        new Tribute("District 3 Female"),
                        new Tribute("District 4 Male"),
                        new Tribute("District 4 Female"),
                        new Tribute("District 5 Male"),
                        new Tribute("Foxface"),
                        new Tribute("Jason"),
                        new Tribute("District 6 Female"),
                        new Tribute("District 7 Male"),
                        new Tribute("District 7 Female"),
                        new Tribute("District 8 Male"),
                        new Tribute("District 8 Female"),
                        new Tribute("District 9 Male"),
                        new Tribute("District 9 Female"),
                        new Tribute("District 10 Male"),
                        new Tribute("District 10 Female"),
                        new Tribute("Rue"),
                        new Tribute("Thresh"),
                        new Tribute("Peeta"),
                        new Tribute("Katniss")
                ));

        fallen = new ArrayList<Tribute>();
    }

    public Reaping(String player) {
        List<String> customtributes = Arrays.asList(player.split(","));
        tributes = new ArrayList<Tribute>(
                Arrays.asList(new Tribute("Marvel"),
                        new Tribute("Glimmer"),
                        new Tribute("Cato"),
                        new Tribute("Clove"),
                        new Tribute("Distric 3 Male"),
                        new Tribute("District 3 Female"),
                        new Tribute("District 4 Male"),
                        new Tribute("District 4 Female"),
                        new Tribute("District 5 Male"),
                        new Tribute("Foxface"),
                        new Tribute("Jason"),
                        new Tribute("District 6 Female"),
                        new Tribute("District 7 Male"),
                        new Tribute("District 7 Female"),
                        new Tribute("District 8 Male"),
                        new Tribute("District 8 Female"),
                        new Tribute("District 9 Male"),
                        new Tribute("District 9 Female"),
                        new Tribute("District 10 Male"),
                        new Tribute("District 10 Female"),
                        new Tribute("Rue"),
                        new Tribute("Thresh"),
                        new Tribute("Peeta"),
                        new Tribute("Katniss")
                ));
        Collections.shuffle(customtributes);
        fallen = new ArrayList<Tribute>();
        //y=rand.nextInt(customtributes.length);
        if (customtributes.size() != 0) {
            int limit = 0;
            if (tributes.size() > customtributes.size()) {
                limit = customtributes.size();
            } else {
                limit = tributes.size();
            }
            for (int i = 0; i < limit; i++) {
                tributes.set(i, new Tribute(customtributes.get(i)));
            }
        }
    }

    public ArrayList<Tribute> gettrib() {
        Collections.shuffle(tributes);
        return tributes;
    }

    public ArrayList<Tribute> getfall() {
        return fallen;
    }

    public void removefallen() {
        for (int i = 0; i < tributes.size(); i++) {
            if (tributes.get(i).vitals()) {
                fallen.add(tributes.get(i));
            }
        }
        for (int i = 0; i < fallen.size(); i++) {
            if (tributes.contains(fallen.get(i))) {
                tributes.remove(fallen.get(i));
            }
        }
    }

    public boolean winner() {
        if (tributes.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String getwinner() {
        String s = "";
        if (winner()) {
            s = tributes.get(0).getname() + " is the winner!";
        }
        return s;
    }

}
