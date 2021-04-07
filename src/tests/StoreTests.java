package src.tests;

import src.shelf_manipulation.store.StoreShelf;
import src.shelf_manipulation.store.StoreGoods;
import src.shelf_manipulation.store.StoreGoodsItem;
import src.shelf_manipulation.goods.Goods;
import src.shelf_manipulation.goods.GoodsItem;
import src.shelf_manipulation.goods.GoodsShelf;

import java.time.LocalDate;

public class StoreTests {
    int correct = 0;
    int all = 0;

    private void test_passed() {
        this.correct++;
    }

    private void test_ran() {
        this.all++;
    }

    private void initialize_test_001() {
        Goods goods1 = new StoreGoods("Stul");
        if (goods1.empty()) {
            test_passed();
            test_ran();
        }
        else {
            test_ran();
        }

        GoodsItem itm11 = new StoreGoodsItem(goods1, LocalDate.of(2021, 1, 5));
        GoodsItem itm12 = new StoreGoodsItem(goods1, LocalDate.of(2021, 1, 6));

        goods1.addItem(itm11);
        goods1.addItem(itm12);

        if (goods1.size() != 2) {
            test_ran();
        }
        else {
            test_passed();
            test_ran();
        }
    }

    private void initialize_test_002() {
        Goods goods1 = new StoreGoods("Stul");
        Goods goods2 = new StoreGoods("Zidle");

        GoodsShelf shelf = new StoreShelf(goods1);
        GoodsShelf shelf2 = new StoreShelf(goods2);

        GoodsItem itm11 = goods1.newItem(LocalDate.of(2021, 1, 5));
        GoodsItem itm12 = goods1.newItem(LocalDate.of(2021, 1, 6));
        GoodsItem itm13 = goods1.newItem(LocalDate.now());
        GoodsItem itm21 = goods2.newItem(LocalDate.of(2021, 2, 5));

        if (goods1.size() == 3)
            test_passed();
        test_ran();

        if (goods2.size() == 1)
            test_passed();
        test_ran();

        shelf.put(itm11);
        shelf.put(itm12);
        shelf.put(itm13);
        if (shelf.size() == 3)
            test_passed();
        test_ran();

        try {
            shelf.put(itm21);
        }
        catch (ArithmeticException e) {
            if (shelf.size() == 3)
                test_passed();
        }
        test_ran();

    }

    public void run_tests() {
        initialize_test_001();
        initialize_test_002();
        System.out.printf("%d/%d\n", this.correct, this.all);
    }
}