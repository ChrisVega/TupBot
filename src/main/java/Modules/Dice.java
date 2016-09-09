package Modules;

import java.util.Random;

public class Dice {
    Random randomGenerator = new Random();
    
    public int roll(int x){
        int randomInt = randomGenerator.nextInt(x)+1;
        return randomInt;
    }
}
