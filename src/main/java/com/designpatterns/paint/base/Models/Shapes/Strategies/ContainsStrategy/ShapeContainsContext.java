package com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

public class ShapeContainsContext {
    private ShapeContainsStrategy shapeContourStrategy;

    public ShapeContainsContext(ShapeContainsStrategy shapeContourStrategy) {
        this.shapeContourStrategy = shapeContourStrategy;
    }

    public boolean executeShapeDrawStrategy(Shape shape, double x, double y) {
        return shapeContourStrategy.contains(shape, x, y);
    }
}
