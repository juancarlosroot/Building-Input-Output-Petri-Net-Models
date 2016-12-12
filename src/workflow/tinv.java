/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workflow;



import java.util.LinkedList;
import org.jgrapht.alg.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
/**
 *
 * @author tonatiuhsun
 */
public class tinv {
    
    process seqNlth = new process();
    ProcessNlen rd = new ProcessNlen();
    //public LinkedList<String> union = new LinkedList();
    
    public void lol(String chain) //antes void
    {
        rd.PsM(chain, 0);
    }
    public LinkedList<String> seq(String chain)
    {
        String[] et = chain.split(" ");
        String observ = "";
        LinkedList<String> alph = seqNlth.alphabet(chain);
        LinkedList<String> seq = new LinkedList();
        for (int y = 0; y < alph.size(); y++)
        {
            //System.out.print(alph.get(y)+" ");
            for (int z = 0; z < alph.size(); z++)
            {
                //System.out.print("");
                if(chain.indexOf((alph.get(y) + " " +alph.get(z) + " "))!= -1) //Se agrega un espacio para evitar mal informacion en seq (t1 t11) != (t1 t1)
                {
                    //System.out.print("  x   ");
                    observ = observ + alph.get(z) + " ";
                    /*if(((ps.get(y).indexOf(alph.get(z) + " "))!= -1) || ((ps.get(z).indexOf(alph.get(y) + " "))!= -1)) //Determinar relaciones causales 
                        //QUE PASA CON LA AGREGACION DE EL ESPACIO PARA EVITAR MAL INFORMACION EN SEQ. " " solucion 
                    {
                        causal = causal + alph.get(z) + " ";
                    }*/
                    
                }
                else{
                    //System.out.print("      "); se tapo el print
                }
            }
            seq.add(observ);//Obs guarda las tranciones que se vieron una antes de la otra /alph.get(y) + " < " +/ se quito
            observ = "";
            //CausalR.add("(" + alph.get(y) + ") " + causal);//CasaulR guarda las relaciones causales de la forma (ti) t5 t4 ... tx
            //causal = "";
            //System.out.println();
        }  
    return seq;    
    }
    public LinkedList<String> CausalR(String chain)
    {
        String causal  = "";
        LinkedList<String> alph = seqNlth.alphabet(chain);
        LinkedList<String> ps = rd.Ps(chain);
        LinkedList<String> tc = Tc(chain);
        LinkedList<String> CausalR = new LinkedList();
        for (int y = 0; y < alph.size(); y++)
        {
            //System.out.print(alph.get(y)+" ");
            for (int z = 0; z < alph.size(); z++)
            {
                //System.out.print("");
                if(chain.indexOf((alph.get(y) + " " +alph.get(z) + " "))!= -1) //Se agrega un espacio para evitar mal informacion en seq (t1 t11) != (t1 t1)
                {
                    //System.out.print("  x   ");
                    //observ = observ + alph.get(z) + " ";
                    if(((ps.get(y).indexOf(alph.get(z) + " "))!= -1) || ((ps.get(z).indexOf(alph.get(y) + " "))!= -1) || (tc.get(y).indexOf(alph.get(z) + " ")!= -1 ) || (tc.get(z).indexOf(alph.get(y) + " ")!= -1 )) //Determinar relaciones causales 
                        //se agrago el segundo o para tc z y 
                        //QUE PASA CON LA AGREGACION DE EL ESPACIO PARA EVITAR MAL INFORMACION EN SEQ. " " solucion 
                    {
                        causal = causal + alph.get(z) + " ";
                    }
                    
                }
                else{
                    //System.out.print("      "); se tapo el print
                }
            }
            CausalR.add(causal);//Obs guarda las tranciones que se vieron una antes de la otra
            causal = "";
        }
        
    return CausalR;    
    }
    public LinkedList<String> Tc(String chain)
    {
        String tc  = "", test = "";
        LinkedList<String> alph = seqNlth.alphabet(chain);
        //LinkedList<String> ps = rd.Ps(chain);
        LinkedList<String> Twoc = new LinkedList();
        for (int y = 0; y < alph.size(); y++)
        {
            //System.out.print(alph.get(y)+" ");
            for (int z = 0; z < alph.size(); z++)
            {
                //System.out.print("");
                //test = alph.get(y) + " " +alph.get(z) + " " +alph.get(y);
                if((chain.indexOf((alph.get(y) + " " +alph.get(z) + " " +alph.get(y) + " " ))!= -1) || 
                        (chain.indexOf((alph.get(z) + " " +alph.get(y) + " " +alph.get(z) + " " ))!= -1)) //Se agrega un espacio para evitar mal informacion en seq (t1 t11) != (t1 t1)
                {
                    //System.out.print("  x   ");
                    //observ = observ + alph.get(z) + " ";
                    //if(((ps.get(y).indexOf(alph.get(z) + " "))!= -1) || ((ps.get(z).indexOf(alph.get(y) + " "))!= -1)) //Determinar relaciones causales 
                        //QUE PASA CON LA AGREGACION DE EL ESPACIO PARA EVITAR MAL INFORMACION EN SEQ. " " solucion 
                    //{
                        tc = tc + alph.get(z) + " ";
                    
                    
                }
                else{
                    //System.out.print("      ");
                }
            }
            Twoc.add(tc);//Obs guarda las tranciones que se vieron una antes de la otra
            tc = "";
        }
        
    return Twoc;
        
    }
    public LinkedList<String> Conccurrent(String chain)
    {
        String Conc  = ""; //test = "";
        LinkedList<String> alph = seqNlth.alphabet(chain);
        //LinkedList<String> ps = rd.Ps(chain);
        LinkedList<String> ConC = new LinkedList();
        LinkedList<String> tc = Tc(chain);
        for (int y = 0; y < alph.size(); y++)
        {
            //System.out.print(alph.get(y)+" ");
            for (int z = y; z < alph.size(); z++)
            {
                //System.out.print("");
                //test = alph.get(y) + " " +alph.get(z) + " " +alph.get(y);
                if((chain.indexOf((alph.get(y) + " " +alph.get(z) + " "))!= -1) && 
                        (chain.indexOf((alph.get(z) + " " +alph.get(y) + " "))!= -1) &&
                        (tc.get(y).indexOf(alph.get(z) + " ")== -1 )) //Se agrega un espacio para evitar mal informacion en seq (t1 t11) != (t1 t1)
                {
                    //System.out.print("  x   ");
                    //observ = observ + alph.get(z) + " ";
                    //if(((ps.get(y).indexOf(alph.get(z) + " "))!= -1) || ((ps.get(z).indexOf(alph.get(y) + " "))!= -1)) //Determinar relaciones causales 
                        //QUE PASA CON LA AGREGACION DE EL ESPACIO PARA EVITAR MAL INFORMACION EN SEQ. " " solucion 
                    //{
                        Conc = Conc + alph.get(z) + " ";
                }
                else{
                    //System.out.print("      ");
                }
            }
            ConC.add(Conc);//Obs guarda las tranciones que se vieron una antes de la otra
            Conc = "";
        }
        
    return ConC;
        
    }
    public LinkedList<String> rem(String chain)// de remanidere
    {
       LinkedList<String> alph = seqNlth.alphabet(chain);
       String reminder = "";
       String[] et = chain.split(" ");
       int log = 0;
       log =  et.length;
       int test = 0, L = et.length;
       LinkedList<String> seq = seq(chain);
       LinkedList<String> CausalR = CausalR(chain);
       LinkedList<String> ConC = Conccurrent(chain);
       LinkedList<String> rem = new LinkedList();
       for (int y = 0; y < alph.size(); y++)
       {
           for (int z = 0; z < alph.size(); z++)
           {
               test =  CausalR.get(y).indexOf(alph.get(z));
               if( (seq.get(y).indexOf(alph.get(z) + " ") != -1) 
                       &&  (CausalR.get(y).indexOf(alph.get(z) + " ") == -1)  
                       &&  (ConC.get(y).indexOf(alph.get(z) + " ") == -1) 
                       &&  (ConC.get(z).indexOf(alph.get(y)+ " ") == -1))
               {
                   reminder = reminder + alph.get(z) + " ";
               }
           }
           rem.add(reminder);
           reminder = "";
       }
       return rem;
       
    }
    public LinkedList<String> noRelacion(String chain)
    {
        String nor  = ""; //test = "";
        LinkedList<String> alph = seqNlth.alphabet(chain);
        //LinkedList<String> ps = rd.Ps(chain);
        LinkedList<String> Rd = rd.Ps(chain);
        LinkedList<String> noRelacion  = new LinkedList();
        //LinkedList<String> tc = Tc(chain);
        for (int y = 0; y < alph.size(); y++)
        {
            //System.out.print(alph.get(y)+" ");
            for (int z = 0; z < alph.size(); z++)
            {
                //System.out.print("");
                //test = alph.get(y) + " " +alph.get(z) + " " +alph.get(y);
                String loco = alph.get(y) + " " +alph.get(z) + " ";
                if( (chain.indexOf((alph.get(y) + " " +alph.get(z) + " ")) == -1))// || 
                        //(chain.indexOf((alph.get(z) + " " +alph.get(y) + " ")) == -1)) 
                        //&& (Rd.get(y).indexOf((alph.get(z)+ " ")) == -1) 
                        //&& (Rd.get(z).indexOf((alph.get(y)+ " ")) == -1)) //Se agrega un espacio para evitar mal informacion en seq (t1 t11) != (t1 t1)
                {
                    //System.out.print("  x   ");
                    //observ = observ + alph.get(z) + " ";
                    //if(((ps.get(y).indexOf(alph.get(z) + " "))!= -1) || ((ps.get(z).indexOf(alph.get(y) + " "))!= -1)) //Determinar relaciones causales 
                        //QUE PASA CON LA AGREGACION DE EL ESPACIO PARA EVITAR MAL INFORMACION EN SEQ. " " solucion 
                    //{
                        nor = nor + alph.get(z) + " ";
                    
                    
                }
                else{
                    //System.out.print("      ");
                }
            }
            noRelacion.add(nor);//Obs guarda las tranciones que se vieron una antes de la otra
            nor = "";
        }
        
     return noRelacion;
        
    }
   public LinkedList<DirectedGraph<String, DefaultEdge>> Graphs(String chain)  //DirectedGraph<String, DefaultEdge> como hacer que devuelva un arreglo de este tipo  antes int[][]
   {
       String ban = "";
       LinkedList<DirectedGraph<String, DefaultEdge>> graphs = new LinkedList<DirectedGraph<String, DefaultEdge>>(); 
       LinkedList<String> RdM = this.rd.lol; //rd.PsM(chain, 0); //cambia por que es variable global en otro archivo. 
       LinkedList<String> RdMod = RdMcor(RdM, chain); //checar si tienen modificacion los rdm
       for(int x = 0; x < RdMod.size();x++)
       {
           String[] rdm = RdMod.get(x).split(" ");//cambia por mod
           graphs.add(Gr(rdm, chain));
       }
       return graphs;
       
   }
   public LinkedList<Boolean> Sclist(String chain)
   {
       //boolean ban = false;
       LinkedList<Boolean> sclist  = new LinkedList();
       LinkedList<DirectedGraph<String, DefaultEdge>> graphs = Graphs(chain); 
       //LinkedList<String> RdM = rd.PsM(chain, 0);
       for(int x = 0; x < graphs.size();x++)
       { 
           //DirectedGraph<String, DefaultEdge> directedGraph = Gr(RdmArray, chain); //hacer el grafo de tipo jgraphT antes int[][] RdmAMatrix = Gr(RdmArray, chain)
           StrongConnectivityInspector sci = new StrongConnectivityInspector(graphs.get(x));
           if(sci.isStronglyConnected() == true ) // antes dentro del if
                {   
                    sclist.add(true);
                    //System.out.print(x+ ","+ y + "   -  ");
                }
           //if(Sc(graphs.get(x)) == true)
           //{
             //   sclist.add(true);
                       //System.out.print( "Grafo  " + x + "  Es fuertemente Conexo");
           //}
           else{
               sclist.add(false);
           //System.out.print( "Grafo  " + x + "  NO fuertemente Conexo");
           }
           
           //System.out.println("");
                       //Prueba de fuerte conexidad. 
           //ban = Dfs(rdm, graphs.get(x),0);//solo como prueba separar despues.
       }
       return sclist;
   }
   public LinkedList<String> Union(String chain)//Falla cuando se intercambia el acomodo si el primero es true no entra y no checa el siguiente falso con el primero
           //prueba con RdM sin modificar. 
   {
       String newRdm = "";
       int count = 0, a = 0;
       LinkedList<String> alph = seqNlth.alphabet(chain);
       LinkedList<String> union = new LinkedList();
       LinkedList<DirectedGraph<String, DefaultEdge>> graphs = Graphs(chain);//antes int[][]
       LinkedList<String> RdM = this.rd.lol;      //PsM(chain, 0); //cambia es variable global en otro archivo
       LinkedList<String> RdMod = RdMcor(RdM, chain);//RdMcor(RdM, chain); borrar RdMod this.rd.lol
       LinkedList<Boolean> sclist = Sclist(chain);
       boolean[] visited = new boolean[graphs.size()];
       for (int t = 0; t < sclist.size(); t++)
       {
           if(sclist.get(t) == true)
           {
               count = count + 1;
           }
           if(RdMod.get(t).length() < 4)
           {
               a = a + 1; 
           }
       }
       if(count == sclist.size() && a < sclist.size()) //a es que son sinleton 
       {    
            System.out.println("T-Invariants");
            for(int x = 0; x < RdMod.size(); x++ )
            {
                System.out.println("Y_"+x+"  =  " + RdMod.get(x));
            }
           return RdMod;
       }
       else{
       for(int x = 0; x< graphs.size(); x++)
       {
          if(sclist.get(x) == false || a == sclist.size()) //se quita para que trate de hacer uniones con el fuertemente conexo. CAMBIAR PARA TRABAJAR CON FUERTEMENTE CONEXOS. 
          {
           //Hace falta condicion de que si la union contiene un componente fuertemente conexo ya formado no pasa.
              for(int y = x; y< graphs.size(); y++) // Y = x menos uniones no repetir uniones. //QUE PASA CUANDO EL PRIMER GRAFO ES FUERTEMENTE CONEXO PERO ALGUIEN LO NECESITA PARA SU UNION.  
              {
                  if(x != y){ ///LA UNION 
                    String[] comp = RdMod.get(y).split(" ");
                    newRdm = RdMod.get(x);
                    for(int z = 0; z< comp.length; z++)
                    {
                        if(RdMod.get(x).indexOf(comp[z] + " ") == -1)//+ " " diferenciar entre T1 Y T1X
                        {
                            newRdm = newRdm + comp[z] + " "; 
                        }
                    }
                    String[] RdmArray = newRdm.split(" ");
                    DirectedGraph<String, DefaultEdge> directedGraph = Gr(RdmArray, chain); //hacer el grafo de tipo jgraphT antes int[][] RdmAMatrix = Gr(RdmArray, chain)
                    StrongConnectivityInspector sci = new StrongConnectivityInspector(directedGraph);
                    if(sci.isStronglyConnected() == true ) // antes dentro del if
                    {   
                         union.add(newRdm);
                         for(int val = 0; val < graphs.size(); val++)
                         {
                             if(val == x || val == y)
                             {
                                 visited[val] = true;
                             }
                         }
                         System.out.print(x+ ","+ y + "   -  ");
                    }
                     //newRdm = RdM.get(x) + " " + RdM.get(y);   
                  }
              }
          }
       }
       
       //Solo funicona para rd single`s. 
       //Modificar la funcion para hacer la condicion de fuerte conexidad.
       if(a == sclist.size())
       {
       int n = alph.size();
       LinkedList<String> combinaciones = print_nCr((n - 1) , 3); //cambiar numeros (numero de eventos diferentes -1, cantidad de eventos en cada combinacion)
       boolean ban = false;
       for(int w = 0; w< combinaciones.size(); w++)
       {
           String[] RdmArray = combinaciones.get(w).split(" ");
           ban = ScSubB(combinaciones.get(w), union );
           if(ban == false)
           {
           DirectedGraph<String, DefaultEdge> directedGraph = Gr(RdmArray, chain); //hacer el grafo de tipo jgraphT antes int[][] RdmAMatrix = Gr(RdmArray, chain)
                    StrongConnectivityInspector sci = new StrongConnectivityInspector(directedGraph);
                    if(sci.isStronglyConnected() == true ) // antes dentro del if
                    {   
                         union.add(combinaciones.get(w));
                         
                    }
           }
       }
       }
       //Solo hacer uniones que no contengan un subconjunto fuertemente conexo antes formado. 
                         for(int val = 0; val < graphs.size(); val++)
                         {
                             if(visited[val] == false)
                             {
                               union.add(RdMod.get(val));
                             }
                         }
                         System.out.println();
                         System.out.println("T-Invariants");
        for(int x = 0; x < union.size(); x++ )
        {
            
            System.out.println("Y_"+x+"  =  " + union.get(x));
        }
                         return union; //No hacer uniones de dos componentes fuertemente conexas.  
       }
   }
   public Boolean ScSubB(String comb, LinkedList<String> union)//Strongly conected Subgraph before calculated 
   {
       boolean ban = false;
       int count = 0;
       String[] sep = comb.split(" ");
       for(int x = 0;x < union.size();x++)
       {
           for(int y = 0; y < sep.length; y++)
           {
               if(union.get(x).indexOf(sep[y] + " ") != -1)
               {
                   count = count + 1;
               }
           }
           //int c = union.get(x).length();
           if(count == (sep.length -1))
           {
               ban = true;
               break;
           }
           count = 0;
       }
       return ban;
       
   }
   
