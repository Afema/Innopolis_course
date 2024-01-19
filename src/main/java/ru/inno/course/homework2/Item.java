package ru.inno.course.homework2;

public class Item {
    private String name;
    private String article;
    private double cost;
    private int amount;
    private String color;

    public Item(String name, String article, double cost, int amount) {
        this.name = name;
        this.article = article;
        this.cost = cost;
        this.amount = amount;
        this.color = "unknown";
    }

    public String getName() {
        return name;
    }

    public String getArticle() {
        return article;
    }

    public double getCost() {
        return cost;
    }

    public int getAmount() {
        return amount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        if(color.equals("unknown") || color.isEmpty()){
            return article + " - " + name + " - " + cost + " - " + amount;
        } else {
            return article + " - " + name + " - " + cost + " - " + amount + " - " + color;
        }
    }
}
