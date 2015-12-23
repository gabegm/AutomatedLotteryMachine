/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automatedlotterymachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author gabegm
 */
public abstract class Lottery {
    public abstract void draw();
    protected List<Integer> randomNumbers;

    /**
     * Constructor for Lottery class
     */
    public Lottery() {
        this.randomNumbers = new ArrayList<>();
    }
    
    /**
     * Generates a random number in a specified range
     * @param min
     * @param max
     * @return 
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
}
