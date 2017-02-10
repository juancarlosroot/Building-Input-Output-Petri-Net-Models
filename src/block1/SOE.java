/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block1;

/**
 *
 * @author PC
 */
public class SOE {
    private IntegerArray array;
    private Boolean []SOE;
    
    public SOE(IntegerArray arary) {
        this.array = arary;
        initialSOE();
    }

    public Boolean[] getSOE() {
        return SOE;
    }
    public IntegerArray getArraySOE() {
        return array;
    }
    
    public void setSOE(Boolean[] nSOE) {
        for(int i = 0; i < SOE.length; i++)
        {
            if(nSOE[i] != this.SOE[i] && nSOE[i] != null)
            {
                this.SOE[i] = nSOE[i];
            }
//            
//            if(nSOE[i] != this.SOE[i] && this.SOE[i] != null)
//            {
//                this.SOE[i] = nSOE[i];
//            }
            
        }
    }
    
    private void initialSOE()
    {   
        this.SOE = new Boolean[this.array.getArray().length];
        for(int i = 0; i < this.array.getArray().length; i++)
        {
            switch(this.array.getArray()[i])
            {
                case 1:
                    this.SOE[i] = true;
                break;
                case -1:
                    this.SOE[i] = false;
                break;
                case 0:
                    this.SOE[i] = null;
                break;
            }
        }
    }
    
    public Boolean compareSOE(SOE soe2){
        for(int i = 0; i < this.SOE.length; i++){
            if(this.SOE[i]!=soe2.SOE[i]){
                return false;
            }
        }
        return true;
    }
    
    public void printSOE(){
        for(int i = 0; i < this.SOE.length; i++){
            System.out.print(this.SOE[i]+" ");
            
        }
        System.out.println();
    }
    
    public void printArraySOE(){
        for(int i = 0; i < this.array.getArray().length; i++){
            System.out.print(this.array.getArray()[i]+" ");
            
        }
        System.out.println();
    }
}
