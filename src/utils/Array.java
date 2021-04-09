/**
* <h1>Array</h1>
* Array zajišťuje operace nad arrayi typu String.
* 
* @author Vojtěch Fiala <xfiala61>
*/


package src.utils;
import java.util.Arrays;

/**
 * <h2>Array zajišťuje operace nad arrayi typu String.</h2>
 */
public class Array {

    // https://www.educative.io/edpresso/how-to-append-to-an-array-in-java

    /**
    * <h2>append</h2>
    * Přidá prvek na konec arraye typu String.
    * @param array Array, ke kterému se má prvek přidat.
    * @param value Prvek, který se má přidat.
    * @return Array s přidaným prvkem.
    */
    public String[] append(String[] array, String value) {
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = value;
        return array;
    }

    // https://stackabuse.com/remove-element-from-an-array-in-java/

    /**
    * <h2>remove</h2>
    * Odstraní prvek na dané pozici z arraye typu String.
    * @param array Array, ze kterého se má prvek odstranit.
    * @param index Index prvku, který se má odstranit.
    * @return Array bez odstraněného prvku.
    */
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