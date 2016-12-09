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
public class SRE {
    private IntegerArray array;
    private Boolean []SRE;
    
    public SRE(IntegerArray arary) {
        this.array = arary;
        initialSRE();
    }

    public Boolean[] getSRE() {
        return SRE;
    }
    
    public void setSRE(Boolean[] nSRE) {
        for(int i = 0; i < SRE.length; i++)
        {
            if(nSRE[i] != this.SRE[i] && nSRE[i] != null)
            {
                this.SRE[i] = nSRE[i];
            }
//            
//            if(nSRE[i] != this.SRE[i] && this.SRE[i] != null)
//            {
//                this.SRE[i] = nSRE[i];
//            }
            
        }
    }
    
    private void initialSRE()
    {   
        this.SRE = new Boolean[this.array.getArray().length];
        for(int i = 0; i < this.array.getArray().length; i++)
        {
            switch(this.array.getArray()[i])
            {
                case 1:
                    this.SRE[i] = true;
                break;
                case -1:
                    this.SRE[i] = false;
                break;
                case 0:
                    this.SRE[i] = null;
                break;
            }
        }
    }
    
    
}

