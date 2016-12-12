/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workflow;

import java.lang.reflect.Array;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;
import drawers.PipeDrawer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 *
 * @author tonatiuhsun
 */
public class PN {
    
    private int[][] incidenceMatrix;
    private int placesNumber;
    private int transitionsNumber;
    private Vector<String> transitionLabels;
    private Vector<String> placeLabels;
    private LinkedList<String> Tinvariants;
    private static Hashtable<String,Transitions> Trans = new Hashtable<String,Transitions>();
    private static Hashtable<String,Place> places = new Hashtable<String,Place>();
    
    tinv invariants = new tinv();
    process seqNlth = new process();
    process pro = new process();
    LinkedList<String> ConC = new LinkedList();
    LinkedList<String> alph = new LinkedList();
    
    
    
    
    public PetriNet PN(String chain)
    {
        invariants.lol(chain);
        Tinvariants =  invariants.Union(chain);
        ConC = invariants.Conccurrent(chain);
        alph = seqNlth.alphabet(chain);
        
        //Place place = new Place("P0");
        //places.put(" P0 ", place );
        
        places = addplaces3(chain); //addplaces2 work try addplace 3
        PetriNet pn = new PetriNet(places, seqNlth.alphabet(chain));
        //new PipeDrawer(Outputfile ,places,seqNlth.alphabet(chain));
        return pn;
    }
    public Hashtable<String,Place> addplaces(String chain)
    {
        String aux = "" ,place = "", event = "";
        boolean mismoT = false;
        int pos = 0, count = 0;
        Vector<String> sourceTransitions = new Vector<String>();
        Vector<String> sinkTransitions = new Vector<String>();
        String chainRev = pro.reverse(chain);
        Hashtable<String,Place> places = new Hashtable<String,Place>();
        LinkedList<String> alph = seqNlth.alphabet(chain);
        String dist = invariants.Distin(chain);
        LinkedList<String> relations = invariants.Relpn(chain);
        LinkedList<String> alphRev = seqNlth.alphabet(chainRev);
        LinkedList<String> relationsRev = invariants.Relpn(chainRev);
        for(int x = 0; x < alph.size(); x++)
        {
            sourceTransitions.add(alph.get(x));
            sinkTransitions.add(alph.get(x));       
        }
        
        //Solo ida. falta vuelta. 
        for(int x = 0; x < alph.size(); x++)
        {
            String[] pair = relations.get(x).split(" ");
            //System.out.println();
            if(pair.length < 2)
               {
                   if(sinkTransitions.contains(pair[0]))
                   {
                     Place p = new Place("p" + (places.size() + 1));
                     p.addInputTransition(alph.get(x));
                     sourceTransitions.remove(alph.get(x));
                     p.addOutputTransition(pair[0]);
                     sinkTransitions.remove(pair[0]);
                     places.put(p.getKey(), p);
                   }else{
                            for(int q = 0; q < alph.size(); q++) // q para reverchain 
                            {
                                if(relationsRev.get(q).indexOf(alph.get(x) + " ") != -1) // buscar el indice para el evneto en chain rev
                                {
                                    
                                    String[] paiRev = relationsRev.get(q).split(" "); 
                                    //buscar que lugar tiene el evento a la salida. 
                                    for(Enumeration<Place> en = places.elements(); en.hasMoreElements();){
                                        aux = en.nextElement().getKey();
                                        if(places.get(aux).hasOutputTransition(pair[0])) //encontro el primer lugar pero hay que examinar mas. 
                                        {
                                            place = aux;
                                            //place = en.nextElement().getKey();
                                            //break;
                                            //Checa todos los lugares con salida 11; 
               int test =  places.get(place).getInputTransitionsString().length();
               if(test == 3)
               {
                    event = places.get(place).getInputTransitionsString().substring(0, 2) + " ";
               }
               else{
                    event = places.get(place).getInputTransitionsString().substring(0, 3) + " ";
               }
               for(int y = 0; y < Tinvariants.size();y++)
               {
                  
                   if(Tinvariants.get(y).indexOf(alph.get(x) +" ") != -1 && Tinvariants.get(y).indexOf(event) != -1) //diferente t-invariante 
                   //if(dist.indexOf(alph.get(x) +" ") == -1 && dist.indexOf(places.get(place).getInputTransitionsString().substring(0, 2) + " ") == -1)     
                   {
                       //count = count + 1;
                       //si no se ha agregado las de entrada
                       //if(count == Tinvariants.size() ) //se cambio para aca. 
                       //{
                       
                       mismoT = true;
                       /*Place p1 = new Place("p" + (places.size() + 1));
                       p1.addInputTransition(alph.get(x));
                       sourceTransitions.remove(alph.get(x));
                       p1.addOutputTransition(pair[z]);
                       sinkTransitions.remove(pair[z]);
                       places.put(p1.getKey(), p1);*/ //maybe 
                       break;
                       
                       //}
                       
                   }
                   else{ if(y == Tinvariants.size() -1) //mismo t-ivarinate.
                   {
                       
                       /*Place p = new Place("p" + (places.size() + 1));
                       p.addInputTransition(alph.get(x));
                       sourceTransitions.remove(alph.get(x));
                       p.addOutputTransition(pair[pos]);
                       sinkTransitions.remove(pair[pos]);
                       p.addOutputTransition(pair[z]);
                       sinkTransitions.remove(pair[z]);
                       places.put(p.getKey(), p);*/
                       mismoT  = false;
                       break;
                       
                   }
                   
                   }
               }
               //fin checar los lugares salida 11. 
                                        }
                                        
                                    }
                                    if(mismoT ==  true)
                                    {
                                        if(sourceTransitions.contains(alph.get(x))) //checar si ya fue tomada en cuenta // es source o sink //no estoy checando los pares correctos
                                        {
                                            Place p = new Place("p" + (places.size() + 1));
                                            p.addInputTransition(alph.get(x));
                                            sourceTransitions.remove(alph.get(x));
                                            p.addOutputTransition(pair[pos]);
                                            sinkTransitions.remove(pair[pos]);
                                            places.put(p.getKey(), p);
                       
                                            count = 0;
                                            mismoT = false;
                                        }
                                        
                                    }
                                    else{
                                        if(sourceTransitions.contains(alph.get(x)))
                                        {
                                            places.get(place).addInputTransition(alph.get(x));
                                            sourceTransitions.remove(alph.get(x));
                                        }
                                        
                                    }
                                    //places.get(q)
                       
/////////////////Rev/////////////                                    
            //for(int z = pos +1 ; z < paiRev.length; z++)//while(pos < pair.length)
            //{
               
               //String  test =  places.get(place).getInputTransitionsString().substring(0, 2);
               /*for(int y = 0; y < Tinvariants.size();y++)
               {
                   if(Tinvariants.get(y).indexOf(alph.get(x) +" ") != -1 && Tinvariants.get(y).indexOf(places.get(place).getInputTransitionsString().substring(0, 2) + " ") != -1) //diferente t-invariante 
                   {
                       //count = count + 1;
                       //si no se ha agregado las de entrada
                       //if(count == Tinvariants.size() ) //se cambio para aca. 
                       //{
                       if(sourceTransitions.contains(alph.get(x))) //checar si ya fue tomada en cuenta // es source o sink //no estoy checando los pares correctos
                       {
                       Place p = new Place("p" + (places.size() + 1));
                       p.addInputTransition(alph.get(x));
                       sourceTransitions.remove(alph.get(x));
                       p.addOutputTransition(pair[pos]);
                       sinkTransitions.remove(pair[pos]);
                       places.put(p.getKey(), p);
                       
                       count = 0;
                       
                       /*Place p1 = new Place("p" + (places.size() + 1));
                       p1.addInputTransition(alph.get(x));
                       sourceTransitions.remove(alph.get(x));
                       p1.addOutputTransition(pair[z]);
                       sinkTransitions.remove(pair[z]);
                       places.put(p1.getKey(), p1); //maybe  cerrar
                       break;
                       }
                       //}
                       
                   }
                   else{ if(y == Tinvariants.size() -1) //mismo t-ivarinate.
                   {
                       if(sourceTransitions.contains(alph.get(x)))
                       {
                       places.get(place).addInputTransition(alph.get(x));
                       sourceTransitions.remove(alph.get(x));
                       /*Place p = new Place("p" + (places.size() + 1));
                       p.addInputTransition(alph.get(x));
                       sourceTransitions.remove(alph.get(x));
                       p.addOutputTransition(pair[pos]);
                       sinkTransitions.remove(pair[pos]);
                       p.addOutputTransition(pair[z]);
                       sinkTransitions.remove(pair[z]);
                       places.put(p.getKey(), p); cerrar
                       }
                   }
                   
                   }
               }*/
               //pos = pos + 1;
               //
            //}
//////////////////////  END REV////////////            
                                }
                                pos = 0;
                                
                            
                            }
                            
                        }
               }
            else{
            for(int z = pos +1 ; z < pair.length; z++)//while(pos < pair.length)
            {
               
              
               for(int y = 0; y < Tinvariants.size();y++)
               {
                   if(Tinvariants.get(y).indexOf(pair[pos] + " ") != -1 && Tinvariants.get(y).indexOf(pair[z] + " ") != -1) //mismo t-invariante
                   //if(dist.indexOf(pair[pos] + " ") == -1 && dist.indexOf(pair[z] + " ") == -1) 
                   {
                       //count = count + 1;
                       if(sinkTransitions.contains(pair[pos]))// && count == (Tinvariants.size()) )
                       {
                       Place p = new Place("p" + (places.size() + 1));
                       p.addInputTransition(alph.get(x));
                       sourceTransitions.remove(alph.get(x));
                       p.addOutputTransition(pair[pos]);
                       sinkTransitions.remove(pair[pos]);
                       places.put(p.getKey(), p);
                       
                       Place p1 = new Place("p" + (places.size() + 1));
                       p1.addInputTransition(alph.get(x));
                       sourceTransitions.remove(alph.get(x));
                       p1.addOutputTransition(pair[z]);
                       sinkTransitions.remove(pair[z]);
                       places.put(p1.getKey(), p1);
                       break;
                       }
                       else{
                       if(sinkTransitions.contains(pair[z])){ // && count == Tinvariants.size()){
                            
                                Place p = new Place("p" + (places.size() + 1));
                                p.addInputTransition(alph.get(x));
                                sourceTransitions.remove(alph.get(x));
                                p.addOutputTransition(pair[z]);
                                sinkTransitions.remove(pair[pos]);
                                places.put(p.getKey(), p);
                                break; //nesesitan siempre estar juntos. 
                            }
                           }
                   }
                   else{ if(y == Tinvariants.size() -1) //diferente t-ivarinate.
                   {
                       if(sinkTransitions.contains(pair[pos])) 
                              // || sourceTransitions.contains(alph.get(x)) ) //|| sinkTransitions.contains(pair[z])) //se agrego ultimo ||
                       {
                       Place p = new Place("p" + (places.size() + 1));
                       p.addInputTransition(alph.get(x));
                       sourceTransitions.remove(alph.get(x));
                       p.addOutputTransition(pair[pos]);
                       sinkTransitions.remove(pair[pos]);
                       p.addOutputTransition(pair[z]);
                       sinkTransitions.remove(pair[z]);
                       places.put(p.getKey(), p);
                       break;
                       }
                       else{
                           if(sinkTransitions.contains(pair[z])){
                              //ponerlo en el mismo lugar que tiene como entrada alph.get(x);
                               for(Enumeration<Place> en = places.elements(); en.hasMoreElements();){
                                        aux = en.nextElement().getKey();
                                        if(places.get(aux).hasInputTransition(alph.get(x)) && places.get(aux).hasOutputTransition(pair[pos])) //encontro el primer lugar pero hay que examinar mas. 
                                        {
                                            place = aux;
                                            
                                        }
                               }
                               places.get(place).addOutputTransition(pair[z]);
                               sourceTransitions.remove(alph.get(x));
                               break;
                           }
                       }
                   }
                   }
               }
               //pos = pos + 1; //se quito 
               //count = 0;
               //
            }
            
            }
            pos = 0;
        }
        return places;
    }
     
