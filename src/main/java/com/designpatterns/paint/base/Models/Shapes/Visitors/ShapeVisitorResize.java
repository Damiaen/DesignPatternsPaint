package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class ShapeVisitorResize implements ShapeVisitor {

    int newWidth;
    int newHeight;
    DrawPanel drawPanel;

    public ShapeVisitorResize(int newWidth, int newHeight,DrawPanel drawPanel) {
        this.newWidth = newWidth;
        this.newHeight = newHeight;
        this.drawPanel = drawPanel;
    }

    @Override
    public void visitShape(IShape shape) {
        shape.setSize(newWidth, newHeight);
        drawPanel.repaint();
    }

    @Override
    public void visitOrnamentDecorator(OrnamentDecorator ornamentDecorator) {

    }

    @Override
    public void visitCompositeShape(CompositeShape compositeShape) {

    }
}
