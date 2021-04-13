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

        Accordion accordion = new Accordion();
        TitledPane pane1 = new TitledPane("Boats" , new Label("1"));
        TitledPane pane2 = new TitledPane("Cars"  , new Label("2"));
        TitledPane pane3 = new TitledPane("Planes", new Label("3"));

        accordion.getPanes().add(pane1);
        accordion.getPanes().add(pane2);
        accordion.getPanes().add(pane3);

        BorderPane.setAlignment(label, Pos.CENTER);
        BorderPane.setAlignment(closeButton, Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        root.setPrefSize(400,350);
        root.setTop(label);
        root.setCenter(accordion);
        root.setBottom(closeButton);

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }
}
