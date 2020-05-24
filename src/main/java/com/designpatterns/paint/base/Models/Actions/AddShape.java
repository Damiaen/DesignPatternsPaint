package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

public class AddShape implements Command {

    // which states do i need to store in order to execute and undo
    private Shape shape;
    private DrawPanel drawPanel;

    public AddShape(Shape shape,DrawPanel drawPanel) {
        this.shape = shape;
        this.drawPanel = drawPanel;
    }

    @Override
    public void execute() {
        shape = drawPanel.addShape(shape.getType(),shape.getPosition(),shape.getWidth(),shape.getHeight());
    }

    @Override
    public void undo() {
        drawPanel.removeShape(shape);
    }

    @Override
    public void redo() {
        execute();
    }

}
