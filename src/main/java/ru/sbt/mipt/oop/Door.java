package ru.sbt.mipt.oop;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void execute(Action action) {
    }

    /*@Override
    public void act(ActionStrategy action) {
        if (action.getObjectId().equals(id)) {
            System.out.println("Alo!");
        }
    }*/
}
