package Games.HungerGames;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Eventarrays {

    private ArrayList<EventObj> Bloodbath;
    private ArrayList<EventObj> BloodbathFATAL;
    private ArrayList<EventObj> Day;
    private ArrayList<EventObj> DayFATAL;
    private ArrayList<EventObj> Night;
    private ArrayList<EventObj> NightFATAL;
    private ArrayList<EventObj> Feast;
    private ArrayList<EventObj> FeastFATAL;
    private List<List<EventObj>> Arena;
    private ArrayList<String> ArenaEvents;
    //add(new Event(String e, boolean death, int numtrib, int type, event followup));
    private Random rand = new Random();

    public Eventarrays() {
        Bloodbath = new ArrayList<EventObj>() {
            {
                add(new EventObj("grabs a shovel.", false, 1, 0, null));
                add(new EventObj("grabs a backpack and retreats.", false, 1, 0, null));
                add(new EventObj("#1 and #2 fight for a bag. #2 gives up and retreats.", false, 2, 1,
                        new EventObj(false)));
                add(new EventObj("#1 and #2 fight for a bag. #1 gives up and retreats.", false, 2, 1,
                        new EventObj(false)));
                add(new EventObj("finds a bow, some arrows, and a quiver.", false, 1, 0, null));
                add(new EventObj("runs into the cornucopia and hides.", false, 1, 0, null));
                add(new EventObj("takes a handful of throwing knives.", false, 1, 0, null));
                add(new EventObj("#1 rips a mace out of #2's hands.", false, 2, 1,
                        new EventObj(false)));
                add(new EventObj("finds a canteen full of water.", false, 1, 0, null));
                add(new EventObj("stays at the cornucopia for resources.", false, 1, 0, null));
                add(new EventObj("gathers as much food as they can.", false, 1, 0, null));
                add(new EventObj("grabs a sword.", false, 1, 0, null));
                add(new EventObj("takes a spear from inside the cornucopia.", false, 1, 0, null));
                add(new EventObj("finds a bag full of explosives.", false, 1, 0, null));
                add(new EventObj("clutches a first aid kit and runs away.", false, 1, 0, null));
                add(new EventObj("takes a sickle from inside the cornucopia.", false, 1, 0, null));
                add(new EventObj("#1, #2, and #3 work together to get as many supplies as possible.", false, 3, 1,
                        new EventObj(false)));
                add(new EventObj("runs away with a lighter and some rope.", false, 1, 0, null));
                add(new EventObj("snatches a bottle of alcohol and a rag.", false, 1, 0, null));
                add(new EventObj("finds a backpack full of camping equipment.", false, 1, 0, null));
                add(new EventObj("grabs a backpack, not realizing it is empty.", false, 1, 0, null));
                add(new EventObj("#1 breaks #2's nose for a basket of bread.", false, 2, 1,
                        new EventObj(false)));
                add(new EventObj("#1, #2, #3, and #4 share everything they gathered before running.", false, 4, 1,
                        new EventObj(false)));
                add(new EventObj("retrieves a trident from inside the cornucopia.", false, 1, 0, null));
                add(new EventObj("#1 grabs a jar of fishing bait while #2 gets fishing gear.", false, 2, 1,
                        new EventObj(false)));
                add(new EventObj("#1 scares #2 away from the cornucopia.", false, 2, 1,
                        new EventObj(false)));
                add(new EventObj("grabs a shield leaning on the cornucopia.", false, 1, 0, null));
                add(new EventObj("snatches a pair of sais.", false, 1, 0, null));
            }
        };

        BloodbathFATAL = new ArrayList<EventObj>() {
            {
                add(new EventObj("#1 steps off their podium too soon and blows up. ", true, 1, 0, null));
                add(new EventObj("#1 throws a knife into #2's head. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 accidently steps on a landmine. ", true, 1, 0, null));
                add(new EventObj("#1 catches #2 off guard and kills them. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 and #2 work together to drown #3. ", false, 3, 2,
                        new EventObj(true)));
                add(new EventObj("#1 strangles #2 after engaging in a fist fight. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 shoots an arrow into #2's head. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 cannot handle the circumstances and commits suicide. ", true, 1, 0, null));
                add(new EventObj("#1 bashes #2's head against a rock several times. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 snaps #2's neck. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 decapitates #2 with a sword. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 spears #2 in the abdomen. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 sets #2 on fire with a molotov. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 falls into a pit and dies. ", true, 1, 0, null));
                add(new EventObj("#1 stabs #2 while their back is turned. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 severely injures #2, but puts them out of their misery. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 severely injures #2 and leaves them to die. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 bashes #2's head in with a mace. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 pushes #2 off a cliff during a knife fight. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 throws a knife into #2's chest. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 is unable to convince #2 to not kill them. ", true, 2, 1,
                        new EventObj(false)));
                add(new EventObj("#1 convinces #2 to not kill them, only to kill #2 instead. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 falls into a frozen lake and drowns. ", true, 1, 0, null));
                add(new EventObj("#1, #2, and #3 start fighting, but #2 runs away as #1 kills #3. ", false, 3, 2,
                        new EventObj(true)));
                add(new EventObj("#1 kills #2 with their own weapon. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 overpowers #2, killing them. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 sets an explosive off, killing #2. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 sets an explosive off, killing #2, and #3. ", false, 3, 1,
                        new EventObj(true)));
                add(new EventObj("#1 sets an explosive off, killing #2, #3, and #4. ", false, 4, 1,
                        new EventObj(true)));
                add(new EventObj("#1 sets an explosive off, killing #2, #3, #4 and #5. ", false, 5, 1,
                        new EventObj(true)));
                add(new EventObj("#1 kills #2 as they tries to run. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 and #2 threaten a double suicide. It fails and they die. ", true, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1, #2, #3, and #4 form a suicide pact, killing themselves. ", true, 4, 1,
                        new EventObj(true)));
                add(new EventObj("#1 kills #2 with a hatchet. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 and #2 fight #3 and #4. #1 and #2 survive. ", false, 4, 2,
                        new EventObj(true)));
                add(new EventObj("#1 and #2 fight #3 and #4. #3 and #4 survive. ", true, 4, 2,
                        new EventObj(false)));

                add(new EventObj("#1 attacks #2, but #3 protects them, killing #1. ", true, 2, 1,
                        new EventObj(false)));
                add(new EventObj("#1 severely slices #2 with a sword. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 strangles #2 with a rope. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 kills #2 for their supplies. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 shoots an arrow at #2, but misses and kills #3 instead. ", false, 3, 2,
                        new EventObj(true)));
                add(new EventObj("#1 shoots a poisonous blow dart into #2's neck, slowly killing them. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 stabs #2 with a tree branch. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 stabs #2 in the back with a trident. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1, #2, and #3 get into a fight. #1 triumphantly kills them both. ", false, 3, 1,
                        new EventObj(true)));
                add(new EventObj("#3, #1, and #2 get into a fight. #1 triumphantly kills them both. ", false, 3, 1,
                        new EventObj(true)));
                add(new EventObj("#3, #2, and #1 get into a fight. #1 triumphantly kills them both. ", false, 3, 1,
                        new EventObj(true)));
                add(new EventObj("#1 finds #2 hiding in the cornucopia and kills them. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 finds #2 hiding in the cornucopia, but #2 kills #1. ", true, 2, 1,
                        new EventObj(false)));
                add(new EventObj("#1 kills #2 with a sickle. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 and #2 fight for a bag. #1 strangles #2 with the straps and runs. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#2 and #1 fight for a bag. #1 strangles #2 with the straps and runs. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 repeatedly stabs #2 to death with sais. ", false, 2, 1,
                        new EventObj(true)));

            }
        };

        Day = new ArrayList<EventObj>() {
            {
                add(new EventObj("#1 goes hunting. ", false, 1, 0, null));
                add(new EventObj("#1 injured them self. ", false, 1, 0, null));
                add(new EventObj("#1 explores the arena. ", false, 1, 0, null));
                add(new EventObj("#1 scares #2 off. ", false, 2, 2, null));
                add(new EventObj("#1 diverts #2's attention and runs away. ", false, 2, 2, null));
                add(new EventObj("#1 stalks #2. ", false, 2, 2, null));
                add(new EventObj("#1 fishes. ", false, 1, 0, null));
                add(new EventObj("#1 camouflages them self in the bushes. ", false, 1, 0, null));
                add(new EventObj("#1 steals from #2 while they aren’t looking. ", false, 2, 2, null));
                add(new EventObj("#1 makes a wooden spear. ", false, 1, 0, null));
                add(new EventObj("#1 discovers a cave. ", false, 1, 0, null));
                add(new EventObj("#1 attacks #2, but they manage to escape. ", false, 2, 2, null));
                add(new EventObj("#1 chases #2. ", false, 2, 2, null));
                add(new EventObj("#1 runs away from #2. ", false, 2, 2, null));
                add(new EventObj("#1 collects fruit from a tree. ", false, 1, 0, null));
                add(new EventObj("#1 receives a hatchet from an unknown sponsor. ", false, 1, 0, null));
                add(new EventObj("#1 receives clean water from an unknown sponsor. ", false, 1, 0, null));
                add(new EventObj("#1 receives medical supplies from an unknown sponsor. ", false, 1, 0, null));
                add(new EventObj("#1 receives fresh food from an unknown sponsor. ", false, 1, 0, null));
                add(new EventObj("#1 searches for a water source. ", false, 1, 0, null));
                add(new EventObj("#1 defeats #2 in a fight, but spares their life. ", false, 2, 2, null));
                add(new EventObj("#1 and #2 work together for the day. ", false, 2, 2, null));
                add(new EventObj("#1 begs for #2 to kill them. #2 refuses, keeping #1 alive. ", false, 2, 2, null));
                add(new EventObj("#1 tries to sleep through the entire day. ", false, 1, 0, null));
                add(new EventObj("#1, #2, #3, and #4 raid #5's camp while #5 is hunting. ", false, 5, 5, null));
                add(new EventObj("#1 constructs a shack. ", false, 1, 0, null));
                add(new EventObj("#1 overhears #2 and #3 talking in the distance. ", false, 3, 3, null));
                add(new EventObj("#1 practices their archery. ", false, 1, 0, null));
                add(new EventObj("#1 thinks about home. ", false, 1, 0, null));
                add(new EventObj("#1 is pricked by thorns while picking berries. ", false, 1, 0, null));
                add(new EventObj("#1 tries to spear fish with a trident. ", false, 1, 0, null));
                add(new EventObj("#1 searches for firewood. ", false, 1, 0, null));
                add(new EventObj("#1 and #2 split up to search for resources. ", false, 2, 2, null));
                add(new EventObj("#1 picks flowers. ", false, 1, 0, null));
                add(new EventObj("#1 tends to #2's wounds. ", false, 2, 2, null));
                add(new EventObj("#1 sees smoke rising in the distance, but decides not to investigate. ", false, 1, 0, null));
                add(new EventObj("#1 sprains their ankle while running away from #2. ", false, 2, 2, null));
                add(new EventObj("#1 makes a slingshot. ", false, 1, 0, null));
                add(new EventObj("#1 travels to higher ground. ", false, 1, 0, null));
                add(new EventObj("#1 discovers a river. ", false, 1, 0, null));
                add(new EventObj("#1 hunts for other tributes. ", false, 1, 0, null));
                add(new EventObj("#1 and #2 hunt for other tributes. ", false, 2, 2, null));
                add(new EventObj("#1, #2, and #3 hunt for other tributes. ", false, 3, 3, null));
                add(new EventObj("#1, #2, #3, and #4 hunt for other tributes. ", false, 4, 4, null));
                add(new EventObj("#1, #2, #3, #4, and #5 hunt for other tributes. ", false, 5, 5, null));
                add(new EventObj("#1 receives an explosive from an unknown sponsor. ", false, 1, 0, null));
                add(new EventObj("#1 questions their own sanity. ", false, 1, 0, null));

            }
        };

        DayFATAL = new ArrayList<EventObj>() {
            {
                add(new EventObj("#1 catches #2 off guard and kills them. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 throws a knife into #2's head. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 begs for #2 to kill them. #2 reluctantly obliges, killing #1. ", true, 2, 1,
                        new EventObj(false)));

                add(new EventObj("#1 and #2 work together to drown #3. ", false, 3, 2,
                        new EventObj(true)));

                add(new EventObj("#1 strangles #2 after engaging in a fist fight. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 shoots an arrow into #2's head. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 bleeds out due to untreated injuries. ", true, 1, 0, null));

                add(new EventObj("#1 cannot handle the circumstances and commits suicide. ", true, 1, 0, null));

                add(new EventObj("#1 bashes #2's head against a rock several times. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 unknowingly eats toxic berries.", true, 1, 0, null));

                add(new EventObj("#1 silently snaps #2's neck. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 taints #2's food, killing them. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 decapitates #2 with a sword. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 dies from an infection.", true, 1, 0, null));

                add(new EventObj("#1 spears #2 in the abdomen. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 sets #2 on fire with a molotov. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 falls into a pit and dies.", true, 1, 0, null));

                add(new EventObj("#1 stabs #2 while their back is turned. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 severely injures #2, but puts them out of their misery. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 severely injures #2 and leaves them to die. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 bashes #2's head in with a mace.", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 attempts to climb a tree, but falls to their death. ", true, 1, 0, null));

                add(new EventObj("#1 pushes #2 off a cliff during a knife fight. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 throws a knife into #2's chest. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1's trap kills #2. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 kills #2 while they are resting. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 is unable to convince #2 to not kill them. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 convinces #2 to not kill them, only to kill #2 instead. ", false, 2, 1,
                        new EventObj(true)));
                add(new EventObj("#1 falls into a frozen lake and drowns. ", true, 1, 0, null));

                add(new EventObj("#1, #2, and #3 start fighting, but #2 runs away as #1 kills #3. ", false, 3, 2,
                        new EventObj(true)));

                add(new EventObj("#1 kills #2 with their own weapon. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 overpowers #2, killing them. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 sets an explosive off, killing #2. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 sets an explosive off, killing #2, and #3. ", false, 3, 1,
                        new EventObj(true)));

                add(new EventObj("#1 sets an explosive off, killing #2, #3, and #4. ", false, 4, 1,
                        new EventObj(true)));

                add(new EventObj("#1 sets an explosive off, killing #2, #3, #4 and #5. ", false, 5, 1,
                        new EventObj(true)));

                add(new EventObj("#1 kills #2 as they try to run. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 and #2 threaten a double suicide. It fails and they die. ", true, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1, #2, #3, and #4 form a suicide pact, killing themselves. ", true, 4, 1,
                        new EventObj(true)));

                add(new EventObj("#1 dies from hypothermia. ", true, 1, 0, null));

                add(new EventObj("#1 dies from hunger. ", true, 1, 0, null));

                add(new EventObj("#1 dies from thirst. ", true, 1, 0, null));

                add(new EventObj("#1 kills #2 with a hatchet.", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 and #2 fight #3 and #4. #1 and #2 survive.", false, 4, 2,
                        new EventObj(true)));

                add(new EventObj("#1 and #2 fight #3 and #4. #3 and #4 survive.", true, 4, 2,
                        new EventObj(false)));

                add(new EventObj("#1 dies trying to escape the arena. ", true, 1, 0, null));

                add(new EventObj("#1 dies of dysentery. ", true, 1, 0, null));

                add(new EventObj("#1 accidently detonates a land mine while trying to arm it. ", true, 1, 0, null));

                add(new EventObj("#1 attacks #2, but #3 protects them, killing #1. ", true, 3, 1,
                        new EventObj(false)));

                add(new EventObj("#1 ambushes #2 and kills them. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 accidently steps on a landmine. ", true, 1, 0, null));

                add(new EventObj("#1 severely slices #2 with a sword. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 strangles #2 with a rope. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 kills #2 for their supplies. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 shoots an arrow at #2, but misses and kills #3 instead. ", false, 3, 2,
                        new EventObj(true)));

                add(new EventObj("#1 shoots a poisonous blow dart into #2's neck, slowly killing them. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1, #2, and #3 successfully ambush and kill #4, #5, and #6. ", false, 6, 3,
                        new EventObj(true)));

                add(new EventObj("#1, #2, and #3 unsuccessfully ambush #4, #5, and #6, who kill them instead. ", true, 6, 3,
                        new EventObj(false)));

                add(new EventObj("#1 stabs #2 with a tree branch. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 forces #2 to kill #4 or #3. They decides to kill #4. ", false, 4, 3,
                        new EventObj(true)));

                add(new EventObj("#1 forces #2 to kill #3 or #4. They decides to kill #4. ", false, 4, 3,
                        new EventObj(true)));

                add(new EventObj("#1 forces #4 to kill #3 or #2. They refuses to kill, so #1 kills #4 instead. ", false, 4, 3,
                        new EventObj(true)));

                add(new EventObj("#1 poisons #2's drink, but mistakes it for their own and dies. ", true, 2, 1,
                        new EventObj(false)));

                add(new EventObj("#1 poisons #2's drink. They drink it and die. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 stabs #2 in the back with a trident. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 attempts to climb a tree, but falls on #2, killing them both. ", true, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1, #2, and #3 get into a fight. #1 triumphantly kills them both. ", false, 3, 1,
                        new EventObj(true)));

                add(new EventObj("#2, #1, and #3 get into a fight. #1 triumphantly kills them both. ", false, 3, 1,
                        new EventObj(true)));

                add(new EventObj("#3, #2, and #1 get into a fight. #1 triumphantly kills them both. ", false, 3, 1,
                        new EventObj(true)));

                add(new EventObj("#1 kills #2 with a sickle. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1, #2, #3, #4, and #5 track down and kill #6. ", false, 6, 5,
                        new EventObj(true)));

                add(new EventObj("#1, #2, #3, and #4 track down and kill #5. ", false, 5, 4,
                        new EventObj(true)));

                add(new EventObj("#1, #2, and #3 track down and kill #4. ", false, 4, 3,
                        new EventObj(true)));

                add(new EventObj("#1 and #2 track down and kill #3. ", false, 3, 2,
                        new EventObj(true)));

                add(new EventObj("#1 tracks down and kills #2. ", false, 2, 1,
                        new EventObj(true)));

                add(new EventObj("#1 repeatedly stabs #2 to death with sais. ", false, 2, 1,
                        new EventObj(true)));

            }
        };

        Night = new ArrayList<EventObj>() {
            {
                add(new EventObj("#1 starts a fire. ", false, 1, 0, null));
                add(new EventObj("#1 sets up camp for the night. ", false, 1, 0, null));
                add(new EventObj("#1 loses sight of where they are. ", false, 1, 0, null));
                add(new EventObj("#1 climbs a tree to rest. ", false, 1, 0, null));
                add(new EventObj("#1 goes to sleep. ", false, 1, 0, null));
                add(new EventObj("#1 and #2 tell stories about themselves to each other. ", false, 2, 1,
                        new EventObj(false)));
                add(new EventObj("#1, #2, #3, and #4 sleep in shifts. ", false, 4, 1,
                        new EventObj(false)));
                add(new EventObj("#1, #2, and #3 sleep in shifts. ", false, 3, 1,
                        new EventObj(false)));
                add(new EventObj("#1 and #2 sleep in shifts. ", false, 2, 1,
                        new EventObj(false)));
                add(new EventObj("#1 tends to their wounds. ", false, 1, 0, null));
                add(new EventObj("#1 sees a fire, but stays hidden. ", false, 1, 0, null));
                add(new EventObj("#1 screams for help. ", false, 1, 0, null));
                add(new EventObj("#1 stays awake all night. ", false, 1, 0, null));
                add(new EventObj("#1 passes out from exhaustion. ", false, 1, 0, null));
                add(new EventObj("#1 cooks their food before putting the fire out. ", false, 1, 0, null));
                add(new EventObj("#1 and #2 run into each other and decide to truce for the night. ", false, 2, 2, null));
                add(new EventObj("#1 fends #2, #3, and #4 away from fire. ", false, 4, 4, null));
                add(new EventObj("#1, #2, and #3 discuss the games and what might happen in the morning. ", false, 3, 3, null));
                add(new EventObj("#1 cries them self to sleep. ", false, 1, 0, null));
                add(new EventObj("#1 tries to treat their infection. ", false, 1, 0, null));
                add(new EventObj("#1 and #2 talk about the tributes still alive. ", false, 2, 2, null));
                add(new EventObj("#1 is awoken by nightmares. ", false, 1, 0, null));
                add(new EventObj("#1 and #2 huddle for warmth. ", false, 2, 2, null));
                add(new EventObj("#1 thinks about winning. ", false, 1, 0, null));
                add(new EventObj("#1, #2, #3, and #4 tell each other ghost stories to lighten the mood. ", false, 4, 4, null));
                add(new EventObj("#1 looks at the night sky. ", false, 1, 0, null));
                add(new EventObj("#1 defeats #2 in a fight, but spares their life. ", false, 2, 2, null));
                add(new EventObj("#1 begs for #2 to kill them. #2 refuses, keeping #1 alive. ", false, 2, 2, null));
                add(new EventObj("#1 destroys #2's supplies while they are asleep. ", false, 2, 2, null));
                add(new EventObj("#1, #2, #3, #4, and #5 sleep in shifts. ", false, 4, 4, null));
                add(new EventObj("#1 lets #2 into their shelter. ", false, 2, 2, null));
                add(new EventObj("#1 receives a hatchet from an unknown sponsor. ", false, 1, 0, null));
                add(new EventObj("#1 receives clean water from an unknown sponsor. ", false, 1, 0, null));
                add(new EventObj("#1 receives medical supplies from an unknown sponsor. ", false, 1, 0, null));
                add(new EventObj("#1 receives fresh food from an unknown sponsor. ", false, 1, 0, null));
                add(new EventObj("#1 tries to sing (himself/herself1) to sleep. ", false, 1, 0, null));
                add(new EventObj("#1 attempts to start a fire, but is unsuccessful. ", false, 1, 0, null));
                add(new EventObj("#1 thinks about home. ", false, 1, 0, null));
                add(new EventObj("#1 tends to #2's wounds. ", false, 2, 2, null));
                add(new EventObj("#1 quietly hums. ", false, 1, 0, null));
                add(new EventObj("#1, #2, and #3 cheerfully sing songs together. ", false, 2, 2, null));
                add(new EventObj("#1 is unable to start a fire and sleeps without warmth. ", false, 1, 0, null));
                add(new EventObj("#1 and #2 hold hands", false, 2, 2, null));
                add(new EventObj("#1 convinces #2 to snuggle with them. ", false, 2, 2, null));
                add(new EventObj("#1 receives an explosive from an unknown sponsor. ", false, 1, 0, null));
                add(new EventObj("#1 questions their sanity. ", false, 1, 0, null));
            }
        };

        NightFATAL = DayFATAL;

        Arena = new ArrayList<List<EventObj>>() {
            {
                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("is crushed by a pack of wolf mutts.", true, 1, 0, null));
                        add(new EventObj("is eaten by wolf mutts.", true, 1, 0, null));
                        add(new EventObj("#1 knocks #2 out and leaves them for the wolf mutts.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 pushes #2 into a pack of wolf mutts.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("As #1 and #2 fight, a pack of wolf mutts show up and kill them both.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("is unable to find shelter and dies.", true, 1, 0, null));
                        add(new EventObj("trips face first into a puddle of acidic rain.", true, 1, 0,
                                new EventObj(false)));
                        add(new EventObj("#1 injures #2 and leaves them in the rain to die", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 refuses #2 shelter, killing them.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 shoves #2 into a pond of acidic rain, but is pulled in by #2, killing them ", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("is engulfed in the cloud of poisonous smoke.", true, 1, 0, null));
                        add(new EventObj("#1 sacrifices themself so #2 can get away.", true, 2, 1,
                                new EventObj(false)));
                        add(new EventObj("#1 slowly pushes #2 closer into the cloud until they can't resist any more.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 and #2 agree to die in the cloud together, but #1 pushes #2 in without warning.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 and #2 decide to run into the cloud together.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("is sucked into the hurricane.", true, 1, 0, null));
                        add(new EventObj("is incapacitated by flying debris and dies.", true, 1, 0, null));
                        add(new EventObj("#1 pushes #2 into an incoming boulder.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 stabs #2, then pushes them close enough to the hurricane to suck them in.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 tries to save #2 from being sucked into the hurricane, only to be sucked in as well.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("is stung to death.", true, 1, 0, null));
                        add(new EventObj("slowly dies from the tracker jacker toxins.", true, 1, 0, null));
                        add(new EventObj("#1 knocks #2 unconscious and leaves them there as bait.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("While running away from the tracker jackers, #1 grabs #2 and throws them to the ground.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 and #2 run out of places to run and are stung to death.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("is swept away.", true, 1, 0, null));
                        add(new EventObj("fatally injures themself on debris.", true, 1, 0, null));
                        add(new EventObj("#1 holds #2 underwater to drown.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 defeats #2, but throws them in the water to make sure they die.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 and #2 smash their heads together as the tsunami rolls in, leaving them both to drown.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("The fire catches up to #1, killing (him/her1).", true, 1, 0, null));
                        add(new EventObj("A fireball strikes #1, killing them.", true, 1, 0, null));
                        add(new EventObj("#1 kills #1 in order to utilize a body of water safely.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 falls to the ground, but kicks #2 hard enough to then push them into the fire.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 and #2 fail to find a safe spot and suffocate.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("is electrocuted by the border.", true, 1, 0, null));
                        add(new EventObj("trips on a tree root and is unable to recover fast enough.", true, 1, 0, null));
                        add(new EventObj("#1 restrains #2 to a tree and leaves them to die.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 pushes #2 into the border while they are not paying attention.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("Thinking they could escape, #1 and #2 attempt to run through the border together.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("dies from internal bleeding caused by a monkey mutt.", true, 1, 0, null));
                        add(new EventObj("is pummeled to the ground and killed by a troop of monkey mutts.", true, 1, 0, null));
                        add(new EventObj("#1 uses #2 as a shield from the monkey mutts.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 injures #2 and leaves them for the monkey mutts.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("While running, #1 falls over and grabs #2 on the way down. The monkey mutts kill them.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("is brutally attacked by a scurry of squirrels.", true, 1, 0, null));
                        add(new EventObj("tries to kills as many squirrels as they can, but there are too many.", true, 1, 0, null));
                        add(new EventObj("#1 uses the squirrels to their advantage, shoving #2 into them.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 in agony, kills #2 so they do not have to be attacked by the squirrels.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("The squirrels separate and kill #1 and #2.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("is buried in ash.", true, 1, 0, null));
                        add(new EventObj("suffocates.", true, 1, 0, null));
                        add(new EventObj("#1 pushes #2 in the lava.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 dips their weapon in the lava and kills #2 with it.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("As #1 trips over #2 into the lava, they grab #2 and pulls them down with as well.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("trips on a rock and falls off a cliff.", true, 1, 0, null));
                        add(new EventObj("accidently makes contact with spiny, lethal plant life.", true, 1, 0, null));
                        add(new EventObj("#1 flails their weapon around, accidently killing #2.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 finds and kills #2, who was making too much noise.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("While fighting, #1 and #2 lose their balance, roll down a jagged hillside, and die.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

                add(new ArrayList<EventObj>() {
                    {
                        add(new EventObj("survives.", false, 1, 0, null));
                        add(new EventObj("eats a scorpion, thinking it is a delicate dessert.", true, 1, 0, null));
                        add(new EventObj("hugs a tracker jacker nest, believing it to be a pillow.", true, 1, 0, null));
                        add(new EventObj("#1 mistakes #2 for a bear and kills them.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 drowns #2, who they thought was a shark trying to eat them.", false, 2, 1,
                                new EventObj(true)));
                        add(new EventObj("#1 and #2 decide to jump down the rabbit hole to Wonderland, which turns out to be a pit of rocks.", true, 2, 1,
                                new EventObj(true)));
                    }
                });

            }
        };

        ArenaEvents = new ArrayList<String>() {
            {
                add("Wolf mutts are let loose in the arena.");
                add("Acidic rain pours down on the arena.");
                add("A cloud of poisonous smoke starts to fill the arena.");
                add("A monstrous hurricane wreaks havoc on the arena.");
                add("A swarm of tracker jackers invades the arena.");
                add("A tsunami rolls into the the arena.");
                add("A fire spreads throughout the arena.");
                add("The arena's border begins to rapidly contract.");
                add("Monkey mutts fill the arena.");
                add("Carnivorous squirrels start attacking the tributes.");
                add("A volcano erupts at the center of the arena.");
                add("The arena turns pitch black and nobody can see a thing.");
                add("The remaining tributes begin to hallucinate.");
            }
        };

        Feast = new ArrayList<EventObj>() {
            {
                add(new EventObj("#1 gathers as much food into a bag as they can before fleeing. ", false, 1, 0, null));
                add(new EventObj("#1 sobs while gripping a photo of their friends and family. ", false, 1, 0, null));
                add(new EventObj("#1 and #2 decide to work together to get more supplies. ", false, 2, 2, null));
                add(new EventObj("#1 and #2 get into a fight over raw meat, but #2 gives up and runs away. ", false, 2, 2, null));
                add(new EventObj("#1 and #2 get into a fight over raw meat, but #1 gives up and runs away. ", false, 2, 2, null));
                add(new EventObj("#1, #2, and #3 confront each other, but grab what they want slowly to avoid conflict. ", false, 3, 3, null));
                add(new EventObj("#1 destroys #2's memoirs out of spite. ", false, 2, 2, null));
                add(new EventObj("#1, #2, #3, and #4 team up to grab food, supplies, weapons, and memoirs. ", false, 2, 2, null));
                add(new EventObj("#1 steals #2's memoirs. ", false, 2, 2, null));
                add(new EventObj("#1 takes a staff leaning against the cornucopia. ", false, 1, 0, null));
                add(new EventObj("#1 stuffs a bundle of dry clothing into a backpack before sprinting away. ", false, 1, 0, null));

            }
        };

        FeastFATAL = DayFATAL;
    }

    public ArrayList<EventObj> getbb() {
        return Bloodbath;
    }

    public ArrayList<EventObj> getbbf() {
        return BloodbathFATAL;
    }

    public ArrayList<EventObj> getdy() {
        return Day;
    }

    public ArrayList<EventObj> getdyf() {
        return DayFATAL;
    }

    public ArrayList<EventObj> getn() {
        return Night;
    }

    public ArrayList<EventObj> getnf() {
        return NightFATAL;
    }

    public int areanchoser() {
        int x = rand.nextInt(13);
        return x;
    }

    public ArrayList<EventObj> getar(int x) {
        ArrayList ar = (ArrayList) Arena.get(x);
        return ar;
    }

    public String getare(int x) {
        return ArenaEvents.get(x);
    }

    public ArrayList<EventObj> getf() {
        return Feast;
    }

    public ArrayList<EventObj> getff() {
        return FeastFATAL;
    }

}
