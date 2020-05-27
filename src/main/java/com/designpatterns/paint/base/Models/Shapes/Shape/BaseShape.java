package com.designpatterns.paint.base.Models.Shapes.Shape;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitor;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy.EllipseContour;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy.RectangleContour;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy.ShapeDrawContext;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy.drawEllipse;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy.drawRectangle;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy.ShapeContourContext;
import com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy.EllipseContains;
import com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy.RectangleContains;
import com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy.ShapeContainsContext;

import java.awt.*;

public class BaseShape implements IShape {

    private int width, height;
    private Position position;
    private final ShapeType type;
    private boolean isSelected;
    private boolean isMoving;

    private ShapeDrawContext shapeDrawContext;
    private ShapeContourContext shapeContourContext;
    private ShapeContainsContext shapeContainsContext;

    private final DrawPanel drawPanel = DrawPanel.getInstance();

    /**
     * Constructor for base shape
     * @param type ShapeType
     * @param position Position
     * @param width int
     * @param height int
     */
    public BaseShape(ShapeType type, Position position, int width, int height) {
        this.type = type;
        this.position = position;
        this.width = width;
        this.height = height;

        // Bind context to shape
        if (type.equals(ShapeType.Ellipse)) {
            shapeDrawContext = new ShapeDrawContext(new drawEllipse());
            shapeContourContext = new ShapeContourContext(new EllipseContour());
            shapeContainsContext = new ShapeContainsContext(new EllipseContains());
        } else if (type.equals(ShapeType.Rectangle)) {
            shapeDrawContext = new ShapeDrawContext(new drawRectangle());
            shapeContourContext = new ShapeContourContext(new RectangleContour());
            shapeContainsContext = new ShapeContainsContext(new RectangleContains());
        }
    }

    /**
     * set size
     * @param width int
     * @param height int
     */
    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * set width
     * @param width int
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * set height
     * @param height int
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * set moving position
     * @param mousePosition Position
     */
    public void setMovingPosition(Position mousePosition) {
        this.position = (new Position(
                (position.x + mousePosition.x) - drawPanel.getCursorSelectedPosition().x,
                (position.y + mousePosition.y) - drawPanel.getCursorSelectedPosition().y)
        );
    }

    /**
     * set position
     * @param position Position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * get position
     * @return Position
     */
    public final Position getPosition() {
        return position;
    }

    /**
     * get width
     * @return width
     */
    public final int getWidth() {
        return width;
    }

    /**
     * get height
     * @return height
     */
    public final int getHeight() {
        return height;
    }

    /**
     * get type
     * @return ShapeType
     */
    public final ShapeType getType() { return type; }

    /**
     * check position
     * @param position Position
     * @return boolean
     */
    public boolean checkPosition(Position position) { return shapeContainsContext.executeShapeDrawStrategy(this, position.x, position.y); }

    /**
     * draw
     * @param g Graphics
     */
    public void draw(Graphics g) {
        shapeDrawContext.executeShapeDrawStrategy(g, this, Color.GREEN);
    }

    /**
     * draw contour
     * @param g Graphics
     * @param color color
     */
    public void drawContour(Graphics g, Color color) {
        shapeContourContext.executeShapeDrawStrategy(g, this, color);
    }

    /**
     * set selected
     * @param bool boolean
     */
    @Override
    public void setSelected(boolean bool) {
        isSelected = bool;
    }

    /**
     * get is selected
     * @return boolean
     */
    @Override
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * accept visitor
     * @param v ShapeVisitor
     */
    @Override
    public void accept(ShapeVisitor v) { v.visit( this ); }

    /**
     * move shape
     * @param oldPos Position
     */
    @Override
    public void moveShape(Position oldPos) {
        this.position = oldPos;
    }

    /**
     * set moving
     * @param bool boolean
     */
    @Override
    public void setMoving(boolean bool){isMoving = bool;}

    /**
     * get is moving
     * @return boolean
     */
    @Override
    public boolean isMoving() {return isMoving;}

    /**
     * to string
     * @return string
     */
    @Override
    public String toString() {
        return type + " " + position.x + " " + position.y + " " + height + " " + width;
    }

}
