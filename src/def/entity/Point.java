package def.entity;

/**
 * Created by Petr Schutz on 21.03.2017
 * <p>
 * Abstraktní třída {@code Point}, která představuje nějaký bod na hracím poli
 *
 * @author Petr Schutz
 * @version 1.0
 */
public abstract class Point {
    /**
     * Instance třídy typu přepravka, která uchovává pozici bodu na herní mapě
     */
    private Position position;

    /**
     * Vytvoří instanci abstraktní třídy {@code Point}, která představuje nějaký bod na hracím poli
     *
     * @param position pouzice bodu
     */
    protected Point(Position position) {
        this.position = position;
    }

    /**
     * @return pozice bodu na herní mapě
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Nastaví pozici bodu na mapě
     *
     * @param position pozice bodu na herní mapě
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Metoda vypočte vzdálenost aktuálního bodu na mapě vzhledem k jinému dobu
     *
     * @param point pozice bodu na herní mapě
     * @return vzdálenost v px
     */
    public double getDistance(Point point) {
        return Math.sqrt(Math.pow(Math.abs(this.getPosition().getX() - point.getPosition().getX()), 2)
                + Math.pow(Math.abs(this.getPosition().getY() - point.getPosition().getY()), 2));
    }
}
