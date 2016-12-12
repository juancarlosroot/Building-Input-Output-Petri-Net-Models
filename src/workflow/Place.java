/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workflow;

/**
 *
 * @author tonatiuhsun
 */
import java.util.Vector;



public class Place {

    private Vector<String> inputTransitions;
    private Vector<String> outputTransitions;
    private boolean marked;

    private String key;

    public boolean isMarked(){
        return marked;
    }

    public void mark(){
        marked = true;
    }

    public void unmark(){
        marked = false;
    }

    public Place(String key){
        this.key = key;
        inputTransitions = new Vector<String>();
        outputTransitions = new Vector<String>();
        marked = false;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getKey(){
        return key;
    }

    public void addInputTransition(String inputTransition){
        int index = 0;
        while(index < inputTransitions.size() && inputTransition.compareTo(inputTransitions.elementAt(index)) < 0)
            index++;
        inputTransitions.add(index, inputTransition);
    }

    public void addOutputTransition(String outputTransition){
        int index = 0;
        while(index < outputTransitions.size() && outputTransition.compareTo(outputTransitions.elementAt(index)) < 0)
            index++;
        outputTransitions.add(index, outputTransition);
    }

    public String getInputTransitionsString(){ 
        String its = "";
        for(int i = 0; i < inputTransitions.size(); i++)
            its += inputTransitions.elementAt(i) + "|";
        return its;
    }

    public String getOutputTransitionsString(){
        String ots = "";
        for(int i = 0; i < outputTransitions.size(); i++)
            ots += outputTransitions.elementAt(i) + "|";
        return ots;
    }

    public boolean hasInputTransition(String transition){
        return inputTransitions.contains(transition);
    }

    public boolean hasOutputTransition(String transition){
        return outputTransitions.contains(transition);
    }

    public Vector<String> getInputTransitions(){
        return inputTransitions;
    }

    public Vector<String> getOutputTransitions(){
        return outputTransitions;
    }
}