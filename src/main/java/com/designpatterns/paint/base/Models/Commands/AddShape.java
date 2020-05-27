package com.designpatterns.paint.base.Models.Commands;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class AddShape implements Command {

    private IShape shape;
    private final DrawPanel drawPanel = DrawPanel.getInstance();

    /**
     * Constructor for adding shape command
     * @param shape shape
     */
    public AddShape(IShape shape) {
        this.shape = shape;
    }

    /**
     * execute the command
     */
    @Override
    public void execute() {
        shape = drawPanel.addShape(shape.getType(), shape.getPosition(), shape.getWidth(), shape.getHeight());
        drawPanel.getAllShapesForView();
        drawPanel.repaint();
    }

    /**
     * Undo the command
     */
    @Override
    public void undo() {
        drawPanel.removeShape(shape);
        drawPanel.getAllShapesForView();
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
