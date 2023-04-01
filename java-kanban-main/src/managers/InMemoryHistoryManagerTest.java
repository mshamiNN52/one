package managers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.*;
import java.util.List;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    Task task1 = new Task(1,"Задача", "купить", TaskStatus.NEW, TaskType.TASK);
    EpicTask epicTask1 = new EpicTask(1,"Задача1", "Задача1", TaskStatus.IN_PROGRES, TaskType.EPIC);

    Task task2 = new Task(2,"Задача2", "купить2", TaskStatus.NEW, TaskType.TASK);

    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();



    @Test
    void getNodeMap() {
    }

    @Test
    void add() {
        historyManager.add(0,task1);
        final List <Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertNotNull(historyManager.getNodeMap(), "Список пуст");

        historyManager.clear();
    }

    @Test
    void getHistory() {

         historyManager.add(0,epicTask1);
         assertEquals(1,historyManager.getHistory().size(), "не верное количество просмотров");
         assertEquals( epicTask1, historyManager.getHistory().get(1), "не верная запись в истории");
        historyManager.clear();
    }

    @Test
    void remove() {
        historyManager.clear();
        historyManager.add(0, task2);
        historyManager.add(1, task1);
        assertEquals(2,historyManager.getHistory().size(), "не верное количество просмотров");
        historyManager.remove(0);
        historyManager.remove(0);
        assertEquals(2, historyManager.getHistory().size(), "не верное количество просмотров");

    }
}