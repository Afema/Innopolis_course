package ru.inno.course.lesson6.validator;

public class MyProgram {
    public static void main(String[] args) {
        Validator v = new PasswordValidator("123456");
        //v.setMessage("warning, invalid password");
        boolean res = v.validate("123457");

        Validator v1 = new EmailValidator("afema13@gmail.com");
        //v1.setMessage("warning, invalid email");
        boolean res1 = v1.validate("afema13gmail.com");

        Validator v2 = new MobileNumberValidator("+7896222123");
        //v2.setMessage("warning, invalid mobile number");
        boolean res2 = v2.validate("225896666");
    }
}
