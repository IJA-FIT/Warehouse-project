package src.cart;

import src.shelf_manipulation.goods.GoodsItem;
import src.shelf_manipulation.goods.GoodsShelf;

public class CartContent {

    private GoodsItem ware = null;
    private GoodsShelf shelf = null;

    public GoodsItem getWare() {
        return this.ware;
    }

    public void setShelf(GoodsShelf shelf) {
        this.shelf = shelf;
    }

    public void loadItem() {
        if (this.shelf.size() > 0)
            this.ware = shelf.removeAny();
    }

    public void unloadItem() {
        this.ware.sell();
        this.ware = null;
    }


}