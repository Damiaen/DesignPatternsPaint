package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class ShapeVisitorMove implements ShapeVisitor {
    private Position mousePosition;

    /**
     * constructor of move shape visitor
     * @param shape IShape
     * @param mousePosition Position
     */
    public void moveShape(IShape shape, Position mousePosition) {
        this.mousePosition = mousePosition;
        shape.accept(this);
    }

    /**
     * visit
     * @param shape IShape
     */
    @Override
    public void visit(IShape shape) {
        shape.setMovingPosition(mousePosition);
    }

    /**
     * visit
     * @param compositeShape CompositeShape
     */
    @Override
    public void visit(CompositeShape compositeShape) {
        compositeShape.setMovingPosition(mousePosition);
    }

    /**
     * visit
     * @param ornamentDecorator OrnamentDecorator
     */
    @Override
    public void visit(OrnamentDecorator ornamentDecorator) {
        ornamentDecorator.setMovingPosition(mousePosition);
    }
}