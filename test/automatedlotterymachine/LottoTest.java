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
public class LottoTest {
    
    public LottoTest() {
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
     * Test of getLottoWinner method, of class Lotto.
     */
    @Test
    public void testGetLottoWinner() {
        List<Integer> numbers = new ArrayList<>();
        for (int x = 1; x <=5; x++){
            numbers.add(50);
        }
        
        LottoTicket lottoTicket1 = new LottoTicket(2, numbers);
        lottoTicket1.setTicketValue(2.50);
        lottoTicket1.calculateWinValue();
        LottoTicket lottoTicket2 = new LottoTicket(2, numbers);
        lottoTicket2.setTicketValue(2.50);
        lottoTicket2.calculateWinValue();
        
        Lotto lotto = new Lotto();
        lotto.addLottoTicket(lottoTicket1);
        lotto.addLottoTicket(lottoTicket1); 
        lotto.randomNumbers.clear();
        for (int x = 1; x <=5; x++){
            lotto.randomNumbers.add(50);
        }
        lotto.getLottoWinner();
        
        assertTrue(lotto.getCount() > 1); //if true there is more than one winner
    }

}
