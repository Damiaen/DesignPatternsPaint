package com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy;

import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;

public interface ShapeContainsStrategy {
    boolean contains(BaseShape baseShape, double x, double y);
}
