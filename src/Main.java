// import external libraries
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;

// import custom libraries
import src.shelf_manipulation.store.*;
import src.shelf_manipulation.goods.*;
import src.tests.StoreTests;
import src.cart.CartControl;
import src.GUI.menu.*;
import src.GUI.boxes.*;

import java.time.LocalDate;

public class Main extends Application { 

    Stage window;
    Button button;

    public static void main(String[] args) {
        // start GUI
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Warehouse");

        // ask to confirm when closing program
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(); 
        });

        // init menu bar
        MenuBar menuBar = new MenuBar();

        Menu menu1 = new Menu("Vozík");
        MenuItem menuItem11 = new MenuItem("Zobrazit status vozíků");
        MenuItem menuItem12 = new MenuItem("Zadat novou objednávku");

        menu1.getItems().add(menuItem11);
        menu1.getItems().add(menuItem12);

        Menu menu2 = new Menu("Regál");
        MenuItem menuItem21 = new MenuItem("Zobrazit informace o regálech");

        menu2.getItems().add(menuItem21);
       
        Menu menu3 = new Menu("Zboží");
        MenuItem menuItem31 = new MenuItem("Zobrazit seznam zboží");
        MenuItem menuItem32 = new MenuItem("Najít zboží");
        menuItem32.setOnAction(e -> {
            searchGoods.display("Vyhledej zboží", "Vyber zboží ze seznamu");        
        });

        menu3.getItems().add(menuItem31);
        menu3.getItems().add(menuItem32);
         
        Menu menu4 = new Menu("Nápověda");

        MenuItem menuItem41 = new MenuItem("Zobrazit nápovědu");
        menuItem41.setOnAction( e -> {
            showHelp.display("Nápověda", "Nápověda pro projekt IJA");
        });

        menu4.getItems().add(menuItem41);

        menuBar.getMenus().add(menu1);
        menuBar.getMenus().add(menu2);
        menuBar.getMenus().add(menu3);
        menuBar.getMenus().add(menu4);

        HBox statusbar = new HBox();
       
        Text demoDescription = new Text("Věci které jdou vyzkoušet v demonstrační třídě");
        demoDescription.setWrappingWidth(600);

        Text descText1 = new Text(
        "Moje demonstrační třída implementuje jednoduché grafické rozhraní, které zatím obsahuje pouze základní menu.");
        descText1.setWrappingWidth(600);

        Text descText2 = new Text("V menu jsou funkční odkazy zboží->najít zboží a nápověda->zobrazit nápovědu.\nPro své potřeby jsem implementoval třídu PopupBox, která vytvoří jedoduché vyskakovací okno se zprávou. Dále jsem vytvořil třídu ConfirmBox, která vytvoří okno, ve kterém může uživatel potvrdit, zda chce opravdu provést nějakou akci, například zavřít okno aplikace.");
        descText2.setWrappingWidth(600);

        Text descText3 = new Text("Grafické rozhraní prozatím není propojeno se zbytkem projektu.");
        descText3.setWrappingWidth(600);

        Text descText4 = new Text("Do grafického rozhraní přidám mapu skladu s pohybujícími vozíky.");
        descText4.setWrappingWidth(600);
 
        Accordion democlassAccordion = new Accordion();
        democlassAccordion.prefWidth(200);
        TitledPane pane1 = new TitledPane("Co jde vidět" , descText1);
        TitledPane pane2 = new TitledPane("Na co jde kliknout"  , descText2);
        TitledPane pane3 = new TitledPane("Propojení", descText3);
        TitledPane pane4 = new TitledPane("Co se přidá", descText4);

        democlassAccordion.getPanes().add(pane1);
        democlassAccordion.getPanes().add(pane2);
        democlassAccordion.getPanes().add(pane3);
        democlassAccordion.getPanes().add(pane4);

 
        BorderPane mainContent = new BorderPane();
        mainContent.setPrefSize(800,600);

        mainContent.setPadding(new Insets(10, 10, 10, 10));
        mainContent.setTop(demoDescription);
        mainContent.setCenter(democlassAccordion);

        // create border pane and init it
        BorderPane root = new BorderPane();
        root.setPrefSize(800,600);
        root.setTop(menuBar);
        root.setCenter(mainContent);
        root.setBottom(statusbar);
        
        Scene scene = new Scene(root);

        window.setScene(scene);
        window.show();
    }

    /**
     *  closeProgram je vyvolá okno, ve kterém se dotáže uživatele zda chce opravdu zavřít program.
     */
    private void closeProgram() {
        boolean result = ConfirmBox.display("exit", "Are you sure you want to exit?");
        if(result == true) {
            window.close();
        }
    }
}
