package com.designpatterns.paint.base.Models.Commands;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitorMove;

public class MoveShape implements Command
{
    private final Position oldPos;

    private Position newPos;

    private final DrawPanel panel = DrawPanel.getInstance();

    private final IShape shape;

    /**
     * Command for moving a shape
     * @param pos new position
     * @param shape shape that has to be moved
     */
    public MoveShape (Position pos, IShape shape) {
        oldPos = pos;
        this.shape = shape;
    }

    /**
     * sets the new position
     * @param pos position
     */
    public void setNewPos(Position pos) {
        newPos = pos;
    }

    /**
     * execute the command
     */
    @Override
    public void execute()
    {
        ShapeVisitorMove shapeVisitorMove = new ShapeVisitorMove();
        shapeVisitorMove.moveShape(shape,new Position(newPos.x,newPos.y));
        panel.repaint();
    }

    /**
     * undo the command
     */
    @Override
    public void undo()
    {
        ShapeVisitorMove shapeVisitorMove = new ShapeVisitorMove();
        shapeVisitorMove.moveShape(shape,new Position(oldPos.x,oldPos.y));
        panel.repaint();
    }
    /**
     * Redo the command
     */
    @Override
    public void redo() { execute(); }
}
