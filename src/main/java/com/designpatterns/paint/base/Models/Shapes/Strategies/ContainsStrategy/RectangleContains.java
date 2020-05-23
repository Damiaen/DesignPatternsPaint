package com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

public class RectangleContains implements ShapeContainsStrategy {

    @Override
    public boolean contains(Shape shape, double x, double y) {
        Position position = shape.getPosition();
        double x0 = position.x;
        double y0 = position.y;

        return x >= x0 && y >= y0 && x < x0 + shape.getWidth() && y < y0 + shape.getHeight();
    }
}
