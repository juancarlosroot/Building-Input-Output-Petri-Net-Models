/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block1;


/**This Object contains the observable and non-observable matrix
 */
public class TwoMatrix {
    
    int MObservable[][];
    int MNOObservable[][];

    public TwoMatrix(int[][] matrix1,int[][] matrix2) {
        this.MObservable=matrix1;
        this.MNOObservable=matrix2;
    }

    public int[][] getMObservable() {
        return MObservable;
    }

    public void setMObservable(int[][] MObservable) {
        this.MObservable = MObservable;
    }

    public int[][] getMNOObservable() {
        return MNOObservable;
    }

    public void setMNOObservable(int[][] MNOObservable) {
        this.MNOObservable = MNOObservable;
    }


  }