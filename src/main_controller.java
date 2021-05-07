/**
* main_controller
* main_controller spravuje akce co se stanou v gui
* @author Vojtěch Bubela <xbubel08>
*/

import javafx.fxml.FXML;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class main_controller {

    @FXML
    private AnchorPane main_content;


    @FXML
    private GridPane main_grid;
    

    @FXML
    private void on_zoom(ScrollEvent event) {
        event.consume();
        double zoom_level = event.getDeltaY();

        if(zoom_level == 0) {
            zoom_level = 1;
        } else if(zoom_level < 0){
            zoom_level = 0.9;
        } else {
            zoom_level = 1.1;
        }

        main_content.setScaleX(zoom_level *main_content.getScaleX());
        main_content.setScaleY(zoom_level *main_content.getScaleY());
    }

    public void init_gui(int[][] map) {
    
        for(int i = 0; i < map.length; i++) {
            for (int k = 0; k < map[0].length; k++) {
                
                if(map[i][k] == 5) {
                    System.out.println(map[i][k]);
                    Rectangle rec = new Rectangle(30,50);
                    main_grid.add(rec ,k,i);
                    GridPane.setHalignment(rec, HPos.CENTER);
                    GridPane.setValignment(rec, VPos.CENTER);
                } else if(map[i][k] == 9) {
                    // obrazek je prevzaty z:
                    // https://www.pinterest.com/pin/342203271663134343/
                    Image rec = new Image("/vydejni_misto.png");
                    ImageView new_image = new ImageView(rec);
                    new_image.setFitHeight(50);
                    new_image.setFitWidth(50);
                    main_grid.add(new_image ,k,i);
                } else if (map[i][k] == 0) {
                    // Rectangle rec = new Rectangle(30,50);
                    Image rec = new Image("/parking_slot.png");
                    ImageView new_image = new ImageView(rec);
                    new_image.setFitHeight(50);
                    new_image.setFitWidth(50);
                    main_grid.add(new_image ,k,i);
                } else if (map[i][k] == 7) {
                    // Rectangle rec = new Rectangle(30,50);
                    Image rec = new Image("/forklift.png");
                    ImageView new_image = new ImageView(rec);
                    new_image.setFitHeight(70);
                    new_image.setFitWidth(70);
                    main_grid.add(new_image ,k,i);
                }
            }
        }
    }
}   