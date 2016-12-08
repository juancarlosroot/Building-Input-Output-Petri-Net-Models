/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block1;

import java.util.ArrayList;

/**
 *
 * @author luise
 */
public class ST 
{
    //private FS LambdaPrima;
    private Fr LambdaPrima;
    float timeFr;
    
    //public ST(FS LambdaPrimaRecibida, float Tau)
    public ST(Fr LambdaPrimaRecibida, float Tau)
    {
        this.LambdaPrima = LambdaPrimaRecibida;
        this.timeFr = Tau;
    }
}
