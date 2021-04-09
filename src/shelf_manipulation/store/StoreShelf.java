/**
* <h1>StoreShelf</h1>
* Implementace třídy GoodsShelf.
* Inspirováno a částečně převzato z vypracování domácího úkolu 1.
* Úkol 1 vypracoval Vojtěch Fiala <xfiala61>.

* @author Vojtěch Fiala <xfiala61>
*/

package src.shelf_manipulation.store;

import src.shelf_manipulation.goods.Goods;
import src.shelf_manipulation.goods.GoodsItem;
import src.shelf_manipulation.goods.GoodsShelf;

import java.util.*;

/**
 * <h2>Implementace třídy GoodsShelf.</h2>
* Inspirováno a částečně převzato z vypracování domácího úkolu 1.
* Úkol 1 vypracoval Vojtěch Fiala &lt; xfiala61 &gt;.
 */
public class StoreShelf implements GoodsShelf {

    private Map<String, ArrayList<GoodsItem>> shelf = new HashMap<String, ArrayList<GoodsItem>>();
    private String name;
    private int max_size = 100;

    /**
    * <h2>StoreShelf Inicializace</h2>
    * Nastaví typ zboží v polici.
    * Regál může obsahovat jen 1 typ zboží.
    * @param type Název typu zboží.
    */
    // Pri inicializaci zadej typ
    public StoreShelf(Goods type) {
        this.name = type.getName();
    }

    /**
    * <h2>getShelfType</h2>
    * Metoda pro zjištění názvu typu zboží, které se do regálu ukládá.
    * @return Typ zboží.
    */
    // funkce na zjistnei typu
    public String getShelfType() {
        return this.name;
    }

    /**
    * <h2>put</h2>
    * Vloží položku do regálu.
    * @param item Konkrétní položka správného typu.
    */
    // Vloz kus zbozi do regalu
    public void put(GoodsItem item) {
        String search = item.goods().getName();
        // Jestlize ale vlozene zbozi neodpovida typu zbozi v regalu, dej to volajicimu vedet
        if (search != getShelfType())
            throw new ArithmeticException("Neplatny typ zbozi!");

        // Pokud je regal prazdny, vytvor novy seznam veci v nem
        if (this.shelf.get(search) == null) {
            ArrayList<GoodsItem> insert = new ArrayList<GoodsItem>();
            insert.add(item);
            this.shelf.put(item.goods().getName(), insert);
        }
        // Jinak jenom vloz do seznamu veci
        else {
            if (this.size() < 100)
                this.shelf.get(search).add(item);
            else {
                throw new ArithmeticException("Regal je plny!");
            }
        }
    }

    /**
    * <h2>containsGoods</h2>
    * Metoda pro zjištění, zda-li položka obsahuje daný typ zboží.
    * @param goods Typ zboží.
    * @return True/False v závislosti na výsledku.
    */
    public boolean containsGoods(Goods goods) {
        String search = goods.getName();
        return this.shelf.get(search) != null;
    }

    /**
    * <h2>removeAny</h2>
    * Metoda pro odstranění položky z regálu.
    * @return Odstraněná položka.
    */
    // Dej z regalu pryc nahodny (aka 1. v seznamu) prvek
    public GoodsItem removeAny() {
        String search = getShelfType();
        if (this.shelf.get(search) != null) {
            GoodsItem ret_val = this.shelf.get(search).get(0);
            this.shelf.get(search).remove(0);
            return ret_val;
        }
        else
            return null;
    }

    /**
    * <h2>size</h2>
    * Metoda pro zjištění počtu položek v regálu.
    * @return Počet položek.
    */
    // Ziskej velikost regalu
    public int size() {
        String search = this.getShelfType();
        if (this.shelf.get(search) != null)
            return this.shelf.get(search).size();
        else
            return 0;
    }
}
