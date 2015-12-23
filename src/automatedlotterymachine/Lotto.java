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
public class Lotto extends Lottery {
    private final List<LottoTicket> lottoTicketList;
    private int count;

    /**
     * Constructor for the Lotto class
     */
    public Lotto() {
        this.lottoTicketList = new ArrayList<>();
    }

    /**
     * Lottery draw
     */
    @Override
    public void draw() {
        int rand;
        for (int x = 0; x < 5; x++) {
            rand = randInt(1, 90);
            while(randomNumbers.contains(rand)){
                rand = randInt(1, 90);
            }
            randomNumbers.add(rand);
        }
    }
    
    /**
     * Adds the lotto ticket to the list
     * @param lottoTicket 
     */
    public void addLottoTicket(LottoTicket lottoTicket){
        lottoTicketList.add(lottoTicket);
    }
    
    /**
     * Checks for a winner
     */
    public void getLottoWinner() {
        List<Integer> winningNumbers = new ArrayList<>();
        count = 0;
        for (LottoTicket ltList: lottoTicketList) {
            if (ltList.getType() == 1) {
                if(randomNumbers.get(0).equals(ltList.getNumbers().get(0))) {
                    int winningID = ltList.getId();
                    int winningType = ltList.getType();
                    double winningTicketValue = ltList.getTicketValue();
                    winningNumbers = ltList.getNumbers();
                    double winningWinningValue = ltList.getWinValue();
                    System.out.println("--- Winners ---");
                    System.out.println("ID: " + winningID + " Type: " + winningType + " Value: " + winningTicketValue + " Winning Value: " + winningWinningValue + " Numbers: " + winningNumbers);
                }
            }else if (ltList.getType() == 2) {
                int tmp;

                for(int i = 0; i < 5 ; i ++) {
                    tmp = ltList.getNumbers().get(i);
                    if(randomNumbers.contains(tmp)) {
                            count++;
                        }
                    }
                if(count >= 2) {
                    int winningID = ltList.getId();
                    int winningType = ltList.getType();
                    double winningTicketValue = ltList.getTicketValue();
                    winningNumbers = ltList.getNumbers();
                    double winningWinningValue = ltList.getWinValue();
                    System.out.println("Winning Ticket:");
                    System.out.println("ID: " + winningID + " Type: " + winningType + " Value: " + winningTicketValue + " Winning Value: " + winningWinningValue + " Numbers: " + winningNumbers);
                }
            }else if (ltList.getType() == 3) {
                int tmp;

                for(int i = 0; i < 5 ; i ++) {
                    tmp = ltList.getNumbers().get(i);
                    if(randomNumbers.contains(tmp)) {
                        count++;
                    }
                }
                if(count >= 3) {
                    int winningID = ltList.getId();
                    int winningType = ltList.getType();
                    double winningTicketValue = ltList.getTicketValue();
                    winningNumbers = ltList.getNumbers();
                    double winningWinningValue = ltList.getWinValue();
                    System.out.println("Winning Ticket:");
                    System.out.println("ID: " + winningID + " Type: " + winningType + " Value: " + winningTicketValue + " Winning Value: " + winningWinningValue + " Numbers: " + winningNumbers);
                }
            }
        }
    }
    
    /**
     * Loads the saved data from file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void retrieveSavedData() throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader("lottoTicket.txt"));  
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            String[] splitted = line.split(",");
            int type = Integer.parseInt(splitted[2]);
            List<Integer> numbers = new ArrayList<>();
            int y = 0;
            if (Integer.parseInt(splitted[2]) == 1) {
                y = 4;
            }else if (Integer.parseInt(splitted[2]) == 2 || Integer.parseInt(splitted[2]) == 3) {
                y = 8;
            }
            for (int x = 3; x < y; x++) {
                numbers.add(Integer.parseInt(splitted[x]));
            }
            LottoTicket lottoTicket = new LottoTicket(type, numbers);
            lottoTicket.setId(Integer.parseInt(splitted[0]));
            lottoTicket.setWinValue(Double.parseDouble(splitted[1]));
            addLottoTicket(lottoTicket);
            System.out.println("Lotto ticket added!");
        }
        try (PrintWriter writer = new PrintWriter("lottoTicket.txt")) {
            writer.print("");
            writer.close();
        }
    }
    
    /**
     * Writes the ticket to file
     * @param lottoTicket
     * @throws IOException 
     */
    public void writeLottoTicket (LottoTicket lottoTicket) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lottoTicket.txt", true)))) {
            out.print(lottoTicket.CSVFile());
        }
    }
    
    /**
     * Deletes all lotto tickets
     */
    public void deleteLottoTickets() {
        this.lottoTicketList.clear();
        try {
            File file = new File("lottoTickets.txt");
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
        return "Lotto{" + "Tickets sold=" + lottoTicketList.size() + ", Last draw = " + randomNumbers + '}';
    }

    /**
     * Returns the ticket list
     * @return 
     */
    public List<LottoTicket> getLottoTicketList() {
        return lottoTicketList;
    }

    public int getCount() {
        return count;
    }
    
}
