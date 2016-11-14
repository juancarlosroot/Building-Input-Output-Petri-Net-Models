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
public class IntegerArray {

    Integer[] array;

    public IntegerArray(Integer[] array) {
        this.array = array;
    }

    public Integer[] getArray() {
        return array;
    }

    public void setArray(Integer[] array) {
        this.array = array;
    }
    
    @Override
    public int hashCode() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            buffer.append(array[i]);
        }

        return buffer.toString().hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object object) {
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
        if (this.hashCode() == integerArray.hashCode()) {
            return true;
        }
        return false;
    }

}
