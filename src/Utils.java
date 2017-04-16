/**
 * Created by Petr Schutz on 21.03.2017
 * <p>
 * Knihovní třída, která má nastarost různě užitečnén funkce
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class Utils {

    /**
     * Knihovní třída
     */
    private Utils() {
    }


    /**
     * Metoda navrátí zkontroluje zda je daný řetězec možné převést na double
     *
     * @param string řetězec
     * @return true pokud je řetězec typu double
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException | NullPointerException ignored) {
            return false;
        }
        return true;
    }

    /**
     * Metoda navrací true, jeli zadaný uhel v rozsahu -90 až 180 stupnů
     *
     * @param angle uhel ve stupních
     * @return true pokud je uhel v platném rozsahu
     */
    public static boolean isValidAngleValue(double angle) {
        return angle >= -90 && angle <= 180;
    }

    /**
     * Metoda navrací true, jeli zadaný uhel v rozsahu -90 až 90 stupnů
     *
     * @param elevation uhel ve stupních
     * @return true pokud je uhel v platném rozsahu
     */
    public static boolean isValidElevationValue(double elevation) {
        return elevation >= -90 && elevation <= 90;
    }
}
