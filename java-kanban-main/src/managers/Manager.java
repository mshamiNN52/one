package managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.HistoryManager;
import managers.Http.HttpTaskManager;
import managers.InMemoryHistoryManager;
import tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public  class Manager  {
    public static Task getDefault(){
        return new Task();
    } // возвращаем новый объект класса Таск

    public HistoryManager getDefaultHistory () {
        return new InMemoryHistoryManager();
    } // возвращаем новый объект класса InMemoryHistoryManager

    public HttpTaskManager getDefaultManager () {
        return new HttpTaskManager();
    }
    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        return gsonBuilder.create();
    }
}
