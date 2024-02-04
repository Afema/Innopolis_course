package ru.inno.course.homework5.task7;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class MyCalculator implements Calculator {
    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mul(int a, int b) {
        return a * b;
    }

    @Override
    public double div(int a, int b) {
        return (a * 1.0) / (b * 1.0);
    }

    @Override
    public double avg(Collection<Integer> nums) {
        return nums.stream()
                .mapToInt(a -> a)
                .average().orElse(0);
    }

    @Override
    public int min(Collection<Integer> nums) {
        return nums.stream()
                .mapToInt(v -> v)
                .min().orElse(0);
    }

    @Override
    public int max(Collection<Integer> nums) {
        return nums.stream()
                .mapToInt(v -> v)
                .max().orElse(0);
    }
}
