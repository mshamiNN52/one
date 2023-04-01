package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EpicTask extends Task {
    SubTask subTask =new SubTask();
    private List <Integer> subTasksId = new ArrayList <>();

    String StartTime;
    Integer duration;
    String endTime;
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public EpicTask(Integer id, String name, String title,TaskStatus status, TaskType taskType ) {
        super(id, name, title, status, taskType);
//        this.subTasksId = subTasksId;
    }

    public void setStartTime() {

    }

//    public void getEndTime () {
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
//        LocalDateTime dateTimeOfStart = LocalDateTime.parse(startTime, inputFormatter);
//        LocalDateTime endTime = dateTimeOfStart.plusMinutes(duration);
//        System.out.println("окончание задачи" + endTime.format(dateFormatter));
//    }
//    public EpicTask(String name, String title, TaskStatus status, List<Integer> subTasksId) {
//        super(name, title, status);
//        this.subTasksId = subTasksId;
//    }

    public EpicTask() {

    }


    public List <Integer> getSTiD(){
    return subTasksId;
}

    public Integer getSubTasksId() {
        Integer subTasksId1 = 0;
        for (int i=0; i< subTasksId.size(); i++){
           subTasksId1 = subTasksId.get(i);

        }
        return subTasksId1;
    }

    public void removeSubTaskID (Integer subTaskId) {
        subTasksId.remove(subTaskId);
    }

    public void setStatus( TaskStatus status) {

        this.status =status;
    }

    public void setSubTasksId(List<Integer> subTasksId) {
        this.subTasksId = subTasksId;
    }
//    public EpicTask(int id, String name, String title, TaskStatus status, TaskType epic){
//    }
//    public EpicTask(int id, String name, String title, TaskStatus status, TaskType taskType) {
//        super(id, name, title, status, taskType);
//    }

    public  void addSubTaskId(Integer subTaskId) {
        subTasksId.add(subTaskId);
    }

    public void clearSubTuskID() {
        subTasksId.clear();
    }

    public void setStartTime(LocalDateTime startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        String st = startTime.format(formatter);
        this.startTime = st;
    }

    public void setEndTime (LocalDateTime endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        String et = endTime.format(formatter);
        this.endTime = et;
    }

    public DateTimeFormatter  getInputFormater () {
        return inputFormatter;
    }



    @Override
    public String toString() {
        return "EpicTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", taskType=" + taskType +
                '}';
    }

    public String toStringEpicTask() {
        String regex =",";
        return  ( id + regex + taskType + regex + name + regex + status + regex + title + regex + "\n");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EpicTask epicTask = (EpicTask) o;
        return subTasksId.equals(epicTask.subTasksId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subTasksId);
    }
}
