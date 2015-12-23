/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automatedlotterymachine;

import java.util.ArrayList;
import java.util.List;
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
public class LottoTicketTest {
    
    public LottoTicketTest() {
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
     * Test of setWinValue method, of class LottoTicket.
     */
    @Test
    public void testSetWinValue() {
        List<Integer> numbers = new ArrayList<>();
        double money = 2.50;
        
        LottoTicket lottoTicket1 = new LottoTicket(1, numbers);//Prima
        lottoTicket1.setTicketValue(money);
        lottoTicket1.calculateWinValue();
        LottoTicket lottoTicket2 = new LottoTicket(2, numbers);//Ambo
        lottoTicket2.setTicketValue(money);
        lottoTicket2.calculateWinValue();
        LottoTicket lottoTicket3 = new LottoTicket(3, numbers);//Terno
        lottoTicket3.setTicketValue(money);
        lottoTicket3.calculateWinValue();
        
        assertTrue(lottoTicket1.getWinValue() != lottoTicket2.getWinValue() && lottoTicket2.getWinValue() != lottoTicket3.getWinValue()); //Although the value of money remains the same, the winValue is changed acroos each type of lotto
    }
    
}
