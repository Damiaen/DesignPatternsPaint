package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

import java.awt.*;

public interface ShapeDrawStrategy {
    void draw(Graphics g, Shape shape, Color color);
}
