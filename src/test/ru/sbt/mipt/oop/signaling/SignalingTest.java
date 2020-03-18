package ru.sbt.mipt.oop.signaling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignalingTest {

    @Test
    void activateNewSignaling() throws ActivationException {
        Signaling signaling = new Signaling();
        assertTrue(signaling.getStatus() instanceof DeactivatedStatus);
        String code = "123456";
        signaling.activateSignaling(code);
        assertTrue(signaling.getStatus() instanceof ActivatedStatus);
        assertEquals(signaling.getCode(), code);
    }

    @Test
    void activateActivatedSignaling() throws ActivationException {
        Signaling signaling = new Signaling();
        String code = "123456";
        signaling.activateSignaling(code);
        assertThrows(ActivationException.class, () -> signaling.activateSignaling(code));
    }

    @Test
    void activateAlarmSignaling() throws ActivationException {
        Signaling signaling = new Signaling();
        signaling.setStatus(new AlarmStatus(signaling));
        signaling.activateSignaling("12345");
        assertTrue(signaling.getStatus() instanceof AlarmStatus);
    }

    @Test
    void correctDeactivateActivatedSignaling() throws ActivationException {
        Signaling signaling = new Signaling();
        String code = "123456";
        signaling.activateSignaling(code);
        signaling.deactivateSignaling(code);
        assertTrue(signaling.getStatus() instanceof DeactivatedStatus);
    }

    @Test
    void incorrectDeactivateActivatedSignaling() throws ActivationException {
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
    void incorrectDeactivateAlarmSignaling() {
        Signaling signaling = new Signaling();
        signaling.turnOnAlarm();
        signaling.deactivateSignaling("abc");
        assertTrue(signaling.getStatus() instanceof AlarmStatus);
    }

    @Test
    void correctDeactivateAlarmSignaling() {
        Signaling signaling = new Signaling();
        signaling.setCode("abc");
        signaling.turnOnAlarm();
        signaling.deactivateSignaling("abc");
        assertTrue(signaling.getStatus() instanceof DeactivatedStatus);
    }

    @Test
    void turnOnAlarm() {
        Signaling signaling = new Signaling();
        signaling.turnOnAlarm();
        assertTrue(signaling.getStatus() instanceof AlarmStatus);
    }
}