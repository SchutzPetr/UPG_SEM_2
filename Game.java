/**
 * Created by Petr Schutz on 18.03.2017
 * <p>
 * Hlavní třída, která se stará o spuštění celé aplikace
 *
 * @author Petr Schutz
 * @version 1.0
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Game extends Application {

    /**
     * Logická hodnota znázornující stav po kliknutí na maximalizaci okna.
     * Není doporučené tuto hodnotu využívat jinak, než je využita!
     */
    public static boolean maximalize = false;

    /**
     * instance třídy {@code GameController}, která se stará o správu GUI
     */
    private static GameController gameController;


    /**
     * Hlavní spouštěcí metoda
     *
     * @param args vstupní parametry
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @return instanci třídy {@code GameController}, která se stará o správu GUI
     */
    public static GameController getGameController() {
        return gameController;
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"));
            root = loader.load();

            primaryStage.setTitle("Schutz Petr - A16B0130P");
            //primaryStage.getIcons().add(new Image(imgLocation));

            Scene scene = new Scene(root, 720, 405);
            primaryStage.setScene(scene);

            primaryStage.show();

            gameController = loader.getController();

            primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
                maximalize = true;
            });

            scene.widthProperty().addListener((obs, oldVal, newVal) -> {
                if (Objects.equals(oldVal, newVal)) return;

                if (maximalize) gameController.onMaximizeOrNormalize((double) newVal, -1);
                else gameController.paintPane();

            });

            scene.heightProperty().addListener((obs, oldVal, newVal) -> {
                if (Objects.equals(oldVal, newVal)) return;

                if (maximalize) gameController.onMaximizeOrNormalize(-1, (double) newVal);
                else gameController.paintPane();

            });


            gameController.paintPane();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
