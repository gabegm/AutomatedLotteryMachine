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
import java.util.Objects;

/**
 *
 * @author gabegm
 */
public class National extends Lottery {
    private final List<NationalTicket> nationalTicketList;

    /**
     * Constructor to the national class
     */
    public National() {
        this.nationalTicketList = new ArrayList<>();
    }
    
    /**
     * Generates lottery draw
     */
    @Override
    public void draw() {
        int rand;
        for (int x = 0; x < 10; x++) {
            rand = randInt(1, 99);
            while(randomNumbers.contains(rand)){
                rand = randInt(1, 99);
            }
            randomNumbers.add(rand);
        }
    }
    
    /**
     * Adds ticket to list if available
     * @param nationalTicket 
     * @throws java.io.IOException 
     */
    public void addNationalTicket(NationalTicket nationalTicket) throws IOException{
        if (isNationalTicketAvailable(nationalTicket)){
            nationalTicketList.add(nationalTicket);
        }
    }
    
    /**
     * Returns true if ticket is available
     * @param nationalTicket
     * @return 
     */
    public boolean isNationalTicketAvailable(NationalTicket nationalTicket){
        boolean check = true;
        for (NationalTicket ntList: nationalTicketList){
            if (ntList.getNumbers().equals(nationalTicket.getNumbers())){
                check = false;
                System.out.println("Ticket already exists!");
            }
        }
        return check;
    }

    /**
     * Overrides hashCode method
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nationalTicketList);
        return hash;
    }

    /**
     * Overrides equals method
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final National other = (National) obj;
        if (!Objects.equals(this.nationalTicketList, other.nationalTicketList)) {
            return false;
        }
        return true;
    }
    
    /**
     * Keeps on generating lottery draws until a winner is found
     */
    public void getNationalWinner() {
        List<Integer> winningNumbers = new ArrayList<>();
        boolean found = false;
        while(!found) {
            for(NationalTicket ntList : nationalTicketList) {
               try {
                    if(randomNumbers.containsAll(ntList.getNumbers())) {
                        int winningID = ntList.getId();
                        winningNumbers = ntList.getNumbers();
                        double winningTicketValue = ntList.getTicketValue();
                        found = true;
                        System.out.println("Winning Ticket: ");
                        System.out.println("ID: " + winningID + " " + "Value: " + winningTicketValue + " " + "Numbers: " + winningNumbers);
                    }else {
                        randomNumbers.clear();
                        draw();
                    }
                }catch(StackOverflowError e) {
                    return;
                }
            }
        }
    }
    
    /**
     * Loads national tickets from file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void retrieveSavedData() throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader("nationalTicket.txt"));  
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            String[] splitted = line.split(",");
            List<Integer> numbers = new ArrayList<>();
            for (int x = 0; x < 10; x++) {
                numbers.add(Integer.parseInt(splitted[2]));
            }
            NationalTicket nationalTicket = new NationalTicket(numbers);
            nationalTicket.setId(Integer.parseInt(splitted[0]));
            addNationalTicket(nationalTicket);
            System.out.println("National ticket added!");
        }
        try (PrintWriter writer = new PrintWriter("nationalTicket.txt")) {
            writer.print("");
            writer.close();
        }
    }
    
    /**
     * Saves national ticket to file
     * @param nationalTicket
     * @throws IOException 
     */
    public void writeNationalTicket (NationalTicket nationalTicket) throws IOException{
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nationalTicket.txt", true)))) {
            out.println(nationalTicket.CSVFile());
        }
    }
    
    /**
     * Deletes all national tickets
     */
    public void deleteNationalTickets() {
        this.nationalTicketList.clear();
        try {
            File file = new File("natioalTickets.txt");
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
        return "National{" + "Tickets sold=" + nationalTicketList.size() + ", Last draw = " + randomNumbers + '}';
    }

    public List<NationalTicket> getNationalTicketList() {
        return nationalTicketList;
    }
    
}
