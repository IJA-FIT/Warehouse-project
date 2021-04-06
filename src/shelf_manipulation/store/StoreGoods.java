package src.shelf_manipulation.store;

import src.shelf_manipulation.goods.Goods;
import src.shelf_manipulation.goods.GoodsItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class StoreGoods implements Goods {

    public String name;
    private ArrayList<GoodsItem> goods_list = new ArrayList<GoodsItem>();

    public StoreGoods(String get_name) {
        this.name = get_name;
    }
    
    public String getName() {
        return this.name;
    }

    public boolean addItem(GoodsItem goodsItem) {
        return goods_list.add(goodsItem);
    }

    public GoodsItem newItem(LocalDate localDate) {
        GoodsItem goods1 = new StoreGoodsItem(this, localDate);
        addItem(goods1);
        return goods1;
    }

    public boolean remove(GoodsItem goodsItem) {
        return goods_list.remove(goodsItem);
    }

    public boolean empty() {
        return goods_list.size() <= 0;
    }

    public int size() {
        return goods_list.size();
    }
}
