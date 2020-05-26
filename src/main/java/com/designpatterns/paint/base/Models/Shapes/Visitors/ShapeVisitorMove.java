package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class ShapeVisitorMove implements ShapeVisitor {

    Position mousePosition;

    public ShapeVisitorMove(Position mousePosition)
    {
        this.mousePosition = mousePosition;
    }

    @Override
    public void visitShape(IShape shape) {
        shape.setPosition(mousePosition);
    }
    @Override
    public void visitOrnamentDecorator(OrnamentDecorator ornamentDecorator) {

    }
}
