package com.designpatterns.paint.base.Models.Commands;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class RemoveShape implements Command {

    // which states do i need to store in order to execute and undo
    private IShape shape;
    private final DrawPanel drawPanel = DrawPanel.getInstance();

    /**
     * Constructor of the remove shape command
     * @param position position
     */
    public RemoveShape(Position position) {
        this.shape = drawPanel.getShapeByCoordinates(position);
    }

    /**
     * Execute the command
     */
    @Override
    public void execute() {
        drawPanel.removeShape(shape);
        drawPanel.repaint();
    }

    /**
     * Undo the command
     */
    @Override
    public void undo() {
        shape = drawPanel.addShape(shape.getType(), shape.getPosition(), shape.getWidth(), shape.getHeight());
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
