/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automatedlotterymachine;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author gabegm
 */
public class LottoTicket extends Ticket {
    private static int id;
    private double ticketValue;
    private double winValue;
    private final int type;
    private final List<Integer> numbers;

    /**
     * Constructor to LottoTicket class
     * @param type
     * @param numbers 
     */
    public LottoTicket(int type, List<Integer> numbers) {
        ++id;
        this.setId(id);
        this.type = type;
        this.numbers = numbers;
    }

    /**
     * Print the lotto ticket to the client
     * @return 
     */
    @Override
    public String toString() {
        return "LottoTicket{" + "id=" + id + ", winValue=" + winValue + ", numbers=" + numbers + '}';
    }
    
    /**
     * Calculates the win value depending on the type
     */
    public void calculateWinValue() {
        double multiplier = 0.00;
        if (type == 1) {
            multiplier = 60;
        }else if (type == 2) {
            multiplier = 215;
        }else if (type == 3) {
            multiplier = 3500;
        }
        winValue = ticketValue * multiplier;
    }
    
    /**
     * Returns numbers in a CSV fashion
     * @return
     * @throws IOException 
     */
    @Override
    public String returnCSVNumbers() throws IOException {
        String str = "";
        for(int i: numbers) {
            str += "" + i + ",";
        }
        return str;
    }
    
    /**
     * Generates output for CSV file
     * @return
     * @throws IOException 
     */
    @Override
    public String CSVFile() throws IOException {
        return getId() + "," + winValue + "," + type + "," + returnCSVNumbers();
    }

    /**
     * Returns the ticker ID
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the ticket type
     * @return 
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the ticket numbers
     * @return 
     */
    public List<Integer> getNumbers() {
        return numbers;
    }

    /**
     * Returns the ticket win value
     * @return 
     */
    public double getWinValue() {
        return winValue;
    }

    /**
     * Sets the ticket id
     * @param id 
     */
    public final void setId(int id) {
        LottoTicket.id = id;
    }

    /**
     * Sets the ticket win value
     * @param winValue 
     */
    public void setWinValue(double winValue) {
        this.winValue = winValue;
    }

    /**
     * Sets the ticket ticketValue
     * @param ticketValue 
     */
    public void setTicketValue(double ticketValue) {
        this.ticketValue = ticketValue;
    }

    /**
     * Returns the ticketValue
     * @return 
     */
    public double getTicketValue() {
        return ticketValue;
    }
    
}
