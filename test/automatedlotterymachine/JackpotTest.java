/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automatedlotterymachine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gabegm
 */
public class JackpotTest {
    
    public JackpotTest() {
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
     * Test of getJackpotWinner method, of class Jackpot.
     */
    @Test
    public void testGetJackpotWinner() {
        Jackpot jackpot = new Jackpot();
        jackpot.getJackpotWinner();
        
        assertTrue(jackpot.getWinningJackpot() != 200000); //if true the jackpot has indeed increased since no one won
        
    }
    
}
