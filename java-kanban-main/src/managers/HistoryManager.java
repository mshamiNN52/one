package managers;

import tasks.Task;

import java.util.List;
import java.util.Map;

public interface HistoryManager {

    public void add(int id, Task task); //  Запись в лист истории вызовов

    public List getHistory(); //получение истории вызова

    void remove(int id);

    Map<Integer, Node<Task>> getNodeMap();

}
