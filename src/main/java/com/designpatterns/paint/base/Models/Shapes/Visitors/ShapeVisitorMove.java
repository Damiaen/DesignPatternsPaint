package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class ShapeVisitorMove implements ShapeVisitor {
    private int mousePositionX;
    private int mousePositionY;

    public void moveShape(IShape shape, int mousePositionX, int mousePositionY) {
        this.mousePositionX = mousePositionX;
        this.mousePositionY = mousePositionY;
        shape.accept(this);
    }

    @Override
    public void visitShape(IShape shape) {
        Position position = shape.getPosition();
        shape.setMovingPosition(position, mousePositionX, mousePositionY);
    }

    @Override
    public void visitCompositeShape(CompositeShape compositeShape) {
        System.out.println(mousePositionX);
        System.out.println(mousePositionY);

        for (IShape shape : compositeShape.getBaseShapes()) {
            Position position = shape.getPosition();
            System.out.println(position.x);
            System.out.println(position.y);
            shape.setMovingPosition(position, mousePositionX, mousePositionY);
        }
        compositeShape.updateBounds();
    }

    @Override
    public void visitOrnamentDecorator(OrnamentDecorator ornamentDecorator) {
        Position position = ornamentDecorator.getPosition();
        ornamentDecorator.setMovingPosition(position, mousePositionX, mousePositionY);
    }
}