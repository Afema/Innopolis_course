package ru.inno.course.homework6.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Copier {
    public static void copyTextFile(String sourcePath, String receiverPath) throws IOException {
        Files.writeString(Path.of(receiverPath), Files.readAllLines(Path.of(sourcePath)).toString());
    }
}
