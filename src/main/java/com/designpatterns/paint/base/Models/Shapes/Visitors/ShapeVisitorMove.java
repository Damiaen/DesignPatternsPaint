package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
<<<<<<< Updated upstream
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class ShapeVisitorMove implements ShapeVisitor {
    private Double mousePositionY;
    private Double mousePositionX;
    private Double cursorSelectedX;
    private Double cursorSelectedY;

    public void moveShape(IShape shape, Double mousePositionY, Double mousePositionX, Double cursorSelectedX, Double cursorSelectedY) {
        this.mousePositionY = mousePositionY;
        this.mousePositionX = mousePositionX;
        this.cursorSelectedX = cursorSelectedX;
        this.cursorSelectedY = cursorSelectedY;
        shape.accept(this);
    }

    @Override
    public void visitShape(IShape shape) {
        Position position = shape.getPosition();
        shape.setPosition(new Position(
                (position.x + mousePositionX) - cursorSelectedX,
                (position.y + mousePositionY) - cursorSelectedY)
        );
    }

    @Override
    public void visitCompositeShape(CompositeShape compositeShape) {
        for (IShape shape : compositeShape.getBaseShapes()) {
            Position position = shape.getPosition();
            shape.setPosition(new Position(
                    (position.x + mousePositionX) - cursorSelectedX,
                    (position.y + mousePositionY) - cursorSelectedY)
            );
        }
        compositeShape.updateBounds();
    }

    @Override
    public void visitOrnamentDecorator(OrnamentDecorator ornamentDecorator) {
        Position position = ornamentDecorator.getPosition();
        ornamentDecorator.setPosition(new Position(
                (position.x + mousePositionX) - cursorSelectedX,
                (position.y + mousePositionY) - cursorSelectedY)
        );
=======
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

public class ShapeVisitorMove implements ShapeVisitor {

    Position mousePosition;

    public ShapeVisitorMove(Position mousePosition)
    {
        this.mousePosition = mousePosition;
    }

    @Override
    public String visitShape(Shape shape) {
        shape.setPosition(mousePosition);
        return shape.toString();
    }

    @Override
    public String visitCompositeShape(CompositeShape compositeShape) {
        compositeShape.setPosition(mousePosition);
        compositeShape.updateBounds();
        return compositeShape.toString();
>>>>>>> Stashed changes
    }
}
