package com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

public class RectangleContains implements ShapeContainsStrategy {

    @Override
    public boolean contains(Shape shape, double x, double y) {
        double shapeX = (shape.getPosition().x - (shape.getWidth() / 2.0D));
        double shapeY = (shape.getPosition().y - (shape.getHeight() / 2.0D));

        return x >= shapeX && y >= shapeY && x < shapeX + shape.getWidth() && y < shapeY + shape.getHeight();
    }
}
