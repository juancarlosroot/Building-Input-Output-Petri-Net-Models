/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block1;

/**
 *
 * @author Humanoide
 */
public class Block3 {
    
    public static void main(String [] args){
        //cargar la matriz observable y no observable desde archivo
        TwoMatrix tm=Merge.readMatrix("observable.txt","non-observable.txt");
        //mergear
        int[][] a=Merge.merge(tm.MObservable, tm.MNOObservable);
        Merge.printMatrix(a);
        //reducir
        int[][] b=Merge.reduceMatrix(tm.MObservable, tm.MNOObservable);
        Merge.printMatrix(b);
        Merge.exportNet(b,"salida.xml");
    }
    
}
