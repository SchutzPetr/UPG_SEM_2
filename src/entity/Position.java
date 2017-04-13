package entity;

/**
 * Created by Petr Schutz on 20.03.2017
 * <p>
 * Třída {@code Position} která je typu kontejner.
 * Třída uschovává souřadnice x a y
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class Position {
    /**
     * Souřadnice x a y
     */
    private final double x, y;


    /**
     * Vytvoří instanci třídy {@code Position} která je typu kontejner
     *
     * @param x souřadnice x
     * @param y souřadnice y
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Metoda navrací souřadnice X
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Metoda navrací souřadnice y
     *
     * @return y
     */
    public double getY() {
        return y;
    }
}
