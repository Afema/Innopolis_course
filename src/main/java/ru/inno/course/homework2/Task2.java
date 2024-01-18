package ru.inno.course.homework2;

public class Task2 {
    public static void main(String[] args) {
        Room room1 = new Room("kitchen", 6, 3);
        System.out.println("Room: " + room1.getRoomName() + " " + room1.getRoomArea());

        Flat flat1 = new Flat(3, "address1", 11, room1);
        flat1.setHasBalcony(true);
        System.out.println("Flat: " + flat1.getAddress() + " " + flat1.hasBalcony() + " " + flat1.getFloor() + " " + flat1.getRoomAmount() + " " + flat1.getRoom().getRoomName());
    }
}
