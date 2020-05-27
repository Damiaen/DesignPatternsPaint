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
    public void visitShape(IShape shape) {
        shape.setSize(newWidth, newHeight);
    }

    @Override
    public void visitOrnamentDecorator(OrnamentDecorator ornamentDecorator) {
        ornamentDecorator.setSize(newWidth, newHeight);
    }

    @Override
    public void visitCompositeShape(CompositeShape compositeShape) {
        compositeShape.setSize(newWidth, newHeight);
        System.out.println("toeeee");
        System.out.println(newWidth);
        System.out.println(newHeight);
//        for (IShape shape : compositeShape.getBaseShapes()) {
//            shape.setSize(newWidth, newHeight);
//        }
        compositeShape.updateBounds();
    }
}
