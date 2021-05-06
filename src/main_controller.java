// hlavicka
import javafx.fxml.FXML;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;


public class main_controller {

    @FXML
    private AnchorPane main_content;
    

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

    

}   