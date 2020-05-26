package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;

import java.util.List;

public class CombineShapes implements Command
{
    private final CompositeShape shape;
    private final DrawPanel drawPanel;

    public CombineShapes(List<IShape> shapes, DrawPanel drawPanel) {
        shape = new CompositeShape(shapes, ShapeType.CompositeShape);
        this.drawPanel = drawPanel;
    }


    @Override
    public void execute() {
        drawPanel.addShape(shape);
        for (IShape shape : this.shape.getBaseShapes()){
            drawPanel.removeShape(shape);
        }
        System.out.println("added");
    }

    @Override
    public void undo()
    {
        for (IShape shape : this.shape.getBaseShapes()){
            drawPanel.addShape(shape.getType(), shape.getPosition(), shape.getWidth(), shape.getHeight());
        }
        System.out.println("deleted");
    }

    @Override
    public void redo() {
        execute();
    }
}
