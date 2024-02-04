package ru.inno.course.lesson6.validator;

public class MobileNumberValidator implements Validator{
    private String number;
    private String message;

    public MobileNumberValidator(String number, String message) {
        this.number = number;
        this.message = message;
    }

    public MobileNumberValidator(String number) {
        this.number = number;
        this.message = "validation failed";
    }

    @Override
    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    public boolean validate(String str) {
        if (str.indexOf('+') >= 0) {
            return true;
        } else {
            System.out.println(this.message);
            return false;
        }
    }
}
