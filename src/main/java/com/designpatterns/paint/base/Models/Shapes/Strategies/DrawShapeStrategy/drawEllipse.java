package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy;
import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class drawEllipse implements ShapeDrawStrategy{

    /**
     * Draw ellipse
     * @param g Graphics
     * @param baseShape BaseShape
     * @param color color
     */
    @Override
    public void draw(Graphics g, BaseShape baseShape, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double((baseShape.getPosition().x - (baseShape.getWidth() / 2.0D)), (baseShape.getPosition().y - (baseShape.getHeight() / 2.0D)), baseShape.getWidth(), baseShape.getHeight());

        // Currently we ignore the given color and force it here
        g2d.setColor(Color.GREEN);
        g2d.fill(circle);
    }
}
