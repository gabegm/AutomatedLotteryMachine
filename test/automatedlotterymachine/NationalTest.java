/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automatedlotterymachine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author gabegm
 */
public class NationalTest {
    
    public NationalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isNationalTicketAvailable method, of class National.
     */
    @Test
    public void testIsNationalTicketAvailable() throws IOException {
        List<Integer> numbers1 = new ArrayList<>();
        List<Integer> numbers2 = new ArrayList<>();
        for (int x = 1; x <= 10; x++){
            numbers1.add(55);
            numbers2.add(55);
        }
        NationalTicket nationalTicket1 = new NationalTicket(numbers1);
        NationalTicket nationalTicket2 = new NationalTicket(numbers2);
        
        National national = new National();
        national.addNationalTicket(nationalTicket1);
        national.addNationalTicket(nationalTicket2);
        
        assertTrue(nationalTicket1.getNumbers().equals(nationalTicket2.getNumbers())); //both tickets have the same value
        assertFalse(national.isNationalTicketAvailable(nationalTicket2)); //the result is false meaning the ticket was not created
    }
    
    /**
     * Test of deleteNationalTickets method, of class National
     */
    @Test
    public void testDeleteNationalTickets() throws IOException {
        List<Integer> numbers = new ArrayList<>();
        for (int x = 1; x <= 10; x++){
            numbers.add(56);
        }
        NationalTicket nationalTicket = new NationalTicket(numbers);
        
        National national = new National();
        national.addNationalTicket(nationalTicket);
        national.deleteNationalTickets();
        
        assertTrue(national.getNationalTicketList().isEmpty());
        
        File f = new File("nationalTickets.txt");
        assertTrue(!f.exists());
    }
    
    /**
     * Test of writeNationalTicket method, of class National
     * @throws java.io.IOException
     */
    @Test
    public void testWriteNationalTicket() throws IOException {
        List<Integer> numbers = new ArrayList<>();
        for (int x = 1; x <= 10; x++){
            numbers.add(57);
        }
        NationalTicket nationalTicket = new NationalTicket(numbers);
        
        National national = new National();
        national.addNationalTicket(nationalTicket);
        national.writeNationalTicket(nationalTicket);
        BufferedReader br = new BufferedReader(new FileReader("nationalTicket.txt"));     

        assertTrue(br.readLine() != null);
    }
    
}
