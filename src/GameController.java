import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController {

    /**
     * ResourceBundle that was given to the FXMLLoader
     */
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    /**
     * URL location of the FXML file that was given to the FXMLLoader
     */
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    /**
     * Komponenta GUI fx:id="borderPane"
     */
    @FXML // fx:id="borderPane"
    private BorderPane borderPane; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="gameBackPane"
     */
    @FXML // fx:id="gameBackPane"
    private AnchorPane gameBackPane; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="angleTextField"
     */
    @FXML // fx:id="angleTextField"
    private TextField angleTextField; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="radiusTextField"
     */
    @FXML // fx:id="radiusTextField"
    private TextField radiusTextField; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="fireButton"
     */
    @FXML // fx:id="fireButton"
    private Button fireButton; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="angleLabel"
     */
    @FXML // fx:id="angleLabel"
    private Label angleLabel; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="radiusLabel"
     */
    @FXML // fx:id="radiusLabel"
    private Label radiusLabel; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="hitLabel"
     */
    @FXML // fx:id="hitLabel"
    private Label hitLabel; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="outOfMap"
     */
    @FXML
    private Label outOfMap; // Value injected by FXMLLoader

    private ImageView game;

    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'Game.fxml'.";
        assert gameBackPane != null : "fx:id=\"gameBackPane\" was not injected: check your FXML file 'Game.fxml'.";
        assert angleTextField != null : "fx:id=\"angleTextField\" was not injected: check your FXML file 'Game.fxml'.";
        assert radiusTextField != null : "fx:id=\"radiusTextField\" was not injected: check your FXML file 'Game.fxml'.";
        assert fireButton != null : "fx:id=\"fireButton\" was not injected: check your FXML file 'Game.fxml'.";
        assert angleLabel != null : "fx:id=\"angleLabel\" was not injected: check your FXML file 'Game.fxml'.";
        assert radiusLabel != null : "fx:id=\"radiusLabel\" was not injected: check your FXML file 'Game.fxml'.";
        assert hitLabel != null : "fx:id=\"hitLabel\" was not injected: check your FXML file 'Game.fxml'.";
        assert outOfMap != null : "fx:id=\"outOfMap\" was not injected: check your FXML file 'Game.fxml'.";

        game = new ImageView();


        game.setLayoutX(0);
        game.setLayoutY(0);

        gameBackPane.getChildren().add(game);

    }

    void setGame(ImageView game) {
        gameBackPane.getChildren().remove(this.game);
        this.game = game;

        game.setLayoutX(0);
        game.setLayoutY(0);

        gameBackPane.getChildren().add(game);

        gameBackPane.widthProperty().addListener((observable, oldValue, newValue) -> game.setFitWidth(newValue.doubleValue()));
        gameBackPane.heightProperty().addListener((observable, oldValue, newValue) -> game.setFitHeight(newValue.doubleValue()));
    }


    /**
     * Metoda, která vykresluje herní mapu
     *
     * @param width  šířka herní mapy
     * @param height výška herní mapy
     */
    void paintMap(double width, double height) {

        gameBackPane.setPrefSize(width, height);
    }

    public AnchorPane getGameBackPane() {
        return gameBackPane;
    }
}