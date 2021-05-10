package ru.sbt.mipt.oop.Objects.RemoteControl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Objects.HomeObjects.Door;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.*;
import static org.junit.jupiter.api.Assertions.*;

class CloseTheHallDoorTest {
    @Mock
    SmartHome smartHome = Mockito.mock(SmartHome.class);
    @Mock
    Room room = Mockito.mock(Room.class);

    @InjectMocks
    CloseTheHallDoorCommand closeTheHallDoorCommand = new CloseTheHallDoorCommand(smartHome);

    @Test
    void executeTest() {
        Door door = new Door(true, "0");

        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            ((Action)args[0]).accept(door);
            ((Action)args[0]).accept(room);
            return null;
        }).when(smartHome).execute(Mockito.any(Action.class));
        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            ((Action)args[0]).accept(door);
            return null;
        }).when(room).execute(Mockito.any(Action.class));
        Mockito.doAnswer((Answer<String>) invocation -> "hall").when(room).getId();

        closeTheHallDoorCommand.execute();
        assertFalse(door.isOpen());
    }
}
