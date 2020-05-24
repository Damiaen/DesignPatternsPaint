package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

import java.awt.*;

public interface ShapeContourStrategy {
    void drawContour(Graphics g, Shape shape, Color color);
}
