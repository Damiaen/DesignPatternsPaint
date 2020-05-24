package com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

import java.awt.*;

public interface ShapeContourStrategy {
    void drawContour(Graphics g, BaseShape baseShape, Color color);
}
