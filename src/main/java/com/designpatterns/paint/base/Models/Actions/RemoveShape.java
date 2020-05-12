package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Figure.Shape;

public class RemoveShape implements Command {

    // which states do i need to store in order to execute and undo
    private Shape shape;
    private DrawPanel drawPanel;

    public RemoveShape(Shape shape,DrawPanel drawPanel) {
        this.shape = shape;
        this.drawPanel = drawPanel;
    }

    @Override
    public void execute() {
        drawPanel.removeShape(shape);
    }

    @Override
    public void undo() {
        shape = drawPanel.addShape(shape.getType(),shape.getX(),shape.getY(),shape.getWidth(),shape.getHeight());
    }

    @Override
    public void redo() {
        execute();
    }

}
