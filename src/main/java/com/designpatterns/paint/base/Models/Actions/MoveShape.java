package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class MoveShape implements Command
{

    private Position oldPos;

    private Position newPos;

    private DrawPanel panel;

    private IShape shape;

    public MoveShape (Position pos, IShape shape, DrawPanel panel){
        oldPos = pos;
        this.shape = shape;
        this.panel = panel;
    }

    public void setNewPos(Position pos) {
        newPos = pos;
    }

    @Override
    public void execute()
    {
        shape.moveShape(newPos);
        panel.repaint();
    }

    @Override
    public void undo()
    {
        shape.moveShape(oldPos);
        panel.repaint();
    }

    @Override
    public void redo() { execute(); }
}
