package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.elements.alarm.AlarmState;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.processors.EventProcessorType;
import ru.sbt.mipt.oop.events.typedefs.AlarmEventType;
import ru.sbt.mipt.oop.events.typedefs.HallDoorEventType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SmartHome implements HomeComponent, HomeComponentComposite {
    private Collection<Room> rooms;
    private AlarmSystem alarmSystem;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }


    @Override
    public void addHomeComponent(ElementType type, HomeComponent component) {
        if (type == HomeElementType.ROOM) {
            rooms.add((Room) component);
        } else if (type == HomeElementType.ALARM) {
            alarmSystem = (AlarmSystem) component;
        }
    }

    @Override
    public Collection<? extends HomeComponent> getComponents(ElementType type) {
        if (type == HomeElementType.ROOM) {
            return rooms;
        }
        return rooms.stream()
                .map((HomeComponent r) -> ((Room)r).getComponents(type))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public HomeComponent getComponent(ElementType type, ComponentId id) {
        if (type == HomeElementType.ROOM) {
            return rooms.stream()
                    .filter((HomeComponent c) -> (c.getId().equals(id)))
                    .findFirst()
                    .orElse(null);
        }
        return getComponents(type).stream()
                .filter((HomeComponent c) -> (c.getId().equals(id)))
                .findFirst()
                .orElse(null);
    }

    public int getElementCount(ElementType type) {
        if (type == HomeElementType.ROOM) {
            return rooms.size();
        } else {
            return rooms.stream().mapToInt((Room r) -> r.getElementCount(type)).reduce(Integer::sum).orElse(0);
        }
    }

    @Override
    public ElementType getType() {
        return HomeElementType.SMART_HOME;
    }

    @Override
    public ComponentId getId() {
        return new StringId("HOME");
    }

    public Event apply(Event event, Action action) {
        if (event.getType().getProcessorType() == EventProcessorType.ALARM) {
            Event newEvent = alarmSystem.apply(event, action);
            return newEvent;
        } else {
            if (alarmSystem.getAlarmState() != AlarmState.WARNING) {
                if (alarmSystem.getAlarmState() != AlarmState.ACTIVATED) {
                    return processRoomEvent(event, action);
                } else {
                    alarmSystem.warn();
                    sendSms("!!! WARNING !!!");
                }
            } else {
                sendSms("!!! WARNING !!!");
            }
            return event;
        }
    }

    private Event processRoomEvent(Event event, Action action) {
        List<Event> newEvents = rooms.stream()
                .map((Room r) -> {
                    return r.apply(event, action);
                })
                .filter((Event e) -> (e.getType() instanceof HallDoorEventType || e.getType() == AlarmEventType.ALARM_WARNING)).collect(Collectors.toList());
        if (!newEvents.isEmpty()) {
            return newEvents.get(0);
        }
        return event;
    }

    private void sendSms(String sms) {
        System.out.println(String.format("Sending sms: %s", sms));
    }
}
