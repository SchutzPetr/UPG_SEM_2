import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Created by Petr Schutz on 16.04.2017
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class WindCompass extends Group {

    private final Line line;
    private final Line arrow1;
    private final Line arrow2;
    private final Circle circle;


    public WindCompass(double x1, double y1, double angle, double radius, double strokeWidth) {
        this(radius, strokeWidth, angle, new Line(x1, y1, x1+Math.cos(Math.toRadians(angle)) * radius, y1+Math.sin(Math.toRadians(angle)) * radius), new Line(), new Line(), new Circle(x1, y1, radius, null));
    }

    private double arrowLength;
    private double strokeWidth;
    private double angle;
    private int percent = 100;

    private double radius;

    private WindCompass(double radius, double strokeWidth, double angle, Line line, Line arrow1, Line arrow2, Circle circle) {
        super(line, arrow1, arrow2, circle);

        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(strokeWidth);

        this.line = line;
        this.arrow1 = arrow1;
        this.arrow2 = arrow2;
        this.circle = circle;

        this.radius = radius;

        this.arrowLength = radius;

        this.strokeWidth = strokeWidth;
        this.angle = angle;

        line.setStrokeWidth(strokeWidth);
        arrow1.setStrokeWidth(strokeWidth);
        arrow2.setStrokeWidth(strokeWidth);

        repaint();
    }

    private void repaint(){
        double sx, sy, dv, kx, ky;

        sx = line.getEndX() - line.getStartX();
        sy = line.getEndY() - line.getStartY();

        dv = Math.sqrt(sx*sx + sy*sy);

        sx /= dv;
        sy /= dv;

        kx = -sy;
        ky = sx;

        kx *= strokeWidth * (radius/1000*percent);
        ky *= strokeWidth * (radius/1000*percent);
        sx *= strokeWidth * (radius/1000*percent);
        sy *= strokeWidth * (radius/1000*percent);

        arrow1.setStartX(line.getEndX() - sx + kx);
        arrow1.setStartY(line.getEndY() - sy + ky);
        arrow2.setStartX(line.getEndX() - sx - kx);
        arrow2.setStartY(line.getEndY() - sy - ky);

        arrow1.setEndX(line.getEndX());
        arrow1.setEndY(line.getEndY());
        arrow2.setEndX(line.getEndX());
        arrow2.setEndY(line.getEndY());
    }

    public void setAngle(double angle){
        line.setEndX(line.getStartX()+Math.cos(Math.toRadians(angle)) * arrowLength);
        line.setEndY(line.getStartY()+Math.sin(Math.toRadians(angle)) * arrowLength);

        this.angle = angle;
        repaint();
    }

    public void setLengthPercent(double percent){
        if(percent > 100) return;
        this.arrowLength = radius/100*percent;
        line.setEndX(line.getStartX()+Math.cos(Math.toRadians(angle)) * arrowLength);
        line.setEndY(line.getStartY()+Math.sin(Math.toRadians(angle)) * arrowLength);

        repaint();
    }

    public void setPosition(double x, double y){
        setX(x);
        setY(y);
    }

    public void setX(double x){
        line.setStartX(x);
        repaint();
    }

    public void setY(double y){
        line.setStartY(y);
        repaint();
    }

    public void changeRadius(double radius){
        this.radius = radius;
        setLengthPercent(this.percent);

        this.circle.setRadius(radius);

        repaint();
    }
}
