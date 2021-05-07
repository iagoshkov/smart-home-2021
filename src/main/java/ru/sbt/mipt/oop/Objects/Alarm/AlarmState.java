  
package ru.sbt.mipt.oop.Objects.Alarm;

public interface AlarmState {
    AlarmState activate(String code);
    AlarmState deactivate(String code);
    AlarmState alert();
}
