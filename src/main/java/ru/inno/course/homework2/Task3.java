package ru.inno.course.homework2;


public class Task3 {
    public static void main(String[] args) {
        Item item1 = new Item("toy1", "art.789-001", 200.55, 3);
        Item item2 = new Item("toy2", "art.789-002", 1000.55, 4);
        Item item3 = new Item("toy3", "art.789-003", 345.00, 6);
        Item item4 = new Item("toy4", "art.789-004", 12.80, 100);
        Item item5 = new Item("toy5", "art.789-005", 500.29, 23);
        item1.setColor("");
        item2.setColor("green");
        item3.setColor("blue");
        item4.setColor("orange");
        item5.setColor("purple");
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(item3);
        System.out.println(item4);
        System.out.println(item5);
    }
}
