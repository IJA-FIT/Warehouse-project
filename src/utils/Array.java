package src.utils;
import java.util.Arrays;


public class Array {

    // https://www.educative.io/edpresso/how-to-append-to-an-array-in-java
    public String[] append(String[] array, String value) {
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = value;
        return array;
    }

    // https://stackabuse.com/remove-element-from-an-array-in-java/
    public String[] remove(String[] array, int index) {
        String[] copy = new String[array.length - 1];

        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != index) {
                copy[j++] = array[i];
            }
        }
        return copy;
    }
}