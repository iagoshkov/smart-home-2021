package ru.sbt.mipt.oop;

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
