/**
* <h1>ShelfManipulator</h1>
* ShelfManipulator zajišťuje operace s regálem.
* 
* @author Vojtěch Fiala <xfiala61>
*/

package src.shelf_manipulation.store;

import src.shelf_manipulation.store.*;
import src.shelf_manipulation.goods.*;
import src.utils.CoordsConverter;
import src.map_manipulation.*;

import java.util.HashMap;

/**
 * <h2>Goods ShelfManipulator zajišťuje operace s regálem.</h2>
 */
public class ShelfManipulator {

    /**
    * <h2>shelf_map představuje seznam regálů a jejich souřadnic.</h2>
    */
    public static HashMap<String, GoodsShelf> shelf_map = new HashMap<String, GoodsShelf>();
    private String id;
    private GoodsShelf shelf;
    private CoordsConverter cnv = new CoordsConverter();
    private MapControl map = new MapControl();
    
    /**
    * <h2>ShelfManipulator Inicializace</h2>
    * Nastaví souřadnice, na kterých se regál nachází.
    * @param id Souřadnice v zkonvertované textové podobě.
    * @param shelf Regál, jež se má na daných souřadnicích vyskytovat.
    */
    public ShelfManipulator(String id, GoodsShelf shelf) {
        this.id = id;
        this.shelf = shelf;

        if (!this.shelf_map.containsKey(id)) {
            int[] coords = cnv.coordsInt(this.id);
            if (this.map.getItem(coords[0], coords[1]) != 5) {
                throw new ArithmeticException("Neplatna inicializace regalu!");
            }
            
            this.shelf_map.put(this.id, this.shelf);
        }
    }

    /**
    * <h2>getPosition</h2>
    * Vrátí souřadnice, na kterých se regál nachází.
    * @return Souřadnice regálu v textové podobě.
    */
    public String getPosition() {
        return this.id;
    }

}
