package entity;

import javafx.scene.layout.Pane;

/**
 * Created by Petr Schutz on 20.03.2017
 * <p>
 * Třída {@code Target}, která rozšiřuje třídu {@code Entity}
 * a představuje cíl
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class Target extends Entity {

    /**
     * Vytvoří instanci třídy {@code Target}, která představuje cíl
     *
     * @param position pozice entity
     * @param column souřadnice soupce, kde se nachází střelec
     * @param row    souřadnice řádku, kde se nachází střelec
     * @param pane   herní plocha na kterou se střelec vykrelsí
     */
    public Target(Position position, int column, int row, Pane pane) {
        super(position, column, row, pane, "/img/Tank_Target.png");
    }
}
