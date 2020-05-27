package com.designpatterns.paint.base.Models.Commands;

public interface Command {
    void execute();
    void undo();
    void redo();
}
