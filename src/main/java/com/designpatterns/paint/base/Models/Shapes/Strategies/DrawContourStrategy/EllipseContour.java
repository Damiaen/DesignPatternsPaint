package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy;
import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

import java.awt.*;

public class EllipseContour implements ShapeContourStrategy {

    /**
     * Draw contour
     * @param g Graphics
     * @param baseShape BaseShape
     * @param color color
     */
    @Override
    public void drawContour(Graphics g, BaseShape baseShape, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        g2d.fillOval((int) (baseShape.getPosition().x - (baseShape.getWidth() / 2.0D) - 3), (int) (baseShape.getPosition().y - (baseShape.getHeight() / 2.0D) - 3), baseShape.getWidth() + 6, baseShape.getHeight() + 6);
    }
}
