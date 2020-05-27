package com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

public class RectangleContains implements ShapeContainsStrategy {

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

        return x >= shapeX && y >= shapeY && x < shapeX + baseShape.getWidth() && y < shapeY + baseShape.getHeight();
    }
}
