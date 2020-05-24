package com.designpatterns.paint.base.Models.File.SaveModels;

import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;

public class TextData {
    private Integer tabCount;
    private String line;
    private boolean loaded;
    private ShapeType type;


    public TextData(Integer tabCount, String line, ShapeType type) {
        this.tabCount = tabCount;
        this.line = line;
        this.type = type;
        this.loaded = false;
    }

    public ShapeType getType() {
        return type;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public Integer getTabCount() {
        return tabCount;
    }

    public String getLine() {
        return line;
    }
}
