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
public class Fr {
    private SOE soe;
    private SRE sre;
    private SCRE scre;
    private int id;
    
    public Fr(SOE soe, SRE sre, SCRE scre, int id) {
        this.soe = soe;
        this.sre = sre;
        this.scre = scre;
        this.id = id;
    }
    
    public SOE getSOE(){
        return this.soe;
    }
    
    public void setSOE(SOE soe){
        this.soe = soe;
    }
    
    public SRE getSRE(){
        return this.sre;
    }
    
    public void setSRE(SRE sre){
        this.sre = sre;
    }   
    
    public SCRE getSCRE(){
        return this.scre;
    }
    
    public void setSCRE(SCRE scre){
        this.scre = scre;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
}
