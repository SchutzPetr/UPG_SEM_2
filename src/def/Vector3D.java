package def;

/**
 * Created by Petr Schutz on 20.03.2017
 * <p>
 * Třída {@code Vector3D} která je typu kontejner, reprezenduje 3 složkový vektor
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class Vector3D {
    /**
     * Složky vektoru
     */
    private final double x, y, z;


    /**
     * Vytvoří instanci třídy {@code Position} která je typu kontejner
     *
     * @param x x-ová složka vektoru
     * @param y y-ová složka vektoru
     * @param z z-ová složka vektoru
     */
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Metoda navrací x-ovou složku vektoru
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Metoda navrací y-ovou složku vektoru
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * Metoda navrací z-ovou složku vektoru
     *
     * @return z
     */
    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
