package com.designpatterns.paint.base.Models.Shapes;

import com.designpatterns.paint.base.Models.Shapes.Figure.Shape;
import com.designpatterns.paint.base.Models.Shapes.Figure.ShapeType;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Group class
 */
public class Group {

    /**
     * Rectangles
     */
    private List<Rectangle> rectangles = new ArrayList<>();

    /**
     * Ellipses
     */
    private List<Ellipse> ellipses = new ArrayList<>();

    /**
     * Count of all figures that are present in the group
     */
    private Integer count;

    /**
     * Group constructor
     */
    public Group(List<Rectangle> rectangles, List<Ellipse> ellipses, Integer count) {
        this.rectangles = rectangles;
        this.ellipses = ellipses;
        this.count = (rectangles.size() + ellipses.size());
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    public List<Ellipse> getEllipses() {
        return ellipses;
    }

    public Integer getCount() {
        return count;
    }
}
