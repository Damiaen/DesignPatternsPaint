package com.designpatterns.paint.base.Models;

public class Position
{
    public int x;
    public int y;

    /**
     * constructor
     */
    public Position() {
        this.x = 0;
        this.y = 0;
    }
    /**
     * constructor
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