    /*public int findPlace (String sourceT)
    {
        int position = 0;
        Collection<Place> col =   places.values();
        for(Enumeration<Place> en = places.elements(); en.hasMoreElements();){
            String transition = en.nextElement().getKey();
            
            //sourceTransitions.add(transition);
            //sinkTransitions.add(transition);
        }
        return position;
    }*/
    /////Funciones mismo T-inv
    public Boolean sameT(String e1, String e2)
    {
        /*
            (Thursday, Octuber 16 2014)
            The function was modified in order to add the exeption to the  Rule 2 a) (see paper)
            "This rule applies most of the time, but
            a special situation could appear when tj||tk; in this case the
            dependency [ti, tj+tk] is not created."
            in this case return true event that tj tk are not in the same T-inv
            this would provoque put a place for each relation instead of one
        la regla ayuda pero no en todos los casos contra example "ElDiablo"
        */
        boolean ban = false;
        for(int x = 0; x < Tinvariants.size(); x++)
        {
            if( (Tinvariants.get(x).indexOf(e1 + " ") != -1 && Tinvariants.get(x).indexOf(e2 + " ") != -1) 
                    || (ConC.get(alph.indexOf(e1)).contains(e2 + " ") || ConC.get(alph.indexOf(e2)).contains(e1 + " "))) //mismo Tinvariante || si estos eventos no son concurrentes
            {
                    ban = true;
                    break;
            }
            else{
                //no hagas nada sigue siendo false.
            }
        }
        return ban;
    }
    public Hashtable<String,Place> addplaces2(String chain)
    {
        String aux = "" ,place = "", event = "";
        boolean same = false;
        int pos = 0, count = 0;
        Vector<String> sourceTransitions = new Vector<String>();
        Vector<String> sinkTransitions = new Vector<String>();
        String chainRev = pro.reverse(chain);
        Hashtable<String,Place> places = new Hashtable<String,Place>();
        LinkedList<String> alph = seqNlth.alphabet(chain);
        String dist = invariants.Distin(chain);
        LinkedList<String> relations = invariants.Relpn(chain);
        LinkedList<String> alphRev = seqNlth.alphabet(chainRev);
        LinkedList<String> relationsRev = invariants.Relpn(chainRev);
        for(int x = 0; x < alph.size(); x++)
        {
            //int a = relations.get(x).length();
            sourceTransitions.add(alph.get(x));
            sinkTransitions.add(alph.get(x));       
        }
        for(int w = 0; w < alph.size(); w++)
        {
            String[] neigh = relations.get(w).split(" "); //cuando vale uno nisiquiera entra.
            if(neigh.length < 2)
            {
                if(sinkTransitions.contains(neigh[0])) //si la tiene ninguna otra transicion la ha tomado
                   {
                     Place p = new Place("p" + (places.size() + 1));
                     p.addInputTransition(alph.get(w));
                     sourceTransitions.remove(alph.get(w));
                     p.addOutputTransition(neigh[0]);
                     sinkTransitions.remove(neigh[0]);
                     places.put(p.getKey(), p);
                   }
                else{
                    String key = findPlaceRev(neigh[0], places);
                    String e1 = getEvent(places.get(key).getInputTransitionsString());
                    same = sameT(alph.get(w), e1);
                    if(same == true)
                    {
                        Place p = new Place("p" + (places.size() + 1));
                        p.addInputTransition(alph.get(w));
                        sourceTransitions.remove(alph.get(w));
                        p.addOutputTransition(neigh[0]);
                        sinkTransitions.remove(neigh[0]);
                        places.put(p.getKey(), p);
                    }
                    else{
                        places.get(key).addInputTransition(alph.get(w));
                        sourceTransitions.remove(alph.get(w));
                    }
                    //System.out.println();
                    
                }
            }
            else{
            for(int x = 0; x < neigh.length ; x++)
            {
                
                for(int y = x +1 ; y < neigh.length; y++)
                {
                    same = sameT(neigh[x], neigh[y]);
                    if(same == true)
                    {
                        if(sinkTransitions.contains(neigh[x]))
                        {
                            // ya se tomo en cuenta la salida pero para diferente entrada
                            Place p = new Place("p" + (places.size() + 1));
                            p.addInputTransition(alph.get(w));
                            sourceTransitions.remove(alph.get(w));
                            p.addOutputTransition(neigh[x]);
                            sinkTransitions.remove(neigh[x]);
                            places.put(p.getKey(), p);
                       
                            Place p1 = new Place("p" + (places.size() + 1));
                            p1.addInputTransition(alph.get(w));
                            sourceTransitions.remove(alph.get(w));
                            p1.addOutputTransition(neigh[y]);
                            sinkTransitions.remove(neigh[y]);
                            places.put(p1.getKey(), p1);
                        }
                        else{
                            if(sinkTransitions.contains(neigh[y])) //nunca tomamos en cuenta el caso para mismo t-invariante de igual forma que para diferente. 
                            {
                                Place p = new Place("p" + (places.size() + 1));//Agregar lo mismo que par diferente t-inv cuando ya existe el lugar. 
                                p.addInputTransition(alph.get(w));
                                sourceTransitions.remove(alph.get(w));
                                p.addOutputTransition(neigh[y]);
                                sinkTransitions.remove(neigh[y]);
                                places.put(p.getKey(), p);
                            }
                            //modificacion
                            String key = findPlace(alph.get(w), neigh[x], places);
                            if(key.equals(""))
                            {
                                key = findPlaceRev(neigh[x], places);
                                String e1 = getEvent(places.get(key).getInputTransitionsString());
                                same = sameT(alph.get(w), e1);
                                if(same == false)
                                {
                                    places.get(key).addInputTransition(alph.get(w));
                                    sourceTransitions.remove(alph.get(w));
                                }
                            }
                            //fin modificacion
                        }
                    }
                    else{
                        if(sinkTransitions.contains(neigh[x]))
                        {
                            Place p = new Place("p" + (places.size() + 1));
                            p.addInputTransition(alph.get(w));
                            sourceTransitions.remove(alph.get(w));
                            p.addOutputTransition(neigh[x]);
                            sinkTransitions.remove(neigh[x]);
                            p.addOutputTransition(neigh[y]);
                            sinkTransitions.remove(neigh[y]);
                            places.put(p.getKey(), p);
                        }
                        else{
                             String key = findPlaceRev(neigh[x], places);
                             if(places.get(key).hasInputTransition(alph.get(w))) //no estoy seguro si es x o es w alph.get();
                             {
                                ////modificacion
                                Vector v = places.get(key).getOutputTransitions();
                                int cont = 0;
                                for(int k = 0; k< v.size(); k++)
                                {
                                    if(!sameT(v.get(k).toString(), neigh[y]))
                                    {
                                       cont++; 
                                    }
                                }
                                if(cont == v.size() )
                                {
                                    places.get(key).addOutputTransition(neigh[y]);
                                    sinkTransitions.remove(neigh[y]);
                                }else{
                                    Place p = new Place("p" + (places.size() + 1));
                                    p.addInputTransition(alph.get(w));
                                    sourceTransitions.remove(alph.get(w));
                                    p.addOutputTransition(neigh[x]);
                                    sinkTransitions.remove(neigh[x]);
                                    p.addOutputTransition(neigh[y]);
                                    sinkTransitions.remove(neigh[y]);
                                    places.put(p.getKey(), p);
                                }
                                ///fin modififcacion
                                /*antes
                                places.get(key).addOutputTransition(neigh[y]);
                                sinkTransitions.remove(neigh[y]);*/
                             }
                             else{
                                 String e1 = getEvent(places.get(key).getInputTransitionsString());
                                 //System.out.println(places.get(key).getInputTransitionsString());
                                 same = sameT(alph.get(w), e1);
                                 if(same == true)
                                 {
                                    Place p = new Place("p" + (places.size() + 1));
                                    p.addInputTransition(alph.get(w));
                                    sourceTransitions.remove(alph.get(w));
                                    p.addOutputTransition(neigh[x]);
                                    sinkTransitions.remove(neigh[x]);
                                    p.addOutputTransition(neigh[y]);
                                    sinkTransitions.remove(neigh[y]);
                                    places.put(p.getKey(), p);
                                 }
                                 else{
                                     places.get(key).addInputTransition(alph.get(w));
                                     sourceTransitions.remove(alph.get(w));
                                 }
                                         
                             }
                       
                        }
                    }
                
                }  
            }
            }
        }
        //Reversa;
        
        return places;
    }
    // encontrar el lugar que ya se creo 
    
