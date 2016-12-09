/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block1;

/**
 *
 * @author yolanda
 */
public class FS {
    private int idFr;
    private float time;
    
    public FS(int idFr, float time){
        this.idFr = idFr;
        this.time = time;
    }
    
    public void setIdFr(int idFr){
        this.idFr = idFr;
    }
    
    public int getIdFr(){
        return this.idFr;
    }
    
    public void setTime(float time){
        this.time = time;
    }
    
    public float getTime(){
        return this.time;
    }
    
}
