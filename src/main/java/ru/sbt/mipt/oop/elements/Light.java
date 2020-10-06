package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.ActionType;

public class Light implements HomeComponent {
    private ComponentId id;
    private boolean isOn;

    public Light(String id, boolean isOn) {
        this.id = new StringId(id);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setActive(boolean on) {
        isOn = on;
    }

    @Override
    public ElementType getType() {
        return HomeElementType.LIGHT;
    }

    @Override
    public ComponentId getId() {
        return id;
    }

    @Override
    public Action apply(Action action, ComponentId component) {
        if ((action.getType() == ActionType.HALL) || ((action.getType() == ActionType.LIGHT) && component.equals(this.id))) {
            action.execute(this);
        }
        return action;
    }
}
