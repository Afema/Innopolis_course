package ru.inno.course.toDoListTests;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListContractTests {
    public static final String URL = "https://todo-app-sky.herokuapp.com/";
    private HttpClient client;

    @BeforeEach
    public void setUp() {
        client = HttpClientBuilder
                .create()
                .build();
    }

    @Test
    @DisplayName("Получить список всех дел. Проверить статус-код, content-type")
    public void getToDoList() throws IOException {
        HttpGet getRequest = new HttpGet(URL);
        HttpResponse response = client.execute(getRequest);
        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getFirstHeader("content-type").getValue());
    }

    @Test
    @DisplayName("Создать новое дело. Проверить статус-код, контент-тип, тело ответа")
    public void createNewTask() throws IOException {
        String requestBody = "{\"title\": \"buy apple\",\"completed\": false, \"order\": null}";
        HttpPost createTask = new HttpPost(URL);
        createTask.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
        HttpResponse response = client.execute(createTask);
        String responseBody = EntityUtils.toString(response.getEntity());
        String id = responseBody.substring(6, 12);

        HttpGet getRequest = new HttpGet(URL + id);
        HttpResponse newTask = client.execute(getRequest);
        String newTaskBody = EntityUtils.toString(newTask.getEntity());
        assertEquals(201, response.getStatusLine().getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getFirstHeader("content-type").getValue());
        assertTrue(newTaskBody.contains("buy apple"));
        assertEquals(responseBody, newTaskBody);
    }

    @Test
    @Tag("negative")
    @DisplayName("Создать новое дело c невалидным телом запроса. Проверить статус-код")
    public void createInvalidTask() throws IOException {
        String requestBody = "{null}";
        HttpPost createTask = new HttpPost(URL);
        createTask.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
        HttpResponse response = client.execute(createTask);
        assertEquals(400, response.getStatusLine().getStatusCode());
    }

    @Test
    @DisplayName("Изменить сущесвующее дело. Проверить статус-код, тело после апдейта")
    public void updateTask() throws IOException {
        String requestBody = "{\"title\": \"buy apple\",\"completed\": false, \"order\": null}";
        HttpPost createTask = new HttpPost(URL);
        createTask.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
        HttpResponse response = client.execute(createTask);
        String responseBody = EntityUtils.toString(response.getEntity());
        String id = responseBody.substring(6, 12);

        String newRequestBody = "{\"title\": \"sell toys\",\"completed\": \"false\", \"order\": null}";
        HttpPatch updateTask = new HttpPatch(URL + id);
        updateTask.setEntity(new StringEntity(newRequestBody, ContentType.APPLICATION_JSON));
        HttpResponse responseAfterUpdate = client.execute(updateTask);

        HttpGet getRequest = new HttpGet(URL + id);
        HttpResponse updatedTask = client.execute(getRequest);
        String updatedTaskBody = EntityUtils.toString(updatedTask.getEntity());

        assertEquals(200, responseAfterUpdate.getStatusLine().getStatusCode());
        assertTrue(updatedTaskBody.contains("sell toys"));
    }

    @Test
    @DisplayName("Отметить дело как выполненное. Проверить статус-код, тело после апдейта")
    public void completeTask() throws IOException {
        String requestBody = "{\"title\": \"buy apple\",\"completed\": false, \"order\": null}";
        HttpPost createTask = new HttpPost(URL);
        createTask.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
        HttpResponse response = client.execute(createTask);
        String responseBody = EntityUtils.toString(response.getEntity());
        String id = responseBody.substring(6, 12);

        String newRequestBody = "{\"title\": \"buy apple\",\"completed\": true, \"order\": null}";
        HttpPatch updateTask = new HttpPatch(URL + id);
        updateTask.setEntity(new StringEntity(newRequestBody, ContentType.APPLICATION_JSON));
        HttpResponse responseAfterUpdate = client.execute(updateTask);

        HttpGet getRequest = new HttpGet(URL + id);
        HttpResponse updatedTask = client.execute(getRequest);
        String updatedTaskBody = EntityUtils.toString(updatedTask.getEntity());

        assertEquals(200, responseAfterUpdate.getStatusLine().getStatusCode());
        assertTrue(updatedTaskBody.contains("true"));
    }

    @Test
    @DisplayName("Удалить дело. Проверить статус-код, отсутствие удаленного дела")
    public void deleteTask() throws IOException {
        String requestBody = "{\"title\": \"buy apple\",\"completed\": false, \"order\": null}";
        HttpPost createTask = new HttpPost(URL);
        createTask.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
        HttpResponse response = client.execute(createTask);
        String responseBody = EntityUtils.toString(response.getEntity());
        String id = responseBody.substring(6, 12);

        HttpDelete deleteRequest = new HttpDelete(URL + id);
        HttpResponse deletedResponse = client.execute(deleteRequest);
        assertEquals(204, deletedResponse.getStatusLine().getStatusCode());

        HttpGet getRequest = new HttpGet(URL + id);
        HttpResponse responseAfterDelete = client.execute(getRequest);
        String responseBodyAfterDelete = EntityUtils.toString(responseAfterDelete.getEntity());
        assertFalse(responseBodyAfterDelete.contains("buy apple"));
    }
}
