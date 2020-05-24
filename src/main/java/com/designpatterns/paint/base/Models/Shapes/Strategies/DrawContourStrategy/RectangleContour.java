package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

import java.awt.*;

public class RectangleContour implements ShapeContourStrategy {

    @Override
    public void drawContour(Graphics g, BaseShape baseShape, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        g2d.fillRect((int) (baseShape.getPosition().x - (baseShape.getWidth() / 2.0D) - 3), (int) ((baseShape.getPosition().y - (baseShape.getHeight() / 2.0D)) - 3), (int) baseShape.getWidth() + 6, (int) baseShape.getHeight() + 6);
    }
}
