package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

import java.awt.*;

public class ShapeContourContext {
    private ShapeContourStrategy shapeContourStrategy;

    public ShapeContourContext(ShapeContourStrategy shapeContourStrategy) {
        this.shapeContourStrategy = shapeContourStrategy;
    }

    public void executeShapeDrawStrategy(Graphics g, BaseShape baseShape, Color color) {
        shapeContourStrategy.drawContour(g, baseShape, color);
    }
}
