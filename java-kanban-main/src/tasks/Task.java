package tasks;

import java.util.Comparator;
import java.util.Objects;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Comparable<Task> {
    Integer id;
    String name;
    String title;
    TaskStatus status;
    TaskType taskType;
    String startTime;
    Integer duration;
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    public static final DateTimeFormatter timeFormatter  = DateTimeFormatter.ofPattern("HH:mm");
    LocalDateTime dateTimeOfStart;


    public Task() {
    }

    public Task(Integer id, String name, String title, TaskStatus status, String startTime, int  duration) {
        this.startTime = startTime;
        this.id = id;
        this.name = name;
        this.title = title;
        this.status = status;
        this.duration = duration;
    }

//    public Task(String name, String title, TaskStatus status) {
//        this.name = name;
//        this.title = title;
//        this.status = status;
//    }

    public void getEndTime () {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        dateTimeOfStart = LocalDateTime.parse(startTime, inputFormatter);
        LocalDateTime endTime = dateTimeOfStart.plusMinutes(duration);
        System.out.println("окончание задачи" + endTime.format(dateFormatter));

    }
    public void setId(Integer id) {
        this.id = id;
    }

    public  Task(String name, String title, TaskStatus status, TaskType taskType){
        this.name = name;
        this.title = title;
        this.status = status;
        this.taskType = taskType;
    }
    public Task(int id, String name, String title, TaskStatus status, TaskType taskType) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.status = status;
        this.taskType = taskType;
    }

    public Integer getId() {
        return id;
    } //берем id

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    } // берем статус

    public TaskType getTaskType() {
        return taskType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }


    public String toStringTask() {
        String regex =",";
        return  ( id + regex + taskType + regex + name + regex + status +regex + title + regex +  "\n");

    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", taskType=" + taskType + '\'' +
                ",  Date= " + startTime +
                ",  Duration= " + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) && name.equals(task.name) && title.equals(task.title) && status == task.status && taskType == task.taskType && startTime == task.startTime && duration == task.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, status, taskType,  startTime, duration);
    }


    @Override
    public int compareTo(Task o) {
        return 0;
    }
}



