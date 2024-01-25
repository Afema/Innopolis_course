package ru.inno.course.homework4.methods.task1;

public class Card {
    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private String pinCode;


    public Card(String cardNumber, String expirationDate, String cvv, String pinCode) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.pinCode = pinCode;
    }

    public void getMaskedCardNumber() {
        System.out.println("**** **** **** " + cardNumber.substring(cardNumber.length() - 4));
    }

    public void getCardNumber(String pinCode) {
        if (this.pinCode.equals(pinCode)) {
            System.out.println(this.cardNumber);
        } else {
            System.out.println("Пинкод введен неверно");
            getMaskedCardNumber();
        }
    }
}