   public DirectedGraph<String, DefaultEdge> Gr(String[] rdm, String chain) //Construir grafo de relaciones//public DirectedGraph<String, DefaultEdge> cambio por int[][]
   {
       LinkedList<String> alph = seqNlth.alphabet(chain);
       int pos = 0;
       LinkedList<String> rem  = rem(chain);
       LinkedList<String> Causal  = CausalR(chain);
       int x = rdm.length; 
       DirectedGraph<String, DefaultEdge> directedGraph = // cambio por int [][] matriz = new int[x][x];
            new DefaultDirectedGraph<String, DefaultEdge>
            (DefaultEdge.class);
       for(int p = 0; p < x; p++)
       {
           directedGraph.addVertex(rdm[p]);
       }    
       for(int y = 0; y < x;y++)
       {
           for(int z = 0; z < x;z++) //ciclos para llenar la matriz de incidencia
           {
               for(int w = 0; w < alph.size(); w++) // buscar la posicion de el evento x
               {
                   if(alph.get(w).equals(rdm[y]))
                   {
                       pos = w;
                       if((Causal.get(w).indexOf(rdm[z])!= -1) || (rem.get(w).indexOf(rdm[z])!= -1)) //si la relacion causal o rem en la posicon de el evento x tiene el evento y agregar adyacencia
                       {
                            directedGraph.addEdge(rdm[y], rdm[z]); //pone un uno en el espacio [x,y] cambio por matriz[y][z] = 1;
                            break;
                       }
                   }
                   /*if(Causal.get(w).indexOf(rdm[y])!= -1) //si la relacion causal en la posicon de el evento x tiene el evento y agregar adyacencia
                   {
                       matriz[x][y] = 1; //pone un uno en el espacio [x,y]
                       break;
                   }*/
               }
           }
       }
       return directedGraph;
   }
   /*public String Dfs(int[][] g, int nodo) //Dfs por nodo SE CAMBIARON LAS MATRICES A DOBLES PARA UTILIZAR LLAMA. 
   {
       String cadena = "";
       int[] isVisited = new int[g.length];
       Stack<Integer> st = new Stack<Integer>();
       st.push(nodo);
       int i = 0,v= 0;
       while(!st.isEmpty())
          {
              v = st.pop();
              if(isVisited[v]==0)
              {
                  cadena = cadena + v;//System.out.print("\n"+(v));
                  isVisited[v]=1;
              }
              for ( i=0;i<g.length;i++)
              {
                  if((g[v][i] == 1) && (isVisited[i] == 0))
                  {
                      st.push(v);
                      isVisited[i]=1;
                      cadena = cadena + i; //System.out.print(" " + (i));
                      v = i;
                      i = -1;
                  }
              }
          }
       return cadena;
       
   }
   public boolean Sc(int[][] g)
   {
       boolean sc = false;
       String d = "", a = " "; 
       int[][] t = trans(g);
       for(int x = 0; x < g.length;x++)
       {
           d = Dfs(g ,x);
           a = Dfs(t, x);
           if((d.length() == g.length) && (a.length() == g.length))
           {
               sc = true;
           }
           else{
               sc = false;
               break;        
           }
           
       }
       return sc;
   }
   public int[][] trans(int[][] g)
   {
       int[][] T = new int[g.length][g.length];
       for(int j = 0; j < g.length; j++)
       {
           for(int i = 0; i < g[0].length; i++)
           {
               T[j][i] = g[i][j]; 
           }
       }      
       return T;        
   }*/
   
   
   
