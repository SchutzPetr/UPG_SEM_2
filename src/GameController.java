import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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
     * Komponenta GUI fx:id="speedLabel"
     */
    @FXML // fx:id="speedLabel"
    private Label speedLabel; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="elevationLabel"
     */
    @FXML // fx:id="radiusLabel"
    private Label elevationLabel; // Value injected by FXMLLoader

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


    private Rectangle backRectangle; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="mainAPane"
     */
    @FXML
    private AnchorPane mainAPane; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="elevationTextField"
     */
    @FXML
    private TextField elevationTextField; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="speedTextField"
     */
    @FXML
    private TextField speedTextField; // Value injected by FXMLLoader

    /**
     * Komponenta GUI fx:id="leftPane"
     */
    @FXML
    private AnchorPane leftPane; // Value injected by FXMLLoader



    private ImageView game;
    private WindCompass compass;

    private double leftPaneWidth;
    private double leftPaneHeight;
    private GameManager gameManager;

    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'Game.fxml'.";
        assert gameBackPane != null : "fx:id=\"gameBackPane\" was not injected: check your FXML file 'Game.fxml'.";
        assert angleTextField != null : "fx:id=\"angleTextField\" was not injected: check your FXML file 'Game.fxml'.";
        assert fireButton != null : "fx:id=\"fireButton\" was not injected: check your FXML file 'Game.fxml'.";
        assert angleLabel != null : "fx:id=\"angleLabel\" was not injected: check your FXML file 'Game.fxml'.";
        assert hitLabel != null : "fx:id=\"hitLabel\" was not injected: check your FXML file 'Game.fxml'.";
        assert outOfMap != null : "fx:id=\"outOfMap\" was not injected: check your FXML file 'Game.fxml'.";

        game = new ImageView();


        compass = new WindCompass(50, 50, 0, 40, 2);

        leftPane.getChildren().add(compass);

        game.setLayoutX(0);
        game.setLayoutY(0);

        gameBackPane.getChildren().add(game);

        backRectangle = new Rectangle();
        backRectangle.setX(10);
        backRectangle.setY(10);

        speedTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!Utils.isDouble(newValue)) {
                speedTextField.setStyle("-fx-background-color: rgba(199, 0, 57, 0.20);");
            } else {
                speedTextField.setStyle("");
                speedLabel.setVisible(false);
            }
        });

        elevationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!Utils.isDouble(newValue)) {
                elevationTextField.setStyle("-fx-background-color: rgba(199, 0, 57, 0.20);");
            } else if (!Utils.isValidElevationValue(Double.parseDouble(newValue))) {
                elevationTextField.setStyle("-fx-background-color: rgba(199, 0, 57, 0.20);");
            } else {

                elevationTextField.setStyle("");
                elevationLabel.setVisible(false);
            }
        });

        angleTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!Utils.isDouble(newValue)) {
                angleTextField.setStyle("-fx-background-color: rgba(199, 0, 57, 0.20);");
            } else if (!Utils.isValidAngleValue(Double.parseDouble(newValue))) {
                angleTextField.setStyle("-fx-background-color: rgba(199, 0, 57, 0.20);");
            } else {

                angleTextField.setStyle("");
                angleLabel.setVisible(false);
            }
        });

        fireButton.setOnAction(event -> {

            double speed = -1;
            double angle = -9999;
            double elevation = -9999;

            if (Utils.isDouble(speedTextField.getText())) {
                speed = Double.parseDouble(speedTextField.getText());
                if (speed < 0) {
                    speedTextField.setText("Zadejte nezáporné cislo!");
                    speedTextField.setVisible(true);
                    speed = -1;
                }
            } else {
                speedLabel.setText("Zadejte platne realne cislo!");
                speedTextField.setVisible(true);
            }

            if (Utils.isDouble(elevationTextField.getText())) {
                elevation = Double.parseDouble(elevationTextField.getText());
            } else {
                elevationLabel.setText("Zadejte platne realne cislo!");
                elevationLabel.setVisible(true);
            }

            if (Utils.isDouble(angleTextField.getText())) {
                angle = Double.parseDouble(angleTextField.getText());
            } else {
                angleLabel.setText("Zadejte platne realne cislo!");
                angleLabel.setVisible(true);
            }

            if (!Utils.isValidAngleValue(angle)) {
                angleLabel.setText("Zadejte realne cislo v rozsahu -90 az 180");
                angleLabel.setVisible(true);
            }else if(!Utils.isValidElevationValue(elevation)) {
                elevationLabel.setText("Zadejte realne cislo v rozsahu -90 az 90");
                elevationLabel.setVisible(true);
            } else if (speed != -1 && angle != -9999 && elevation != -9999) {

                gameManager.getShooter().shoot(angle, elevation, speed);

                angleLabel.setVisible(false);
                speedLabel.setVisible(false);
                elevationLabel.setVisible(false);
            }
        });

        final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), actionEvent -> {
            Wind wind = WindSpeedGenerator.generate();

            compass.setAngle(wind.getAngle());
            compass.setLengthPercent(wind.getSpeed() / 2);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

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

        backRectangle.setWidth(width);
        backRectangle.setHeight(height);

        mainAPane.setClip(backRectangle);
    }


    public AnchorPane getGameBackPane() {
        return gameBackPane;
    }

    public Rectangle getBackRectangle() {
        return backRectangle;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Metoda, která skryje či zobrazí text řikající, zda uživatel střelil mimo mapu, či nikoli
     *
     * @param outOfMap strela je mimo mapu?
     */
    public void setOutOfMap(boolean outOfMap) {
        this.outOfMap.setVisible(outOfMap);
    }

    /**
     * Metoda, která skryje či zobrazí text řikající, zda uživatel zasáhl cíl, či nikoli
     *
     * @param hit zásah?
     */
    public void setHit(boolean hit) {
        hitLabel.setVisible(hit);
    }
}