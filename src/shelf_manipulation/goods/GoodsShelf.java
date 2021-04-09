/**
* <h1>GoodsShelf</h1>
* GoodsShelf reprezentuje regál a zajišťuje operace s položkami v něm.
* Inspirováno domácím úkolem č.1.
*
* @author Vojtěch Fiala <xfiala61>
*/

package src.shelf_manipulation.goods;


/**
 * <h2>Goods GoodsShelf reprezentuje regál a zajišťuje operace s položkami v něm.</h2>
 * Inspirováno domácím úkolem č.1.
 */
public interface GoodsShelf {

    /**
    * <h2>put</h2>
    * Vloží položku do regálu.
    * @param var1 Konkrétní položka správného typu.
    */
    void put(GoodsItem var1);

    /**
    * <h2>containsGoods</h2>
    * Metoda pro zjištění, zda-li položka obsahuje daný typ zboží.
    * @param var1 Typ zboží.
    * @return True/False v závislosti na výsledku.
    */
    boolean containsGoods(Goods var1);

    /**
    * <h2>removeAny</h2>
    * Metoda pro odstranění položky z regálu.
    * @return Odstraněná položka.
    */
    GoodsItem removeAny();

    /**
    * <h2>size</h2>
    * Metoda pro zjištění počtu položek v regálu.
    * @return Počet položek.
    */
    int size();

    /**
    * <h2>getShelfType</h2>
    * Metoda pro zjištění názvu typu zboží, které se do regálu ukládá.
    * @return Typ zboží.
    */
    String getShelfType();

}
