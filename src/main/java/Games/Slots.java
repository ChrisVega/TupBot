package Games;

import java.util.Random;

public class Slots {
    private String[] roll1;
    private String[] roll2;
    private String[] roll3;
    private String row1;
    private String row2;
    private String row3;
    private Random randomGenerator = new Random();

    public Slots(){
        roll1 = new String[]{":100:",":fire:",":ok_hand::skin-tone-5:",":flag_cu:",":clap::skin-tone-5:",
                ":grapes:",":sweat_drops:",":eggplant:",":peach:",":ok:"};
        roll2 = new String[]{":ok_hand::skin-tone-5:",":sweat_drops:",":peach:",":flag_cu:",":100:",":fire:",":eggplant:",":clap::skin-tone-5:",
                ":grapes:",":ok:"};
        roll3 = new String[]{":fire:",":eggplant:",":peach:",":flag_cu:",":clap::skin-tone-5:",
                ":grapes:",":ok_hand::skin-tone-5:",":100:",":sweat_drops:",":ok:"};
    }

    public String pull(){
        String result =":slot_machine: SLOTS :slot_machine:\n";
        String winner="";
        row1="";
        row2="";
        row3="";
        int po1=randomGenerator.nextInt(10);
        int po2=randomGenerator.nextInt(10);
        int po3=randomGenerator.nextInt(10);
        row2=roll1[po1]+roll2[po2]+roll3[po3];
        if(roll1[po1].contentEquals(roll2[po2])||roll2[po2].contentEquals(roll3[po3])){
            if(roll1[po1].contentEquals(roll2[po2])&&roll2[po2].contentEquals(roll3[po3])) {
                winner = "\n*LOUD NOISES*\nYou win maybe? Who knows!";
            }else if((roll1[po1].equals(":flag_cu:")&&roll2[po2].equals(":flag_cu:")) || (roll2[po2].equals(":flag_cu:")&&roll3[po3].equals(":flag_cu:"))){
                winner = "\n*The machine rattles*\nA few Cuban pennies and a croqueta falls out";
            }else if((roll1[po1].equals(":eggplant:")&&roll2[po2].equals(":eggplant:")) || (roll2[po2].equals(":eggplant:")&&roll3[po3].equals(":eggplant:"))){
                winner = "\n*The machine hums*\nThe tip of an eggplant pokes out";
            }else if((roll1[po1].equals(":peach:")&&roll2[po2].equals(":peach:")) || (roll2[po2].equals(":peach:")&&roll3[po3].equals(":peach:"))){
                winner = "\n*There a thumps from within the machine*\nA coupon falls out '10% off peaches'";
            }else if((roll1[po1].equals(":fire:")&&roll2[po2].equals(":fire:")) || (roll2[po2].equals(":fire:")&&roll3[po3].equals(":fire:"))){
                winner = "\n*'YOU'RE ON FIRE' screeches the machine*\nA pack of matches falls out";
            }else{
                winner = "\n*The machine plays a tune*\nA paper falls out it says 'ARBY'S HAS FOOD'";
            }
        }else{
            winner = "\n*Various slot machine noises*\nYou lost I think.";
        }
        if(po1==0||po2==0||po3==0){//so if a position is == 0 when 1 is subtracted it wont =-1
            if(po1==0){
                po1=10;
            }
            if(po2==0){
                po2=10;
            }
            if(po3==0){
                po3=10;
            }
        }
        row1=roll1[po1-1]+roll2[po2-1]+roll3[po3-1];
        if(po1>=9||po2>=9||po3>=9){//so if a position is == 9 when 1 is subtracted it wont =10
            if(po1>=9){
                po1=-1;
            }
            if(po2>=9){
                po2=-1;
            }
            if(po3>=9){
                po3=-1;
            }
        }
        row3=roll1[po1+1]+roll2[po2+1]+roll3[po3+1];
        result = result + row1+"\n"+row2+"\n"+row3+winner;
        return result;
    }

}
