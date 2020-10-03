package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface HomeReader {
    SmartHome homeReader(String file) throws IOException;
}
