/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workflow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
//import gui.Interface;

/**
 *
 * @author tonatiuhsun
 */
public class processMining {
    
    ProcessNlen pronl = new ProcessNlen();
    process pro = new process();
    tinv Caltin = new tinv();
    PN pn = new PN();
    //Interface gui = new Interface();
    private long executionTime;
    
    public processMining(String chain) 
    {

        pn.PN(chain);
      //34567543  long endTime = System.currentTimeMillis();
    }
    public long getExecutionTime(){
        return executionTime;
    }
    
}
