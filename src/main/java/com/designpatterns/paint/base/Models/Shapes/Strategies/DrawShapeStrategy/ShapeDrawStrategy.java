package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

import java.awt.*;

public interface ShapeDrawStrategy {
    void draw(Graphics g, BaseShape baseShape, Color color);
}
