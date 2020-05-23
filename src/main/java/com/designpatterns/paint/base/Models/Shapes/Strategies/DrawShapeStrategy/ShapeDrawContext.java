package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

import java.awt.*;

public class ShapeDrawContext {
    private ShapeDrawStrategy shapeDrawStrategy;

    public ShapeDrawContext(ShapeDrawStrategy shapeDrawStrategy) {
        this.shapeDrawStrategy = shapeDrawStrategy;
    }

    public void executeShapeDrawStrategy(Graphics g, Shape shape, Color color) {
        shapeDrawStrategy.draw(g, shape, color);
    }
}
