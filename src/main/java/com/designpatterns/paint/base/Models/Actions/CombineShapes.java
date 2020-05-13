package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Figure.Shape;

import java.util.List;

public class CombineShapes implements Command
{
    private final List<Shape> shapes;
    private final DrawPanel drawPanel;

    public CombineShapes(List<Shape> shapes, DrawPanel drawPanel) {
        this.shapes = shapes;
        this.drawPanel = drawPanel;
    }


    @Override
    public void execute() {
        //combine shapes with composite pattern for group creation
    }

    @Override
    public void undo() {
        for (Shape shape : shapes){
            drawPanel.addShape(shape.getType(),shape.getX(),shape.getY(),shape.getWidth(),shape.getHeight());
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
