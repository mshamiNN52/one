package managers.Http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import managers.FileBackedTasksManager;
import managers.InMemoryTaskManager;
import managers.Manager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.TaskStatus;
import tasks.TaskType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HttpTaskServerTwoTest {
    KVServer server;

    Task task = new Task(1, "test", "test1", TaskStatus.NEW, TaskType.TASK);
    InMemoryTaskManager taskManager;
    private HttpTaskServerTwo serverTwo;

    private Gson gson = Manager.getGson();
    Manager manager = new Manager();
    HttpTaskManager httpTaskManager = new HttpTaskManager();


    @BeforeEach
    void init() throws IOException {
        server = new KVServer();
        serverTwo = new HttpTaskServerTwo();
        server.start();
//       manager.getDefaultManager();
        serverTwo.start();
    }

    @AfterEach
    void tarDown() {
        serverTwo.stop();
    }


    @Test
    void handleTasksGET() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI url = URI.create("http://localhost:8080/tasks/task/");
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .uri(url)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());

    }
}
