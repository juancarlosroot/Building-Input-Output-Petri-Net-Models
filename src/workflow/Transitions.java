/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workflow;



/**
 *
 * @author tonatiuhsun
 */
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


public class Transitions {

    private String key;
    private boolean firstTime = true;
    private boolean secondTime = false;
    private Vector<String> intermediateTransitions;
    private Hashtable<String,IntermediateTransition> intermediateTransitionsCounter;
    private Vector<String> tComponent;  //Always between two apparitions of key
    private double occurrencesNumber;
    private Vector<String> Cycs; //key belongs to tComponent
    private Vector<String> concurrentTransitions;
    private Vector<String> inputPlaces;
    private Vector<String> outputPlaces;

    public void addInputPlace(String place){
        inputPlaces.add(place);
    }

    public void addOutputPlace(String place){
        outputPlaces.add(place);
    }

    public Vector<String> getInputPlaces(){
        return inputPlaces;
    }

    public Vector<String> getOutputPlaces(){
        return outputPlaces;
    }

    public Transitions(String key){
        this.key = key;
        Cycs = new Vector<String>();
        concurrentTransitions = new Vector<String>();
        inputPlaces = new Vector<String>();
        outputPlaces = new Vector<String>();
    }
    public String getKey(){
        return key;
    }

    private boolean isFirstTimeAnalyzed(){
        if(firstTime){
            firstTime =  false;
            secondTime = true;
            return true;
        }
        else
            return false;
    }


    private boolean isSecondTimeAnalyzed(){
        if(secondTime){
            secondTime = false;
            return true;
        }
        else
            return false;
    }



    public void updateIntermediateTransitions(String observedIntermediate){
        if(!observedIntermediate.equals(getKey())){
            if(!intermediateTransitions.contains(observedIntermediate))
                intermediateTransitions.add(observedIntermediate);
        }
        else if(isFirstTimeAnalyzed()){
                occurrencesNumber = 1;
                intermediateTransitions = new Vector<String>();
                intermediateTransitionsCounter = new Hashtable<String,IntermediateTransition>();
                tComponent = new Vector<String>();
        }
        else if(isSecondTimeAnalyzed()){
            occurrencesNumber++;
            intermediateTransitionsCounter.put(key, new IntermediateTransition(key));
            tComponent.add(getKey());
            for(int i = 0; i < intermediateTransitions.size(); i++){
                tComponent.add(intermediateTransitions.elementAt(i));
                intermediateTransitionsCounter.put(intermediateTransitions.elementAt(i), new IntermediateTransition(intermediateTransitions.elementAt(i)));
            }
            intermediateTransitions = new Vector<String>();
        }
        else{
            occurrencesNumber++;
            intermediateTransitionsCounter.get(key).incrementCounter();
            for(int i = tComponent.size() - 1; i > 0; i--){
                if(!intermediateTransitions.contains(tComponent.elementAt(i))){
                    tComponent.removeElementAt(i);
                }
            }
            for(int i = 0; i < intermediateTransitions.size(); i++){
                if(!intermediateTransitionsCounter.containsKey(intermediateTransitions.elementAt(i))){
                    intermediateTransitionsCounter.put(intermediateTransitions.elementAt(i), new IntermediateTransition(intermediateTransitions.elementAt(i)));
                }
                else{
                    intermediateTransitionsCounter.get(intermediateTransitions.elementAt(i)).incrementCounter();
                }
            }
            intermediateTransitions = new Vector<String>();
        }
    }

    public double getOccurrencesNumber(){
        return occurrencesNumber;
    }

    public void printOccurrencesNumber(){
        System.out.println("Occ(" + getKey() + ") = " + (int)occurrencesNumber);
    }

    public void printTcomponent(){
        System.out.print("PS(" + getKey() + ") = { ");
        for(int i = 0; i < tComponent.size(); i++)
            System.out.print(tComponent.elementAt(i) + " ");
        System.out.println("}");
    }

    public void printCycs(){
        System.out.print("InvRD(" + getKey() + ") = { ");
        for(int i = 0; i < Cycs.size(); i++)
            System.out.print(Cycs.elementAt(i) + " ");
        System.out.println("}");
    }

    public void printProbTComponent(){
        System.out.print("ProbCyc(" + getKey() + ") = { ");
        DecimalFormat df = new DecimalFormat("0.00");

        for(Enumeration<IntermediateTransition> en = intermediateTransitionsCounter.elements(); en.hasMoreElements();){
            IntermediateTransition ie = en.nextElement();
            double prob = (double)(ie.getCounter()/(occurrencesNumber - 1));
            System.out.print(df.format(prob) + ie.getKey() + " ");
        }
        System.out.println("}");
    }

    public Vector<String> getComponentPairs(){
        Vector<String> pairs = new Vector<String>();
        for(int i = 0; i < tComponent.size(); i++){
            for(int j = i + 1; j <tComponent.size(); j++){
                pairs.add(tComponent.elementAt(i) + "|" + tComponent.elementAt(j));
            }
        }
        return pairs;
    }

    public boolean hasIntermediateTransition(String transition){
        return tComponent.contains(transition);
    }

    public Vector<String> getTcomponent(){
        return tComponent;
    }

    public void addTransitionToCycs(String transition){
        Cycs.add(transition);
    }

    public void addConcurrentTransition(String transition){
        if(!concurrentTransitions.contains(transition)){
            concurrentTransitions.add(transition);
            //System.out.println(key + " is concurrent to " +  transition);
        }
    }

    public boolean isConcurrentTo(String transition){
        return concurrentTransitions.contains(transition);
    }
    
    public Vector<String> getCycs(){
        return Cycs;
    }

    public Vector<String> getConcurrentTransitions(){
        return concurrentTransitions;
    }
}