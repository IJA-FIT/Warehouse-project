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

/**
 *  searchGoods zajišťuje zobrazení okna pro vyhledávání zboží
 */
public class searchGoods {

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        ChoiceBox choiceBox = new ChoiceBox();

        Label label = new Label();
        label.setText(message);
        label.setAlignment(Pos.CENTER);

        Button closeButton = new Button("Exit");
        closeButton.setOnAction(e -> window.close());

        Button confirmButton = new Button("Confrim");
        confirmButton.setOnAction(e -> {
            String returnItem = (String) choiceBox.getValue();

            if(returnItem == null) {
                PopupBox.display("Informace o zboží", "Prosím vyberte zboží");
            } else if(returnItem.equals("Komody")) {
                PopupBox.display("Informace o zboží", "Na skladě se nachází 5 komod");
            } else {
                PopupBox.display("Informace o zboží", "Zboží momentálně není dostupné");
            }
        });

        // create choice box

        choiceBox.getItems().add("Komody");
        choiceBox.getItems().add("Křesla");
        choiceBox.getItems().add("Lampy");
        choiceBox.getItems().add("Postele");
        choiceBox.getItems().add("Regály");
        choiceBox.getItems().add("Skříně");
        choiceBox.getItems().add("Stoly");
        choiceBox.getItems().add("Věšáky");
        choiceBox.getItems().add("Zrcadla");
        choiceBox.getItems().add("Židle");

        HBox centerhbox = new HBox(choiceBox);
        centerhbox.setAlignment(Pos.CENTER);

        HBox bottomhbox = new HBox();
        bottomhbox.getChildren().addAll(closeButton, confirmButton);
        bottomhbox.setAlignment(Pos.CENTER);
        bottomhbox.setSpacing(10);
        
        BorderPane.setAlignment(label, Pos.CENTER);
        BorderPane.setAlignment(bottomhbox, Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        root.setPrefSize(200,150);
        root.setTop(label);
        root.setCenter(centerhbox);
        root.setBottom(bottomhbox);
        
        Scene scene = new Scene(root);

        window.setScene(scene);
        window.show();
    }   
}  
