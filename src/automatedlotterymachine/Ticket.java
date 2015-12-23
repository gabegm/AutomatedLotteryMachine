/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automatedlotterymachine;

import java.io.IOException;

/**
 *
 * @author gabegm
 */
public abstract class Ticket {
    public abstract String CSVFile() throws IOException;
    public abstract String returnCSVNumbers() throws IOException;

}
