package com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy;
import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

public class EllipseContains implements ShapeContainsStrategy {

    /**
     * check if shape is inside
     * @param baseShape BaseShape
     * @param x double
     * @param y double
     * @return boolean
     */
    @Override
    public boolean contains(BaseShape baseShape, double x, double y) {
        double shapeX = (baseShape.getPosition().x - (baseShape.getWidth() / 2.0D));
        double shapeY = (baseShape.getPosition().y - (baseShape.getHeight() / 2.0D));

        double ellw = baseShape.getWidth();
        if (ellw <= 0.0D) {
            return false;
        } else {
            double normx = (x - shapeX) / ellw - 0.5D;
            double ellh = baseShape.getHeight();
            if (ellh <= 0.0D) {
                return false;
            } else {
                double normy = (y - shapeY) / ellh - 0.5D;
                return normx * normx + normy * normy < 0.25D;
            }
        }
    }
}
