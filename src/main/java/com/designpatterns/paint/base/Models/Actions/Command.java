package com.designpatterns.paint.base.Models.Actions;

public interface Command {
    public void execute();
    public void undo();
    public void redo();
}
