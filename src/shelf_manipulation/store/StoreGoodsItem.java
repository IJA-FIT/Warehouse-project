/**
* <h1>StoreGooodsItem</h1>
* Implementace třídy GoodsItem.
* Inspirováno a částečně převzato z vypracování domácího úkolu 1.
* Úkol 1 vypracoval Vojtěch Fiala <xfiala61>.

* @author Vojtěch Fiala <xfiala61>
*/

package src.shelf_manipulation.store;

import src.shelf_manipulation.goods.Goods;
import src.shelf_manipulation.goods.GoodsItem;

import java.time.LocalDate;

/**
 * <h2>Implementace třídy GoodsItem.</h2>
* Inspirováno a částečně převzato z vypracování domácího úkolu 1.
* Úkol 1 vypracoval Vojtěch Fiala &lt; xfiala61 &gt;.
 */
public class StoreGoodsItem implements GoodsItem {

    private Goods goods;
    private LocalDate date;

    /**
    * <h2>StoreGoodsItem Inicializace</h2>
    * Nastaví atributy položky.
    * @param goods_nm Název typu zboží.
    * @param date_get Datum.
    */
    public StoreGoodsItem(Goods goods_nm, LocalDate date_get) {
        this.goods = goods_nm;
        this.date = date_get;
    }

    /**
    * <h1>goods</h1>
    * Metoda pro získání typu konkrétní položky.
    * @return Typu zboží.
    */
    public Goods goods() {
        return this.goods;
    }

    /**
    * <h1>goods</h1>
    * Metoda pro "prodání" konkrétní položky - odstraní tuto položku ze skladu.
    * @return True/False Výsledek operace.
    */
    public boolean sell() {
        return goods.remove(this);
    }
}
