package com.designpatterns.paint.base.Models.Commands;

import java.util.Stack;

/**
 * The invoker class is part of the command pattern, which is used to execute commands and save them for undo/redo.
 */
public class Invoker {

    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;
    private static Invoker instance;

    /**
     * constructor of the Invoker class
     */
    public Invoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * Get Instance of the invoker
     * @return instance of Invoker
     */
    public static Invoker getInstance(){
        if(instance == null) instance = new Invoker();
        return instance;
    }

    /**
     * execute a command
     * @param cmd command you want to execute
     */
    public void execute(Command cmd) {
        undoStack.push(cmd);
        redoStack.clear();
        cmd.execute();
    }

    /**
     * Undo a command
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
            redoStack.push(cmd);
        }
    }

    /**
     * Redo a command
     */
    public void redo()
    {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            undoStack.push(cmd);
        }
    }
}
