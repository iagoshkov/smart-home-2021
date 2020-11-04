package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.actions.Action;

public class Door implements HomeComponent {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setOpen() {
        isOpen = true;
    }

    public void setClosed() {
        isOpen = false;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
