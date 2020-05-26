package com.designpatterns.paint.base.Models;

public class Position
{
    // Members
    public int x;
    public int y;

    // Constructors
    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Compare two vectors
    public boolean equals(Position other) {
        return (this.x == other.x && this.y == other.y);
    }
}
