package def;

import def.entity.HitSpot;
import def.entity.Position;
import def.entity.Shooter;
import def.entity.Target;
import javafx.scene.paint.Color;
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

    private final List<Line> lines = new ArrayList<>();

    private final List<Position> trajectoryVectors = new ArrayList<>();

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

        this.hitSpot = new HitSpot(new Position(0,0, 0), gameManager);
    }

    public void shoot(double azimuth, double elevation, double speed){
        this.gameManager.getGamePane().getChildren().removeAll(lines);

        lines.clear();

        double windSpeedX = 0;
        double windSpeedY = -500;
        double windSpeedZ = 0;

        double posX = 0;
        double posY = 0;
        double posZ = shooter.getPosition().getZ();

        double prevVX = speed;
        double prevVY = 0;
        double prevVZ = speed;

        double lastPosX;
        double lastPosY;

        Position trajectoryVector = new Position(posX+prevVX*0.01, posY+prevVY*0.01, posZ+prevVZ*0.01);

        trajectoryVectors.add(trajectoryVector);

        while (true) {
            prevVX = prevVX - (prevVX - windSpeedX) * 0.01 * 0.05;
            prevVY = prevVY - (prevVY - windSpeedY) * 0.01 * 0.05;
            prevVZ = prevVZ + (-10 * 0.01) - (prevVZ - windSpeedZ) * 0.01 * 0.05;

            trajectoryVector = new Position(trajectoryVector.getX() + prevVX * 0.01, trajectoryVector.getY() + prevVY * 0.01, trajectoryVector.getZ() + prevVZ * 0.01);

            double x = Math.cos(Math.toRadians(azimuth)) * Math.abs(trajectoryVector.getX());
            double y = Math.sin(Math.toRadians(azimuth)) * Math.abs(trajectoryVector.getX());

            lastPosX = shooter.getPosition().getX() + (x * this.gameManager.getScale());
            lastPosY = shooter.getPosition().getY() + (this.gameManager.getScale()*(y + trajectoryVector.getY()));


            if (lastPosX < 0 || lastPosY < 0) break;

            double altitude = gameManager.getTerrainData().getAltitudeInM(lastPosX / this.gameManager.getScale(), lastPosY / this.gameManager.getScale());


            if (altitude == -1.0) {
                System.out.println("out of map");
                break;
            } else if (trajectoryVector.getZ() <= altitude) {
                System.out.println("naraz!");
                break;
            }

            trajectoryVectors.add(trajectoryVector);
        }


        trajectoryVectors.forEach(System.out::println);
    }

    public void recalculate(){
        if (trajectoryVectors.isEmpty())return;

        gameManager.getGamePane().getChildren().removeAll(lines);
        lines.clear();

        Position prev = trajectoryVectors.get(0);
        Position current = null;

        double x = Math.cos(Math.toRadians(lastAzimuth)) * Math.abs(prev.getX());
        double y = Math.sin(Math.toRadians(lastAzimuth)) * Math.abs(prev.getX());

        double lastPosX = shooter.getPosition().getX() + (x * this.gameManager.getScale());
        double lastPosY = shooter.getPosition().getY() + (this.gameManager.getScale()*(y + prev.getY()));

        for (int i = 1; i < trajectoryVectors.size(); i++) {
            current = trajectoryVectors.get(i);

            Line line = new Line();

            line.setStartX(lastPosX);
            line.setStartY(lastPosY);

            x = Math.cos(Math.toRadians(lastAzimuth)) * Math.abs(current.getX());
            y = Math.sin(Math.toRadians(lastAzimuth)) * Math.abs(current.getX());

            lastPosX = shooter.getPosition().getX() + (x * this.gameManager.getScale());
            lastPosY = shooter.getPosition().getY() + (this.gameManager.getScale()*(y + current.getY()));


            line.setEndX(lastPosX);
            line.setEndY(lastPosY);

            line.setStroke(Color.RED);
            line.setStrokeWidth(1);

            lines.add(line);
        }

        if(current != null){
            hitSpot.repaint(new Position(lastPosX, lastPosY, 0.0), this.gameManager.getScale());
            hitSpot.show();
        }

        gameManager.getGamePane().getChildren().addAll(lines);
    }

    public void newCalculator2(double azimuth, double elevation, double speed) {
        if (trajectoryVectors.isEmpty()) shoot(azimuth, elevation, speed);

        gameManager.getGamePane().getChildren().removeAll(lines);
        lines.clear();

        Position prev = trajectoryVectors.get(0);
        Position current = null;

        double x = Math.cos(Math.toRadians(azimuth)) * Math.abs(prev.getX());
        double y = Math.sin(Math.toRadians(azimuth)) * Math.abs(prev.getX());

        double lastPosX = shooter.getPosition().getX() + (x * this.gameManager.getScale());
        double lastPosY = shooter.getPosition().getY() + (this.gameManager.getScale()*(y + prev.getY()));

        for (int i = 1; i < trajectoryVectors.size(); i++) {
            current = trajectoryVectors.get(i);

            Line line = new Line();

            line.setStartX(lastPosX);
            line.setStartY(lastPosY);

            x = Math.cos(Math.toRadians(azimuth)) * Math.abs(current.getX());
            y = Math.sin(Math.toRadians(azimuth)) * Math.abs(current.getX());

            lastPosX = shooter.getPosition().getX() + (x * this.gameManager.getScale());
            lastPosY = shooter.getPosition().getY() + (this.gameManager.getScale()*(y + current.getY()));


            line.setEndX(lastPosX);
            line.setEndY(lastPosY);

            line.setStroke(Color.RED);
            line.setStrokeWidth(1);

            lines.add(line);
        }

        if(current != null){
            hitSpot.repaint(new Position(lastPosX, lastPosY, 0.0), this.gameManager.getScale());
            hitSpot.show();
        }

        gameManager.getGamePane().getChildren().addAll(lines);
    }

    /**
     * @return navrací instanci třídy {@code HitSpot}
     */
    public HitSpot getHitSpot() {
        return hitSpot;
    }
}