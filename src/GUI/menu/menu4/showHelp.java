/**
 * author: Vojtěch Bůbela
 * login: xbubel08
 */
package src.GUI.menu;

import src.GUI.boxes.*;
import javafx.stage.*;
import javafx.scene.*; 
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.text.*;

/**
 * showHelp zajišťuje zobrazení okna nápovědy 
 */
public class showHelp {
        
    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button("Close the window");
        closeButton.setOnAction(e -> window.close());

        Text helpText1 = new Text(
        "Projekt implementuje jednoduchý sklad s grafickým rozhraním a backendem.");
        helpText1.setWrappingWidth(300);

        Text helpText2 = new Text(
        "Po kliknutí na 'vozík' můžete zobrazit stav vozíků nebo zadat novou objednávku");
        helpText1.setWrappingWidth(300);

        Text helpText3 = new Text(
        "Po kliknutí na 'regál' můžete zobrazit informace o regálech");
        helpText1.setWrappingWidth(300);

        Text helpText4 = new Text(
        "Po kliknutí na 'zboží' můžete zobrazit dostupné zboží, či vyhledat zboží podle jména");
        helpText1.setWrappingWidth(300);

        Accordion accordion = new Accordion();
        TitledPane pane1 = new TitledPane("Projekt" , helpText1);
        TitledPane pane2 = new TitledPane("Vozík"  , helpText2);
        TitledPane pane3 = new TitledPane("Regál", helpText3);
        TitledPane pane4 = new TitledPane("Zboží", helpText4);

        accordion.getPanes().add(pane1);
        accordion.getPanes().add(pane2);
        accordion.getPanes().add(pane3);
        accordion.getPanes().add(pane4);

        BorderPane.setAlignment(label, Pos.CENTER);
        BorderPane.setAlignment(closeButton, Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        root.setPrefSize(350,250);
        root.setTop(label);
        root.setCenter(accordion);
        root.setBottom(closeButton);

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }
}
