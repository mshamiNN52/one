package managers.Http;

import tasks.EpicTask;
import tasks.SubTask;
import tasks.Task;

import java.io.FileNotFoundException;
import java.util.List;

public interface HttpManagerTask {
    public void createTask(int keyId, Task task) throws FileNotFoundException;

    public void createEpic(int keyId, EpicTask epicTask);

    public void creatSubTask(SubTask subTask);

    public void getTask();

    public void getEpicTask();

    public void getAllSubtuskByEpic();

    public void deleteTask();

    public void deleteEpicTask();

    public void deleteSubTask();


    public void updateSubTack(int id, SubTask subTask);

    public void updateTask(int id, Task task);

    public void updateEpicTask(int id, EpicTask epicTask);

    public void deleteByIdTask(int id);

    public void deleteByIdSubTask(int id);

    public void deleteByIdEpicTask(int id);

    public void getTaskId(int id);

    public void getSubTaskId(int id);

    public void getEpicTaskId(int id);
}

