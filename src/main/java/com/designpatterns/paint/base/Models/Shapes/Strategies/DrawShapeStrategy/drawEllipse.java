package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class drawEllipse implements ShapeDrawStrategy{

    @Override
    public void draw(Graphics g, Shape shape, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double((shape.getPosition().x - (shape.getWidth() / 2.0D)), (shape.getPosition().y - (shape.getHeight() / 2.0D)), shape.getWidth(), shape.getHeight());

        // Currently we ignore the given color and force it here
        g2d.setColor(Color.GREEN);
        g2d.fill(circle);
    }
}
