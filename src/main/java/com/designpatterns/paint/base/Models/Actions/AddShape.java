package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class AddShape implements Command {

    // which states do i need to store in order to execute and undo
    private IShape shape;
    private DrawPanel drawPanel = DrawPanel.getInstance();

    public AddShape(IShape shape) {
        this.shape = shape;
    }

    @Override
    public void execute() {
        shape = drawPanel.addShape(shape.getType(), shape.getPosition(), shape.getWidth(), shape.getHeight());
        drawPanel.getAllShapesForView();
        drawPanel.repaint();
    }

    @Override
    public void undo() {
        drawPanel.removeShape(shape);
        drawPanel.getAllShapesForView();
        drawPanel.repaint();
    }

    @Override
    public void redo() {
        execute();
    }

}
