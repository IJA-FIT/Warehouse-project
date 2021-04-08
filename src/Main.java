// import external libraries
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// import custom libraries
import src.shelf_manipulation.store.*;
import src.shelf_manipulation.goods.*;
import src.tests.StoreTests;
import src.map_manipulation.Map;
import src.cart.CartControl;

import java.time.LocalDate;

public class Main extends Application {

    Button button;

    public static void main(String[] args) {
    
        // start GUI
        launch(args);

        


        // testing implemented classes
        StoreTests test = new StoreTests();
        test.run_tests();

        CartControl cart = new CartControl("8.10");
        CartControl cart2 = new CartControl("8.9");

        cart.map.printLayout();
        cart.gotoPosition(7, 10);

        System.out.printf("\n");

        cart.map.printLayout();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        primaryStage.setTitle("Title of the window");

        button = new Button();
        button.setText("click me");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