   ///////////COMBINACIONES PARA LAS UNIONES
   
   public LinkedList<String> print_nCr(final int n, final int r) {
    int[] res = new int[r];
    String par = "";
    //String[] rd = {"T5", "T4", "T3", "T1", "T2", "T0"};
    for (int i = 0; i < res.length; i++) {
        res[i] = i;
    }
    LinkedList<String> comb = new LinkedList<String>();
    boolean done = false;
    while (!done) {
        //System.out.println(Arrays.toString(res));
        for(int z = 0;z < res.length; z++)
        {
            par = par + rd.lol.get(res[z]);
            //System.out.print(rd.lol.get([res[z]] + " ");
        }
        comb.add(par);
        par = "";
        done = getNext(res, n, r);
    }
    return comb;
}

/////////

public boolean getNext(final int[] num, final int n, final int r) {
    int target = r - 1;
    num[target]++;
    if (num[target] > ((n - (r - target)) + 1)) {
        // Carry the One
        while (num[target] > ((n - (r - target)))) {
            target--;
            if (target < 0) {
                break;
            }
        }
        if (target < 0) {
            return true;
        }
        num[target]++;
        for (int i = target + 1; i < num.length; i++) {
            num[i] = num[i - 1] + 1;
        }
    }
    return false;
}
////////////////// fIN COMBINACIONES

///RdM Correction. 
public  LinkedList<String> RdMcor(LinkedList<String> rdm, String chain)
{
    int pos = 0, inter = 0, visited = 0;
    LinkedList<String> ps = rd.Ps(chain);
    LinkedList<String> alph = seqNlth.alphabet(chain);
    LinkedList<String> Conc = Conccurrent(chain);
    String dist = Dist(rdm);
    LinkedList<String> RdMod = new LinkedList();
    
    for(int x = 0; x < alph.size(); x++)
    {
        if(dist.indexOf(alph.get(x) + " ") != -1)
        { 
          
           String[] concx = Conc.get(x).split(" ");
           if(!(concx[0].equals("")))
           {
           for(int y = 0; y < concx.length; y++)
           {
               if(dist.indexOf(concx[y] + " ") != -1)
               {
                   System.out.print("");
                   for(int w = 0; w < ps.size(); w++)
                   {
                       //pos =  ps.get(w).indexOf(concx[y] + " ");
                        if((ps.get(w).indexOf(concx[y] + " ") != -1))
                        {   
                            pos = w; 
                            break;
                        }
                   }
                   String[] rdsep = ps.get(x).split(" ");
                   for(int z = 0; z < rdsep.length; z++)
                   {
                       if(ps.get(pos).indexOf(rdsep[z] + " ") != -1)
                       {
                           inter = inter + 1;
                       }
                   }
                   if(inter == 0)
                   {
                       
                   }
                   else{
                       //modificar los RdM.
                       int pos1 = 0; int pos2 = 0;
                       
                       for(int p = 0; p < rdm.size(); p++)
                       {
                           if((rdm.get(p).indexOf(alph.get(x)) != -1))
                           {
                               pos1 = p;
                           }
                           else{
                               if((rdm.get(p).indexOf(alph.get(pos)) != -1))
                               {
                                   pos2 = p;
                               }
                               else{
                                   RdMod.add(rdm.get(p)); //Modificado 17 del 10 del 2014 para que se quede solo con los nuevos
                               }
                           }
                          
                           
                       }
                       String nrdm = rdm.get(pos2);
                       String[] uni =  rdm.get(pos1).split(" ");
                       for(int q = 0; q < uni.length; q++)
                       {
                           if(rdm.get(pos2).indexOf(uni[q]) == -1)
                           {
                               nrdm = nrdm + uni[q] + " ";
                           }
                           
                       }
                       RdMod.add(nrdm);
                       nrdm = "";
                   }
               }
               visited = pos;
               pos = 0;
           }
           }
        }
        
    }
    if(RdMod.size() == 0)
    {
        for(int x = 0;x < rdm.size(); x++)
        {
            RdMod.add(rdm.get(x));
        }
    }
    return RdMod;
}

public  String Dist(LinkedList<String> rdm)
{
    String dist = "";
    int count = 0;
    for(int x = 0; x < rdm.size(); x++)
    {
        String[] ev = rdm.get(x).split(" ");
        for(int w = 0; w < ev.length; w++)
        {
            //String p = ev[w];
            for(int y = 0; y < rdm.size(); y++)
            {
                if(x != y)
                {
                    if(rdm.get(y).indexOf(ev[w] + " ") == -1)
                    {
                        count = count + 1;
                    }
                }
        
            }
            if(count == rdm.size() -1 )
            {
                dist = dist + ev[w] + " ";
                count = 0;
            }
            else{count = 0;}
        }
    }
    return dist;
    
}
public  String Distin(String chain)
{
    LinkedList<String> rdm = this.rd.lol;  
    String dist = "";
    int count = 0;
    for(int x = 0; x < rdm.size(); x++)
    {
        String[] ev = rdm.get(x).split(" ");
        for(int w = 0; w < ev.length; w++)
        {
            //String p = ev[w];
            for(int y = 0; y < rdm.size(); y++)
            {
                if(x != y)
                {
                    if(rdm.get(y).indexOf(ev[w] + " ") == -1)
                    {
                        count = count + 1;
                    }
                }
        
            }
            if(count == rdm.size() -1 )
            {
                dist = dist + ev[w] + " ";
                count = 0;
            }
            else{count = 0;}
        }
    }
    return dist;
    
}
public LinkedList<String> Relpn(String chain)
{
    LinkedList<String> Rel = new LinkedList();
    LinkedList<String> causal = CausalR(chain);
    LinkedList<String> remin = rem(chain);
    LinkedList<String> alph = seqNlth.alphabet(chain);
    for(int  x = 0; x < alph.size(); x++ )
    { 
        //int le = causal.get(x).length();
        if(causal.get(x).length() == 0)
        {
            Rel.add(remin.get(x));
      
        }
        else
        {
            Rel.add(causal.get(x) + remin.get(x)); //antes espacio + " " + 
        }
    }
    return Rel;
    
}
}
