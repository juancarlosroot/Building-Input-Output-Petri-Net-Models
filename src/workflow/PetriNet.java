/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package workflow;

import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author Ana Estrada
 */
public class PetriNet {
    private int[][] incidenceMatrix;
    private int placesNumber;
    private int transitionsNumber;
    private Vector<String> transitionLabels;
    private Vector<String> placeLabels;
    

    public PetriNet(Hashtable<String,Place> places, LinkedList<String> alph){
        this.placesNumber = places.size();
        this.transitionsNumber = alph.size();
        transitionLabels = new Vector<String>();
        placeLabels = new Vector<String>();
        for(Enumeration<Place> enPlace = places.elements(); enPlace.hasMoreElements();){
            Place p = enPlace.nextElement();
            placeLabels.add(p.getKey());
        }
        for(int x = 0;x < alph.size(); x++)
        {
            transitionLabels.add(alph.get(x));
        }
        /*for(Enumeration<Transitions> enTrans = alph; enTrans.hasMoreElements();){
            Transitions t = enTrans.nextElement();
            transitionLabels.add(t.getKey());
        }*/
        incidenceMatrix = new int[placesNumber][transitionsNumber];
        for(int i = 0; i < placesNumber; i++){
            for(int j = 0; j < transitionsNumber; j++){
                if(places.get(placeLabels.elementAt(i)).hasInputTransition(transitionLabels.elementAt(j))){
                    incidenceMatrix[i][j] = 1;
                }
                else if(places.get(placeLabels.elementAt(i)).hasOutputTransition(transitionLabels.elementAt(j))){
                    incidenceMatrix[i][j] = -1;
                }
                else{
                    incidenceMatrix[i][j] = 0;
                }
            }
        }
        printIncidenceMatrix();
    }

    private void printIncidenceMatrix(){
    //private void printMatrix(Matrix m, String matrixName, String[] columnNames, String[] rowNames, int columnLength, int precision){
        System.out.println("Indicence matrix:");

        String stringMatrix[][] = getMatrixAsString(0);
        String[] columnNames = getVectorAsString(transitionLabels);
        String[] rowNames = getVectorAsString(placeLabels);

        int columnLength = 5;

        String format1 = "";
        for(int i = 1 ; i <= transitionsNumber; i++){
            format1 += "%" + i + "$" + columnLength + "s";
        }
        format1 +="\n";

        String whites = "";
        for(int i = 0; i < columnLength; i++)
            whites += " ";

        System.out.format(String.format(whites + format1, (Object[])columnNames));

        for(int i = 0; i < placesNumber; i++){
            System.out.format("%1$-" + columnLength + "s", rowNames[i]);
            System.out.format(format1, (Object[])stringMatrix[i]);
        }
        System.out.println();

    }

    private String[] getVectorAsString(Vector<String> vector){
        String string[] = new String[vector.size()];
        for(int i = 0; i < vector.size(); i++)
            string[i] = vector.elementAt(i);
        return string;
    }

    private String[][] getMatrixAsString(int precision){
        String stringMatrix[][] = new String[placesNumber][transitionsNumber];
        String format ="0.";
        for(int i = 0; i < precision; i++)
            format +="0";
        DecimalFormat df = new DecimalFormat(format);
        for(int j = 0; j < placesNumber; j++){
            for(int i = 0; i < transitionsNumber; i++){
                stringMatrix[j][i] = df.format(incidenceMatrix[j][i]);
            }
        }
        return stringMatrix;
    }

    private int getPlaceIndex(String place){
        return placeLabels.indexOf(place);
    }

    private int getTransitionIndex(String transition){
        return transitionLabels.indexOf(transition);
    }
}
