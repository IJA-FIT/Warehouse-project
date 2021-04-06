package src.shelf_manipulation.goods;

public interface GoodsShelf {

    int put(GoodsItem var1);

    boolean containsGoods(Goods var1);

    GoodsItem removeAny();

    int size();

}
