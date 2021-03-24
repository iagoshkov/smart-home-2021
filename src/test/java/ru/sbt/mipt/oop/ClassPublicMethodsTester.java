package ru.sbt.mipt.oop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassPublicMethodsTester {
    public static void testClassPublicMethods(Object testClass) throws IllegalAccessException, InvocationTargetException {
        Class<?> clazz = testClass.getClass();
        for (Method test : clazz.getDeclaredMethods()) {
            if (test.getName().contains("Test")) {
                System.out.println("\nTesting: " + clazz.getName() + "." + test.getName() + "\n");
                test.invoke(testClass);
            }
        }
    }
}