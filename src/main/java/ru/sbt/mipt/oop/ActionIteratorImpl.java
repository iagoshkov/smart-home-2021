package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class ActionIteratorImpl implements ActionIterator{
    private final List<Action> actions;

    public ActionIteratorImpl(List<Action> actions) {
        if (actions != null) {
            this.actions = actions;
        } else {
            this.actions = new ArrayList<>();
        }
    }

    @Override
    public void actAllActions() {
        actions.forEach(action -> action.act(action));
    }

    @Override
    public void actLights() {
        for(Action action : actions) {
            if (action.getActionObject() instanceof Light) {
                action.act(action);
            }
        }
    }
}
