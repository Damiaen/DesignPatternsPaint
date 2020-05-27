package com.designpatterns.paint.base.Models.Commands;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;

import java.util.List;

public class CombineShapes implements Command
{
    private final CompositeShape shape;
    private final DrawPanel drawPanel = DrawPanel.getInstance();

    /**
     * Constructor of the combine shapes command
     */
    public CombineShapes(List<IShape> shapes) {
        shape = new CompositeShape(shapes, ShapeType.CompositeShape);
    }

    /**
     * Execute the command
     */
    @Override
    public void execute() {
        drawPanel.addShape(shape);
        for (IShape shape : this.shape.getBaseShapes())
        {
            drawPanel.removeShape(shape);
        }
        drawPanel.getAllShapesForView();
        drawPanel.repaint();
    }

    /**
     * Undo the command
     */
    @Override
    public void undo()
    {
        for (IShape shape : this.shape.getBaseShapes())
        {
            drawPanel.invoker.execute(new AddShape(shape));
        }
        drawPanel.removeShape(shape);
        drawPanel.getAllShapesForView();
        drawPanel.repaint();
    }

    /**
     * Undo the command
     */
    @Override
    public void redo() {
        execute();
    }
}
