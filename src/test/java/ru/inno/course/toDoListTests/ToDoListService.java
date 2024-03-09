package ru.inno.course.toDoListTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.inno.course.toDoListTests.model.Task;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListService {
    private HttpClient client;

    public final String URL;

    public ToDoListService(String url) {
        this.client = HttpClientBuilder.create().build();
        this.URL = url;
    }

    public List<Task> getToDoList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpGet getRequest = new HttpGet(URL);
        HttpResponse taskList = client.execute(getRequest);
        String responseBody = EntityUtils.toString(taskList.getEntity());
        return mapper.readValue(responseBody, new TypeReference<>() {
        });
    }


    public Task createNewTask(String title) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "{\"title\":\"" + title + "\"}";
        HttpPost createTask = new HttpPost(URL);
        createTask.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
        HttpResponse response = client.execute(createTask);
        String responseBody = EntityUtils.toString(response.getEntity());
        return mapper.readValue(responseBody, Task.class);
    }


    public Task updateTask(int id, String newTitle) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newRequestBody = "{\"title\": \"" + newTitle + "\"}";
        HttpPatch updateTask = new HttpPatch(URL + id);
        updateTask.setEntity(new StringEntity(newRequestBody, ContentType.APPLICATION_JSON));
        HttpResponse responseAfterUpdate = client.execute(updateTask);
        String responseBodyAfterUpdate = EntityUtils.toString(responseAfterUpdate.getEntity());
        return mapper.readValue(responseBodyAfterUpdate, Task.class);
    }

    public Task completeTask(int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newRequestBody = "{\"completed\": true}";
        HttpPatch updateTask = new HttpPatch(URL + id);
        updateTask.setEntity(new StringEntity(newRequestBody, ContentType.APPLICATION_JSON));
        HttpResponse responseAfterUpdate = client.execute(updateTask);
        String responseBodyAfterComplete = EntityUtils.toString(responseAfterUpdate.getEntity());
        return mapper.readValue(responseBodyAfterComplete, Task.class);
    }


    public void deleteOneTask(int id) throws IOException {
        HttpDelete deleteRequest = new HttpDelete(URL + id);
        HttpResponse deletedResponse = client.execute(deleteRequest);
    }

    public void deleteAllTask() throws IOException {
        HttpDelete deleteRequest = new HttpDelete(URL);
        HttpResponse deletedResponse = client.execute(deleteRequest);
        assertEquals(204, deletedResponse.getStatusLine().getStatusCode());
    }
}
