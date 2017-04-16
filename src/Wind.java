/**
 * Created by Petr Schutz on 20.03.2017
 * <p>
 * Třída {@code Wind} která je typu kontejner, reprezenduje 3 složkový vektor
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class Wind extends Vector3D {
    /**
     * uhel smeru vetru
     */
    private final double angle;
    /**
     * rychlost vetru
     */
    private final double speed;


    /**
     * Vytvoří instanci třídy {@code Position} která je typu kontejner
     *
     * @param x     x-ová složka vektoru
     * @param y     y-ová složka vektoru
     * @param z     z-ová složka vektoru
     * @param angle uhel smeru vetru
     * @param speed rychlost vetru
     */
    public Wind(double x, double y, double z, double angle, double speed) {
        super(x, y, z);
        if (angle < 0) this.angle = angle + 360;
        else if (angle > 360) this.angle = angle - 360;
        else this.angle = angle;

        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "x=" + this.getX() +
                ", y=" + this.getY() +
                ", z=" + this.getZ() +
                ", angle=" + angle +
                ", speed=" + speed +
                '}';
    }

    /**
     * @return uhel smeru vetru
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @return rychlost vetru
     */
    public double getSpeed() {
        return speed;
    }
}
