package Modules;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.util.Random;

public class Dice {
    private Random randomGenerator = new Random();
    private String[] die;
    private String[] dicerolls;
    private String results;
    private String msg;
    
    public String roll(String message){//message format should follow #roll 3 d20, 4 d6
        //[# of rolls] d[number of faces]
        results="";
        int dicetype;
        int rolls;
        msg = message;
        die = msg.split(", ");
        if(die.length>10){
            return "you can only roll up to 10 dice.";
        }
        for(int i=0;i<die.length;i++){
            if(!die[i].matches("^\\d\\sd\\d{1,4}")){
                return "Incorrect formatting";
            }else{
                dicerolls=die[i].split("\\sd");//puts number of rolls in [0] and number of faces in [1]
                rolls=Integer.parseInt(dicerolls[0]);
                dicetype=Integer.parseInt(dicerolls[1]);
                if(rolls>20){
                    return "Too many rolls, limit is 20";
                }
                if(dicetype>100){
                    return "Dice type is too large, limit is D100";
                }
                results = results.concat("D"+dicetype+": ");//puts dice type before rolls
                for(int j=0;j<rolls;j++){//gets rolls for that amount of faces
                    results = results.concat((randomGenerator.nextInt(dicetype) +1)+" ");
                }
                results = results.concat("\n");
            }
        }
        return results;
    }
}
