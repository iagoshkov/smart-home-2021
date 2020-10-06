package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.ActionType;

public class Door implements HomeComponent {
    private ComponentId id;

    public ComponentId getId() {
        return id;
    }

    private boolean isOpen;

    public Door(ComponentId id, boolean isOpen) {
        this.id = id;
        this.isOpen = isOpen;
    }

    public Door(String id, boolean isOpen) {
        this.id = new StringId(id);
        this.isOpen = isOpen;
    }


    public void setActive(boolean open) {
        isOpen = open;
    }

    @Override
    public ElementType getType() {
        return HomeElementType.DOOR;
    }

    @Override
    public Action apply(Action action, ComponentId component) {
        if (action.getType() == ActionType.DOOR || component.equals(this.id)) {
            action.execute(this);
        }
        return action;
    }
}
