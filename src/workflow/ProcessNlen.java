/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workflow;


import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Arrays;
/**
 *
 * @author tonatiuhsun
 */
public class ProcessNlen {
    
    
    LinkedList<String> lol = new LinkedList<String>();
    String in = " ";
    int veces = 0;
    process seqNlth = new process();
    //tinv uni = new tinv();
    
    public double CondProb(String chain, String S)
    {
        int count = 0;
        String[] et = chain.split(" ");
        String[] seq = S.split(" "); 
        int OccurSeq = 0, OccurPre = 0;
        double CondProb = 0;
        for(int x = 0; x < et.length; x++)
        {
            //System.out.println(et[x]);
            if(et[x].equals(seq[0]))
            {
                for(int y = 0; y < (seq.length - 1); y++)
                {
                    if((x + y) < et.length){
                    if(et[x + y].equals(seq[y]))
                    {
                        count = count + 1;
                    }
                    }
                    if(count == (seq.length - 1))
                    {
                        OccurPre = OccurPre + 1;
                        if((x < et.length - 1) && (x != et.length) )
                        {x = x + (seq.length - 1);
                        if(et[x].equals(seq[seq.length - 1]))
                        {
                            OccurSeq = OccurSeq + 1;
                        }}
                    }
                }
                count = 0;
            }
            
        }
        //System.out.println(OccurSeq);
        //System.out.println(OccurPre);
        CondProb = (double) OccurSeq /(double) OccurPre;
        return CondProb;
    }
    public int Position(String chain,String S,int i)
    {
        int pos = 0,aux = 0, count = 0;
        String[] et = chain.split(" ");
        String[] seq = S.split(" ");
        for(int x = 0; x < et.length; x++)
        {
            if(et[x].equals(seq[0]))
            {
                if(seq.length > 1)
                {
                for(int y = 1; y < seq.length ; y++)
                {
                    if((x + y) < et.length){
                    if(et[x + y].equals(seq[y]))
                    {
                        count = count + 1;
                    }
                    }
                    if(count == (seq.length - 1)){
                        x = x + (seq.length - 1);
                        aux = aux + 1;
                    }
                    
                }
                count = 0;
                }
                else{
                    aux = aux +1;
                }
            }
            if(aux == i){
                pos = x;
                break;
            }
        
        }
        return pos + 1;
    }
    public void conbination(String chain)
    {
        
        LinkedList<String> alph = seqNlth.alphabet(chain);
        int n = alph.size(), aux = 0;
        String[] log2 = new String[n*n];// aumenta la n el numero de longitud de las cadenas
        double[] Result = new double[n*n];
        for(int x = 0; x < n; x++)
        {
            for(int y = 0; y < n; y++)
            {
                
                //for(int z = 0; z < n; z++)
                //{
                     log2[aux] = (alph.get(x) +  " " + alph.get(y)); //+  " " + alph.get(z)); 
                     aux = aux + 1;
                //}
            }
            //aux = aux + 1;       
        }
        String[][] rangos = ordenar(log2, chain);
        for(int x = 0; x < log2.length; x++)
        {
                Result[x] = redondear(seqNlth.CondProb(chain, log2[x]), 3);
                //Result[x][y] =  seqNlth.CondProb(chain, log2[x] + " " + alph.get(y));
                //aux = aux + 1;       
        }
        System.out.print("      ");
        for(int w = 0; w < n; w++) 
        {
            System.out.print(alph.get(w) + "       ");
        }
        int c = 0,w = 0;
        System.out.print("StdDev    Rank     RenkRev");
        System.out.println();
        double[] Rank = new double[2*n];
        for(int x = 0; x < log2.length; x++)
        {
            c = log2[x].lastIndexOf(" ");
            System.out.print(log2[x].substring(0, c) + "  ");
            for(int y = 0; y < n; y++)
            {
                System.out.print(Result[x + y] + "  ,  ");
            }
            System.out.print(redondear(seqNlth.StdDev(chain, log2[x].substring(0, c)), 3) + "      ");
            for(int s = 0;s < rangos.length;s++)
            {
                String m = rangos[s][1];
                String ames = log2[x].substring(0, c);
                if(m.equals(ames))
                {
                    System.out.print(rangos[s][2]+ "      ");
                    break;
                }
            }
            //System.out.print(redondear(seqNlth.Rank(chain, log2[x].substring(0, c)), 3) + "      ");
            for(int s = 0;s < rangos.length;s++)
            {
                String m = rangos[s][1];
                String ames = log2[x].substring(0, c)+ "Rev";
                if(m.equals(ames))
                {
                    System.out.print(rangos[s][2]+ "      ");
                    break;
                }
            }
            //String chainRev = seqNlth.reverse(chain);
            //System.out.print(redondear(seqNlth.Rank(chainRev, log2[x].substring(0, c)), 3) + "      ");
            x = x + n - 1;
            System.out.println();
        } 
        
    }
    public double redondear( double numero, int decimales ) 
    {
        return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
    }
    public String[][] ordenar(String[] log2, String chain)
    {
        
        LinkedList<String> alph = seqNlth.alphabet(chain);
        int n = alph.size(), aux = 0;
        int c = 0, y = 0;
        String[][] rango = new String[2*n][3];// aumenta la n el numerode longitud de la cadena menos una vez
        //int f = rango.length;
        //int f2 = rango[0].length;
        
        for(int x = 0; x < log2.length; x++)
        {
            c = log2[x].lastIndexOf(" ");
            //System.out.print(log2[x].substring(0, c) + "  ");
            //System.out.print(redondear(seqNlth.StdDev(chain, log2[x].substring(0, c)), 3) + "      ");
             
            //System.out.print(redondear(seqNlth.Rank(chain, log2[x].substring(0, c)), 3) + "      ");
            rango[y][0] = Double.toString(redondear(seqNlth.Rank(chain, log2[x].substring(0, c)), 3));
            rango[y][1] = log2[x].substring(0, c);
            String chainRev = seqNlth.reverse(chain);
            y = y + 1;
            //System.out.print(redondear(seqNlth.Rank(chainRev, log2[x].substring(0, c)), 3) + "      ");
            rango[y][0] = Double.toString(redondear(seqNlth.Rank(chainRev, log2[x].substring(0, c)), 3));
            rango[y][1] = (log2[x].substring(0, c) + "Rev");
            y = y + 1;
            x = x + n - 1;
            System.out.println();
        }
        String Aux = "";
        for(int x = 0; x < rango.length; x++)
        {
            for(int z = x + 1; z < rango.length; z++)
            {
                if(Double.valueOf(rango[x][0]) > Double.valueOf(rango[z][0]))
                {
                    for(int s = 0;s < rango[0].length;s++)
                    {
                        Aux = rango[x][s];
                        rango[x][s] = rango[z][s];
                        rango[z][s] = Aux;
                    }
                }
            }
        }
        for(int x = 0; x < rango.length; x++)
        {
            rango[x][2] = Integer.toString(x + 1);
        }
        
        return rango; 
    }
    public LinkedList<String> Ps(String chain)
    {
        
        LinkedList<String> alph = seqNlth.alphabet(chain);
        LinkedList<String> Peses = new LinkedList<String>();
        if(veces == 0)
        {
        System.out.print("         ");
        for(int i = 0; i < alph.size(); i++)
        {
            System.out.print("  "+ alph.get(i) + "  ");
        }
        }
        System.out.println();
        for(int i = 0; i < alph.size(); i++)   ////////////ANTERIOR//////////// IMPRIMIR psS
        {
            
        
        LinkedList<String> aux = new LinkedList<String>();
        String bt = "", ps = "";
        String[] et = chain.split(" ");
        boolean ban = true;
        double Piprob = 0;
        int numPi = 0;
        
        for(int x = 0; x < et.length; x++)
        {
            if(et[x].equals(alph.get(i)))
            {
                for(int y = x + 1; y< et.length; y++)
                {
                    if(!(et[y].equals(alph.get(i))))
                    {
                        bt = bt + et[y] + " ";
                    }
                    else
                    {
                        aux.add(bt);
                        x = y - 1;
                        bt = "";
                        break;
                    }
                }
            }
        }
        if(veces == 0)
        {
        System.out.print("Rd(" + alph.get(i) + ") = ");}
        for(int x = 0; x < alph.size(); x++)
        {
            for(int y = 0; y < aux.size(); y++)
            {
                //int n = (aux.get(y).indexOf(" " + alph.get(x) + " "));
                if((aux.get(y).indexOf(alph.get(x) + " "))!= -1)
                {
                    //System.out.print();
                    ban = true;
                }
                else
                {
                    
                    for (int z = 0; z < aux.size(); z++)
                    {
                        if((aux.get(z).indexOf(alph.get(x) + " "))!= -1)
                        {
                            numPi = numPi + 1;
                        } 
                    }
                    if(!(alph.get(x).equals(alph.get(i))))
                    {
                    Piprob = (double)numPi/ (double)aux.size();
                    if(veces == 0)
                    {
                    System.out.print(" " + redondear(Piprob, 2)  +" " );} //se removio esta etiqueta "alph.get(x) + " al principio
                    }
                    else{if(veces == 0){System.out.print("   1  ");}}
                    numPi = 0;
                    //y = 0;
                    ban = false;
                    break;
                }
                
            }
            if(ban == true)
            {
                if(veces == 0){System.out.print("   1  ");}
                ps = ps + alph.get(x)+ " ";
            }
            else{}
        }
        Peses.add(alph.get(i)+ " " + ps);
        if(veces == 0)
        {
        System.out.println();}
        //System.out.println("Ps(" + alph.get(i) + ") = " + alph.get(i)+ " " + ps);
        }
        
        veces = veces +1;
        return Peses;
    }
    public String[] PsO(String chain)
    {
        String aux = " ";
        LinkedList<String> linkPs = Extr(chain); //cambiar por Extr O Ps sea el caso
        String[] arr = new String[linkPs.size()];
        for (int x = 0; x < linkPs.size(); x++)
        {
            arr[x] = linkPs.get(x);
        }
        for (int x = 0; x < arr.length; x++)
        {
            for (int y = 0; y < arr.length -1; y++)
            {
                if(arr[y].length() < arr[y + 1].length())
                {
                    aux = arr[y];
                    arr[y] = arr[y+1];
                    arr[y+1] = aux;
                }
            }
        }
        return arr;
    }
    public LinkedList<String> PsM(String chain,int pos)
    {
        //LinkedList<String> lol = new LinkedList<String>(); //se camnbio de global a metodo
        String[] arr = PsO(chain);
        boolean ban = true; 
        String  ps = " ",out = " ",num = " "; //num es la posicion en que se encuentra el arreglo.
        for (int x = 0; x < arr.length; x++)
        {
            String[] seq = arr[x].split(" ");
            for (int y = 0; y < seq.length; y++)
            {
                if((arr[pos].indexOf(seq[y]+ " "))!= -1)
                {
                    ban = true;
                    
                }
                else
                {
                    ban = false; //queizas break;
                    break;
                }
            }
            if(ban == true)
            {
                ps = ps + seq[0] + " ";
                if(in.indexOf(" "+ String.valueOf(x) + " ") == -1)// sigue sin entrar el dos por que en realidad si existe .... " "+ String.valueOf(x) + " "   x + " "
                {
                    //aux = x+"";
                    in = in + x + " ";
                }
            }
            else{
                //out = out + x + " "; //se va a quitar por que funcionaria mejor con puero in y residuo. 
            }
        }
        lol.add(arr[pos]);
        //lol.add(ps);
        for (int x = 0; x < arr.length; x++)
        {
            num = " "+ String.valueOf(x) + " ";
            //System.out.println(num);
            if((in.indexOf(num) == -1))    // se quito //se puso num en vez de x+""
            {       
                out = x+"";
                break;
            }
        }
        if(!(out.equals(" ")))
        {
            big(in,out,chain);
        }
        return lol;
    }
    /*public LinkedList<String> PsMod(String chain,int pos) //manera diferente de calcular el PsM.
    {
        String[] arr = PsO(chain);
        boolean ban = true; 
        String  ps = " ",out = " ",num = " "; //num es la posicion en que se encuentra el arreglo.
        for (int z = 0; z < arr.length; z++)
        {
        String[] seq = arr[z].split(" ");
        for (int x = 0; x < arr.length; x++)
        {
           
            if(arr[x].length() > arr[z].length())
            {
                for (int y = 0; y < seq.length; y++)
                {
                    if((arr[x].indexOf(seq[y]+ " "))!= -1)
                    {
                        ban = false;
                    }
                    else
                    {
                        ban = true;
                        break;
                    }
                } 
                
            }
            
        }
        if(ban == true)
        {
           lol.add(arr[z]); 
        }
        }
        
        return lol;
            
    }*/
    public void big(String in,String out,String chain)//hace Psm con el vamlor mas grande de los que no quedaron machados
    {
        //String[] outside = out.split(" ");
        //Arrays.sort(outside);
        //System.out.println();
        PsM(chain , Integer.parseInt(out));
    }
    public void seq(String chain)
    {
        LinkedList<String> alph = seqNlth.alphabet(chain);
        LinkedList<String> ps = Ps(chain);// Cambiar a Extr o a Ps
        LinkedList<String> CausalR = new LinkedList<String>();
        LinkedList<String> Obs = new LinkedList<String>();
        String causal = "";
        String observ = "";
        //String[] et = chain.split(" ");
        System.out.println();
        System.out.println("                          SEQ");
        System.out.print("  ");
        for(int i = 0; i < alph.size(); i++)
        {
            System.out.print("  "+ alph.get(i) + "  ");
        }
        System.out.println();
        for (int y = 0; y < alph.size(); y++)
        {
            System.out.print(alph.get(y)+" ");
            for (int z = 0; z < alph.size(); z++)
            {
                System.out.print("");
                if(chain.indexOf((alph.get(y) + " " +alph.get(z) + " "))!= -1) //Se agrega un espacio para evitar mal informacion en seq (t1 t11) != (t1 t1)
                {
                    System.out.print("  x   ");
                    observ = observ + alph.get(z) + " ";
                    if(((ps.get(y).indexOf(alph.get(z) + " "))!= -1) || ((ps.get(z).indexOf(alph.get(y) + " "))!= -1)) //Determinar relaciones causales 
                        //QUE PASA CON LA AGREGACION DE EL ESPACIO PARA EVITAR MAL INFORMACION EN SEQ. " " solucion 
                    {
                        causal = causal + alph.get(z) + " "; //nunca checo si son concurrentes
                    }
                    
                }
                else{
                    System.out.print("      ");
                }
            }
            Obs.add( alph.get(y) + " < " + observ);//Obs guarda las tranciones que se vieron una antes de la otra
            observ = "";
            CausalR.add("(" + alph.get(y) + ") " + causal);//CasaulR guarda las relaciones causales de la forma (ti) t5 t4 ... tx
            causal = "";
            System.out.println();
        }
        System.out.println();
        System.out.println("      Observaciones ti<tj                CausalR");
        for (int w = 0; w < CausalR.size(); w++)
        {
            System.out.println(Obs.get(w) +"         "+CausalR.get(w));
        }
    }
    public LinkedList<String> Extr(String chain)
    {
        //boolean ban = false;
        String newitem = "";
        LinkedList<String> Ps = Ps(chain);
        LinkedList<String> PsEx = new LinkedList();
        for (int x = 0; x < Ps.size(); x++) //x 0,..n todos los Rd 
        {
            String[] aux1 = Ps.get(x).split(" ");
            for (int y = 0; y < Ps.size(); y++)//deveria ser Ps.size antes Ps.get(x).length();
            {
                String[] aux2 = Ps.get(y).split(" ");
                for(int z = 1; z < aux1.length; z++)
                {
                    if(aux1[z].equals(aux2[0]))
                    {
                        for(int w = 1; w < aux2.length; w++)
                        {
                            if(Ps.get(x).indexOf(aux2[w]) != -1)
                            {
                                //System.out.println("hola");
                            }
                            else {
                                //System.out.println("no tiene este elemento"); COMPARAR SI LA SIGUIENTE CADENA TIENE ALGUN ELEMENTO QUE NO TENGA NEW ITEM SI ES ASI PONERLO????
                                if(newitem.indexOf(aux2[w]) == -1)
                                {
                                    newitem = newitem + aux2[w] + " ";
                                    
                                }
                                } //agregar todo aquel elemento que no este al PsExt
                        }
                        //Agrego todos los elementos faltantes de esa transicion 
                        //System.out.println("\n");
                    }
                }
                /*if(!(newitem.equals("")))
                {
                    PsEx.add(Ps.get(x) + newitem);
                    newitem = "";
                    //ban = true;
                }
                else{}*/ //se movio todo el bloque hasta un nuevo ciclo 
                //Agrego todos los elementos faltantes para ese Rd se cambia el set para no sobre escribir informacion  
            }
            if(!(newitem.equals("")))
                {
                    PsEx.add(Ps.get(x) + newitem);
                    newitem = "";
                    //ban = true;
                }
                else{}
            if(PsEx.size() == x)
            {
                PsEx.add(Ps.get(x));
            }
            
            //Aqui cambia el string por el nuevo
        }
        return PsEx;
    }
}
