package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitorResize;

public class Reshape implements Command {

    IShape shape;
    int width;
    int height;
    int oldWidth;
    int oldHeight;
    DrawPanel drawPanel;

    public Reshape(IShape shape, int width, int height,DrawPanel drawPanel) {
        this.shape = shape;
        this.width = width;
        this.height = height;
        this.drawPanel = drawPanel;
    }

    @Override
    public void execute()
    {
        oldWidth = shape.getWidth();
        oldHeight = shape.getHeight();
        ShapeVisitorResize shapeVisitorResize = new ShapeVisitorResize(width,height,drawPanel);
        shapeVisitorResize.visitShape(shape);
        drawPanel.repaint();
    }

    @Override
    public void undo() {
        ShapeVisitorResize shapeVisitorResize = new ShapeVisitorResize(oldWidth,oldHeight,drawPanel);
        shapeVisitorResize.visitShape(shape);
        drawPanel.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}
