package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;
import com.designpatterns.paint.base.Models.Position;

public class MoveShape implements Command
{

    private Position oldPos;

    private Position newPos;

    private DrawPanel panel;

    private Shape shape;

    public MoveShape (Position pos, Shape shape, DrawPanel panel){
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
        panel.moveShapeBack(shape,newPos);
    }

    @Override
    public void undo()
    {
        panel.moveShapeBack(shape,oldPos);
    }

    @Override
    public void redo() { execute(); }
}
