package def.entity;

import def.Vector3D;

/**
 * Created by Petr Schutz on 20.03.2017
 * <p>
 * Třída {@code Position} která je typu kontejner.
 * Třída uschovává souřadnice x a y
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class Position extends Vector3D{


    /**
     * Vytvoří instanci třídy {@code Position} která je typu kontejner
     *
     * @param x souřadnice x
     * @param y souřadnice y
     * @param z souradnice z
     */
    public Position(double x, double y, double z) {
        super(x, y, z);
    }
}
