package com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

public class ShapeContainsContext {
    private ShapeContainsStrategy shapeContourStrategy;

    public ShapeContainsContext(ShapeContainsStrategy shapeContourStrategy) {
        this.shapeContourStrategy = shapeContourStrategy;
    }

    public boolean executeShapeDrawStrategy(BaseShape baseShape, double x, double y) {
        return shapeContourStrategy.contains(baseShape, x, y);
    }
}
