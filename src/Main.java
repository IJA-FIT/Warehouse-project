/**
 * Demonstrační třída sloužící k ukázce současné funkcionality programu.
 *
 * @author Vojtěch Fiala <xfiala61>
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import src.shelf_manipulation.store.*;
import src.shelf_manipulation.goods.*;
import src.tests.StoreTests;
import src.cart.*;
import src.utils.*;

import java.time.LocalDate;

/**
 * <h2>Demonstrační třída sloužící k ukázce současné funkcionality programu.</h2>
 */
public class Main {


    public static void main(String[] args) {
        CoordsConverter cnv = new CoordsConverter();
        StoreTests test = new StoreTests();
        test.run_tests();

        Goods goods1 = new StoreGoods("Stul");
        GoodsShelf shelf = new StoreShelf(goods1);
        GoodsItem itm11 = new StoreGoodsItem(goods1, LocalDate.of(2021, 8, 5));
        GoodsItem itm12 = new StoreGoodsItem(goods1, LocalDate.of(2021, 6, 6));
        goods1.addItem(itm11);
        goods1.addItem(itm12);

        shelf.put(itm11);
        shelf.put(itm12);

        CartControl cart = new CartControl("8.10");
        CartControl cart2 = new CartControl("8.9");

        // seznam regalu vcetne jejich pozice
        ShelfManipulator regals = new ShelfManipulator("0.0", shelf);

        HashIndexFinder fnd = new HashIndexFinder();
        // najdi v seznamu regalu typ zbozi a urci, na jake misto  ma vozik dojet
        String key = fnd.getIndex(regals.shelf_map, "Stul");
        int[] destination = cnv.coordsInt(key);

        //cart.gotoPosition(7, 10);

        System.out.printf("%s\n", regals.shelf_map.get("0.0").getShelfType());

        cart.findPath(destination[0], destination[1]);

    }
}
