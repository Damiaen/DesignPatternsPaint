package com.designpatterns.paint.base.Models.Interfaces;

public interface IShapes {
    Number x = null;
    Number y = null;
    Number size_x = null;
    Number size_y = null;

    default String Draw() {
        return "test";
    }
}
