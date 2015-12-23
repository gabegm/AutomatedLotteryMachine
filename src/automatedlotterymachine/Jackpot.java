/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automatedlotterymachine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabegm
 */
public class Jackpot extends Lottery {
    private final List<JackpotTicket> jackpotTicketList;
    private static final double INITIAL_JACKPOT = 200000;
    private double winningJackpot = INITIAL_JACKPOT;

    /**
     * Constructor for the Jackpot class
     */
    public Jackpot() {
        this.jackpotTicketList = new ArrayList<>();
    }

    /**
     * Generates lottery draw
     */
    @Override
    public void draw() {
        int rand;
        for (int x = 0; x < 5; x++) {
            rand = randInt(1, 35);
            while(randomNumbers.contains(rand)){
                rand = randInt(1, 35);
            }
            randomNumbers.add(rand);
        }
    }
    
    /**
     * Adds jackpot ticket to list
     * @param jackpotTicket 
     */
    public void addJackpotTicket(JackpotTicket jackpotTicket){
        jackpotTicketList.add(jackpotTicket);
    }
    
    /**
     * Checks for a winner
     */
    public void getJackpotWinner() {
        List<Integer> winningNumbers = new ArrayList<>();
        int count = 0;
        for(JackpotTicket jtList : jackpotTicketList) {
            if(randomNumbers.containsAll(jtList.getNumbers())) {
                int winningID = jtList.getId();
                double winningTicketValue = jtList.getTicketValue();
                winningNumbers = jtList.getNumbers();
                count++;
                System.out.println("Winning Ticket: ");
                System.out.println("ID: " + winningID + " Value: " + winningTicketValue + " Numbers: " + winningNumbers);
            }
        }
        if(count == 0) {
            winningJackpot += 100000;
            System.out.println("No Winners, jackpot up to: " + winningJackpot);
        }else {
            System.out.println("Number of winners: " + count + " Winnings each: " + (winningJackpot / count));
        }
    }
    
    /**
     * Loads any jackpot tickets from file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void retrieveSavedData() throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader("jackpotTicket.txt"));  
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            String[] splitted = line.split(",");
            List<Integer> numbers = new ArrayList<>();
            for (int x = 0; x < 5; x++) {
                numbers.add(Integer.parseInt(splitted[2]));
            }
            JackpotTicket jackpotTicket = new JackpotTicket(numbers);
            jackpotTicket.setId(Integer.parseInt(splitted[0]));
            addJackpotTicket(jackpotTicket);
            System.out.println("Jackpot ticket added!");
        }
        try (PrintWriter writer = new PrintWriter("jackpotTicket.txt")) {
            writer.print("");
            writer.close();
        }
    }
    
    /**
     * Saves jackpot ticket to file
     * @param jackpotTicket
     * @throws IOException 
     */
    public void writeJackpotTicket (JackpotTicket jackpotTicket) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("jackpotTicket.txt", true)))) {
            out.print(jackpotTicket.CSVFile());
        }
    }
    
    /**
     * Deletes all jackpot tickets
     */
    public void deleteJackpotTickets() {
        this.jackpotTicketList.clear();
        try {
            File file = new File("jackpotTickets.txt");
            file.delete();
        }catch(Exception e) {
           System.out.println("File could not be deleted");
        }
    }
    
    /**
     * Prints the amount of tickets sold and draw numbers to the employee
     * @return 
     */
    @Override
    public String toString() {
        return "Jackpot{" + "Tickets sold=" + jackpotTicketList.size() + ", Last draw = " + randomNumbers + '}';
    }

    public double getWinningJackpot() {
        return winningJackpot;
    }
    
}
