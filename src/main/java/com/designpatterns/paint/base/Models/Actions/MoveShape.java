package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitorMove;

public class MoveShape implements Command
{

    private Position oldPos;

    private Position newPos;

    private DrawPanel panel = DrawPanel.getInstance();

    private IShape shape;

    public MoveShape (Position pos, IShape shape){
        oldPos = pos;
        this.shape = shape;
    }

    public void setNewPos(Position pos) {
        newPos = pos;
    }

    @Override
    public void execute()
    {
        ShapeVisitorMove shapeVisitorMove = new ShapeVisitorMove();
        shapeVisitorMove.moveShape(shape,newPos.x,newPos.y);
        panel.repaint();
    }

    @Override
    public void undo()
    {
        ShapeVisitorMove shapeVisitorMove = new ShapeVisitorMove();
        shapeVisitorMove.moveShape(shape,oldPos.x,oldPos.y);
        panel.repaint();
    }

    @Override
    public void redo() { execute(); }
}
