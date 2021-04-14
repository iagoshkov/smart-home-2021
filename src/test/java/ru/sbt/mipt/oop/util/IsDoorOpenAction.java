package ru.sbt.mipt.oop.util;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Door;

public class IsDoorOpenAction implements Action {

    private final String id;
    private Boolean isOpen = null;

    public Boolean isOpen() {
        return isOpen;
    }

    public IsDoorOpenAction(String id) {
        this.id = id;
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Door door && door.getId().equals(id)) {
            isOpen = door.isOpen();
        }
    }

}
