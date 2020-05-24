package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

public interface ShapeVisitor {
    String visitShape(Shape shape);
    String visitCompositeShape(CompositeShape compositeShape);
}
