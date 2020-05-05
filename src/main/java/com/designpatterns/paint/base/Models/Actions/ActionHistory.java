package com.designpatterns.paint.base.Models.Actions;

import java.util.ArrayList;
import java.util.List;

public class ActionHistory
{
    private static ActionHistory instance;
    private List<Action> history = new ArrayList<>();
    private int index = -1;
    private int maxHistorySize = 20;

    public static ActionHistory getInstance() {
        if (instance == null) instance = new ActionHistory();
        return instance;
    }

    public Action getLastAction()
    {
        return history.get(index);
    }

    /*
        When adding a new action to the list, first check if the index is lower then the current history size.
        If the index is lower the the history size then undo has been used, which means that all actions after the index need to be removed before adding a new item.
    */
    public void addAction(Action action)
    {
        if (history.size() > index) removeAllAfterIndex();
        history.add(action);
        if (history.size() > maxHistorySize) {
            history.remove(0);
        }
        else {
            index++;
        }
    }

    public int getIndex() { return index; }
    public void undo() { index--; }

    public void redo() { index++;}

    private void removeAllAfterIndex() {
        history.subList(index + 1, history.size()).clear();
    }

    public List<Action> getHistory() {
        return history;
    }
}
