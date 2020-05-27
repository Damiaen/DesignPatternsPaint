package com.designpatterns.paint.base.Models.File.SaveModels;

import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;

public class TextData {
    private final Integer tabCount;
    private final String line;
    private boolean loaded;
    private final ShapeType type;

    /**
     * Constructor of Text Data
     * @param tabCount tabCount
     * @param line line
     * @param type type
     */
    public TextData(Integer tabCount, String line, ShapeType type) {
        this.tabCount = tabCount;
        this.line = line;
        this.type = type;
        this.loaded = false;
    }

    /**
     * Get type
     * @return type
     */
    public ShapeType getType() {
        return type;
    }

    /**
     * Set loaded
     * @param loaded boolean
     */
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    /**
     * return if loaded is true/false
     * @return boolean
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * get tab count
     * @return Integer
     */
    public Integer getTabCount() {
        return tabCount;
    }

    /**
     * Get line
     * @return String
     */
    public String getLine() {
        return line;
    }
}
