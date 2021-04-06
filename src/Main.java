import src.shelf_manipulation.store.StoreShelf;
import src.shelf_manipulation.store.StoreGoods;
import src.shelf_manipulation.store.StoreGoodsItem;
import src.shelf_manipulation.goods.Goods;
import src.shelf_manipulation.goods.GoodsItem;
import src.shelf_manipulation.goods.GoodsShelf;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Goods goods1 = new StoreGoods("Stul");
        if (goods1.empty()) {
            System.out.println("Yey, zadnej stul");
        }
        else {
            System.out.println("Error, nakej stul");
        }

        GoodsItem itm11 = new StoreGoodsItem(goods1, LocalDate.of(2021, 1, 5));
        GoodsItem itm12 = new StoreGoodsItem(goods1, LocalDate.of(2021, 1, 6));

        goods1.addItem(itm11);
        goods1.addItem(itm12);

        if (goods1.size() != 2) {
            System.out.println("Error, uz mam 2 stoly");
        }
        else {
            System.out.println("Nice, mam 2 stoly");
        }
    }
}
