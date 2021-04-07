import src.shelf_manipulation.store.*;
import src.shelf_manipulation.goods.*;
import src.tests.StoreTests;
import src.map_manipulation.Map;
import src.cart.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        StoreTests test = new StoreTests();
        test.run_tests();

        CartControl cart = new CartControl("8.10");
        CartControl cart2 = new CartControl("8.9");

        cart.gotoPosition(7, 10);

        cart.Dijkstra();



    }
}
