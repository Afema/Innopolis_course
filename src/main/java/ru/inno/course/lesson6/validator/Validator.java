package ru.inno.course.lesson6.validator;

public interface Validator {
    boolean validate(String str);
    void setMessage(String msg);
}
