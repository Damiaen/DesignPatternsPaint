package com.designpatterns.paint.base.Models.Shapes.Visitors.SaveVisitor;

import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public interface SaveShapeVisitor {
    String visitShape(IShape shape);
    String visitCompositeShape(CompositeShape compositeShape);
    String visitOrnamentDecorator(OrnamentDecorator ornamentDecorator);
}
