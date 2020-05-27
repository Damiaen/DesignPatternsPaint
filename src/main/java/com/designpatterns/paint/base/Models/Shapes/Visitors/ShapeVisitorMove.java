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
    public void visit(IShape shape) {
        shape.setMovingPosition(mousePositionX, mousePositionY);
    }

    @Override
    public void visit(CompositeShape compositeShape) {
        //for (IShape shape : compositeShape.getBaseShapes()) {
         //   Position position = shape.getPosition();
        compositeShape.setMovingPosition(mousePositionX, mousePositionY);
      //  }
        compositeShape.updateBounds();
    }

    @Override
    public void visit(OrnamentDecorator ornamentDecorator) {
        ornamentDecorator.setMovingPosition(mousePositionX, mousePositionY);
    }
}