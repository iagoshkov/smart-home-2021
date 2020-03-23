package ru.sbt.mipt.oop.signaling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignalingTest {

    @Test
    void activateNewSignaling() {
        Signaling signaling = new Signaling();
        assertTrue(signaling.getStatus() instanceof DeactivatedStatus);
        String code = "123456";
        signaling.activateSignaling(code);
        assertTrue(signaling.getStatus() instanceof ActivatedStatus);
    }

    @Test
    void activateAlarmSignaling(){
        Signaling signaling = new Signaling();
        signaling.setStatus(new AlarmStatus(signaling, ""));
        signaling.activateSignaling("12345");
        assertTrue(signaling.getStatus() instanceof AlarmStatus);
    }

    @Test
    void correctDeactivateActivatedSignaling() {
        Signaling signaling = new Signaling();
        String code = "123456";
        signaling.activateSignaling(code);
        signaling.deactivateSignaling(code);
        assertTrue(signaling.getStatus() instanceof DeactivatedStatus);
    }

    @Test
    void incorrectDeactivateActivatedSignaling() {
        Signaling signaling = new Signaling();
        String code = "123456";
        String anotherCode = "abcd";
        signaling.activateSignaling(code);
        signaling.deactivateSignaling(anotherCode);
        assertTrue(signaling.getStatus() instanceof AlarmStatus);
    }

    @Test
    void deactivateDeactivatedSignaling() {
        Signaling signaling = new Signaling();
        assertTrue(signaling.getStatus() instanceof DeactivatedStatus);
        signaling.deactivateSignaling("abc");
        assertTrue(signaling.getStatus() instanceof DeactivatedStatus);
    }

    @Test
    void incorrectDeactivatedSignaling() {
        Signaling signaling = new Signaling();
        signaling.activateSignaling("abc");
        signaling.deactivateSignaling("abcd");
        assertTrue(signaling.getStatus() instanceof AlarmStatus);
    }

    @Test
    void correctDeactivateAlarmSignaling() {
        Signaling signaling = new Signaling();
        signaling.setStatus(new AlarmStatus(signaling, "abc"));
        signaling.turnOnAlarm();
        signaling.deactivateSignaling("abc");
        assertTrue(signaling.getStatus() instanceof DeactivatedStatus);
    }

    @Test
    void turnOnAlarm() {
        Signaling signaling = new Signaling();
        signaling.turnOnAlarm();
        assertTrue(signaling.getStatus() instanceof DeactivatedStatus);
    }
}