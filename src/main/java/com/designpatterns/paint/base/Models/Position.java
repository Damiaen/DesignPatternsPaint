package com.designpatterns.paint.base.Models;

public class Position
{
    // Members
    public double x;
    public double y;

    // Constructors
    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Compare two vectors
    public boolean equals(Position other) {
        return (this.x == other.x && this.y == other.y);
    }
}
