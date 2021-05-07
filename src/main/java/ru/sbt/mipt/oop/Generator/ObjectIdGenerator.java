  
package ru.sbt.mipt.oop.Generator;

public class ObjectIdGenerator {
    public String Generate() {
        return "" + (int) (10 * Math.random());
    }
}
