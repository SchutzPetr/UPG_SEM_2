import java.util.Random;

/**
 * Created by Petr Schutz on 16.04.2017
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class WindSpeedGenerator {

    private static int currentSpeed = randomNumbers(0, 200);
    private static int currentDirection = randomNumbers(0, 360);

    public static Wind generate(){
        int currentSpeedDispersion = currentSpeed/100*10;
        int currentSpeedMin = currentSpeed - currentSpeedDispersion;
        int currentSpeedMax = currentSpeed + currentSpeedDispersion;

        if(currentSpeedMin < 0)currentSpeedMin = 0;
        if(currentSpeedMax > 200)currentSpeedMax = 200;

        currentSpeed = randomNumbers(currentSpeedMin, currentSpeedMax);

        int currentDirectionDispersion = currentDirection/100*20;
        int currentDirectionMin = currentDirection - currentDirectionDispersion;
        int currentDirectionMax = currentDirection + currentDirectionDispersion;

        currentDirection = randomNumbers(currentDirectionMin, currentDirectionMax);

        if(currentDirection < 0){
            currentDirection = currentDirection+360;
        }
        if(currentDirection > 360){
            currentDirection = currentDirection-360;
        }

        return new Wind(Math.cos(Math.toRadians(currentDirection)) * currentSpeed, Math.sin(Math.toRadians(currentDirection)) * currentSpeed*-1, 0, currentDirection, currentSpeed);
    }

    public static int randomNumbers(int min, int max) {
        Random r = new Random();
        if(min== max)max+=5;
        return r.nextInt((max - min) + 1) + min;
    }
}
