package ru.sbt.mipt.oop;

public class OpenCloseDoorAction implements Action {

    private final String id;
    private final boolean open;
    private boolean successful = false;

    public boolean isSuccessful() {
        return successful;
    }

    public OpenCloseDoorAction(String id, boolean open) {
        this.id = id;
        this.open = open;
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Door door) {
            if (door.getId().equals(id)) {
                door.setOpen(open);
                successful = true;
            }
        }
    }
}
