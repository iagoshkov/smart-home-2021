package ru.sbt.mipt.oop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.events.DoorEvent;
import ru.sbt.mipt.oop.handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.objects.home_objects.Door;
import ru.sbt.mipt.oop.objects.home_objects.Light;
import ru.sbt.mipt.oop.objects.home_objects.actionable.Room;
import ru.sbt.mipt.oop.objects.home_objects.actionable.SmartHome;
import ru.sbt.mipt.oop.types.DoorEventType;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class HallDoorEventHandlerTest {
  
    @Mock
    SmartHome smartHome;
  
    @Mock
    DoorEvent doorEvent;
  
    @Mock
    Room room;

    @InjectMocks
    HallDoorEventHandler handler;
  
    @InjectMocks
    Door door = new Door(true, "0");
  
    @InjectMocks
    Light light = new Light("10", true);

    @Test
    public void turnOff() {
        handler = new HallDoorEventHandler(smartHome);
        handler.handleEvent(doorEvent);
        Mockito.when(doorEvent.getObjectId()).thenReturn(door.getId());
        Mockito.when(doorEvent.getDoorEventType()).thenReturn(DoorEventType.DOOR_CLOSED);
        Mockito.when(room.getId()).thenReturn("hall");
        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            ((Action)args[0]).accept(room);
            ((Action)args[0]).accept(door);
            ((Action)args[0]).accept(light);
            return null;
        }).when(smartHome).execute(Mockito.any(Action.class));
        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            ((Action)args[0]).accept(light);
            ((Action)args[0]).accept(door);
            return null;
        }).when(room).execute(Mockito.any(Action.class));
        handler.handleEvent(doorEvent);
        assertFalse(light.isOn());
    }
}
