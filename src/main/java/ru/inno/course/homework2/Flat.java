package ru.inno.course.homework2;

public class Flat {
    private int roomAmount;
    private boolean hasBalcony;
    private String address;
    private int floor;
    private Room room;

    public Flat(int roomAmount, String address, int floor, Room room) {
        this.roomAmount = roomAmount;
        this.hasBalcony = false;
        this.address = address;
        this.floor = floor;
        this.room = room;
    }

    public int getRoomAmount() {
        return roomAmount;
    }

    public boolean hasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public String getAddress() {
        return address;
    }

    public int getFloor() {
        return floor;
    }

    public Room getRoom() {
        return room;
    }
}
