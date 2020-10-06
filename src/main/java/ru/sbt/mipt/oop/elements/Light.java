package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.typedefs.HallDoorEventType;
import ru.sbt.mipt.oop.events.typedefs.LightEventType;

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

    public void setOn(boolean on) {
        isOn = on;
        System.out.println("Light " + id + " was turned " + (on ? "on" : "off"));
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
    public Event apply(Event event, Action action) {
        if ((event.getType() instanceof HallDoorEventType) || ((event.getType() instanceof LightEventType) && event.getObjectId().equals(this.id))) {
            action.accept(this);
        }
        return event;
    }
}
