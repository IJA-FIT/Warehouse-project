/**
* <h1>GoodsItem</h1>
* GoodsItem představují konkrétní položku zboží daného typu.
* Inspirováno domácím úkolem č.1.
*
* @author Vojtěch Fiala <xfiala61>
*/

package src.shelf_manipulation.goods;

/**
 * <h2>Goods GoodsItem představují konkrétní položku zboží daného typu.</h2>
 * Inspirováno domácím úkolem č.1.
 */
public interface GoodsItem {

    /**
    * <h1>goods</h1>
    * Metoda pro získání typu konkrétní položky.
    * @return Typu zboží.
    */
    Goods goods();

    /**
    * <h1>goods</h1>
    * Metoda pro "prodání" konkrétní položky - odstraní tuto položku ze skladu.
    * @return True/False Výsledek operace.
    */
    boolean sell();
}
