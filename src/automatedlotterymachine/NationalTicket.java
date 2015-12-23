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
public class NationalTicket extends Ticket {
    private static int id;
    private final double ticketValue;
    private final List<Integer> numbers;

    /**
     * Constructor to the NationalTicket class
     * @param numbers 
     */
    public NationalTicket(List<Integer> numbers) {
        ++id;
        this.setId(id);
        this.numbers = numbers;
        this.ticketValue = 10.00;
    }

    /**
     * Outputs the ticket to the client
     * @return 
     */
    @Override
    public String toString() {
        return "NationalTicket{" + "id=" + id + ", ticketValue=" + ticketValue + ", numbers=" + numbers + '}';
    }
    
    /**
     * Returns the numbers in a CSV fashion
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
     * Outputs CSVs
     * @return
     * @throws IOException 
     */
    @Override
    public String CSVFile() throws IOException {
        return getId() + "," + getTicketValue() + "," + returnCSVNumbers();
    }

    /**
     * Returns the ticket id
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the ticket value
     * @return 
     */
    public double getTicketValue() {
        return ticketValue;
    }

    /**
     * Returns the numbers
     * @return 
     */
    public List<Integer> getNumbers() {
        return numbers;
    }

    /**
     * Sets the ticket id
     * @param id 
     */
    public final void setId(int id) {
        NationalTicket.id = id;
    }
    
}
