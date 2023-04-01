package managers;

import tasks.*;

import java.io.FileNotFoundException;
import java.util.*;

public interface ManagerTask {

    SubTask subTask = new SubTask();
    EpicTask epicTask = new EpicTask();



    public Task createTask(int keyId, Task task) throws FileNotFoundException;

    public EpicTask createEpic(int keyId, EpicTask epicTask);

    public SubTask creatSubTask(SubTask subTask);

    public List<Task> getTask();

    public List<Task> getEpicTask();

    public List <Task> getAllSubtuskByEpic();

    public void deleteTask();

    public void deleteEpicTask();

    public void deleteSubTask();


    public void updateSubTack(int id, SubTask subTask);

    public void updateTask(int id, Task task);

    public void updateEpicTask(int id, EpicTask epicTask);

    public boolean deleteByIdTask(int id);

    public boolean deleteByIdSubTask(int id);

    public boolean deleteByIdEpicTask(int id);

    public Task getTaskId(int id);

    public SubTask getSubTaskId(int id);

    public EpicTask getEpicTaskId(int id);
}


/*Методы для каждого из типа задач(Задача/Эпик/Подзадача):
1 . Получение списка всех задач.
2. Удаление всех задач.
3. Получение по идентификатору.
4. Создание. Сам объект должен передаваться в качестве параметра.
5. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
6. Удаление по идентификатору. */

