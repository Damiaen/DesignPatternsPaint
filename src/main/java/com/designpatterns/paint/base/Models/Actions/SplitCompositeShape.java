package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class SplitCompositeShape implements Command
{
    private final CompositeShape shape;
    private final DrawPanel drawPanel;

    public SplitCompositeShape(CompositeShape shape, DrawPanel drawPanel) {
        this.shape = shape;
        this.drawPanel = drawPanel;
    }


    @Override
    public void execute() {
        for (IShape shape : this.shape.getBaseShapes()){
            drawPanel.addShape(shape.getType(), shape.getPosition(), shape.getWidth(), shape.getHeight());
        }
        System.out.println("deleted");
    }

    @Override
    public void undo()
    {
        drawPanel.addShape(shape);
        for (IShape shape : this.shape.getBaseShapes()){
            drawPanel.removeShape(shape);
        }
        System.out.println("added");
    }

    @Override
    public void redo() {
        execute();
    }
}