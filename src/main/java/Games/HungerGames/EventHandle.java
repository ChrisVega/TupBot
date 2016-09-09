package Games.HungerGames;

import Games.HungerGames.Tribute;
import java.util.ArrayList;
import java.util.Random;

public class EventHandle {

    private ArrayList<EventObj> Bloodbath;
    private ArrayList<EventObj> BloodbathFATAL;
    private ArrayList<EventObj> Day;
    private ArrayList<EventObj> DayFATAL;
    private ArrayList<EventObj> Night;
    private ArrayList<EventObj> NightFATAL;
    private ArrayList<EventObj> Feast;
    private ArrayList<EventObj> FeastFATAL;
    private Random rand = new Random();
    private int turn = -1;
    private int Dayn = 1;
    private int Nightn = 1;
    Eventarrays EA = new Eventarrays();

    public EventHandle() {
        Bloodbath = EA.getbb();
        BloodbathFATAL = EA.getbbf();
        Day = EA.getdy();
        DayFATAL = EA.getdyf();
        Night = EA.getn();
        NightFATAL = EA.getnf();
        Feast = EA.getf();
        FeastFATAL = EA.getff();
    }

	//turn 0 bloodbath, odd day, even night
    public String eventhandle(ArrayList<Tribute> t) {

        //random nnumber 1-10 if 7 or 8 arena event, if 10 feast.
        int x = rand.nextInt(10) + 1;
        String s = "";
        if (turn == -1) {
            s = s.concat("__**The Reaping**__\n\n");
            for (int i = 0; i < t.size(); i++) {
                s = s.concat(t.get(i).getname() + "\n");
            }
            turn = turn + 1;
            return s;
        }
        if (turn == 0) {
            s = s.concat("__**The Bloodbath**__\n\n");
            s = s.concat(bbfatal(t, Bloodbath, BloodbathFATAL));
            turn = turn + 1;
            return s;
        }

        if (x == 7 || x == 8) {
            x = EA.areanchoser();
            s = s.concat("__**Arena Event**__\n\n" + EA.getare(x) + "\n\n");
            s = s.concat(bbfatal(t, EA.getar(x), EA.getar(x)));
            return s;
        }
        if (x == 10) {
            s = s.concat("__**Feast**__\n\n");
            s = s.concat(bbfatal(t, Feast, FeastFATAL));
            turn = turn + 1;
            return s;
        }
        if (turn % 2 == 0) {
            s = s.concat("__**Night " + Nightn + "**__\n\n");
            s = s.concat(bbfatal(t, Night, NightFATAL));
            turn = turn + 1;
            Nightn = Nightn + 1;
            return s;
        }
        if (turn % 2 != 0) {
            s = s.concat("__**Day " + Dayn + "**__\n\n");
            s = s.concat(bbfatal(t, Day, DayFATAL));
            turn = turn + 1;
            Dayn = Dayn + 1;
            return s;
        }
        return "An error occured";
    }

    private String bbfatal(ArrayList<Tribute> t, ArrayList<EventObj> a, ArrayList<EventObj> b) {
        int x;
        int y;
        ArrayList<EventObj> e = a;
        String s = "";
        String build = "";
        for (int i = 0; i < t.size(); i++) {
            e = a;
            y = rand.nextInt(5) + 1;
            if (y == 4 || y == 5) {
                e = b;
            }
            x = rand.nextInt(e.size());
            if (!t.get(i).vitals()) {
                if (e.get(x).gettribnum() > 1 && i + (e.get(x).gettribnum() - 1) <= t.size() - 1) {
                    build = e.get(x).getocc();
                    int n = i;
                    for (int j = 1; j <= e.get(x).gettribnum(); j++) {
                        if (!t.get(n).vitals()) {
                            String repmnt = "#" + j;
                            build = build.replace(repmnt, t.get(n).getname());
                            if (j > e.get(x).gettype() && e.get(x).gettype() != 0) {
                                t.get(n).nextturn(e.get(x).getfollowup());
                                n = n + 1;
                            } else {
                                t.get(n).nextturn(e.get(x));
                                n = n + 1;
                            }
                        }
                    }
                    i = i + (e.get(x).gettribnum() - 1);
                    s = s.concat(build + " " + "\n");
                } else if (e.get(x).gettribnum() > 1 && i + (e.get(x).gettribnum() - 1) > t.size() - 1) {
                    i = i - 1;
                } else if (e.get(x).gettribnum() == 1) {
                    if (e.get(x).getocc().contains("#1")) {
                        s = s.concat(e.get(x).getocc().replace("#1", t.get(i).getname()) + " " + "\n");
                    } else {
                        s = s.concat(t.get(i).getname() + " " + e.get(x).getocc() + " " + "\n");
                    }
                    t.get(i).nextturn(e.get(x));
                }
            }
        }

        build = "";
        int fallen = 0;
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i).vitals()) {
                fallen = fallen + 1;
                build = build.concat(t.get(i).getname() + "\n");
            }
        }
        if (fallen > 0) {
            s = s.concat("\n" + "\n" + "__**Fallen Tributes**__ "+"\n\n");
            s = s.concat(fallen + " cannon shots can be heard in the distance.\n\n");
            s = s.concat(build);
        }
        return s;
    }

}
