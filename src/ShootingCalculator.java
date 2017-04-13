import entity.HitSpot;
import entity.Position;
import entity.Shooter;
import entity.Target;

/**
 * Created by Petr Schutz on 21.03.2017
 * <p>
 * Tato třída se stará o výpočet výpočet střelby střelce a vykreslení místa dopadu
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class ShootingCalculator {

    /**
     * Instance třídy {@code GameManager}, která má na starost celou hru
     */
    private final GameManager gameManager;
    /**
     * Instance třídy {@code Shooter}
     */
    private final Shooter shooter;
    /**
     * Instance třídy {@code Target}
     */
    private final Target target;
    /**
     * Instance třídy {@code HitSpot}
     */
    private HitSpot hitSpot = null;

    /**
     * Proměná představující naposledy zadané parametry střelby - uhel
     */
    private double lastAzimuth;

    /**
     * Proměná představující naposledy zadané parametry střelby - vzdálenost
     */
    private double lastDistance;

    /**
     * Vytvoří novou instanci třídy {@code ShootingCalculator}, která se stará o výpočet střelby střelce
     *
     * @param gameManager spravce hry
     * @param shooter střelec
     * @param target  cíl
     */
    public ShootingCalculator(GameManager gameManager, Shooter shooter, Target target) {
        this.gameManager = gameManager;
        this.shooter = shooter;
        this.target = target;

        this.hitSpot = new HitSpot(new Position(0,0), gameManager.getGamePane());
    }

    public void newCalculator(double azimuth, double elevation, double speed){
        int windSpeed = 0;

        double posX = shooter.getPosition().getX();
        double posY = shooter.getPosition().getY();
        double posZ = gameManager.getTerrainData().getAltitudeInM(posX, posY);

        double prevVX = 100;

        for (double i = 0; true; i+=0.1) {
            double currentX = prevVX+0*i-(windSpeed-prevVX)*0.05;
        }

    }

    /*/**
     * Metoda vypočítá bod dopadu střely a vykreslí jí
     *
     * @param azimuth  úhel
     * @param distance vzdálenost střely
     */
   /* public void calculate(double azimuth, double distance) {
        lastAzimuth = azimuth;
        lastDistance = distance;

        double x = Math.cos(Math.toRadians(azimuth)) * distance;
        double y = Math.sin(Math.toRadians(azimuth)) * distance;

        double xx = shooter.getPosition().getX() + (Game.getGameController().getGameMap().getScale() * x);
        double yy = shooter.getPosition().getY() + (Game.getGameController().getGameMap().getScale() * y);

        if (Game.getGameController().getGameMap().getPaneWidth() < xx || Game.getGameController().getGameMap().getPaneHeight() < yy) {
            Game.getGameController().setOutOfMap(true);
            Game.getGameController().setHit(false);
            if (hitSpot != null){
                hitSpot.hide();
            }
        } else {
            Game.getGameController().setOutOfMap(false);
            if (hitSpot != null) {
                hitSpot.repaint(new Position(xx, yy));
            } else {
                hitSpot = new HitSpot(new Position(xx, yy));
            }

            Game.getGameController().setHit(this.hitSpot.getDistance(target) <= Game.getGameController().getGameMap().getScale() * 30);
        }

    }

    /**
     * Metoda přepočítá bod dopatu střely a vykreslí ji na správném místě
     */
    /*public void recalculateHitPoint() {
        if (hitSpot == null) return;

        double x = Math.cos(Math.toRadians(lastAzimuth)) * lastDistance;
        double y = Math.sin(Math.toRadians(lastAzimuth)) * lastDistance;

        double xx = shooter.getPosition().getX() + (Game.getGameController().getGameMap().getScale() * x);
        double yy = shooter.getPosition().getY() + (Game.getGameController().getGameMap().getScale() * y);

        hitSpot.repaint(new Position(xx, yy));
    }*/

    /**
     * @return navrací instanci třídy {@code HitSpot}
     */
    public HitSpot getHitSpot() {
        return hitSpot;
    }
}
