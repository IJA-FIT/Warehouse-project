/**
* <h1>Goods</h1>
* Goods představují rozhraní typu zboží.
* Inspirováno domácím úkolem č.1.
* 
* @author Vojtěch Fiala <xfiala61>
*/

package src.shelf_manipulation.goods;
import java.time.LocalDate;

/**
 * <h2>Goods představují rozhraní typu zboží.</h2>
 * Inspirováno domácím úkolem č.1.
 */
public interface Goods {

    /**
    * <h1>getName</h1>
    * Metoda pro získání názvu typu zboží.
    * @return Název typu zboží.
    */
    String getName();

    /**
    * <h1>addItem</h1>
    * Přidá konkrétní položku pod tento typ zboží.
    * @param var1 Konkrétní položka.
    * @return Výsledek operace.
    */
    boolean addItem(GoodsItem var1);

    /**
    * <h1>newItem</h1>
    * Vytvoří novou konkrétní položku tohoto typu zboží.
    * @param var1 Současný čas typu LocalDate.
    * @return Odkaz na vytvořený prvek.
    */
    GoodsItem newItem(LocalDate var1);

    /**
    * <h1>remove</h1>
    * Odstraní konkrétní položku z tohoto typu zboží.
    * @param var1 Konkrétní položka.
    * @return Výsledek operace.
    */
    boolean remove(GoodsItem var1);

    /**
    * <h1>empty</h1>
    * Zjistí, zda-li nějaká položka tohoto typu existuje.
    * @return Výsledek operace.
    */
    boolean empty();

    /**
    * <h1>size</h1>
    * Zjistí počet konkrétních položek typu.
    * @return Počet položek.
    */
    int size();
}
