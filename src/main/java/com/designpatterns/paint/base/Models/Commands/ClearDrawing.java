package com.designpatterns.paint.base.Models.Commands;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

import java.util.ArrayList;
import java.util.List;

public class ClearDrawing implements Command
{
    private List<IShape> shapes;
    private final DrawPanel drawPanel = DrawPanel.getInstance();

    /**
     * Constructor for clear drawing command
     */
    public ClearDrawing()
    {
        shapes = drawPanel.getShapes();
    }

    /**
     * execute the command
     */
    @Override
    public void execute()
    {
        for (IShape shape : shapes)
        drawPanel.removeShape(shape);
    }

    /**
     * Undo the command
     */
    @Override
    public void undo()
    {
        List<IShape> newShapes = new ArrayList<>();
        for (IShape shape : shapes) {
            IShape s = drawPanel.addShape(shape.getType(), shape.getPosition(), shape.getWidth(), shape.getHeight());
            newShapes.add(s);
        }
        shapes = newShapes;
    }

    /**
     * Redo the command
     */
    @Override
    public void redo() {
        execute();
    }
}
