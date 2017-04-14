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
     * Souřadnice x a y a z
     */
    private final double x, y, z;


    /**
     * Vytvoří instanci třídy {@code Position} která je typu kontejner
     *
     * @param x souřadnice x
     * @param y souřadnice y
     */
    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    /**
     * Metoda navrací souřadnice z
     *
     * @return z
     */
    public double getZ() {
        return z;
    }
}
