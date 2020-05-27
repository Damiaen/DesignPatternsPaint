package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public interface ShapeVisitor {
    void visitShape(IShape shape);
    void visitOrnamentDecorator(OrnamentDecorator ornamentDecorator);
}
