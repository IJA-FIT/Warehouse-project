/**
* <h1>StoreGooods</h1>
* Implementace třídy Goods.
* Inspirováno a částečně převzato z vypracování domácího úkolu 1.
* Úkol 1 vypracoval Vojtěch Fiala <xfiala61>.

* @author Vojtěch Fiala <xfiala61>
*/

package src.shelf_manipulation.store;

import src.shelf_manipulation.goods.Goods;
import src.shelf_manipulation.goods.GoodsItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * <h2>Implementace třídy Goods.</h2>
* Inspirováno a částečně převzato z vypracování domácího úkolu 1.
* Úkol 1 vypracoval Vojtěch Fiala &lt; xfiala61 &gt;.
 */
public class StoreGoods implements Goods {

    private String name;
    private ArrayList<GoodsItem> goods_list = new ArrayList<GoodsItem>();

    /**
    * <h2>StoreGoods Inicializace</h2>
    * Nastaví název typu zboží.
    * @param get_name Název typu zboží.
    */
    public StoreGoods(String get_name) {
        this.name = get_name;
    }
    
    /**
    * <h1>getName</h1>
    * Metoda pro získání názvu typu zboží.
    * @return Název typu zboží.
    */
    public String getName() {
        return this.name;
    }

    /**
    * <h1>addItem</h1>
    * Přidá konkrétní položku pod tento typ zboží.
    * @param goodsItem Konkrétní položka.
    * @return Výsledek operace.
    */
    public boolean addItem(GoodsItem goodsItem) {
        return goods_list.add(goodsItem);
    }

    /**
    * <h1>newItem</h1>
    * Vytvoří novou konkrétní položku tohoto typu zboží.
    * @param localDate Současný čas typu LocalDate.
    * @return Odkaz na vytvořený prvek.
    */
    public GoodsItem newItem(LocalDate localDate) {
        GoodsItem goods1 = new StoreGoodsItem(this, localDate);
        addItem(goods1);
        return goods1;
    }

    /**
    * <h1>remove</h1>
    * Odstraní konkrétní položku z tohoto typu zboží.
    * @param goodsItem Konkrétní položka.
    * @return Výsledek operace.
    */
    public boolean remove(GoodsItem goodsItem) {
        return goods_list.remove(goodsItem);
    }
    
    /**
    * <h1>empty</h1>
    * Zjistí, zda-li nějaká položka tohoto typu existuje.
    * @return Výsledek operace.
    */
    public boolean empty() {
        return goods_list.size() <= 0;
    }

    /**
    * <h1>size</h1>
    * Zjistí počet konkrétních položek typu.
    * @return Počet položek.
    */
    public int size() {
        return goods_list.size();
    }
}
