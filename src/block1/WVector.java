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

    IntegerArray inputs;
    IntegerArray outputs;
    SCRE scre;
    
    public WVector(Integer[] inputs, Integer[] outputs) {
        this.inputs = new IntegerArray(inputs);
        this.outputs = new IntegerArray(outputs);
        this.scre = new SCRE(this.inputs); 
    }

    public IntegerArray getInputs() {
        return inputs;
    }

    public void setInputs(IntegerArray inputs) {
        this.inputs = inputs;
    }

    public IntegerArray getOutputs() {
        return outputs;
    }

    public void setOutputs(IntegerArray outputs) {
        this.outputs = outputs;
    }

    public SCRE getScre() {
        return scre;
    }
    
    public boolean isEntrance0()
    {
        for(int i = 0; i < inputs.getArray().length; i++)
            if(inputs.getArray()[i] != 0)
                return false;
        return true;
    }
    public boolean isOutput0()
    {
        for(int i = 0; i < outputs.getArray().length; i++)
            if(outputs.getArray()[i] != 0)
                return false;
        return true;
    }

    public void print()
    {
        for(int i = 0; i < inputs.getArray().length; i++)
            System.out.print(inputs.getArray()[i] + " ");
        System.out.print("| ");
        for(int i = 0; i < outputs.getArray().length; i++)
            System.out.print(outputs.getArray()[i] + " ");
        System.out.print("| ");
        for(int i = 0; i < scre.getSCRE().length; i++)
            System.out.print(scre.getSCRE()[i] + " ");
        System.out.print("\n");
    }
}
