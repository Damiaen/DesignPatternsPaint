package com.designpatterns.paint.base.Models.Commands;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitorResize;

public class Reshape implements Command {

    IShape shape;
    int width;
    int height;
    int oldWidth;
    int oldHeight;
    DrawPanel drawPanel = DrawPanel.getInstance();

    /**
     * Constructor of the reshape the command
     */
    public Reshape(IShape shape, int width, int height) {
        this.shape = shape;
        this.width = width;
        this.height = height;
    }

    /**
     * Execute the command
     */
    @Override
    public void execute()
    {
        oldWidth = shape.getWidth();
        oldHeight = shape.getHeight();
        ShapeVisitorResize shapeVisitorResize = new ShapeVisitorResize(width,height);
        shape.accept(shapeVisitorResize);
        drawPanel.repaint();
    }

    /**
     * Undo the command
     */
    @Override
    public void undo() {
        ShapeVisitorResize shapeVisitorResize = new ShapeVisitorResize(oldWidth,oldHeight);
        shape.accept(shapeVisitorResize);
        drawPanel.repaint();
    }

    /**
     * Redo the command
     */
    @Override
    public void redo() {
        execute();
    }
}
