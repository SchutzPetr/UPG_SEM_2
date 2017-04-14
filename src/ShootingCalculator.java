import entity.HitSpot;
import entity.Position;
import entity.Shooter;
import entity.Target;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

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

        this.hitSpot = new HitSpot(new Position(0,0, 0), gameManager.getGamePane());
    }

    /*public void shoot(double azimuth, double elevation, double speed){
        double windSpeedX = 0;
        double windSpeedY = -30;
        double windSpeedZ = 0;

        double posX = 0;
        double posY = 0;
        double posZ = 0;

        double prevVX = 100;
        double prevVY = 0;
        double prevVZ = 100;

        List<Vector> trajectoryVectors = new ArrayList<>();
        List<Vector> vectors = new ArrayList<>();

        trajectoryVectors.add(new Vector(posX+prevVX*0.01, posY+prevVY*0.01, posZ+prevVZ*0.01));

        Vector trajectoryVector = trajectoryVectors.get(0);

        for (int i = 1; i < 100; i++) {
            prevVX = prevVX-(prevVX-windSpeedX)*0.01*0.05;
            prevVY = prevVY-(prevVY-windSpeedY)*0.01*0.05;
            prevVZ = prevVZ + (-10*0.01)-(prevVZ-windSpeedZ)*0.01*0.05;

            trajectoryVector = new Vector(trajectoryVector.getX()+prevVX*0.01, trajectoryVector.getY()+prevVY*0.01, trajectoryVector.getZ()+prevVZ*0.01);
            trajectoryVectors.add(trajectoryVector);

            double x = Math.cos(Math.toRadians(azimuth)) * Math.abs(trajectoryVector.getX()-posX);
            double y = Math.sin(Math.toRadians(azimuth)) * Math.abs(trajectoryVector.getX()-posX);

            Line line = new Line();

            line.setStartX(lastPosX);
            line.setStartY(lastPosY);

            lastPosX = shooter.getPosition().getX()+(x*this.gameManager.getScale());
            lastPosY = shooter.getPosition().getY()+(y*this.gameManager.getScale()*trajectoryVector.getY());

            line.setEndX(lastPosX);
            line.setEndY(lastPosY);

            line.setStroke(Color.RED);
            line.setStrokeWidth(1);

            lines.add(line);

        }




    }*/

    private final List<Line> lines = new ArrayList<>();

    private final List<PositionVector> positionVectors = new ArrayList<>();

    public void newCalculator2(double azimuth, double elevation, double speed){

        this.gameManager.getGamePane().getChildren().removeAll(lines);

        lines.clear();

        double windSpeedX = 0;
        double windSpeedY = -30;
        double windSpeedZ = 0;

        double posX = 0;
        double posY = 0;
        double posZ = shooter.getPosition().getZ();

        double prevVX = 100;
        double prevVY = 0;
        double prevVZ = 1000;

        double lastPosX = shooter.getPosition().getX();
        double lastPosY = shooter.getPosition().getY();

        PositionVector trajectoryVector = new PositionVector(posX+prevVX*0.01, posY+prevVY*0.01, posZ+prevVZ*0.01);

        positionVectors.add(new PositionVector(lastPosX, lastPosY, lastPosX));

        while(true){
            prevVX = prevVX-(prevVX-windSpeedX)*0.01*0.05;
            prevVY = prevVY-(prevVY-windSpeedY)*0.01*0.05;
            prevVZ = prevVZ + (-10*0.01)-(prevVZ-windSpeedZ)*0.01*0.05;

            trajectoryVector = new PositionVector(trajectoryVector.getX()+prevVX*0.01, trajectoryVector.getY()+prevVY*0.01, trajectoryVector.getZ()+prevVZ*0.01);

            double x = Math.cos(Math.toRadians(azimuth)) * Math.abs(trajectoryVector.getX());
            double y = Math.sin(Math.toRadians(azimuth)) * Math.abs(trajectoryVector.getX());

            Line line = new Line();

            line.setStartX(lastPosX);
            line.setStartY(lastPosY);

            lastPosX = shooter.getPosition().getX()+(x*this.gameManager.getScale());
            lastPosY = shooter.getPosition().getY()+(y*this.gameManager.getScale()*trajectoryVector.getY());

            line.setEndX(lastPosX);
            line.setEndY(lastPosY);

            if(lastPosX < 0 || lastPosY < 0)break;

            if(trajectoryVector.getZ() <= gameManager.getTerrainData().getAltitudeInM(lastPosX/this.gameManager.getScale(), lastPosY/this.gameManager.getScale())){
                System.out.println("naraz!");
                break;
            }

            System.out.println(trajectoryVector.getZ() + "-" + gameManager.getTerrainData().getAltitudeInM(lastPosX/this.gameManager.getScale(), lastPosY/this.gameManager.getScale()));

            line.setStroke(Color.RED);
            line.setStrokeWidth(1);

            lines.add(line);

        }

        this.gameManager.getGamePane().getChildren().addAll(lines);
    }

    /*
    public void newCalculator2(double azimuth, double elevation, double speed){
        double windSpeedX = 0;
        double windSpeedY = 0;
        double windSpeedZ = 0;

        double posX = 0;
        double posY = 0;
        double posZ = 0;

        double prevVX = 100;
        double prevVY = 0;
        double prevVZ = 100;

        List<Vector> trajectoryVectors = new ArrayList<>();
        List<Vector> vectors = new ArrayList<>();

        trajectoryVectors.add(new Vector(posX+prevVX*0.01, posY+prevVY*0.01, posZ+prevVZ*0.01));

        Vector trajectoryVector = trajectoryVectors.get(0);

        for (int i = 1; i < 200; i++) {
            prevVX = prevVX-(prevVX-windSpeedX)*0.01*0.05;
            prevVY = prevVY-(prevVY-windSpeedY)*0.01*0.05;
            prevVZ = prevVZ + (-10*0.01)-(prevVZ-windSpeedZ)*0.01*0.05;

            trajectoryVector = new Vector(trajectoryVector.getX()+prevVX*0.01, trajectoryVector.getY()+prevVY*0.01, trajectoryVector.getZ()+prevVZ*0.01);
            trajectoryVectors.add(trajectoryVector);
        }

        System.out.println(vectors);
    }
     */

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

class PositionVector {
    final double x, y, z;


    PositionVector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

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
