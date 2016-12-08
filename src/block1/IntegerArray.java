/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block1;

import java.util.Objects;

/**
 *
 * @author juancarlosroot
 */
public class IntegerArray {

    //el motivo de esta clase es para evitar problemas con los códigos hash, en caso de que no se utilicen se puede
    //cambiar el objeto por un array normal(clase WVector)
    
    Integer[] array;// contiene un arreglo

    public IntegerArray(Integer[] array) {// contructor
        this.array = array;
    }

    public Integer[] getArray() {
        return array;
    }

    public void setArray(Integer[] array) {
        this.array = array;
    }
    
    @Override
    public int hashCode() {// se crea un nuevo código para el objeto(IntegerArray)
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            buffer.append(array[i]*i);
        }
        return buffer.toString().hashCode(); 
    }
    
    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < this.array.length; i++) {
            buffer.append(this.array[i]*i);
        }
        return buffer.toString(); 
    }

    @Override
    public boolean equals(Object object) {// Iterador para comparar entre objetos del mismo tipo
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }
        IntegerArray integerArray = (IntegerArray) object;
        /*if (this.hashCode() == integerArray.hashCode()) {
            return true;
        }
        boolean flag = true;*/
        for (int i = 0; i < array.length; i++) {
            if(!Objects.equals(this.array[i], integerArray.array[i])){
                return false;
            }
        }
        return true;
    }
    
    public void add(Integer[] array){
        for (int i = this.array.length; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

}
