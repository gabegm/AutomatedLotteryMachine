/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automatedlotterymachine;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
public class AutomatedLotteryMachineTest {
    
    public AutomatedLotteryMachineTest() {
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
     * Test of checkFileExists method, of class AutomatedLotteryMachine.
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.IOException
     */
    @Test
    public void testCheckFileExists() throws NoSuchAlgorithmException, IOException {
        AutomatedLotteryMachine alm = new AutomatedLotteryMachine();
        assertTrue(alm.checkFileExists("password.txt"));
    }

    /**
     * Test of the compareString method, of class AutomatedLotteryMachine
     * @throws NoSuchAlgorithmException 
     */
    @Test
    public void testCompareString() throws NoSuchAlgorithmException {
        AutomatedLotteryMachine alm = new AutomatedLotteryMachine();
        assertTrue(alm.compareString("hello", "hello"));
    }
    
}