    public String findPlace(String e1, String e2, Hashtable<String,Place> places)
    {
        String key = "", aux = " ";
        for(Enumeration<Place> en = places.elements(); en.hasMoreElements();)
        {
            aux = en.nextElement().getKey();
            if(places.get(aux).hasInputTransition(e1) && places.get(aux).hasOutputTransition(e2)) //encontro el primer lugar pero hay que examinar mas. 
            {
                 key = aux;
            }
        }
        return key;
    }
    
    public boolean alreadyExist(String e1,String e2, Hashtable<String,Place> places)
    {
        String aux = "";
        for(Enumeration<Place> en = places.elements(); en.hasMoreElements();)
        {
            aux = en.nextElement().getKey();
            if(places.get(aux).hasInputTransition(e1) && places.get(aux).hasOutputTransition(e2) ) //encontro el primer lugar pero hay que examinar mas. 
            {
                 return true;
            }
        }
        return false;
    }
    public ArrayList<String> sameInputPlaces(String e1, Hashtable<String,Place> places)
    {
        String aux = " ";
        ArrayList<String> keys = new ArrayList<String>();
        for(Enumeration<Place> en = places.elements(); en.hasMoreElements();)
        {
            aux = en.nextElement().getKey();
            if(places.get(aux).hasInputTransition(e1)) //encontro el primer lugar pero hay que examinar mas. 
            {
                 keys.add(aux);
            }
        }
        return keys;
    }
    public ArrayList<String> sameOutputPlaces(String e1, Hashtable<String,Place> places)
    {
        String aux = " ";
        ArrayList<String> keys = new ArrayList<String>();
        for(Enumeration<Place> en = places.elements(); en.hasMoreElements();)
        {
            aux = en.nextElement().getKey();
            if(places.get(aux).hasOutputTransition(e1)) //encontro el primer lugar pero hay que examinar mas. 
            {
                 keys.add(aux);
            }
        }
        return keys;
    }
    public String findPlaceRev(String e1, Hashtable<String,Place> places)
    {
        String key = "", aux = " ";
        for(Enumeration<Place> en = places.elements(); en.hasMoreElements();)
        {
            aux = en.nextElement().getKey();
            if(places.get(aux).hasOutputTransition(e1)) //encontro el primer lugar pero hay que examinar mas. 
            {
                 key = aux;
            }
        }
        return key;
    }
     public int findRev(String e1, LinkedList<String> alphRev)
     {
         int pos = 0;
         for(int x = 0; x < alphRev.size(); x++)
         {
             if(alphRev.get(x).indexOf(e1) != -1)
             {
                 pos = x;
             }
         }
         return pos;
     }
     /////modificacion 
     public String getEvent(String e1)//cambiar para cuando las transiciones son T1 or A B 
     {
         String event = "";
         if(e1.length() == 2)
         {
             event = e1.substring(0, 1);
         }
         else{
             event = e1.substring(0, 2);//antes 3 cambio a 2.  
         }
         return event;
     }
     public Hashtable<String,Place> addplaces3(String chain)
     {
        String aux = "",aux2 = "" ,place = "", event = "";
        boolean same = false, newp = false;
        int pos = 0, count = 0;
        Vector<String> sourceTransitions = new Vector<String>();
        Vector<String> sinkTransitions = new Vector<String>();
        String chainRev = pro.reverse(chain);
        Hashtable<String,Place> places = new Hashtable<String,Place>();
        LinkedList<String> alph = seqNlth.alphabet(chain);
        String dist = invariants.Distin(chain);
        LinkedList<String> relations = invariants.Relpn(chain);
        LinkedList<String> alphRev = seqNlth.alphabet(chainRev);
        LinkedList<String> relationsRev = invariants.Relpn(chainRev);
        /*IF the not real concurrent disaperss
        //relations.set(5, "T5 T3 T1 ");
        //relations.set(6, "T2 T8 T0 ");
        //*/
        /*If T-invariantes are O.K.
        Tinvariants.set(1, "T1 T2 T7 T8");
        Tinvariants.set(2, "T0 T3 T4 T5 T6");
        Tinvariants.add("T0 T1 T5 T6 T7 T8");*/
       /* for(int x = 0; x < alph.size(); x++)//llenado de los vectores de entrada salida
        {
            sourceTransitions.add(alph.get(x));
            sinkTransitions.add(alph.get(x));       
        }*/
        //[C -> (A,B,...,D)]
        for(int x = 0; x < alph.size(); x++) //source
        {
            String[] neigh = relations.get(x).split(" "); 
            for(int y = 0; y < neigh.length; y++)//sink  [x -> y]
            {
                newp = false;
                if( !alreadyExist( alph.get(x), neigh[y] , places )){
                //else{
///////////////////////////////
                    ArrayList<String> inkeys = sameInputPlaces(alph.get(x), places);
                if(!inkeys.isEmpty()){    
                    Iterator<String> it = inkeys.iterator();
                    while(it.hasNext()){ //recorrer todos los lugares con la entrada x
                        String key = it.next();
                        String outpuT = places.get(key).getOutputTransitionsString();
                        StringTokenizer token = new StringTokenizer(outpuT, "|");
                        while(token.hasMoreTokens()){ //recorrer todas las salidas de cada uno de los lugares anteriores
                            String outpuTransition = token.nextToken();
                            if(sameT(outpuTransition, neigh[y]))
                            {
                                newp = true; //antes de agregar el nuevo lugar checar si ya existe un lugar con esa salida para intentar el merge
                                /*Place p2 = new Place("p" + (places.size() + 1));
                                p2.addInputTransition(alph.get(x));
                                p2.addOutputTransition(neigh[y]);
                                places.put(p2.getKey(), p2); //p2*/
                            }else{ //tratamos de ponerla transicion en un lugar existente si no esposible para ninguna crear un lugar nuevo
                                places.get(key).addOutputTransition(neigh[y]);
                                newp = false;
                                break;
                            }  
                        }//fin segundo while
                        if(!newp){break;}
                    } //fin primer while
                    if(newp){
                                Place p2 = new Place("p" + (places.size() + 1));
                                p2.addInputTransition(alph.get(x));
                                p2.addOutputTransition(neigh[y]);
                                places.put(p2.getKey(), p2);
                    }
                } else{ //trata con else
////////////////////////
                        ArrayList<String> outkeys = sameOutputPlaces(neigh[y], places);
                if(!outkeys.isEmpty()){    
                        Iterator<String> it = outkeys.iterator();
                        while(it.hasNext()){ //recorrer todos los lugares con la salida y
                        String key = it.next();
                        String inpuT = places.get(key).getInputTransitionsString(); //todas sus transiciones de salida 
                        StringTokenizer token = new StringTokenizer(inpuT, "|");
                        while(token.hasMoreTokens()){ //recorrer todas las salidas de cada uno de los lugares anteriores
                            String inpuTransition = token.nextToken();
                            if(sameT(inpuTransition, alph.get(x)))
                            {
                                newp = true;
                                /*Place p3 = new Place("p" + (places.size() + 1));
                                p3.addInputTransition(alph.get(x));
                                p3.addOutputTransition(neigh[y]);
                                places.put(p3.getKey(), p3);*/
                            }else{
                                places.get(key).addInputTransition(alph.get(x)); //places.get(key).addOutputTransition(neigh[y])
                                newp = false;
                                break;
                            }  
                        }
                        if(!newp){break;}
                    }
                        if(newp){
                                Place p2 = new Place("p" + (places.size() + 1));
                                p2.addInputTransition(alph.get(x));
                                p2.addOutputTransition(neigh[y]);
                                places.put(p2.getKey(), p2);
                        }
                }else{
                     //se remueve la condicion al principio
                    //si no exixte ponlo 
                    //{
                        //no existe ningun lugar con entrada x y con salida w por lo tanto se crea.
                        Place p = new Place("p" + (places.size() + 1));
                        p.addInputTransition(alph.get(x));
                        p.addOutputTransition(neigh[y]);
                        places.put(p.getKey(), p);
                    //}
                }
  //////////////////////////
            }
            //} //fin primer else     
            }
            }
        }
        return places;
     }
     public boolean[][] invector(String[] a)
     {
         int n = a.length, aux = 0, aux1 = 0;
         //int c = factorial(n) / factorial(2) * factorial(n -2);
         boolean[][] invec = new boolean[n][n];
         for(int x = 0; x < a.length; x++)
         {
             for(int y = x +1 ; y < a.length; y++)
             {
                 invec[x][y] = sameT(a[x], a[y]);
                 //aux1 = (x + y) - 1;
                 //aux++;
                 //System.out.println();
             }
         }
         return invec;
         
     }
     public int factorial(int num)
     {
         int factorial = 1;
         for( int x = 1; x <= num; x++)
         {
             factorial *= x;
         }
         return factorial;
     }
     public String findPlace3(String e1, Hashtable<String,Place> places)
    {
        String key = "", aux = " ";
        for(Enumeration<Place> en = places.elements(); en.hasMoreElements();)
        {
            aux = en.nextElement().getKey();
            if(places.get(aux).hasOutputTransition(e1)) //encontro el primer lugar pero hay que examinar mas. 
            {
                 key = aux;
            }
        }
        return key;
    }
     
     public LinkedList<String> alg(String neig)
     {
         LinkedList<String> inPout = new LinkedList();
         
         return inPout;
     }
             
}
