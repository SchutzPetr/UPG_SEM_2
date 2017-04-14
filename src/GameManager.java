import entity.Entity;
import entity.Position;
import entity.Shooter;
import entity.Target;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.j3d.geom.terrain.HeightImageCreator;

import javax.vecmath.Color4b;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Petr Schutz on 13.04.2017
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class GameManager {

    /**
     * Instance třídy {@code TerrainData}
     */
    private final TerrainData terrainData;

    /**
     * instance třídy {@code GameController}, která se stará o správu GUI
     */
    private final GameController gameController;

    /**
     * Obrázek mapy
     */
    private final BufferedImage mapImage;

    /**
     * Poměr stran hrací plochy
     */
    private double ratio = 1;

    /**
     * Šířka jednoho sloupce
     */
    private double widthOfColumn;

    /**
     * Výška jednoho řádku
     */
    private double heightOfColumn;

    /**
     * Šířka herního okna
     */
    private double gameWidth;

    /**
     * Výška herního okna
     */
    private double gameHeight;

    /**
     * Meřítko herní mapy - kolik px na metr
     */
    private double scale = 6.3;

    /**
     * Instance třídy {@code Target}
     */
    private final Target target;

    /**
     * Instance třídy {@code Shooter}
     */
    private final Shooter shooter;

    GameManager(String terrainDataFile, GameController gameController) {
        this.terrainData = TerrainData.loadTerrain(terrainDataFile);
        this.gameController = gameController;

        int[][] data = terrainData.getTerrain();


        float[][] terrain = new float[data.length][];

        boolean isFlat = true;

        for (int i = 0; i < data.length; i++) {
            float[] row = new float[data[i].length];
            for (int j = 0; j < data[i].length; j++) {
                row[j] = data[i][j];
            }
            terrain[i] = row;
        }

        for (int i = 0; i < terrain.length && isFlat; i++) {
            float first = terrain[i][0];

            for (int j = 1; j < terrain[i].length && isFlat; j++) {
                isFlat = terrain[i][j] == first;
            }
        }

        HeightImageCreator imageCreator;

        if (isFlat) {
            imageCreator = new HeightImageCreator(new Color4b(Color.GRAY), new Color4b(Color.GRAY));
            mapImage = imageCreator.createColorImage(terrain);
        } else {
            imageCreator = new HeightImageCreator(new Color4b(Color.black), new Color4b(Color.WHITE));
            mapImage = imageCreator.createGreyScaleImage(terrain);
        }

        ratio = (double) terrainData.getDeltaX() / (double) terrainData.getDeltaY();

        this.gameController.setGame(new ImageView(SwingFXUtils.toFXImage(this.getMapImage(), null)));

        this.target = new Target(new Position(this.terrainData.getTargetX()*this.terrainData.getDeltaX(),
                this.terrainData.getTargetY()*this.terrainData.getDeltaY(), this.terrainData.getShooterAltitudeInM()),
                this.terrainData.getTargetX(), this.terrainData.getTargetY(), this.getGamePane());

        this.shooter = new Shooter(new Position(this.terrainData.getShooterX()*this.terrainData.getDeltaX(),
                this.terrainData.getShooterY()*this.terrainData.getDeltaY(), this.terrainData.getTargetAltitudeInM()),
                this.terrainData.getShooterX(), this.terrainData.getShooterY(), this.getGamePane(), this.target);

        calculator = new ShootingCalculator(this, this.shooter, this.target);

    }

    private ShootingCalculator calculator;

    /**
     * Metoda, která vykresluje herní plochu
     *
     * @param width  šířka herní plochy
     * @param height výška herní plochy
     */
    void paint(double width, double height) {
        double maxW = width - 20;
        double maxH = height - 20 - 70;

        if (maxW / this.ratio > maxH) {
            this.gameWidth = maxH * this.ratio;
            this.gameHeight = maxH;
        } else {
            this.gameWidth = maxW;
            this.gameHeight = maxW / this.ratio;
        }

        this.gameController.paintMap(this.gameWidth, this.gameHeight);
        this.widthOfColumn = this.gameWidth / this.terrainData.getColumns();
        this.heightOfColumn = this.gameHeight / this.terrainData.getRows();

        this.scale = this.widthOfColumn / (this.terrainData.getDeltaX() / 1000);

        this.target.repaint(this.widthOfColumn, this.heightOfColumn);
        this.shooter.repaint(this.widthOfColumn, this.heightOfColumn);

        this.calculator.newCalculator2(0, 0, 0);
    }

    public BufferedImage getMapImage() {
        return mapImage;
    }

    public double getRatio() {
        return ratio;
    }

    public GameController getGameController() {
        return gameController;
    }

    public Pane getGamePane(){
        return gameController.getGameBackPane();
    }

    public TerrainData getTerrainData() {
        return terrainData;
    }

    public double getScale() {
        return scale;
    }
}
