package def;

import java.util.List;

/**
 * Created by Petr Schutz on 18.03.2017
 * <p>
 * Třídy {@code TerrainData} reprezentující data herní plochy
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class TerrainData {
    /**
     * výšky v terénu
     */
    private final int[][] terrain;
    /**
     * Parametry terénu
     */
    private final int column, row, deltaX, deltaY, shooterX, shooterY, targetX, targetY;
    /**
     * Jméno souboru
     */
    private final String fName;

    /**
     * Vytvoří instanci třídy {@code TerrainData}, která reprezentuje data herní plochy
     *
     * @param terrain  výšky v terénu
     * @param column   počet sloupců
     * @param row      počet řádků
     * @param deltaX   šířka sloupce
     * @param deltaY   výška sloupce
     * @param shooterX x souřadnice střelce
     * @param shooterY y souřadnice střelce
     * @param targetX  x souřadnice cíle
     * @param targetY  y souřadnice cíle
     * @param fName    jméno souboru
     */
    private TerrainData(int[][] terrain, int column, int row, int deltaX, int deltaY, int shooterX, int shooterY, int targetX, int targetY, String fName) {
        this.terrain = terrain;
        this.column = column;
        this.row = row;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.shooterX = shooterX;
        this.shooterY = shooterY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.fName = fName;
    }

    /**
     * Statická tovární metoda, která yytvoří instanci třídy {@code TerrainData}, která reprezentuje data herní plochy
     *
     * @param fileName jméno souboru
     * @return instancie třídy {@code TerrainData}
     */
    public static TerrainData loadTerrain(String fileName) {
        List<Integer> integers = ReadFile.readFile(fileName);


        int[][] terrain = new int[integers.get(1)][integers.get(0)];

        int counter = 8;

        for (int y = 0; y < integers.get(1); ++y) {
            for (int x = 0; x < integers.get(0); ++x) {
                terrain[y][x] = integers.get(counter);
                counter++;
            }
        }

        return new TerrainData(
                terrain, integers.get(0), integers.get(1), integers.get(2), integers.get(3),
                integers.get(4), integers.get(5), integers.get(6),
                integers.get(7), fileName);
    }

    /**
     * @return výšky v terénu
     */
    public int[][] getTerrain() {
        return terrain;
    }

    /**
     * @return počet sloupců
     */
    public int getColumns() {
        return column;
    }

    /**
     * @return počet řádků
     */
    public int getRows() {
        return row;
    }

    /**
     * @return šířka sloupce v mm
     */
    public int getDeltaX() {
        return deltaX;
    }

    /**
     * @return výška řádku v mm
     */
    public int getDeltaY() {
        return deltaY;
    }

    /**
     * @return x souřadnice střelce
     */
    public int getShooterX() {
        return shooterX;
    }

    /**
     * @return y souřadnice střelce
     */
    public int getShooterY() {
        return shooterY;
    }

    /**
     * @return x souřadnice cíle
     */
    public int getTargetX() {
        return targetX;
    }

    /**
     * @return y souřadnice cíle
     */
    public int getTargetY() {
        return targetY;
    }

    /**
     * @return jméno souboru
     */
    public String getfName() {
        return fName;
    }

    /**
     * Navrací výšky(v metrech)v okrajich zadaného čtverce(podle hodin. ručiček)
     *
     * @param x souřadnice v metrech
     * @param y souřadnice v metrech
     *
     * @return pole výšek v rozích
     */
    private int[] getAltitudeInSquareCorners(double x, double y) {
        int[] corners = new int[4];

        int xx = (int) ((x * 1000) / deltaX);
        int yy = (int) ((y * 1000) / deltaY);


        //System.out.println(xx + " - " +yy );

        try{
            if(yy == row-1 && xx == column-1){
                corners[0] = terrain[yy][xx]/1000;
                corners[1] = terrain[yy][xx]/1000;
                corners[2] = terrain[yy][xx]/1000;
                corners[3] = terrain[yy][xx]/1000;
            }else if(yy == row-1){
                corners[0] = terrain[yy][xx]/1000;
                corners[1] = terrain[yy][xx+1]/1000;
                corners[2] = terrain[yy][xx+1]/1000;
                corners[3] = terrain[yy][xx]/1000;
            }else if(xx == column-1){
                corners[0] = terrain[yy][xx]/1000;
                corners[1] = terrain[yy][xx]/1000;
                corners[2] = terrain[yy+1][xx]/1000;
                corners[3] = terrain[yy+1][xx]/1000;
            } else{
                corners[0] = terrain[yy][xx]/1000;
                corners[1] = terrain[yy][xx+1]/1000;
                corners[2] = terrain[yy+1][xx+1]/1000;
                corners[3] = terrain[yy+1][xx]/1000;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(xx + " - " +yy );
            System.out.println(terrain.length-1);
            System.out.println(terrain[0].length-1);
        }


        return corners;
    }

    /**
     * Navrací výšku v daném bodě
     *
     * @param x souřadnice v metrech
     * @param y souřadnice v metrech
     *
     * @return výšku v daném bodě
     */
    public double getAltitudeInM(double x, double y) {
        int xxx = (int) ((x * 1000) / deltaX);
        int yyy = (int) ((y * 1000) / deltaY);

        if(xxx >= column || yyy >= row ||xxx < 0 || yyy < 0 ){
            return -1.0;
        }
        int[] corners = getAltitudeInSquareCorners(x, y);

        double xx = x - ((int)((x * 1000) / deltaX));
        double yy = y - ((int) ((y * 1000) / deltaY));

        double u = xx*(corners[2]-corners[3])+corners[3];
        double v = xx*(corners[1]-corners[0])+corners[0];

        double z = yy*(v-u)+u;

        return z;
    }

    /**
     * @return Navrací výšku střelce v metrech
     */
    public double getShooterAltitudeInM() {
        return terrain[this.getShooterY()][this.getShooterX()]/1000;
    }

    /**
     * @return Navrací výšku cíle v metrech
     */
    public double getTargetAltitudeInM() {
        return terrain[this.getTargetY()][this.getTargetX()]/1000;
    }
}
