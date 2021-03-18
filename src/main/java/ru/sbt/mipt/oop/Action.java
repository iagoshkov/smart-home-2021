package ru.sbt.mipt.oop;

/**
 * Общий интерфейс всех стратегий.
 */
public class Action {
    private final Object actionObject;

    public void act(Action action) { // выполнить действие (Action) над каждым элементом дома
        System.out.println("Act!");
        if (actionObject instanceof Door) {
            System.out.println("Act Door " + ((Door) actionObject).getId());
        }
        if (actionObject instanceof Light) {
            System.out.println("Act Light " + ((Light) actionObject).getId());
        }
    }

    public Action(Object actionObject) {
        this.actionObject = actionObject;
    }

    public Object getActionObject() {
        return actionObject;
    }
}
