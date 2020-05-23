package com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

public class EllipseContains implements ShapeContainsStrategy {

    @Override
    public boolean contains(Shape shape, double x, double y) {
        Position position = shape.getPosition();
        double ellw = shape.getWidth();
        if (ellw <= 0.0D) {
            return false;
        } else {
            double normx = (x - position.x) / ellw - 0.5D;
            double ellh = shape.getHeight();
            if (ellh <= 0.0D) {
                return false;
            } else {
                double normy = (y - position.y) / ellh - 0.5D;
                return normx * normx + normy * normy < 0.25D;
            }
        }
    }
}
