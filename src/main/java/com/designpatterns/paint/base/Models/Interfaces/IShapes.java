package com.designpatterns.paint.base.Models.Interfaces;

public interface IShapes {
    Integer x = 0;
    Integer y = 0;
    Integer size_x = 0;
    Integer size_y = 0;

    default String Draw() {
        return "test";
    }

    default Integer[] position() {
        return new Integer[]{x, y};
    }
}
