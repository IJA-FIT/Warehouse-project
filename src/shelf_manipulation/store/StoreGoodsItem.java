package src.shelf_manipulation.store;

import src.shelf_manipulation.goods.Goods;
import src.shelf_manipulation.goods.GoodsItem;

import java.time.LocalDate;

public class StoreGoodsItem implements GoodsItem {

    private Goods goods;
    private LocalDate date;
    public StoreGoodsItem(Goods goods_nm, LocalDate date_get) {
        this.goods = goods_nm;
        this.date = date_get;
    }

    public Goods goods() {
        return this.goods;
    }

    public boolean sell() {
        return goods.remove(this);
    }
}
