//package ru.sbt.mipt.oop.events.actions;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.sbt.mipt.oop.Constants;
//import ru.sbt.mipt.oop.smart.devices.Alarm;
//import ru.sbt.mipt.oop.smart.devices.Door;
//import ru.sbt.mipt.oop.smart.devices.Light;
//import ru.sbt.mipt.oop.smart.home.Room;
//import ru.sbt.mipt.oop.smart.home.SmartHome;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AlarmTests {
//    SmartHome smartHome;
//    Alarm alarm;
//    Map<String, SmartDevice> devices = new HashMap<>();
//
//    @BeforeEach
//    void initialization() {
//        SmartDevice device_1 = new Door("1", false);
//        SmartDevice device_2 = new Door("2", false);
//        SmartDevice device_3 = new Light("3", false);
//        SmartDevice device_4 = new Light("4", false);
//
//        devices.put("1", device_1);
//        devices.put("2", device_2);
//        devices.put("3", device_3);
//        devices.put("4", device_4);
//
//        ArrayList<Room> rooms = new ArrayList<>();
//        rooms.add(new Room("hall", devices.values()));
//
//        alarm = new Alarm("99", Constants.ALARM_PASSWORD);
//        smartHome = new SmartHome(alarm, rooms);
//    }
//
//    @Test
//    void actionAlarmActive_whenCodeIsNull() {
//        // when
//        smartHome.execute(new ActionAlarmActivate(null));
//        boolean alarmActive = alarm.isActivated();
//        //then
//        assertFalse(alarmActive);
//    }
//
//    @Test
//    void actionAlarmActive_whenCodeIsInvalid() {
//        // when
//        smartHome.execute(new ActionAlarmActivate(Constants.ALARM_PASSWORD + "0"));
//        boolean alarmActive = alarm.isActivated();
//        //then
//        assertFalse(alarmActive);
//    }
//
//    @Test
//    void actionAlarmActive_whenCodeIsValid() {
//        // when
//        smartHome.execute(new ActionAlarmActivate(Constants.ALARM_PASSWORD));
//        boolean alarmActive = alarm.isActivated();
//        //then
//        assertTrue(alarmActive);
//    }
//
//    @Test
//    void actionAlarmActivate_whenAlarmAlert() {
//        // when
//        smartHome.execute(new ActionAlarmActivate(Constants.ALARM_PASSWORD));
//        smartHome.execute(new ActionLightOff(null));
//        smartHome.execute(new ActionAlarmActivate(Constants.ALARM_PASSWORD));
//        boolean alarmActivated = alarm.isActivated();
//        boolean alarmAlert = alarm.isAlert();
//        //then
//        assertTrue(alarmActivated);
//        assertFalse(alarmAlert);
//    }
//
//    @Test
//    void actionAlarmDeactivate_whenCodeIsNull() {
//        // when
//        smartHome.execute(new ActionAlarmDeactivate(null));
//        boolean alarmActive = alarm.isActivated();
//        //then
//        assertFalse(alarmActive);
//    }
//
//    @Test
//    void actionAlarmDeactivate_whenCodeIsInvalid() {
//        // when
//        smartHome.execute(new ActionAlarmDeactivate(Constants.ALARM_PASSWORD + "0"));
//        boolean alarmActive = alarm.isActivated();
//        //then
//        assertFalse(alarmActive);
//    }
//
//    @Test
//    void actionAlarmDeactivate_whenCodeIsValidAndAlarmIsDeactivated() {
//        // when
//        smartHome.execute(new ActionAlarmDeactivate(Constants.ALARM_PASSWORD));
//        boolean alarmActive = alarm.isActivated();
//        //then
//        assertFalse(alarmActive);
//    }
//
//    @Test
//    void actionAlarmDeactivate_whenCodeIsValidAndAlarmIsActivated() {
//        // when
//        smartHome.execute(new ActionAlarmActivate(Constants.ALARM_PASSWORD));
//        boolean alarmActive = alarm.isActivated();
//        smartHome.execute(new ActionAlarmDeactivate(Constants.ALARM_PASSWORD));
//        boolean alarmDeactive = !alarm.isActivated();
//        //then
//        assertTrue(alarmActive);
//        assertTrue(alarmDeactive);
//    }
//
//    @Test
//    void actionAlarmDeactivate_whenAlarmAlert() {
//        // when
//        smartHome.execute(new ActionAlarmActivate(Constants.ALARM_PASSWORD));
//        smartHome.execute(new ActionLightOff(null));
//        smartHome.execute(new ActionAlarmDeactivate(Constants.ALARM_PASSWORD));
//        boolean alarmDeactive = !alarm.isActivated();
//        //then
//        assertTrue(alarmDeactive);
//    }
//
//    @Test
//    void alert_whenAlarmIsActivated() {
//        // when
//        smartHome.execute(new ActionAlarmActivate(Constants.ALARM_PASSWORD));
//        smartHome.execute(new ActionLightOff(null));
//        boolean alarmAlert = alarm.isAlert();
//        // then
//        assertTrue(alarmAlert);
//    }
//
//    @Test
//    void alert_actionsNotExecutedWhenAlert() {
//        // when
//        smartHome.execute(new ActionAlarmActivate(Constants.ALARM_PASSWORD));
//        smartHome.execute(new ActionLightOn(null));
//        boolean light1IsOff = !((Light)devices.get("3")).isOn();
//        boolean light2IsOff = !((Light)devices.get("3")).isOn();
//        // then
//        assertTrue(light1IsOff);
//        assertTrue(light2IsOff);
//    }
//
//    @Test
//    void alert_actionsExecutedWhenNotAlert() {
//        // when
//        smartHome.execute(new ActionAlarmActivate(Constants.ALARM_PASSWORD));
//        smartHome.execute(new ActionLightOn(null));
//        boolean light1IsOff = !((Light)devices.get("3")).isOn();
//        boolean light2IsOff = !((Light)devices.get("3")).isOn();
//        smartHome.execute(new ActionAlarmDeactivate(Constants.ALARM_PASSWORD));
//        smartHome.execute(new ActionLightOn(null));
//        boolean light1IsOn = ((Light)devices.get("3")).isOn();
//        boolean light2IsOn = ((Light)devices.get("3")).isOn();
//        // then
//        assertTrue(light1IsOff);
//        assertTrue(light2IsOff);
//        assertTrue(light1IsOn);
//        assertTrue(light2IsOn);
//    }
//}