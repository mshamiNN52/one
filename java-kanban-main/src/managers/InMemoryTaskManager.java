package managers;

import tasks.*;

import java.io.FileNotFoundException;
import java.util.*;


public class InMemoryTaskManager implements ManagerTask {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
    Task task = new Task();
    SubTask subTask = new SubTask();
    EpicTask epicTask = new EpicTask();

    Integer id = 0;

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, SubTask> getSubTasks() {
        return subTasks;
    }

    public HashMap<Integer, EpicTask> getEpicTasks() {
        return epicTasks;
    }

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private final HashMap<Integer, EpicTask> epicTasks = new HashMap<>();
    private final Set<Task> prioritySetTask = new TreeSet<>();
    private final Set<EpicTask> prioritySetEpicTask = new TreeSet<>();
    private final Set<SubTask> prioritySetSubTask = new TreeSet<>();


    public Task createTask(int keyId, Task task) throws FileNotFoundException { // создаем таску
        task.setId(id);
        this.tasks.put(keyId, task);
        return task;
    }

    public EpicTask createEpic(int keyId, EpicTask epicTask) { // создаем епик
        epicTask.setId(id);
        this.epicTasks.put(keyId, epicTask);
        for (Map.Entry<Integer, SubTask> sabtask1 : subTasks.entrySet()) {
            if(sabtask1.getValue().getEpicId() == epicTask.getId()) {
            if (this.subTask.getDateTimeOfStart().isBefore(subTask.getDateTimeOfStart())){
                epicTask.setStartTime(subTask.getDateTimeOfStart());
            }
            if (this.subTask.getEndTimeToSubTask().isAfter(subTask.getEndTimeToSubTask())) {
                epicTask.setEndTime(subTask.getEndTimeToSubTask());
            }
            }
        }
        return epicTask;
    }

    public SubTask creatSubTask(SubTask subTask) { // создаем саб таск
        subTask.setId(getNextId(id)); //
        subTasks.put(subTask.getId(), subTask); // кладем в мапу по ключу объект
        int epicId = subTask.getEpicId(); // достаем id subTask
        epicTasks.get(epicId); // находим эпик по id
        epicTask.addSubTaskId(epicId); // кладем id в список в эпике
        return subTask;
    }

    public List getTask() { //берем таск
        List<Task> listTask = new ArrayList<>(tasks.values());
        return listTask; //возвращаем лист с тасками
    }

    public List getEpicTask() {
        List<EpicTask> listEpicTask = new ArrayList<>(epicTasks.values()); //запись епиков в лист по значениям мапы
        return listEpicTask;
    }

    public List getAllSubtuskByEpic() {
        List<SubTask> subTasks1 = new ArrayList<>(subTasks.values());
        for (SubTask sT : subTasks.values()) {
            if (sT.getEpicId() == epicTask.getSubTasksId()) {
                subTasks1.add(sT);
            }
        }
        return subTasks1;
    }

    public void deleteTask() { //удаляем все таски
        if (!tasks.isEmpty()) {
            tasks.clear();
        }
    }

    public void deleteEpicTask() { //удаляем все эпики
        if (!epicTasks.isEmpty()) {
            epicTasks.clear();
        }
    }

    public void deleteSubTask() { //удаляем все саб таски
        if (!subTasks.isEmpty()) {
            subTasks.clear();
            for (EpicTask epicTasks1 : epicTasks.values()) {
                epicTasks1.clearSubTuskID(); //чистим лист с эпик id
                updateSubTack(epicTasks1.getSubTasksId(), subTask);
            }
        }
    }


    public void updateSubTack(int id, SubTask subTask) { //обновляем саб таски по id
        subTask.setId(id);
        this.subTasks.put(id, subTask);
    }

    public void updateTask(int id, Task task) {//обновляем таски по id
        task.setId(id);
        this.tasks.put(id, task);
    }

