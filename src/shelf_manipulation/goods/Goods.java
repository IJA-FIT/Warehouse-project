package src.shelf_manipulation.goods;
import java.time.LocalDate;

public interface Goods {

    String getName();

    boolean addItem(GoodsItem var1);

    GoodsItem newItem(LocalDate var1);

    boolean remove(GoodsItem var1);

    boolean empty();

    int size();
}
