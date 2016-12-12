/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package drawers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import workflow.Place;
import workflow.Transitions;
//import sequenceanalyzer.Transitions;

/**
 *
 * @author Ana Paula Estrada
 */
public class PipeDrawer {

    PrintWriter pw;
    int placesIndex = 0;
    int transitionsIndex = 0;
    private Hashtable<String,Position> placePositions = new Hashtable<String,Position>();
    private Hashtable<String,Position> transitionPositions = new Hashtable<String,Position>();

    private class Position{
        private int xPosition;
        private int yPosition;

        public Position(int xPosition,int yPosition){
            this.xPosition = xPosition;
            this.yPosition = yPosition;
        }
    }

    public PipeDrawer(String fileName){
        createFile(fileName);
        /* Add places, transitions and arcs here*/
        closeFile();
    }

    public PipeDrawer(String fileName,Hashtable<String,Place> places, LinkedList<String> alph){
        createFile(fileName);

        //Draw transitions
        for(int x = 0;x < alph.size(); x++)
        {
            drawTransition(alph.get(x),false);
        }
        /*for(Enumeration<Transitions> enTrans = transitions.elements(); enTrans.hasMoreElements();){
            Transitions t = enTrans.nextElement();
            drawTransition(t.getKey(),false);
        }*/

        //Draw places
        for(Enumeration<Place> enPlace = places.elements(); enPlace.hasMoreElements();){
            Place p = enPlace.nextElement();
            drawPlace(p.getKey(),p.isMarked(),false);
            Vector<String> inputTransitions = p.getInputTransitions();
            for(int i = 0; i < inputTransitions.size(); i++){
                drawArcFromTransitionToPlace(inputTransitions.elementAt(i),p.getKey());
            }
            Vector<String> outputTransitions = p.getOutputTransitions();
            for(int i = 0; i < outputTransitions.size(); i++){
                drawArcFromPlaceToTransition(p.getKey(),outputTransitions.elementAt(i));
            }
        }
        closeFile();
    }

    private void drawPlace(String placeLabel,boolean isMarked,boolean isObservable){
        placesIndex++;
        int xPosition = 90 * (placesIndex - 1) + 30;
        int yPosition = 15;
        placePositions.put(placeLabel, new Position(xPosition,yPosition));
        int tokens;
        if(isMarked)
            tokens = 1;
        else
            tokens = 0;
        pw.println("<place id=\"" + placeLabel + "\">");
        pw.println("<graphics>");
        pw.println("<position x=\"" + xPosition + ".0\" y=\"" + yPosition + ".0\"/>");
        pw.println("</graphics>");
        pw.println("<name>");
        pw.println("<value>" + placeLabel+ "</value>");
        pw.println("<graphics>");
        pw.println("<offset x=\"5.0\" y=\"35.0\"/>");
        pw.println("</graphics>");
        pw.println("</name>");
        pw.println("<initialMarking>");
        pw.println("<value>Default," + tokens + "</value>");
        pw.println("<graphics>");
        pw.println("<offset x=\"0.0\" y=\"0.0\"/>");
        pw.println("</graphics>");
        pw.println("</initialMarking>");
        pw.println("<capacity>");
        if(isObservable)
            pw.println("<value>1</value>");
        else
            pw.println("<value>0</value>");
        pw.println("</capacity>");
        pw.println("</place>");
    }

    private void drawTransition(String transitionLabel,boolean horizontal){
        transitionsIndex++;
        int xPosition = 90 * (transitionsIndex - 1) + 30;
        int yPosition = 225;
        transitionPositions.put(transitionLabel, new Position(xPosition,yPosition));
        int orientation = 0;
        if(horizontal)
            orientation = 90;
        pw.println("<transition id=\"" + transitionLabel + "\">");
        pw.println("<graphics>");
        pw.println("<position x=\"" + xPosition + ".0\" y=\"" + yPosition + ".0\"/>");
        pw.println("</graphics>");
        pw.println("<name>");
        pw.println("<value>" + transitionLabel + "</value>");
        pw.println("<graphics>");
        pw.println("<offset x=\"5.0\" y=\"35.0\"/>");
        pw.println("</graphics>");
        pw.println("</name>");
        pw.println("<orientation>");
        pw.println("<value>" + orientation + "</value>");
        pw.println("</orientation>");
        pw.println("<rate>");
        pw.println("<value>1.0</value>");
        pw.println("</rate>");
        pw.println("<timed>");
        pw.println("<value>false</value>");
        pw.println("</timed>");
        pw.println("<infiniteServer>");
        pw.println("<value>false</value>");
        pw.println("</infiniteServer>");
        pw.println("<priority>");
        pw.println("<value>1</value>");
        pw.println("</priority>");
        pw.println("</transition>");
    }

    private void drawArcFromPlaceToTransition(String place,String transition){
        Position placePosition = placePositions.get(place);
        Position transitionPosition = transitionPositions.get(transition);
        int x1 = placePosition.xPosition;
        int y1 = placePosition.yPosition;
        int x2 = transitionPosition.xPosition;
        int y2 = transitionPosition.yPosition;
        drawArc(place,transition,x1,y1,x2,y2);
    }

    private void drawArcFromTransitionToPlace(String transition,String place){
        Position placePosition = placePositions.get(place);
        Position transitionPosition = transitionPositions.get(transition);
        int x1 = placePosition.xPosition;
        int y1 = placePosition.yPosition;
        int x2 = transitionPosition.xPosition;
        int y2 = transitionPosition.yPosition;
        drawArc(transition,place,x1,y1,x2,y2);
    }

    private void drawArc(String source,String target,int x1, int y1, int x2, int y2){
        //System.out.println("Adding arc from " + source + " to " + target);
        pw.println("<arc id=\"" + source + " to " + target + "\" source=\"" + source + "\" target=\"" + target + "\">");
        pw.println("<graphics/>");
        pw.println("<inscription>");
        pw.println("<value>Default,1</value>");
        pw.println("<graphics/>");
        pw.println("</inscription>");
        pw.println("<tagged>");
        pw.println("<value>false</value>");
        pw.println("</tagged>");
        pw.println("<arcpath id=\"000\" x=\"" + x1 + "\" y=\"" + y1 + "\" curvePoint=\"false\"/>");
        pw.println("<arcpath id=\"001\" x=\"" + x2 + "\" y=\"" + y2 + "\" curvePoint=\"false\"/>");
        pw.println("<type value=\"normal\"/>");
        pw.println("</arc>");
    }

    private void createFile(String fileName){
        try {
             pw = new PrintWriter(new BufferedWriter(new FileWriter("Nets/" + fileName + ".xml")));
             pw.println("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
             pw.println("<pnml>");
             pw.println("<net id=\"Net-One\" type=\"P/T net\">");
             pw.println("<token id=\"Default\" enabled=\"true\" red=\"0\" green=\"0\" blue=\"0\"/>");
        } catch (IOException ex) {
            Logger.getLogger(PipeDrawer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeFile(){
        pw.println("</net>");
        pw.println("</pnml>");
        pw.close();
    }
}
