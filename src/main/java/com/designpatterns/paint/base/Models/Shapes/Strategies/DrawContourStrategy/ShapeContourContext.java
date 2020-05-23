package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy.ShapeContourStrategy;

import java.awt.*;

public class ShapeContourContext {
    private ShapeContourStrategy shapeContourStrategy;

    public ShapeContourContext(ShapeContourStrategy shapeContourStrategy) {
        this.shapeContourStrategy = shapeContourStrategy;
    }

    public void executeShapeDrawStrategy(Graphics g, Shape shape, Color color) {
        shapeContourStrategy.drawContour(g, shape, color);
    }
}
