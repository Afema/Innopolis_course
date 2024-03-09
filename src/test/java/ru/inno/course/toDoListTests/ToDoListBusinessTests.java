package ru.inno.course.toDoListTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.inno.course.toDoListTests.model.Task;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ToDoListBusinessTests {

    public final String URL = "https://todo-app-sky.herokuapp.com/";
    private ToDoListService service;

    @BeforeEach
    public void setUp() {
        service = new ToDoListService(URL);
    }

    @Test
    @DisplayName("Создать первое дело")
    public void createFirstTask() throws IOException {
        service.deleteAllTask();
        Task newTask = service.createNewTask("wash dishes");
        List<Task> list = service.getToDoList();
        assertTrue(list.contains(newTask));
    }

    @Test
    @DisplayName("Создать несколько дел")
    public void createSomeTask() throws IOException {
        service.deleteAllTask();
        Task newTask1 = service.createNewTask("wash dishes");
        Task newTask2 = service.createNewTask("do homework");
        List<Task> list = service.getToDoList();
        assertTrue(list.contains(newTask1));
        assertTrue(list.contains(newTask2));
    }

    @Test
    @DisplayName("Создать дело с уже существующим названием")
    public void createTaskWithSameName() throws IOException {
        service.deleteAllTask();
        Task newTask1 = service.createNewTask("wash dishes");
        Task newTask2 = service.createNewTask("wash dishes");
        List<Task> list = service.getToDoList();
        assertTrue(list.contains(newTask1));
        assertTrue(list.contains(newTask2));
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Создать дело с пустым названием")
    public void createTaskWithEmptyName() throws IOException {
        service.deleteAllTask();
        Task newTask1 = service.createNewTask("");
        List<Task> list = service.getToDoList();
        assertTrue(list.contains(newTask1));
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Создать дело с title is null")
    public void createTaskWithNull() throws IOException {
        service.deleteAllTask();
        Task newTask1 = service.createNewTask(null);
        List<Task> list = service.getToDoList();
        assertTrue(list.contains(newTask1));
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Изменить невыполненное дело")
    public void updateNotCompletedTask() throws IOException {
        service.deleteAllTask();
        Task newTask1 = service.createNewTask("buy apple");
        List<Task> list = service.getToDoList();
        int id = newTask1.getId();
        assertTrue(list.contains(newTask1));
        assertEquals(1, list.size());
        service.updateTask(id, "sell pear");
        assertEquals(1, list.size());
        List<Task> listAfterUpdate = service.getToDoList();
        assertEquals("sell pear", listAfterUpdate.get(0).getTitle());
    }

    @Test
    @DisplayName("Изменить выполненное дело")
    public void updateCompletedTask() throws IOException {
        service.deleteAllTask();
        Task newTask1 = service.createNewTask("buy apple");
        List<Task> list = service.getToDoList();
        int id = newTask1.getId();
        assertTrue(list.contains(newTask1));
        assertEquals(1, list.size());

        service.completeTask(id);
        service.updateTask(id, "sell pear");
        assertEquals(1, list.size());
        List<Task> listAfterUpdate = service.getToDoList();
        assertEquals("sell pear", listAfterUpdate.get(0).getTitle());
        assertTrue(listAfterUpdate.get(0).isCompleted());
    }

    @Test
    @DisplayName("Отметить дело как выполненное")
    public void completeTask() throws IOException {
        service.deleteAllTask();
        Task newTask1 = service.createNewTask("buy apple");
        List<Task> list = service.getToDoList();
        int id = newTask1.getId();
        assertTrue(list.contains(newTask1));
        assertEquals(1, list.size());
        service.completeTask(id);
        assertEquals(1, list.size());
        List<Task> listAfterComplete = service.getToDoList();
        assertTrue(listAfterComplete.get(0).isCompleted());
    }

    @Test
    @DisplayName("Отметить дело как выполненное дважды")
    public void completeTaskTwice() throws IOException {
        service.deleteAllTask();
        Task newTask1 = service.createNewTask("buy apple");
        List<Task> list = service.getToDoList();
        int id = newTask1.getId();
        assertTrue(list.contains(newTask1));
        assertEquals(1, list.size());
        service.completeTask(id);
        service.completeTask(id);
        assertEquals(1, list.size());
        List<Task> listAfterComplete = service.getToDoList();
        assertTrue(listAfterComplete.get(0).isCompleted());
    }

    @Test
    @DisplayName("Удалить выполненное дело")
    public void deleteCompletedTask() throws IOException {
        service.deleteAllTask();
        Task newTask = service.createNewTask("wash dishes");
        List<Task> list = service.getToDoList();
        int id = newTask.getId();
        service.deleteOneTask(id);
        List<Task> listAterDelete = service.getToDoList();
        assertFalse(listAterDelete.contains(newTask));
    }

    @Test
    @DisplayName("Удалить невыполненное дело")
    public void deleteNotCompletedTask() throws IOException {
        service.deleteAllTask();
        Task newTask = service.createNewTask("wash dishes");
        List<Task> list = service.getToDoList();
        int id = newTask.getId();
        service.deleteOneTask(id);
        List<Task> listAterDelete = service.getToDoList();
        assertFalse(listAterDelete.contains(newTask));
    }

    @Test
    @DisplayName("Удалить все дела из списка дел")
    public void deleteAllTasks() throws IOException {
        service.deleteAllTask();
        Task newTask1 = service.createNewTask("wash dishes");
        Task newTask2 = service.createNewTask("do something");
        Task newTask3 = service.createNewTask("sing something");
        List<Task> list = service.getToDoList();
        assertEquals(3, list.size());
        service.deleteAllTask();
        List<Task> listAterDelete = service.getToDoList();
        assertEquals(0, listAterDelete.size());
    }

    @AfterEach
    public void clearAfterTests() throws IOException {
        service.deleteAllTask();
    }
}