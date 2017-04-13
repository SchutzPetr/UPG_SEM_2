package entity;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Petr Schutz on 21.03.2017
 * <p>
 * Tato třída představuje místo dopadu střely a stará se o jeho vykreslení
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class HitSpot extends Point {
    /**
     * Instance třídy {@code Circle} představující bod dopadu střely
     */
    private Circle circle;


    /**
     * Vytvoří instanci třídy {@code HitSpot}
     *
     * @param position pozice střely
     */
    public HitSpot(Position position, Pane pane) {
        super(position);
        circle = new Circle(position.getX(), position.getY(), 30);
        circle.setFill(Color.ORANGE);

        pane.getChildren().add(circle);

        hide();
    }

    /**
     * Tato metoda překreslí bod dopadu
     *
     * @param position nová pozice dopadu
     * @param scale px na metr
     */
    public void repaint(Position position, double scale) {
        show();
        circle.setRadius(scale * 30);

        setPosition(position);

        circle.setCenterX(position.getX());
        circle.setCenterY(position.getY());

    }

    /**
     * Tato metoda skryje zobrazení místa dopadu střely
     */
    public void hide() {
        this.circle.setVisible(false);
    }

    public void show(){
        this.circle.setVisible(true);
    }
}
