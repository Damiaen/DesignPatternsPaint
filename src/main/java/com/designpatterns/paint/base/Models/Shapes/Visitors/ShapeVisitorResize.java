package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class ShapeVisitorResize implements ShapeVisitor {

    int newWidth;
    int newHeight;

    /**
     * Constructor for resize visitor
     * @param newWidth int
     * @param newHeight int
     */
    public ShapeVisitorResize(int newWidth, int newHeight) {
        this.newWidth = newWidth;
        this.newHeight = newHeight;
    }

    /**
     * visit
     * @param shape IShape
     */
    @Override
    public void visit(IShape shape) {
        shape.setSize(newWidth, newHeight);
    }

    /**
     * visit
     * @param ornamentDecorator OrnamentDecorator
     */
    @Override
    public void visit(OrnamentDecorator ornamentDecorator) {
        ornamentDecorator.setSize(newWidth, newHeight);
    }

    /**
     * visit
     * @param compositeShape CompositeShape
     */
    @Override
    public void visit(CompositeShape compositeShape) {
        compositeShape.setSize(newWidth, newHeight);
        compositeShape.updateBounds();
    }
}
