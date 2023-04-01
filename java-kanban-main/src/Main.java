import managers.*;
import managers.Http.HttpTaskManager;
import managers.Http.HttpTaskServerTwo;
import managers.Http.KVServer;
import managers.Http.KVTaskClient;
import tasks.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        FileBackedTasksManager managerTask = new FileBackedTasksManager();
        HttpTaskServerTwo server2 = new HttpTaskServerTwo();
        HttpTaskManager manager = new HttpTaskManager();
        KVServer serv = new KVServer();
        KVTaskClient client = new KVTaskClient();

        Task task1 = new Task(0, "Zadacha1", "Sdelat", TaskStatus.NEW, "12.01.23 09:00", 90);
        EpicTask epicTask1 = new EpicTask(1,"Задача", "Задача", TaskStatus.IN_PROGRES, TaskType.EPIC);
        EpicTask epicTask2 = new EpicTask(2,"Задача2", "Задача2", TaskStatus.NEW, TaskType.EPIC);
        SubTask subTask1 = new SubTask(0, "user", "one", TaskStatus.IN_PROGRES, TaskType.SUBTASK,"13.01.23 11:00", 30, 0);
        SubTask subTask2 = new SubTask(1, "user2", "one2", TaskStatus.IN_PROGRES, TaskType.SUBTASK,"13.01.23 12:00", 50, 0);







    }
}