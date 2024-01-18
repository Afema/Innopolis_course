package ru.inno.course.homework2;

public class Room {
    private String roomName;
    private int roomArea;
    private int length;
    private int width;

    public Room(String roomName, int length, int width) {
        this.roomName = roomName;
        this.length = length;
        this.width = width;
    }

    public int getRoomArea() {
        return this.roomArea = length * width;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
