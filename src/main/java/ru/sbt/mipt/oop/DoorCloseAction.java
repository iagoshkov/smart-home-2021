package ru.sbt.mipt.oop;

public class DoorCloseAction implements Action {

    @Override
    public void apply(Object obj) {
        if (obj instanceof Door door) {
            door.setOpen(false);
        }
    }

}
