/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workflow;


import java.util.LinkedList;
import java.util.Arrays;

/**
 *
 * @author tonatiuhsun
 */
public class process {
    
    
    /////////// PROBABILIDAD CONDICIONAL //////////
    
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
                        if((x + (seq.length - 1)) <= et.length -1 && (x != et.length))//SE QUITO LENGTH -1 Y SE DEJO LENGTH
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
        if(OccurPre != 0){
        CondProb = (double) OccurSeq /(double) OccurPre;}
        return CondProb;
        /*String[] et = chain.split(" ");
        String[] seq = S.split(" ");
        int OccurSeq = 0, OccurPre = 0;
        double CondProb = 0;
        for(int x = 0; x < et.length; x++)
        {
            //System.out.println(et[x]);
            if(et[x].equals(seq[0]))
            {
                OccurPre = OccurPre +1;
                if(x +1 < et.length){
                if(et[x+1].equals(seq[1]))
                {
                    OccurSeq = OccurSeq + 1;
                }}
            }
        }
        //System.out.println(OccurSeq);
        //System.out.println(OccurPre);
        CondProb = (double) OccurSeq /(double) OccurPre;
        return CondProb;*/
    }
    
    
    ////////////RESETEAR EL VALOR DE S.concate a cada iteracion 
    //Chek _/ good _/
    /////////ENTROPIA/////
    
    public double Entropy(String chain, String S)
    {
        double Ent = 0, Cp = 0;
        LinkedList<String> alph = alphabet(chain);
        for(int x = 0; x < alph.size(); x++)
        {
            Cp =  CondProb(chain, S + " " + alph.get(x));
            String cadena =  S + " " + alph.get(x);
            if(Cp != 0)
            {
                Ent = Ent + (Cp * (Math.log10(Cp) / Math.log10(alph.size())) );
            }
        }
        //System.out.println(Ent);
        Ent = (Math.abs(Ent * -1));
        //System.out.println(Ent);
        return Ent;
    }
    
    ///////////ALFABETO//////////
    
    public LinkedList<String> alphabet(String chain)
    {
        
        String[] et = chain.split(" ");
        LinkedList<String> aux = new LinkedList<String>(); 
        aux.add(et[0]);
        int z = 0;
        boolean ban = false;
        for(int x = 1; x < et.length; x++)
        {
            ban = false;
            for(int y = 0; y < aux.size(); y++)
            {
                if((et[x].equals(aux.get(y))))
                {
                    ban = true;
                    break;
                       
                }
              
            }
            if(ban == false)
            {
                aux.add(et[x]);
            }
            
        }
        return aux;
    }
    
    /////////LIMITE DE ENTROPÌA///////
    
    //Check _/ good _/
    public double EntLim(String chain, int T)
    {
        double EntLim = 0;
        LinkedList<String> alph = alphabet(chain);
        int N = alph.size();
        EntLim = ( Math.log10(T) / Math.log10(N) );
        return EntLim;
    }
    
    ////////////////POSICION DE LA I ESIMA OCURRENCIA DE S///////////
    
    //Check _/ good _/
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
        /*int pos = 0,aux = 0;
        String[] et = chain.split(" ");
        String[] seq = S.split(" ");
        for(int x = 0; x < et.length; x++)
        {
            if(et[x].equals(S))
            {
                aux = aux + 1;
            }
            if(aux == i)
            {
                pos = (x + 1);
                break;
            }
        }
        return pos;*/
    }
    
    
    /////////NUMERO DE OCURRENCIAS DE S/////////
    ////////////////////////////////////////
    
    public int Occur(String chain, String S)
    {
        int occur = 0,aux = 0, count = 0;
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
            
        }
        occur = aux;
        return occur;
        //////////CADENAS DE LONGITUD 2
        /*int occur = 0;
        String[] et = chain.split(" ");
        for(int x = 0; x < et.length; x++)
        {
            if(et[x].equals(S))
            {
                occur = occur + 1;
            }
        }
        return occur;*/
    }
    
    
    
    
    
    /*
     * 
     * 
     * 
     * 
     */

    //////////// PERIODO DE S.///////
    //Chek _/ good _/
    public double PeriodMean(String chain,String S)
    {
        double PeriodMean = 0, sum = 0;
        int Oc = Occur(chain, S);
        for(int x = 2; x <= Oc; x++)
        {
            //System.out.println(Position(chain ,S , x) - Position(chain ,S , x - 1));
            sum = sum + (Position(chain ,S , x) - Position(chain ,S , x - 1)); 
        }
        if(Occur(chain , S) == 1)
        {
           PeriodMean = 0;         
        }//casos donde la secuencia solo ocurre 1 ves 
        else{
        PeriodMean = (sum / (Occur(chain , S) - 1));}
        return PeriodMean;
    }
    
    
    
    //Chek _/ good _/
    public double Dsq(String chain, String S, int i)
    {
        double Dsq = 0;
        Dsq = Math.pow((Position(chain ,S , i) - Position(chain ,S , i - 1)), 2);
        return Dsq;
    }
    
    //Chek _/ good _/
    public double StdDev(String chain, String S) // se cambio occur po la variable oc
    {
        double StdDev = 0, sum = 0, div = 0; 
        int Oc = Occur(chain, S);
        for(int x = 2; x <= Oc; x++)
        {
            sum  = sum + (Dsq(chain , S, x) - Math.pow(PeriodMean(chain, S), 2));
        }
        if(Oc == 1 || Oc == 0)
        {
           StdDev = 0;         
        }//casos donde la secuencia solo ocurre 1 ves 
        else{
        div = (sum / (Oc - 2) );
        StdDev = Math.sqrt(div);} 
        return StdDev;
    }
    
    
    public double EntDiff(String chain,int i, String S)
    {
        double EntDiff = 0;
        EntDiff = (Math.abs(EntLim(chain, i) - Entropy(chain, S)));
        return EntDiff;
    }
    
    
    public int BrFactor(String chain, String S)
    {
        int BrFactor = 0;
        LinkedList<String> alph = alphabet(chain);
        int N = (alph.size() - 1);
        double menor = 0, aux = 0;
        menor = EntDiff(chain, 2, S);
        BrFactor = 1;
        for(int x = 2; x < N;x++)
        {
            aux = EntDiff(chain, x, S);
            if(aux <= menor )
            {   
                menor = aux;
                BrFactor = x;
            }
        }
        return BrFactor;
    }
    public double EntropyRank(String chain,String S)
    {
        double EntropyRank = 0;
        LinkedList<String> alph = alphabet(chain);
        int N = alph.size();
        EntropyRank = Math.pow(N, EntDiff(chain, BrFactor(chain, S), S));
        return EntropyRank;
    }
    
    
    public double[] SortedSeq(String chain, String S)
    {
        
        LinkedList<String> alph = alphabet(chain);
        int N = alph.size();
        double[] SortedSeq = new double[N];
        for(int x = 0; x < N; x++)
        {
            SortedSeq[x] = CondProb(chain, S + " " + alph.get(x)); 
        }
        Arrays.sort(SortedSeq);
        for(int y = 0; y < N/2; y++)
        {
            double temp = SortedSeq[y];
            SortedSeq[y] = SortedSeq[N-(y+1)];
            SortedSeq[N - (y+1)] = temp;
        }
        return SortedSeq;        
    }
    
    
    
    public double ProbRank( String chain, String S)
    {
        int n = BrFactor(chain, S);
        double sum = 0;
        double ProbRank = 0;
        double[] Ss = SortedSeq(chain, S);
        for(int x = 0; x < n; x++)
        {
            sum = sum + Ss[x];
        }
        ProbRank = 1 - sum;
        return ProbRank;
    }
    
    
    public double MaxSdv(String chain)
    {
        LinkedList<String> alph = alphabet(chain);
        int N = alph.size();
        double[] Std = new double[N]; 
        for(int x = 0; x < N; x++)
        {
            Std[x] = StdDev(chain, alph.get(x));
        }
        Arrays.sort(Std);
        return Std[N-1];
    }
    
    
    
    public double PeriodRank(String chain, String S)
    {
        double PeriodRank = 0, Max = MaxSdv(chain); //se cambio max para no calcularlo dos veces.
        if(Max != 0)
        {
            PeriodRank =(StdDev(chain , S) / MaxSdv(chain));
        }
        else{
            PeriodRank = 0;
        }
        return PeriodRank;
    }
    
    
    
    public double Rank(String chain, String S)
    {
        double rank = 0;
        double We = 1, Wf = 1.5, Wp = .25;
        rank = (We * EntropyRank(chain, S) + Wf * ProbRank(chain, S) + Wp * PeriodRank(chain, S));
        return rank;
    }
    
    
    
    public String reverse(String chain)
    {
        String chainRev = "";
        String[] et = chain.split(" ");
        int N = et.length; 
        for(int x = 0; x < N/2; x++)
        {
            String temp = et[x];
            et[x] = et[N-(x+1)];
            et[N - (x+1)] = temp;
        }
        for(int y = 0; y < N; y++)   
        {
            chainRev = chainRev + et[y] + " "; 
        }
        return chainRev;
    }
}
