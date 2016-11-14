/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block1;

/**
 *
 * @author juancarlosroot
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Block1 {

    ArrayList<WVector> listE;
    ArrayList<WVector> listRE;

    public Block1() {
        listE = new ArrayList<WVector>();
        listRE = new ArrayList<WVector>();
    }

    public static void main(String[] args) {
        String inputFileName = "e.txt";
        String outputFileName = "05_10_2011-2_1.txt";

        Block1 block1 = new Block1();

        block1.readFile(inputFileName, outputFileName);

        System.out.println(block1.listE.size());//E
        System.out.println(block1.listRE.size());//RE

        block1.Algorithm1();

//        System.out.println(block1.listE.indexOf(block1.listRE.get(0)));
    }

    public void readFile(String inputFileName, String outputFileName) {
        File nFile = new File(inputFileName);
        try (
                BufferedReader br = new BufferedReader(new FileReader(outputFileName));
                FileWriter fw = new FileWriter(nFile.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);) {
            String sPastLine = null;
            String sCurrentLine = null;

            if (!nFile.exists()) {
                nFile.createNewFile();
            }

            while ((sCurrentLine = br.readLine()) != null) {
                if (sPastLine == null) {
                    sPastLine = sCurrentLine;
                } else {
                    String sInputs = "";
                    String sOutputs = "";

                    if (sCurrentLine.split("\t")[1].length() == sPastLine.split("\t")[1].length()
                            && sCurrentLine.split("\t")[2].length() == sPastLine.split("\t")[2].length()) {
                        Integer[] array_Inputs = new Integer[sCurrentLine.split("\t")[1].length()];
                        Integer[] array_Outputs = new Integer[sCurrentLine.split("\t")[2].length()];

                        for (int i = 0; i < sCurrentLine.split("\t")[1].length(); i++) {
                            int w0 = Character.getNumericValue(sPastLine.split("\t")[1].charAt(i));
                            int w1 = Character.getNumericValue(sCurrentLine.split("\t")[1].charAt(i));
                            array_Inputs[i] = w1 - w0;
                            // sInputs += Integer.toString(w1 - w0);
                        }

                        for (int i = 0; i < sCurrentLine.split("\t")[2].length(); i++) {
                            int w0 = Character.getNumericValue(sPastLine.split("\t")[2].charAt(i));
                            int w1 = Character.getNumericValue(sCurrentLine.split("\t")[2].charAt(i));
                            array_Outputs[i] = w1 - w0;
                            // sOutputs += Integer.toString(w1 - w0);
                        }

                        WVector wVector = new WVector(array_Inputs, array_Outputs);

                        if (outputDifferentTo0(array_Outputs)) {
                            // out.println(sInputs + "\t" + sOutputs);
                            this.listRE.add(wVector);
                        }
                        this.listE.add(wVector);
                    }

                    sPastLine = sCurrentLine;//Guardando la línea pasada 🐶
                }

            }

            out.close();
            bw.close();
            fw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Algorithm1() {
        //1
        Set<IntegerArray> OES = new HashSet<IntegerArray>();
        int pastRE = -1000;
        boolean added = false;

        int lastAdded = -1000;

        for (int i = 0; i < listRE.size(); i++) {// 2
            if (!listRE.get(i).isEntrance0()) {
                lastAdded = i;
                lastAdded = listE.indexOf(listRE.get(lastAdded));

                added = OES.add(listE.get(lastAdded).getInputs());
            }// else falta por poner
            else
            {
                lastAdded = listE.indexOf(listRE.get(lastAdded)) - 1;    
                System.out.println(lastAdded);
                added = OES.add(listE.get(lastAdded).inputs);
            }

            if (added) {

                pastRE = (pastRE < 0 ? 0 : pastRE);

                Boolean[] nSCRE = recalculateSCRE(pastRE, lastAdded);

                listE.get(lastAdded).getScre().setSCRE(nSCRE);
                if (listRE.indexOf(listE.get(lastAdded)) != -1) {
                    listRE.get(
                            listRE.indexOf(listE.get(lastAdded))
                    ).getScre().setSCRE(nSCRE);
                }

                listRE.get(lastAdded).print();
//                System.out.println(listE.indexOf(listRE.get(lastAdded)));
                added = false;

                if (pastRE < i) {
                    pastRE = i;
                }
            }

            //2.2 Compute SCRE(k) from w(k)
        }

        System.out.println(OES.size());
    }

    private Boolean[] recalculateSCRE(int pastRE, int currentRE) {

        int indexPastRE = listE.indexOf(listRE.get(pastRE));
        int indexCurrentRE = listE.indexOf(currentRE);

        int i = indexPastRE + 1;

        Boolean[] nSCRE = new Boolean[listRE.get(currentRE).getInputs().getArray().length];
        int[] counter = new int[listRE.get(currentRE).getInputs().getArray().length];

        while (i < indexCurrentRE) {
            if (i != pastRE + 1) {
                for (int c = 0; c < counter.length; c++) {
                    if (listE.get(currentRE - 1).getInputs().getArray()[c] != listE.get(currentRE).getInputs().getArray()[c]) {
                        counter[c]++;
                    }
                }
            }

            i++;
        }

        for (i = 0; i < counter.length; i++) {
            if (counter[i] >= 2) {
                nSCRE[i] = true;
            }
        }
        return nSCRE;
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
