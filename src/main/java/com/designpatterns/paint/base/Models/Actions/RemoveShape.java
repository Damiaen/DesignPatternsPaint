package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class RemoveShape implements Command {

    // which states do i need to store in order to execute and undo
    private IShape shape;
    private DrawPanel drawPanel;

    public RemoveShape(Position position, DrawPanel drawPanel) {
        this.shape = drawPanel.getShapeByCoordinates(position);
        this.drawPanel = drawPanel;
    }

    @Override
    public void execute() {
        drawPanel.removeShape(shape);
    }

    @Override
    public void undo() {
        shape = drawPanel.addShape(shape.getType(), shape.getPosition(), shape.getWidth(), shape.getHeight());
    }

    @Override
    public void redo() {
        execute();
    }

}
