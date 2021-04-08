import org.junit.Test;


import static org.junit.Assert.*;

public class TestForDoor {

    @Test
    public void testExecute() {

        Door door = new Door(true, "halldoor");
        door.execute(component -> {

            Door currentDoor = (Door) component;
            currentDoor.setOpen(false);
        });
        assertFalse(door.isOpen());
    }
}
