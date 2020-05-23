package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy.ShapeContourStrategy;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EllipseContour implements ShapeContourStrategy {

    @Override
    public void drawContour(Graphics g, Shape shape, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        g2d.fillOval((int) (shape.getPosition().x - (shape.getWidth() / 2.0D) - 3), (int) (shape.getPosition().y - (shape.getHeight() / 2.0D) - 3), (int)shape.getWidth() + 6, (int)shape.getHeight() + 6);
    }
}
