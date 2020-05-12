package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Figure.Shape;

import java.awt.event.MouseEvent;

public class MoveShape implements Command
{

    private Vector2 oldPos;

    private Vector2 newPos;

    private DrawPanel panel;

    private Shape shape;

    public MoveShape (Vector2 pos,Shape shape, DrawPanel panel){
        oldPos = pos;
        this.shape = shape;
        this.panel = panel;
    }

    public void setNewPos(Vector2 pos) {
        newPos = pos;
    }

    @Override
    public void execute()
    {
        panel.moveShapeBack(shape,(int)newPos.x,(int)newPos.y);
    }

    @Override
    public void undo()
    {
        panel.moveShapeBack(shape,(int)oldPos.x,(int)oldPos.y);
    }

    @Override
    public void redo() { execute(); }
}
