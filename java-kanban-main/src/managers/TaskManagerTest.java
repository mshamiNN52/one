package managers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest< T extends InMemoryTaskManager> {
    Task task1 = new Task("Задача", "купить", TaskStatus.NEW, TaskType.TASK);
    EpicTask epicTask1 = new EpicTask(0,"Задача1", "Задача1", TaskStatus.IN_PROGRES, TaskType.EPIC);
    SubTask subTask1 = new SubTask(1, "user", "one", TaskStatus.IN_PROGRES, TaskType.SUBTASK);
    Task task2 = new Task("Задача2", "купить2", TaskStatus.NEW, TaskType.TASK);
    EpicTask epicTask2 = new EpicTask(1,"Задача2", "Задача2", TaskStatus.NEW, TaskType.EPIC);
    SubTask subTask2 = new SubTask(1, "user", "one", TaskStatus.NEW, TaskType.SUBTASK);

    Task task3 = new Task("Задача3", "купить3", TaskStatus.NEW, TaskType.TASK);
    EpicTask epicTask3 = new EpicTask(2,"Задача3", "Задача3", TaskStatus.IN_PROGRES, TaskType.EPIC);
    SubTask subTask3 = new SubTask(2, "user3", "one3", TaskStatus.IN_PROGRES, TaskType.SUBTASK);

    FileBackedTasksManager manager = new FileBackedTasksManager();


    @Test
    void getTasks() throws FileNotFoundException {
        manager.createTask(0,task1);
        final int taskId = task1.getId();

        final Task savedTask = manager.getTaskId(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task1, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = manager.getTask();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task1, tasks.get(0), "Задачи не совпадают.");
    }


    @Test
    void getSubTasks() {

        manager.creatSubTask(subTask1);
        final int taskId = subTask1.getId();
        final Task savedTask = manager.getSubTaskId(taskId);
        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(subTask1, savedTask, "Задачи не совпадают.");
        final List<SubTask> subTasks1 = manager.getAllSubtuskByEpic();
        assertNotNull(subTasks1, "Задачи не возвращаются.");
        assertEquals(2, subTasks1.size(), "Неверное количество задач.");
        assertEquals(subTask1, subTasks1.get(0), "Задачи не совпадают.");
    }

    @Test
    void getEpicTasks() {
        manager.createEpic(1,epicTask1);
        final int taskId = epicTask1.getId();
        final EpicTask savedTask = manager.getEpicTaskId(taskId);
        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(epicTask1, savedTask, "Задачи не совпадают.");
        final List<Task> epicTask1 = manager.getEpicTask();
        assertNotNull(epicTask1, "Задачи не возвращаются.");
        assertEquals(1, epicTask1.size(), "Неверное количество задач.");
        assertEquals(savedTask, epicTask1.get(0), "Задачи не совпадают.");
    }

    @Test
    void createTask() throws FileNotFoundException {
        manager.deleteTask();
        manager.createTask(1, task1);
        final List<Task> tasks1 = manager.getTask();
        assertNotNull(tasks1, "Список пуст");
        assertEquals(task1, tasks1.get(0), "Задачи не совпадают.");
    }

    @Test
    void createEpic() {
        manager.deleteEpicTask();
        manager.createEpic(1,epicTask1);
        final List<EpicTask> epicTasksList = manager.getEpicTask();
        assertNotNull(epicTasksList, "Список пуст");
        assertEquals(epicTask1, epicTasksList.get(0), "Задачи не совпадают.");
    }


    @Test
    void creatSubTask() {
        manager.deleteSubTask();
        manager.creatSubTask(subTask1);
        final List<SubTask> subTasksList = manager.getAllSubtuskByEpic();
        assertNotNull(subTasksList, "Список пуст");
        assertEquals(subTask1, subTasksList.get(0), "Задачи не совпадают.");
    }


    @Test
    void getTask() throws FileNotFoundException {
        manager.deleteTask();
        final List<Task> tasks1 = manager.getTask();
        assertNotNull(tasks1.toString(), "задач нет");
        manager.createTask(0,task1);
        manager.createTask(1, task2);
        manager.createTask(2,task3);
        final List<Task> tasks2 = manager.getTask();
        assertNotNull(tasks2, "Задачи не возвращаются.");
        assertEquals(3, tasks2.size(), "Неверное количество задач.");
    }

    @Test
    void getEpicTask() {
        manager.deleteEpicTask();
        final List<EpicTask> epicTasks1 = manager.getEpicTask();
        assertNotNull(epicTasks1.toString(), "задач нет");
        manager.createEpic(1,epicTask1);
        final List<EpicTask> epicTasks2 = manager.getEpicTask();
        assertNotNull(epicTasks2, "Задачи не возвращаются.");
        assertEquals(1, epicTasks2.size(), "Неверное количество задач.");
    }

    @Test
    void getAllSubtuskByEpic() {
        manager.deleteSubTask();
        final List<SubTask> subTasks1 = manager.getAllSubtuskByEpic();
        assertNotNull(subTasks1.toString(), "задач нет");
        manager.creatSubTask(subTask1);
        manager.creatSubTask( subTask2);
        manager.creatSubTask(subTask3);
        final List<SubTask> subTasks2 = manager.getAllSubtuskByEpic();
        assertNotNull(subTasks2, "Задачи не возвращаются.");
        assertEquals(6, subTasks2.size(), "Неверное количество задач.");
    }

    @Test
    void deleteTask() throws FileNotFoundException {
        manager.createTask(0,task1);
        manager.createTask(1, task2);
        manager.createTask(2,task3);
        final List<Task> tasks1 = manager.getTask();
        assertEquals(3, tasks1.size(), "Неверное количество задач.");
        manager.deleteTask();
        final List<Task> tasks2 = manager.getTask();
        assertEquals(0, tasks2.size(), "Неверное количество задач.");
        assertNotNull(tasks2, "задач нет");
    }

    @Test
    void deleteEpicTask() {
        manager.createEpic(0,epicTask1);
        final List<EpicTask> epicTaskList1 = manager.getEpicTask();
        assertEquals(1, epicTaskList1.size(), "Неверное количество задач.");
        manager.deleteEpicTask();
        final List<EpicTask> epicTaskList2 = manager.getTask();
        assertEquals(0, epicTaskList2.size(), "Неверное количество задач.");
        assertNotNull(epicTaskList2, "задач нет");
    }

    @Test

    void deleteSubTask() {
        manager.creatSubTask(subTask1);
        final List<SubTask> subTaskList1 = manager.getAllSubtuskByEpic();
        assertEquals(2, subTaskList1.size(), "Неверное количество задач.");
        manager.deleteSubTask();
        final List<SubTask> subTaskList2 = manager.getTask();
        assertEquals(0, subTaskList2.size(), "Неверное количество задач.");
        assertNotNull(subTaskList2, "задач нет");
    }

    @Test
    void updateSubTack() {
    manager.creatSubTask(subTask1);
    manager.creatSubTask(subTask2);
        final List<SubTask> subTaskList1 = manager.getAllSubtuskByEpic();
        assertEquals(4, subTaskList1.size(), "Неверное количество задач.");
        manager.updateSubTack(1,subTask3);
        final SubTask subTaskUpdate = manager.getSubTaskId(1);
        assertEquals(subTask3,subTaskUpdate, "задача не обновилась");

    }

    @Test
    void updateTask() throws FileNotFoundException {
        manager.createTask(0, task1);
        manager.createTask(1, task2);
        final List<Task> taskList1 = manager.getTask();
        assertEquals(2, taskList1.size(), "Неверное количество задач.");
        manager.updateTask(1, task3);
        final Task taskUpdate = manager.getTaskId(1);
        assertEquals(task3,taskUpdate, "задача не обновилась");
    }

    @Test
    void updateEpicTask() {
        manager.creatSubTask(subTask2);
        manager.createEpic(0, epicTask2);
        final List<EpicTask> epicTaskList = manager.getEpicTask();
        assertEquals(1, epicTaskList.size(), "Неверное количество задач.");
        manager.updateEpicTask(1, epicTask3);
        final EpicTask epicTaskUpdate = manager.getEpicTaskId(1);
        assertEquals(epicTask3,epicTaskUpdate, "задача не обновилась");

    }

    @Test
    void deleteByIdTask() throws FileNotFoundException {
        manager.createTask(0,subTask1);
        manager.createTask(1, task2);
        final List <Task> taskList1 = manager.getTask();
        assertEquals(2, taskList1.size(), "Неверное количество задач.");
        manager.deleteByIdTask(1);
        final List <Task> taskList2 = manager.getTask();
        assertNull(manager.getTaskId(1), "задача не удалена");

    }

    @Test
    void deleteByIdSubTask() {
        manager.creatSubTask(subTask1);
        final List<SubTask> subTaskList1 = manager.getAllSubtuskByEpic();
        assertEquals(2, subTaskList1.size(), "Неверное количество задач.");
        manager.deleteByIdSubTask(1);
        assertNull(manager.getSubTaskId(1), "задача не удалена");
    }

    @Test
    void deleteByIdEpicTask() {
        manager.createEpic(0, epicTask1);
        manager.createEpic(1, epicTask2);
        final List<EpicTask> epicTaskList = manager.getEpicTask();
        assertEquals(1, epicTaskList.size(), "Неверное количество задач.");
        manager.deleteByIdEpicTask(0);
        assertNull(manager.getEpicTaskId(0), " задача не удалена");
    }
    @Test
    void save(){
        assertNotNull(new File("SaveInFile"), "Список пуст");
    }


}

