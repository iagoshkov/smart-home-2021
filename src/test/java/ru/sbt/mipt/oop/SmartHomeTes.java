package ru.sbt.mipt.oop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Collections;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.Room;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.SmartHome;

@RunWith(MockitoJUnitRunner.class)
public class SmartHomeTes {
  
    @Mock
    Action action;
  
    @Mock
    Room room;

    @InjectMocks
    SmartHome smartHome;

    @Test
    public void execute() {
        smartHome = new SmartHome(new ArrayList<>(Collections.singletonList(room)));
        smartHome.execute(action);
    }
}
