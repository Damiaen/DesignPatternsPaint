package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class ShapeVisitorMove implements ShapeVisitor {
    private int mousePositionY;
    private int mousePositionX;
    private int cursorSelectedX;
    private int cursorSelectedY;

    public void moveShape(IShape shape, int mousePositionY, int mousePositionX, int cursorSelectedX, int cursorSelectedY) {
        this.mousePositionY = mousePositionY;
        this.mousePositionX = mousePositionX;
        this.cursorSelectedX = cursorSelectedX;
        this.cursorSelectedY = cursorSelectedY;
        shape.accept(this);
    }

    @Override
    public void visitShape(IShape shape) {
        Position position = shape.getPosition();
        shape.setMovingPosition(position, mousePositionX, cursorSelectedX, mousePositionY, cursorSelectedY);
    }

    @Override
    public void visitCompositeShape(CompositeShape compositeShape) {
        for (IShape shape : compositeShape.getBaseShapes()) {
            Position position = shape.getPosition();
            shape.setMovingPosition(position, mousePositionX, cursorSelectedX, mousePositionY, cursorSelectedY);
        }
        compositeShape.updateBounds();
    }

    @Override
    public void visitOrnamentDecorator(OrnamentDecorator ornamentDecorator) {
        Position position = ornamentDecorator.getPosition();
        ornamentDecorator.setMovingPosition(position, mousePositionX, cursorSelectedX, mousePositionY, cursorSelectedY);
    }
}
