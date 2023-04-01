package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class SubTask extends Task{
    private int epicId;
    LocalDateTime dateTimeOfStart;
    LocalDateTime endTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SubTask subTask = (SubTask) o;
        return epicId == subTask.epicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }

    public SubTask() {

    }

    public SubTask(Integer id, String name, String title, TaskStatus status,TaskType taskType, String startTime, int  duration, int epicId) {
        super(id, name, title, status,startTime, duration);
        this.epicId = epicId;
    }

    public void getEndTime () {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
         dateTimeOfStart = LocalDateTime.parse(startTime, inputFormatter);
         endTime = dateTimeOfStart.plusMinutes(duration);
        System.out.println("окончание задачи" + endTime.format(dateFormatter));
    }


    public LocalDateTime getDateTimeOfStart() {
        return dateTimeOfStart;
    }

    public LocalDateTime getEndTimeToSubTask() {
        return endTime;
    }


//    public SubTask(String name, String title, TaskStatus status, int epicId) {
//        super(name, title, status);
//        this.epicId = epicId;
//    }

    public TaskStatus getStatus() {
        this.status = status;
        return status;
    }

    public void setEpicId (int epicId) {
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public SubTask(int epicId, String name, String title, TaskStatus status, TaskType taskType) {

        super(name, title, status, taskType);
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", taskType=" + taskType +
                ",  Date= " + startTime +
                ",  Duration= " + duration +
                ",  EpicId= " + epicId +
                '}';
    }

    public String toStringSubTask() {
        String regex =",";
        return  ( id + regex + taskType + regex + name + regex + status + regex + title + regex + epicId + regex + startTime + regex + duration + regex + epicId + "\n");
    }
    public Integer getId() {
        this.id =id;
        return id;
    }
}
