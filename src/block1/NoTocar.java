/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class NoTocar {

    ArrayList<WVector> listW;//
    ArrayList<WVector> listE;//
    ArrayList<WVector> listRE;//
    int index;

    private ArrayList<Float> time;

    public NoTocar() {
        listW = new ArrayList<WVector>();
        listE = new ArrayList<>();//
        listRE = new ArrayList<WVector>();//variables globales inicializadas en el constructor del objeto
        index = 0;
        time = new ArrayList<Float>();
    }

    public static void main(String[] args) {
        String inputFileName = "e.txt";// opcional para guardar los archivos
        //String outputFileName = "05_10_2011-2_1.txt";
        String outputFileName = "pru.txt";// lectura de archivo

        NoTocar block1 = new NoTocar();

        block1.readFile(inputFileName, outputFileName);

        System.out.println(block1.listW.size());//W

        System.out.println(block1.listE.size());//E

        System.out.println(block1.listRE.size());//RE

    }

    /**
     * No retorna nada, guarda los valores de E y RE en las listas(globales).
     * Sirve para dar los parámetros de entrada del programa
     * <p>
     * Guarda los vectores en objetos
     *
     * @param inputFileName nombre del txt que vamos a leer(vectores w)
     * @param outputFileName nombre del archivo donde se guardaran los nuevos
     * valores(por el momento no lo uso)
     * @return --
     * @see WVector
     */
    public void readFile(String inputFileName, String outputFileName) {
        File nFile = new File(inputFileName);
        try (
                BufferedReader br = new BufferedReader(new FileReader(outputFileName));// buffer para leer
                FileWriter fw = new FileWriter(nFile.getAbsoluteFile());// obj para abrir archivo y excribir
                BufferedWriter bw = new BufferedWriter(fw);// obj para guardar y preparar para escribir
                PrintWriter out = new PrintWriter(bw);/*obj para escribir*/) {
            String sPastLine = null;// lo usamos para 
            String sCurrentLine = null;// iterar en la línea actual del archivo

            if (!nFile.exists()) {
                nFile.createNewFile();// si el archivo no existe, se crea
            }

            while ((sCurrentLine = br.readLine()) != null) {// iteramos igualando a la línea actual
                if (sPastLine == null) {// agregamos la primera línea a la lista de Ws(se puede optimizar)
                    sPastLine = sCurrentLine;
                    Integer[] array_Inputs = new Integer[sCurrentLine.split("\t")[1].length()];
                    Integer[] array_Outputs = new Integer[sCurrentLine.split("\t")[2].length()];

                    for (int i = 0; i < sCurrentLine.split("\t")[1].length(); i++) {
                        int w0 = Character.getNumericValue(sPastLine.split("\t")[1].charAt(i));
                        array_Inputs[i] = w0;
                    }

                    for (int i = 0; i < sCurrentLine.split("\t")[2].length(); i++) {
                        int w0 = Character.getNumericValue(sPastLine.split("\t")[2].charAt(i));
                        array_Outputs[i] = w0;
                    }
                    listW.add(new WVector(array_Inputs, array_Outputs, Float.parseFloat(sCurrentLine.split("\t")[0])));
                } else {

                    if (sCurrentLine.split("\t")[1].length() == sPastLine.split("\t")[1].length()
                            && sCurrentLine.split("\t")[2].length() == sPastLine.split("\t")[2].length()) {
                        Integer[] array_Inputs = new Integer[sCurrentLine.split("\t")[1].length()];
                        Integer[] array_Outputs = new Integer[sCurrentLine.split("\t")[2].length()];

                        //current W
                        Integer[] array_Inputs_w = new Integer[sCurrentLine.split("\t")[1].length()];
                        Integer[] array_Outputs_w = new Integer[sCurrentLine.split("\t")[2].length()];

                        for (int i = 0; i < sCurrentLine.split("\t")[1].length(); i++) {
                            int w0 = Character.getNumericValue(sPastLine.split("\t")[1].charAt(i));//valor de entrada para la w pasada
                            int w1 = Character.getNumericValue(sCurrentLine.split("\t")[1].charAt(i));
                            array_Inputs_w[i] = w1;
                            array_Inputs[i] = w1 - w0;// para agregar a ER y E
                        }

                        for (int i = 0; i < sCurrentLine.split("\t")[2].length(); i++) {
                            int w0 = Character.getNumericValue(sPastLine.split("\t")[2].charAt(i));//valor de salida para la w pasada
                            int w1 = Character.getNumericValue(sCurrentLine.split("\t")[2].charAt(i));
                            array_Outputs_w[i] = w1;
                            array_Outputs[i] = w1 - w0;// para agregar a ER y E
                        }
                        //agrega current a la lista de Ws
                        listW.add(
                                new WVector(
                                        array_Inputs_w, array_Outputs_w, Float.parseFloat(sCurrentLine.split("\t")[0])
                                )
                        );
                        /*
                            1. Hace un split de la línea, utiliza como separador el tabulador(\n)
                            2. Creamos un objeto vector con los nuevos valores
                            3. Agregamos el objeto donde corresponda, se agregan a E la resta (vectores - 1)
                                Si se identifica que su salida es diferente a cero se agregan a RE
                         */
                        WVector wVector = new WVector(array_Inputs, array_Outputs, Float.parseFloat(sCurrentLine.split("\t")[0]));

                        if (outputDifferentTo0(array_Outputs)) {
                            wVector.setIndex(index);
                            this.listRE.add(wVector);
                            //System.out.println("index "+index);
                        }
                        this.listE.add(wVector);
                        index += 1;
                    }

                    sPastLine = sCurrentLine;//Guardando la línea pasada 
                }

            }
            //cerramos todo lo que tenga que ver con leer y escribir archivos
            out.close();
            bw.close();
            fw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean outputDifferentTo0(Integer[] array_Outputs) {
        for (int i = 0; i < array_Outputs.length; i++) {
            if (array_Outputs[i] != 0) {
                return true;
            }
        }
        return false;
    }

}
