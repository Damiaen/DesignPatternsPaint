package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public interface ShapeVisitor {
    void visit(IShape shape);
    void visit(OrnamentDecorator ornamentDecorator);
    void visit(CompositeShape compositeShape);
}
