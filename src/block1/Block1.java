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
import java.util.*;
import java.util.Arrays;
import workflow.processMining;

public class Block1 {

    ArrayList<WVector> listW;//
    ArrayList<WVector> listE;//
    ArrayList<WVector> listRE;//
    int index;
    private SCRE scre;
    private SRE sre;
    private SOE soe;
    private ArrayList<Fr> fr;
    private ArrayList<Float> time;
    private ArrayList<IntegerArray> FInputs;
    private Set<IntegerArray> FOO;
    private ArrayList<FS> FS;
    private ArrayList<Fr> F;
    private Set<SRE> OES;
    //private ArrayList<FS> S;
    private ArrayList<Fr> S;
    private ArrayList<ST> ST;
    private Set<SRE> T;
    private ArrayList<Lambda> Lambda;
    private ArrayList<SOE> OESPrueba;
    //private ArrayList<String> writeFile;

    public Block1() {
        listW = new ArrayList<WVector>();
        listE = new ArrayList<WVector>();//
        listRE = new ArrayList<WVector>();//variables globales inicializadas en el constructor del objeto
        index = 0;
        time = new ArrayList<Float>();
        this.fr = new ArrayList<>();
        FInputs = new ArrayList<>();        
        FOO = new HashSet<>();
        FS = new ArrayList<>();
        F = new ArrayList<>();
        OES = new HashSet<>();
        S = new ArrayList<>();
        ST = new ArrayList<>();
        T = new HashSet<>();
        Lambda = new ArrayList<>();
        OESPrueba = new ArrayList<>();
    }

