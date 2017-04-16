import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Game extends Application {

    /**
     *
     */
    private static GameManager gameManager;


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

    public static GameManager getGameManager() {
        return gameManager;
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGUI.fxml"));
            root = loader.load();

            primaryStage.setTitle("Schutz Petr - A16B0130P");
            primaryStage.getIcons().add(new Image("Tank.png"));

            Scene scene = new Scene(root, 720, 405);
            primaryStage.setScene(scene);


            gameManager = new GameManager("rovny1metr.ter", loader.getController());

            //gameManager = new GameManager("sikmy45stupnu.ter", loader.getController());

            //gameManager = new GameManager("terrain512x512.ter", loader.getController());


            scene.widthProperty().addListener((obs, oldVal, newVal) -> {
                if (Objects.equals(oldVal, newVal)) return;

                gameManager.paint(newVal.doubleValue(), scene.getHeight());

            });

            scene.heightProperty().addListener((obs, oldVal, newVal) -> {
                if (Objects.equals(oldVal, newVal)) return;

                gameManager.paint(scene.getWidth(), newVal.doubleValue());

            });

            gameManager.paint(scene.getWidth(), scene.getHeight());
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

