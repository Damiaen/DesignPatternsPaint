package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.Actions.Reshape;
import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

import java.util.List;

public class ShapeVisitorResize implements ShapeVisitor {

    int newWidth;
    int newHeight;
    DrawPanel drawPanel;

    public ShapeVisitorResize(int newWidth, int newHeight,DrawPanel drawPanel) {
        this.newWidth = newWidth;
        this.newHeight = newHeight;
        this.drawPanel = drawPanel;
    }

    public String visitShape(Shape shape)
    {
        if (drawPanel.getSelectedShapes() == null) return null;
        for (Shape selectedShapes : drawPanel.getSelectedShapes()) {
            if (selectedShapes != null) {
                shape.setSize(newWidth,newHeight);
                drawPanel.repaint();
            }
        }
        return shape.toString();
    }

    @Override
    public String visitCompositeShape(CompositeShape compositeShape)
    {
        for (Shape selectedShapes : drawPanel.getSelectedShapes()) {
            if (selectedShapes != null) {
                compositeShape.setSize(newWidth,newHeight);
                drawPanel.repaint();
            }
        }
        return null;
    }
}
