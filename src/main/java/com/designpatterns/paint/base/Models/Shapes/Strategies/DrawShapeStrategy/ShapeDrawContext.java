package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

import java.awt.*;

public class ShapeDrawContext {
    private ShapeDrawStrategy shapeDrawStrategy;

    public ShapeDrawContext(ShapeDrawStrategy shapeDrawStrategy) {
        this.shapeDrawStrategy = shapeDrawStrategy;
    }

    public void executeShapeDrawStrategy(Graphics g, BaseShape baseShape, Color color) {
        shapeDrawStrategy.draw(g, baseShape, color);
    }
}
