package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class ShapeVisitorResize implements ShapeVisitor {

    int newWidth;
    int newHeight;

    public ShapeVisitorResize(int newWidth, int newHeight) {
        this.newWidth = newWidth;
        this.newHeight = newHeight;
    }

    @Override
    public void visit(IShape shape) {
        shape.setSize(newWidth, newHeight);
    }

    @Override
    public void visit(OrnamentDecorator ornamentDecorator) {
        ornamentDecorator.setSize(newWidth, newHeight);
    }

    @Override
    public void visit(CompositeShape compositeShape) {
        compositeShape.setSize(newWidth, newHeight);
        for (IShape shape : compositeShape.getBaseShapes()) {
            shape.setSize(newWidth, newHeight);
        }
        compositeShape.updateBounds();
    }
}
