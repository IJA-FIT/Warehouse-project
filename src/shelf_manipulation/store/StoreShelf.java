package src.shelf_manipulation.store;

import src.shelf_manipulation.goods.Goods;
import src.shelf_manipulation.goods.GoodsItem;
import src.shelf_manipulation.goods.GoodsShelf;

import java.util.*;

public class StoreShelf implements GoodsShelf {

    private Map<String, ArrayList<GoodsItem>> shelf = new HashMap<String, ArrayList<GoodsItem>>();

    public void put(GoodsItem item) {
        String search = item.goods().getName();
        if (this.shelf.get(search) == null) {
            ArrayList<GoodsItem> insert = new ArrayList<GoodsItem>();
            insert.add(item);
            this.shelf.put(item.goods().getName(), insert);
        }
        else {
           this.shelf.get(search).add(item);
        }
    }

    public boolean containsGoods(Goods goods) {
        String search = goods.getName();
        return this.shelf.get(search) != null;
    }

    public GoodsItem removeAny(Goods goods) {
        String search = goods.getName();
        if (this.shelf.get(search) != null) {
            GoodsItem ret_val = this.shelf.get(search).get(0);
            this.shelf.get(search).remove(0);
            return ret_val;
        }
        else
            return null;
    }

    public int size(Goods goods) {
        String search = goods.getName();
        if (this.shelf.get(search) != null)
            return this.shelf.get(search).size();
        else
            return 0;
    }
}
