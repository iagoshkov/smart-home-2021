package ru.sbt.mipt.oop;

/**
 * Общий интерфейс всех стратегий.
 */
public interface ActionStrategy {
    //SensorEvent event;
    public void act(ActionStrategy action); // выполнить действие (Action) над каждым элементом дома

    /*public ActionStrategy(SensorEvent event) {
        this.event = event;
    }*/

    /*public String getObjectId() {
        return event.getObjectId();
    }*/
}
