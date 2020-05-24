package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class ClearDrawing implements Command
{
    private List<Shape> shapes;
    private final DrawPanel drawPanel;

    public ClearDrawing( DrawPanel drawPanel)
    {
        this.drawPanel = drawPanel;
        shapes = drawPanel.getShapes();
    }

    @Override
    public void execute()
    {
        for (Shape shape : shapes)
        drawPanel.removeShape(shape);
    }

    @Override
    public void undo()
    {
        List<Shape> newShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            Shape s = drawPanel.addShape(shape.getType(),shape.getPosition(),shape.getWidth(),shape.getHeight());
            newShapes.add(s);
        }
        shapes = newShapes;
    }

    @Override
    public void redo() {
        execute();
    }
}
