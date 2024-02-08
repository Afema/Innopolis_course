package ru.inno.course.homework6.task2;


import java.time.LocalDateTime;

public interface HumanReadableTimestamp {
    String getTimestamp(LocalDateTime eventTimestamp);
}