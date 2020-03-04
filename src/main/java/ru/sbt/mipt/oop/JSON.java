package ru.sbt.mipt.oop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSON {
    private String jsonStr;

    public JSON(){
        jsonStr = "";
    }

    public JSON(String addr) throws IOException {
        jsonStr = new String(Files.readAllBytes(Paths.get(addr)));
    }

    public String getJSON() {
        return jsonStr;
    }
    public void setJSON(String addr) throws IOException {
        jsonStr = new String(Files.readAllBytes(Paths.get(addr)));
    }
}