    public static void main(String[] args) {
        String inputFileName = "e.txt";// opcional para guardar los archivos
        //String outputFileName = "05_10_2011-2_1.txt";
        String outputFileName = "pru.txt";

        Block1 block1 = new Block1();

        block1.readFile(inputFileName, outputFileName);
        
        System.out.println(block1.listW.size());//W
        
        System.out.println(block1.listE.size());//E
        
        System.out.println(block1.listRE.size());//RE

        block1.Algorithm1();

        block1.Algorithm2();
        block1.PrintS();
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
                    String sInputs = "";
                    String sOutputs = "";

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

    public int Algorithm1() {       
        
        FS tempFS;
        Fr tempFr;
        IntegerArray c;
        Integer[] REInputs,REOutputs;
        int r = 0, k;
        float timeFr;
        for (int h = 0; h < listRE.size(); h++) {
            k = listRE.get(h).getIndex();
            if (!listRE.get(h).isEntrance0()) {                
                this.sre = new SRE(listE.get(k).getInputs());
            }
            else{
                this.sre = new SRE(listE.get(k-1).getInputs());
            }
            this.scre = new SCRE(listW.get(k+1).getInputs());
            if(h == 0){
                recalculateSCRE(k);                
                //PrintSCRE(k);
            }
            else {
                //System.out.println(listRE.get(h-1).getIndex() + " "+ k);
                recalculateSCRE2(listRE.get(h-1).getIndex(),k);                
                //PrintSCRE(k);
            }
            this.soe = new SOE(listE.get(k).getOutputs());
            //PrintSOE(k);
            writeFile("e.txt", h);
            tempFr = new Fr(this.soe, this.sre, this.scre, h);
            this.fr.add(tempFr);
            //Concatenar Inputs y Outputs   
            REInputs=listRE.get(h).getInputs().getArray();
            REOutputs=listRE.get(h).getOutputs().getArray();         
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.addAll(Arrays.asList(REInputs));
            temp.addAll(Arrays.asList(REOutputs));
            Integer [] concatedArgs = temp.toArray(new Integer[REInputs.length+REOutputs.length]);
            OESPrueba.add(this.soe);
        //Termina concatenar    
            c = new IntegerArray(concatedArgs);
            if(FOO.add(c)){
                timeFr = listRE.get(h).getTime();
                FInputs.add(c);
                F.add(tempFr);
                r += 1;                
                time.add(timeFr);
                tempFS = new FS(h,timeFr);
                FS.add(tempFS);
            }
            else{   
                timeFr = listRE.get(h).getTime();
                tempFS = new FS(tempFr.getId(),timeFr);
                FS.add(tempFS);
                FS.get(h).setIdFr(recalculateId(r,c));         
                time.add(timeFr);
            }
        }
        PrintFS();
        PrintF();
        return 0;
    }
    
    public void Algorithm2()
    {
        //Parte 1: Declarar ArrayList S, ST y el set T; así como el ArrayList para Omega. 
        System.out.println("=== EMPIEZA ALGORITMO 2 ===");
        
        ArrayList<ArrayList<Fr>> Omega = new ArrayList<>();
                
        //Parte 2: Finding functions with the sale output event (oe)
        for(int i=0; i<OESPrueba.size(); i++)
        {
            Omega.add(new ArrayList<>());
            Boolean[] oe = OESPrueba.get(i).getSOE();

            for(int j=0; j<F.size(); j++)
            {
                int idParaCalcularRho = FS.get(j).getIdFr();
                SOE Rho = F.get(idParaCalcularRho).getSOE();
                if(Rho.getSOE() == oe)
                    Omega.get(i).add(F.get(j));
            }
        }
        
        //Parte 3: Gathering the functions in Omega(oe) that have the same SRE
        ArrayList<Fr> Omegas = new ArrayList<>();
        ArrayList<ArrayList<Fr>> Omegai = new ArrayList<>();
        Set<SRE> Gei = new HashSet<>();
        Fr Ff = null;
        Fr Fr = null;
        Lambda LambdaAux = null;
        ArrayList<Fr> LambdaPrima = new ArrayList<>();
        //ArrayList<FS> LambdaPrima = new ArrayList<>();
        //Set<SRE> LambdaPrima = new HashSet<>();
        Iterator<SRE> TIt = T.iterator();
        ST ObjetoST;
        for(int h=0; h<OESPrueba.size(); h++)
        {
            Omegai.add(new ArrayList<>());
            int i = 1;
            while(Omega.get(h) != null)
            {
                if(Omega.get(h).isEmpty() == false)
                {
                    Ff = Omega.get(h).get(0);
                    for(int j=0; j<Omega.get(h).size(); j++)
                    {
                        Fr = Omega.get(h).get(j);
                        if(Ff.getSRE() == Fr.getSRE())
                        {
                            Gei.add(Fr.getSRE());
                            Omegai.get(h).add(Fr);
                            Omega.get(h).remove(Fr);//SE SALT6A UN ELEMENTO , CORREGIR
                        }
                    }
                    T.add(Fr.getSRE());
                    if(Omegai.size() > i)
                    {
                        int TamanoOmegai = Omegai.get(i).size();
                        for(int j=0; j<TamanoOmegai; j++)
                        {
                            //LambdaPrima.add(FS.get(j));
                            //TIt = TIt.hasNext();
                            //LambdaPrima.add(T.equals(TIt));
                            if(i < Omegai.size())
                            {
                                if(j < Omegai.get(i).size())
                                    LambdaPrima.add(Omegai.get(i).get(j));
                                if(Omegai.get(i).size() > j)
                                    Omegas.add(Omegai.get(i).get(j));
                                i++;
                            }
                            TIt.hasNext();
                        }
                    }
                }
                else
                    break;
            }
        }
        
        //Parte 4: Building compound functions
        for(int i=0; i<Omegas.size(); i++)
        {
            LambdaAux = new Lambda(Gei, Omegas.get(i));
            Lambda.add(LambdaAux);
        }
        
        //Parte 5: Transition sequence formation
        for(int i=0; i<FS.size(); i++)
        {
            if(LambdaPrima.isEmpty() == false)
            {
                if(LambdaPrima.size() > i)
                {
                    S.add(LambdaPrima.get(i));
                    ObjetoST = new ST(LambdaPrima.get(i), FS.get(i).getTime());
                    ST.add(ObjetoST);
                }
                else
                    break;
            }
        }
        
        System.out.println("=== TERMINA ALGORITMO 2 ===");
    }
       
    public int recalculateId(int h, IntegerArray in){
        int i = h-1;       
        while (i>=0){
            if(FInputs.get(i).equals(in)){
                return i;
            }
            i--;           
        }
        return 0;
    }
    
    public void recalculateSCRE(int k){
        Integer[] inputs;
        inputs = listE.get(k).getInputs().getArray();
        for (int i = 0; i < inputs.length; i++ ){
            if(inputs[i]==1||inputs[i]==-1){
                this.scre.setSCRE(null, i);
            }
        }        
    }
    
    public void recalculateSCRE2(int pastRE, int currentRE){
        Integer[] inputs, changes;        
        int length = listE.get(currentRE).getInputs().getArray().length;
        changes = new Integer[length];
        for (int i = 0; i < length; i++){
            changes[i] = 0;
        }
        for (int i = pastRE+1; i < currentRE; i++ ){
            inputs = listE.get(i).getInputs().getArray();
            for (int j = 0; j < length; j++ ){
                if((inputs[j]==1)||(inputs[j]==-1)){
                    changes[j] += 1;
                }
                //System.out.println("inputs "+changes[j]+" "+j);
            }
        }
        for (int i = 0; i < length; i++ ){
            if(changes[i]>1){
                this.scre.setSCRE(null, i);
            }            
        }
        recalculateSCRE(currentRE);        
    }
        
    public void PrintSCRE(int k){
        Boolean[] temp = this.scre.getSCRE();
        int length = listE.get(k).getInputs().getArray().length;
        for (int i = 0; i < length; i++ ){
            System.out.println("scre "+temp[i]);
        }
    }
    
    public void PrintSOE(int k){
        Boolean[] temp = this.soe.getSOE();
        int length = listE.get(k).getOutputs().getArray().length;
        for (int i = 0; i < length; i++ ){
            System.out.println("soe "+temp[i]);
        }
    }
    
    public void writeFile(String outputFileName, int k) {
        File nFile = new File(outputFileName);
        Integer[] in;
        Integer[] ou;
        in = listRE.get(k).getInputs().getArray();
        ou = listRE.get(k).getOutputs().getArray();
        try (                
                FileWriter fw = new FileWriter(nFile.getAbsoluteFile(),true);// obj para abrir archivo y excribir
                BufferedWriter bw = new BufferedWriter(fw);// obj para guardar y preparar para escribir
                PrintWriter out = new PrintWriter(bw);/*obj para escribir*/) {
            for (int i = 0; i < in.length; i++ ){
                out.append(in[i]+"\t");
            }
            out.append("\t");            
            for (int i = 0; i < ou.length; i++ ){
                out.append(ou[i]+"\t");
            }     
            out.append("\n");
            out.close();
            bw.close();
            fw.close();            
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
    
    public void PrintF(){
        for (int i = 0; i < F.size(); i++ ){
            System.out.println("F id= "+F.get(i).getId());
        }
    }
    public void PrintFS(){
        for (int i = 0; i < FS.size(); i++ ){
            System.out.println("FS id= "+FS.get(i).getIdFr()+
                    "  FS time= "+FS.get(i).getTime());
        }
    }
    
    public void PrintS(){
        String cad="";
        for(Fr f: S){
           cad=cad+"T"+f.getId()+" ";          
        }
        cad=cad.substring(0, cad.length()-1);
        processMining ps=new processMining(cad);
    }

}
