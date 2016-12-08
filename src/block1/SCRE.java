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
public class SCRE {
    private IntegerArray array;
    private Boolean []SCRE;
    
    public SCRE(IntegerArray arary) {
        this.array = arary;
        initialSCRE();
    }

    public Boolean[] getSCRE() {
        return SCRE;
    }
    
    public void setSCRE(Boolean[] nSCRE) {
        for(int i = 0; i < SCRE.length; i++)
        {
            if(nSCRE[i] != this.SCRE[i] && nSCRE[i] != null)
            {
                this.SCRE[i] = nSCRE[i];
            }
//            
//            if(nSCRE[i] != this.SCRE[i] && this.SCRE[i] != null)
//            {
//                this.SCRE[i] = nSCRE[i];
//            }
            
        }
    }
    
    public void setSCRE(Boolean nSCRE, int i) {
        this.SCRE[i] = nSCRE;
    }
    
    private void initialSCRE()
    {   
        this.SCRE = new Boolean[this.array.getArray().length];
        for(int i = 0; i < this.array.getArray().length; i++)
        {
            switch(this.array.getArray()[i])
            {
                case 1:
                    this.SCRE[i] = true;
                break;
                case -1:
                    this.SCRE[i] = null;
                break;
                case 0:
                    this.SCRE[i] = false;
                break;
            }
        }
    }
    
    
}
