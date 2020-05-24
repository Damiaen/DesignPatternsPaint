package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class drawRectangle implements ShapeDrawStrategy{

    @Override
    public void draw(Graphics g, BaseShape baseShape, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double rectangle = new Rectangle2D.Double((baseShape.getPosition().x - (baseShape.getWidth() / 2.0D)), (baseShape.getPosition().y - (baseShape.getHeight() / 2.0D)), baseShape.getWidth(), baseShape.getHeight());

        // Currently we ignore the given color and force it here
        g2d.setColor(Color.RED);
        g2d.fill(rectangle);
    }
}
