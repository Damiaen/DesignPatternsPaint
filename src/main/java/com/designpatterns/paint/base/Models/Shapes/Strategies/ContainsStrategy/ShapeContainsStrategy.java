package com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

public interface ShapeContainsStrategy {
    boolean contains(Shape shape, double x, double y);
}
