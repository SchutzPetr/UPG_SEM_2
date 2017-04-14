
package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by Petr Schutz on 20.03.2017
 * <p>
 * Třída, reprezentující bytost na herním poli
 *
 * @author Petr Schutz
 * @version 1.0
 */
public abstract class Entity extends Point {
    /**
     * souřadnice soupce, kde se nachází prvek
     */
    private final int column;
    /**
     * souřadnice řádku, kde se nachází prvek
     */
    private final int row;

    /**
     * Ikona entity
     */
    private final ImageView imageView;

    /**
     * @param position pozice entity
     * @param column  souřadnice soupce, kde se nachází prvek
     * @param row     souřadnice řádku, kde se nachází prvek
     * @param pane    herní plocha na kterou se prvek vykrelsí
     * @param imgPath cesta k ikonce entity
     */
    public Entity(Position position, int column, int row, Pane pane, String imgPath) {
        super(position);
        this.column = column;
        this.row = row;

        this.imageView = new ImageView(imgPath);

        this.imageView.setFitWidth(27);
        this.imageView.setFitHeight(27);

        pane.getChildren().add(this.imageView);
    }

    /**
     * Metoda, která se stará o překreslení prvku
     *
     * @param widthOfColumn  šířka sloupce
     * @param heightOfColumn výška sloupce
     */
    public void repaint(double widthOfColumn, double heightOfColumn) {
        double x = this.column * widthOfColumn;
        double y = this.row * heightOfColumn;

        this.setPosition(new Position(x, y, this.getPosition().getZ()));

        this.imageView.setLayoutX(x - 13);
        this.imageView.setLayoutY(y - 13);
    }

    /**
     * @return sloupec, ve kterém se prvek nachází
     */
    public int getColumn() {
        return column;
    }

    /**
     * @return řádek, ve kterém se prvek nachází
     */
    public int getRow() {
        return row;
    }
}
