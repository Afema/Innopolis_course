package ru.inno.course.lesson6.validator;

public class PasswordValidator implements Validator{

    private String message;
    private String password;

    public PasswordValidator(String message, String password) {
        this.message = message;
        this.password = password;
    }

    public PasswordValidator(String password) {
        this.message = "validation failed";
        this.password = password;
    }

    @Override
    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    public boolean validate(String str) {
        if (password.equals(str)) {
            return true;
        }
        System.out.println(this.message);
        return false;
    }
}
