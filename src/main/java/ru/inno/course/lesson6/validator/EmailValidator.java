package ru.inno.course.lesson6.validator;

public class EmailValidator implements Validator {
    private String email;
    private String message;

    public EmailValidator(String email, String message) {
        this.email = email;
        this.message = message;
    }

    public EmailValidator(String email) {
        this.email = email;
        this.message = "validation failed";
    }

    @Override
    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    public boolean validate(String str) {
        if (str.indexOf('@') >= 0) {
            return true;
        } else {
            System.out.println(this.message);
            return false;
        }
    }
}