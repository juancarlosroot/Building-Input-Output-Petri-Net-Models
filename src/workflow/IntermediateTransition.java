/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workflow;

/**
 *
 * @author tonatiuhsun
 */

public class IntermediateTransition {

    private double counter;
    private String key;

    /**
     * Bla bla
     * @param key The key name for the new transition
     */
    public IntermediateTransition(String key){
        counter = 1;
        this.key = key;
    }

    public void incrementCounter(){
        counter++;
    }

    public double getCounter(){
        return counter;
    }

    public String getKey(){
        return key;
    }
}

