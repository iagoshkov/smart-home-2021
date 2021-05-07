  
package ru.sbt.mipt.oop.Application;
import com.coolcompany.SmartHome.Event.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.MyConfiguration;

public class Application {
    public static void main(String args) {
        AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfiguration.class);
        SensorEventsManager sensorEventsManager = applicationContext.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }
}
