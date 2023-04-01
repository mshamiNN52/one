package managers;

import tasks.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileBackedTasksManager extends InMemoryTaskManager {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    public void save() {
        try (FileWriter writer = new FileWriter("SaveInFile", StandardCharsets.UTF_8)) {
            writer.write("id,type,name,status,description,epic" + "\n");
            if (getTask() == null) {
                throw new ManagerSaveException("задач для сохранения нет");
            } else {
                for (int i = 0; i < getTask().size(); i++) {
                    Task lineTask = (Task) getTask().get(i);
                    writer.write(lineTask.toStringTask());
                }
            }
            if (getAllSubtuskByEpic() == null) {
                throw new ManagerSaveException("SubTask для сохранения нет");
            } else {
                for (int i = 0; i < getAllSubtuskByEpic().size(); i++) {
                    Task lineSubTask = (Task) getAllSubtuskByEpic().get(i);
                    writer.write(lineSubTask.toStringTask());
                }
            }
            if (getAllSubtuskByEpic() == null) {
                throw new ManagerSaveException("EpicTask для сохранения нет");
            } else {
                for (int i = 0; i < getEpicTask().size(); i++) {
                    Task lineEpicTask = (Task) getEpicTask().get(i);
                    writer.write(lineEpicTask.toStringTask());
                }
            }
            writer.write("\n");
            writer.write(historyManager.historyToString().toString());

        } catch (IOException e) {
            System.out.println("ошибочка вышла");
        }
    }


    public String loadFromFile() throws IOException {
        FileReader reader = new FileReader("SaveInFile");
        BufferedReader br = new BufferedReader(reader);
        String line = " ";
        while (br.ready()) {
            line = br.readLine();
            fromString(line);
        }
        return line;
    }

    public Task fromString(String value) {
        String task = "TASK";
        String epic = "EPIC";
        String sub = "SUBTASK";
        String[] strings = value.split(",");
        if (task.equals(strings[1])) {
            int id = Integer.parseInt(strings[0]);
            String name = strings[2];
            String title = strings[4];
            Task task1 = new Task(id, name, title, TaskStatus.status, TaskType.TASK);
            return task1;
        }
        if (epic.equals(strings[1])) {
            int id = Integer.parseInt(strings[0]);
            String name = strings[2];
            String title = strings[4];
            EpicTask epicTask1 = new EpicTask(id, name, title, TaskStatus.status, TaskType.EPIC);
            createEpic(id, epicTask1);
            return epicTask1;
        }
        if (sub.equals(strings[1])) {
            int id = Integer.parseInt(strings[0]);
            String name = strings[2];
            String title = strings[4];
            SubTask subTask1 = new SubTask(id, name, title, TaskStatus.status, TaskType.SUBTASK);
            creatSubTask(subTask1);
            return subTask1;
        }
        return new Task();
    }


    @Override
    public Task createTask(int id, Task task) throws FileNotFoundException {
        super.createTask(id, task);
        save();
        return task;
    }

    @Override
    public EpicTask createEpic(int keyId, EpicTask epicTask) {
        super.createEpic(id, epicTask);
        save();
        return epicTask;
    }

    @Override
    public SubTask creatSubTask(SubTask subTask) {
        super.creatSubTask(subTask);
        save();
        return subTask;
    }

    @Override
    public void updateSubTack(int id, SubTask subTask) {
        super.updateSubTack(id, subTask);
        save();
    }

    @Override
    public void updateTask(int id, Task task) {
        super.updateTask(id, task);
        save();
    }

    @Override
    public void updateEpicTask(int id, EpicTask epicTask) {
        super.updateEpicTask(id, epicTask);
        save();
    }

    @Override
    public Task getTaskId(int id) {
        return super.getTaskId(id);
    }

    @Override
    public SubTask getSubTaskId(int id) {
        return super.getSubTaskId(id);
    }

    @Override
    public EpicTask getEpicTaskId(int id) {
        return super.getEpicTaskId(id);
    }

    @Override
    public HistoryManager getDefaultHistory() {
        return super.getDefaultHistory();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileBackedTasksManager that = (FileBackedTasksManager) o;
        return historyManager.equals(that.historyManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyManager);
    }

    private class ManagerSaveException extends IOException {
        public ManagerSaveException(String message) {
        }
    }
}




