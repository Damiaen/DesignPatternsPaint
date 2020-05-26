package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;
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
        if (drawPanel.getSelectedShapes() == null) return;
        for (IShape selectedShapes : drawPanel.getSelectedShapes()) {
            if (selectedShapes != null) {
                shape.setSize(newWidth,newHeight);
                drawPanel.repaint();
            }
        }
    }

    @Override
    public void visitCompositeShape(CompositeShape compositeShape)
    {
        for (IShape selectedShapes : drawPanel.getSelectedShapes()) {
            if (selectedShapes != null) {
                compositeShape.setSize(newWidth,newHeight);
                drawPanel.repaint();
            }
        }
    }

    @Override
    public void visitOrnamentDecorator(OrnamentDecorator ornamentDecorator) {

    }
}