    public void updateEpicTask(int id, EpicTask epicTask) { //обновляем эпик по id
        boolean allEpicTasksNew = true;
        for (Map.Entry<Integer, SubTask> sabtask1 : subTasks.entrySet()) {
            if(sabtask1.getValue().getEpicId() == epicTask.getId()) {
                if (sabtask1.getValue().getStatus() == TaskStatus.IN_PROGRES) {
                    epicTask.setStatus(TaskStatus.IN_PROGRES);
                    break;
                }
                else if (sabtask1.getValue().getStatus() == TaskStatus.NEW) {
                    epicTask.setStatus(TaskStatus.NEW);
                } else if(sabtask1.getValue().getStatus() == TaskStatus.DONE){
                    epicTask.setStatus(TaskStatus.DONE);
                } else {
                    epicTask.setStatus(null);
                }
                {
                    sabtask1.getValue().setStatus(TaskStatus.DONE); }
            }}
            }

    public boolean deleteByIdTask(int id) { //удаляем такс по id
        boolean containsId = tasks.containsKey(id);
        if (containsId) {
            tasks.remove(id);
            return true;
        } else {
            System.out.println("task с таким id нет");
            return false;
        }
    }

    public boolean deleteByIdSubTask(int id) { //удаляем саб такс по id
        boolean containsId = subTasks.containsKey(id);
        if (containsId) {
            int epicIdOfSubTask = subTasks.get(id).getEpicId();
            subTasks.remove(id);
            epicTasks.get(epicIdOfSubTask).removeSubTaskID(id);
            return true;
        } else {
            System.out.println("SubTask с таким id нет");
            return false;
        }
    }

    public boolean deleteByIdEpicTask(int id) { //удаляем эпик по id
        boolean containsId = epicTasks.containsKey(id);
        if (containsId) {
            epicTasks.remove(id);
            return true;
        } else {
            System.out.println("EpicTask с таким id нет");
            return false;
        }
    }

    public Task getTaskId(int id) { // вызов таски по  id
        if (tasks.containsKey(id)) {
            historyManager.add(id, tasks.get(id)); //записываем в историю вызова
            return tasks.get(id);
        } else {
            System.out.println("task с таким id нет");
            return null;
        }
    }

    public SubTask getSubTaskId(int id) { //вызов саб таск по id
        if (subTasks.containsKey(id)) {
            historyManager.add(id, subTasks.get(id)); //записываем в историю вызова
            return subTasks.get(id);
        } else {
            System.out.println("SubTask с таким id нет");
        }
        return null;
    }

    public EpicTask getEpicTaskId(int id) { //вызов эпик по id
        if ( epicTasks.containsKey(id)) {
            historyManager.add(id, epicTasks.get(id)); //записываем в историю вызова
            return epicTasks.get(id);
        } else {
            System.out.println("task с таким id нет");
            return null;
        }
    }

    private Integer getNextId(int id) { //получаем id для следующей задачи
        this.id++;
        return id;
    }

    public HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    public TreeSet getPriorityTask() {
        prioritySetTask.addAll(getTask());
        return (TreeSet) prioritySetTask;
    }

    public TreeSet getPriorityEpicTask() {
        prioritySetEpicTask.addAll(getEpicTask());
        return (TreeSet) prioritySetEpicTask;
    }

    public TreeSet getPrioritySubTask() {
        prioritySetSubTask.addAll(getAllSubtuskByEpic());
        return (TreeSet) prioritySetSubTask;
    }

    @Override
    public String toString() {
        return "ManagerTask{" +
                "subTask=" + subTask +
                ", epicTask=" + epicTask +
                ", id=" + id +
                ", tasks=" + tasks +
                ", subTasks=" + subTasks +
                ", epicTasks=" + epicTasks +
                '}';
    }

}
/*Методы для каждого из типа задач(Задача/Эпик/Подзадача):
1 . Получение списка всех задач.
2. Удаление всех задач.
3. Получение по идентификатору.
4. Создание. Сам объект должен передаваться в качестве параметра.
5. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
6. Удаление по идентификатору. */



