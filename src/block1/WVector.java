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
class WVector {
    //leer clase IntegerArray
    private IntegerArray inputs;//entradas
    private IntegerArray outputs;//salidas
    private float time;
    private int index;
    //private SCRE scre;
    
    public WVector(Integer[] inputs, Integer[] outputs, float time) {// constructor
        this.inputs = new IntegerArray(inputs);
        this.outputs = new IntegerArray(outputs);
        //this.scre = new SCRE(this.inputs); 
        this.time = time;
        this.index = 0;
    }

    public IntegerArray getInputs() {// get objeto IntegerArray correspondiente a los inputs/entradas
        return inputs;
    }

    public void setInputs(IntegerArray inputs) {// set objeto IntegerArray correspondiente a los inputs/entradas
        this.inputs = inputs;
    }

    public IntegerArray getOutputs() {// get objeto IntegerArray correspondiente a los outputs/salidas
        return outputs;
    }

    public void setOutputs(IntegerArray outputs) {// set objeto IntegerArray correspondiente a los outputs/salidas
        this.outputs = outputs;
    }

    /*public SCRE getScre() {
        return scre;
    }*/

    public float getTime() {
        return time;
    }

    public void setTime(float time) {// set el tiempo del vector
        this.time = time;
    }
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {// set el tiempo del vector
        this.index = index;
    }
    
    public boolean isEntrance0()// método para verificar si sus entradas son 0
    {
        for(int i = 0; i < inputs.getArray().length; i++)
            if(inputs.getArray()[i] != 0)
                return false;
        return true;
    }
    public boolean isOutput0()// método para verificar si sus salidas son 0
    {
        for(int i = 0; i < outputs.getArray().length; i++)
            if(outputs.getArray()[i] != 0)
                return false;
        return true;
    }

    public void print()//imprime todo lo que lleva el vector
    {
        System.out.print(this.time+"\t");
        for(int i = 0; i < inputs.getArray().length; i++)
            System.out.print(inputs.getArray()[i] + " ");
        System.out.print("| ");
        for(int i = 0; i < outputs.getArray().length; i++)
            System.out.print(outputs.getArray()[i] + " ");
        System.out.print("| ");
        /*for(int i = 0; i < scre.getSCRE().length; i++)
            System.out.print(scre.getSCRE()[i] + " ");*/
        System.out.print("\n");
    }
}
