// import external libraries
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
import src.map_manipulation.Map;
import src.cart.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        StoreTests test = new StoreTests();
        test.run_tests();

        CartControl cart = new CartControl("8.10");
        CartControl cart2 = new CartControl("8.9");

        //cart.gotoPosition(7, 10);

        cart.Dijkstra();



    }
}
