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

    private final Target target;
    /**
     * Instance třídy {@code GameManager}, která má na starost celou hru
     */
    private final GameManager gameManager;

    /**
     * Vytvoří instanci třídy {@code Shooter}
     *
     * @param position pozice entity
     * @param column souřadnice soupce, kde se nachází střelec
     * @param row    souřadnice řádku, kde se nachází střelec
     * @param gameManager   spravce hry
     * @param target cíl, na který má střelec střílet
     */
    public Shooter(Position position, int column, int row, GameManager gameManager, Target target) {
        super(position, column, row, gameManager.getGamePane(), "/Tank_Shooter.png");
        this.target = target;
        this.gameManager = gameManager;
    }

    /**
     * Střelec vystřelí
     *
     * @param azimuth  uhel střely
     * @param elevation zdvih zbraně
     * @param speed počáteční rychlost střely
     */
    public void shoot(double azimuth, double elevation, double speed){
        this.gameManager.getCalculator().shoot(azimuth, elevation, speed);
    }
}
