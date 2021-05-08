  
package ru.sbt.mipt.oop;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.junit.runner.RunWith;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Event.*;
import ru.sbt.mipt.oop.Type.*;
import ru.sbt.mipt.oop.Handler.*;
import ru.sbt.mipt.oop.Objects.HomeObjects.*;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.SmartHome;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class DoorEventHandlerTest {
  
    @Mock
    SmartHome smartHome;
  
    @Mock
    DoorEvent doorEvent;
  
    @Mock
    LightEvent lightEvent;

    @InjectMocks
    DoorEventHandler handler;
  
    @InjectMocks
    LightEventHandler handler;
  
    @InjectMocks
    Door door = new Door(true, "0");

    @InjectMocks
    Light light = new Light("0", true);

    @Test
    public void closeTheDoor() {
        handler = new DoorEventHandler(smartHome);
        handler.handleEvent(doorEvent);
        Mockito.when(doorEvent.getObjectId()).thenReturn(door.getId());
        Mockito.when(doorEvent.getDoorEventType()).thenReturn(DoorEventType.DOOR_CLOSED);
        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            ((Action) args[0]).accept(door);
            return null;
        }).when(smartHome).execute(Mockito.any(Action.class));
        handler.handleEvent(doorEvent);
        assertFalse(door.isOpen());
    }

    @Test
    public void openTheDoor() {
        handler = new DoorEventHandler(smartHome);
        handler.handleEvent(doorEvent);
        Mockito.when(doorEvent.getObjectId()).thenReturn(door.getId());
        Mockito.when(doorEvent.getDoorEventType()).thenReturn(DoorEventType.DOOR_OPEN);
        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            ((Action) args[0]).accept(door);
            return null;
        }).when(smartHome).execute(Mockito.any(Action.class));
        handler.handleEvent(doorEvent);
        assertTrue(door.isOpen());
    }
  
      @Test
    public void turnTheLightOff() {
        handler = new LightEventHandler(smartHome);
        handler.handleEvent(lightEvent);
        Mockito.when(lightEvent.getObjectId()).thenReturn(light.getId());
        Mockito.when(lightEvent.getLightEventType()).thenReturn(LightEventType.LIGHT_OFF);
        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            ((Action)args[0]).accept(light);
            return null;
        }).when(smartHome).execute(Mockito.any(Action.class));
        handler.handleEvent(lightEvent);
        assertFalse(light.isOn());
    }

    @Test
    public void turnTheLightOn() {
        handler = new LightEventHandler(smartHome);
        handler.handleEvent(lightEvent);
        Mockito.when(lightEvent.getObjectId()).thenReturn(light.getId());
        Mockito.when(lightEvent.getLightEventType()).thenReturn(LightEventType.LIGHT_ON);
        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            ((Action)args[0]).accept(light);
            return null;
        }).when(smartHome).execute(Mockito.any(Action.class));
        handler.handleEvent(lightEvent);
        assertTrue(light.isOn());
    }
  
}
