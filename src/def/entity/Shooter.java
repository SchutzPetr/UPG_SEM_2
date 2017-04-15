package def.entity;

import javafx.scene.layout.Pane;

/**
 * Created by Petr Schutz on 20.03.2017
 * <p>
 * Instance třídy {@code Shooter}, která rozšiřuje třídu {@code Entity}
 * a reprezentuje střelce
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class Shooter extends Entity {

    /**
     * Vytvoří instanci třídy {@code Shooter}
     *
     * @param position pozice entity
     * @param column souřadnice soupce, kde se nachází střelec
     * @param row    souřadnice řádku, kde se nachází střelec
     * @param pane   herní plocha na kterou se střelec vykrelsí
     * @param target cíl, na který má střelec střílet
     */
    public Shooter(Position position, int column, int row, Pane pane, Target target) {
        super(position, column, row, pane, "/img/Tank_Shooter.png");
    }
}
