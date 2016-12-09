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
public class MRow{
    int[] row;
    boolean mark;
    boolean Nobservable;
    
    public MRow(int[] row, boolean Nobservable){
        this.Nobservable=Nobservable;
        this.row=row;
        mark=false;
    }

    public int[] getRow() {
        return row;
    }

    public void setRow(int[] row) {
        this.row = row;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }
    /**
     * método para saber si una fila es igual a otra
     * @param r
     * @return 
     */
    
    public boolean isEquals(MRow r){
        int array[]=r.row;
        int array2[]=this.row;
        boolean band=false;
        if(array.length==array2.length){
            for(int i=0;i<r.row.length;i++){
                if(array[i]==array2[i]){
                    if(i==r.row.length-1){
                        band=true;
                    }
                }
                else{
                    band=false;
                    break;
                }
                //System.out.println(" "+array[i]+" - "+array2[i]);
            }
                      
        }
        else{
            band=false;
        }  
        return band;
    }
    
    public void printRow(){
        String cad="";
        for(int i:this.row){
            cad=cad+" "+i;
        }
        System.out.println(cad+" "+this.mark);
    }
    
    
    
}
