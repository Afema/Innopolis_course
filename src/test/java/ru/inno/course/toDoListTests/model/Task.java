package ru.inno.course.toDoListTests.model;

import java.util.Objects;


public class Task {
    private int id;
    private String title;
    private boolean completed;
    private String order;
    private String url;

    public Task() {
    }

    public Task(int id, String title, boolean completed, String order, String url) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.order = order;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getOrder() {
        return order;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && completed == task.completed && Objects.equals(title, task.title) && Objects.equals(order, task.order) && Objects.equals(url, task.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, completed, order, url);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", order='" + order + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
