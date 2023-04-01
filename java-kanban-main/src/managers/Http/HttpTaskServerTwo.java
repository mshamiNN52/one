package managers.Http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import com.google.gson.Gson;
import managers.FileBackedTasksManager;
import managers.InMemoryHistoryManager;
import managers.InMemoryTaskManager;
import managers.Manager;
import tasks.EpicTask;
import tasks.SubTask;
import tasks.Task;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpTaskServerTwo extends FileBackedTasksManager {

    public static final int PORT = 8080;
    private HttpServer server;
    private Gson gson;
    InMemoryTaskManager taskManager = new InMemoryTaskManager();
    InMemoryHistoryManager history = new InMemoryHistoryManager();

    public HttpTaskServerTwo() throws IOException {
//        gson = Manager.getGson();
//        server = HttpServer.create(new InetSocketAddress(PORT), 0);
//        server.createContext("/tasks", this:: handleTasks);


    }

    public void start() throws IOException {
        System.out.println("Запускаем сервер на порту " + PORT);
        System.out.println("Открой в браузере http://localhost:" + PORT + "/");
        gson = Manager.getGson();
        server = HttpServer.create(new InetSocketAddress("localhost",PORT), 0);
        server.createContext("/tasks", this:: handleTasks);
        server.start();
    }

    public void stop(){
        server.stop(0);
        System.out.println("Остановили сервер на порту " + PORT);
    }

    protected String readText(HttpExchange h) throws IOException {
        return new String(h.getRequestBody().readAllBytes(), UTF_8);
    }

    protected void sendText(HttpExchange h, String text) throws IOException {
        byte[] resp = text.getBytes(UTF_8);
        h.getResponseHeaders().add("Content-Type", "application/json");
        h.sendResponseHeaders(200, resp.length);
        h.getResponseBody().write(resp);
    }
    private void handleTasks(HttpExchange httpExchange) {
        try {
            String path = httpExchange.getRequestURI().getPath();
            String metod = httpExchange.getRequestMethod();

            switch (metod) {
                case "GET": {
                    //TASK
                    if (Pattern.matches("^/tasks/task/$", path)) {
                        if (Pattern.matches("^/tasks/task/\\d+$", path)) {
                            String pathId = path.replaceFirst("/tasks/task/", " ");
                            int id = parsePath(pathId);
                            if (id != -1) {
                                String response = gson.toJson(taskManager.getTaskId(id));
                                sendText(httpExchange, response);
                                httpExchange.sendResponseHeaders(200, 0);

                            } else {
                                System.out.println("NO TASK id:" + id + "!");
                                httpExchange.sendResponseHeaders(400, 0);
                            }
                        } else {
                            String response = gson.toJson(taskManager.getTask());
                            sendText(httpExchange, response);
                            httpExchange.sendResponseHeaders(200, 0);
                        }
                        break;
                    }
//SUBTASK
                    if (Pattern.matches("^/tasks/subtask/$", path)) {
                        if (Pattern.matches("^/tasks/subtask/\\d+$", path)) {
                            String pathId = path.replaceFirst("/tasks/subtask/", " ");
                            int id = parsePath(pathId);
                            if (id != -1) {
                                String response = gson.toJson(taskManager.getSubTaskId(id));
                                sendText(httpExchange, response);
                                httpExchange.sendResponseHeaders(200, 0);

                            } else {
                                System.out.println("NO SUBTASK id:" + id + "!");
                                httpExchange.sendResponseHeaders(400, 0);
                            }
                        } else {
                            String response = gson.toJson(taskManager.getSubTasks());
                            sendText(httpExchange, response);
                            httpExchange.sendResponseHeaders(200, 0);
                        }
                        break;
                    }
//EPIC
                    if (Pattern.matches("^/tasks/epic/$", path)) {
                        if (Pattern.matches("^/tasks/epic/\\d+$", path)) {
                            String pathId = path.replaceFirst("/tasks/epic/", " ");
                            int id = parsePath(pathId);
                            if (id != -1) {
                                String response = gson.toJson(taskManager.getEpicTaskId(id));
                                sendText(httpExchange, response);
                                httpExchange.sendResponseHeaders(200, 0);
                            } else {
                                System.out.println("NO EPICTASK id:" + id + "!");
                                httpExchange.sendResponseHeaders(400, 0);
                            }
                        } else {
                            String response = gson.toJson(taskManager.getEpicTask());
                            sendText(httpExchange, response);
                            httpExchange.sendResponseHeaders(200, 0);
                        }
                        break;
                    }
 //history
                    if (Pattern.matches("^/tasks/history/$", path)) {
                        String response = gson.toJson(history.getHistory());
                        sendText(httpExchange, response);
                        httpExchange.sendResponseHeaders(200, 0);
                        break;
//priority
                    } if
                        (Pattern.matches("^/tasks/priority/$", path)) {
                        String response = gson.toJson(taskManager.getPriorityTask());
                        sendText(httpExchange, response);
                        httpExchange.sendResponseHeaders(200, 0);
                        break;
                    }

                    }
                case "POST": {
                    //TASK
                    if(Pattern.matches("^/tasks/task/$", path)){
                        if (Pattern.matches("^/tasks/task/\\d+$", path)) {
                            String pathId = path.replaceFirst("/tasks/task/", " ");
                            int id = parsePath(pathId);
                            if (id != -1) {
                               Task task = gson.fromJson(Arrays.toString(httpExchange.getRequestBody().readAllBytes()), Task.class);
                               taskManager.updateTask(id, task);
                                String response = "update/create completed";
                                sendText(httpExchange, response);
                                httpExchange.sendResponseHeaders(200,0);

                            }  else {
                                System.out.println("NO TASK id:" + id + "!");
                                httpExchange.sendResponseHeaders(400,0);
                            }
                        }
                        break;
                    }
//SUBTASK
                    if(Pattern.matches("^/tasks/subtask/$", path)){
                        if (Pattern.matches("^/tasks/subtask/\\d+$", path)) {
                            String pathId = path.replaceFirst("/tasks/subtask/", " ");
                            int id = parsePath(pathId);
                            if (id != -1) {
                                SubTask task = gson.fromJson(Arrays.toString(httpExchange.getRequestBody().readAllBytes()), SubTask.class);
                                taskManager.creatSubTask(task);
                                String response = "update/create completed";
                                sendText(httpExchange, response);
                                httpExchange.sendResponseHeaders(200,0);

                            }  else {
                                System.out.println("NO SUBTASK id:" + id + "!");
                                httpExchange.sendResponseHeaders(400,0);
                            }
                        } else {
                            String response = gson.toJson(taskManager.getSubTasks());
                            sendText(httpExchange, response);
                            httpExchange.sendResponseHeaders(200,0);
                        }
                        break;
                    }
//EPIC
                    if(Pattern.matches("^/tasks/epic/$", path)){
                        if (Pattern.matches("^/tasks/epic/\\d+$", path)) {
                            String pathId = path.replaceFirst("/tasks/epic/", " ");
                            int id = parsePath(pathId);
                            if (id != -1) {
                                EpicTask task = gson.fromJson(Arrays.toString(httpExchange.getRequestBody().readAllBytes()), EpicTask.class);
                                taskManager.createEpic(id,task);
                                String response = "update/create completed";
                                sendText(httpExchange, response);
                                httpExchange.sendResponseHeaders(200,0);
                            }  else {
                                System.out.println("NO EPICTASK id:" + id + "!");
                                httpExchange.sendResponseHeaders(400,0);
                            }
                        } else {
                            String response = gson.toJson(taskManager.getEpicTask());
                            sendText(httpExchange, response);
                            httpExchange.sendResponseHeaders(200,0);
                        }
                        break;
                    }
                    break;
                }
                case "DELETE": {
                    if(Pattern.matches("^/tasks/task/$", path)){
                        if (Pattern.matches("^/tasks/task/\\d+$", path)) {
                            String pathId = path.replaceFirst("/tasks/task/", " ");
                            int id = parsePath(pathId);
                            if (id != -1) {
                                taskManager.deleteByIdTask(id);
                                System.out.println("deleted Task id:" + id + "complete");
                                httpExchange.sendResponseHeaders(200,0);

                        }  else {
                                System.out.println("NO TASK id:" + id + "!");
                                httpExchange.sendResponseHeaders(400,0);
                            }
                    } else {
                            taskManager.deleteTask();
                            System.out.println("deleted all Tasks complete");
                            httpExchange.sendResponseHeaders(200,0);
                        }
                        break;
                    }

                    if(Pattern.matches("^/tasks/subtask/$", path)){
                        if (Pattern.matches("^/tasks/subtask/\\d+$", path)) {
                            String pathId = path.replaceFirst("/tasks/subtask/", " ");
                            int id = parsePath(pathId);
                            if (id != -1) {
                                taskManager.deleteByIdSubTask(id);
                                System.out.println("deleted SubTask id:" + id + "complete");
                                httpExchange.sendResponseHeaders(200,0);

                            }  else {
                                System.out.println("NO SUBTASK id:" + id + "!");
                                httpExchange.sendResponseHeaders(400,0);
                            }
                        } else {
                            taskManager.deleteSubTask();
                            System.out.println("deleted all SubTasks complete");
                            httpExchange.sendResponseHeaders(200,0);
                        }
                        break;
                    }

                    if(Pattern.matches("^/tasks/epic/$", path)){
                        if (Pattern.matches("^/tasks/epic/\\d+$", path)) {
                            String pathId = path.replaceFirst("/tasks/epic/", " ");
                            int id = parsePath(pathId);
                            if (id != -1) {
                                taskManager.deleteByIdEpicTask(id);
                                System.out.println("deleted epicTask id:" + id + "complete");
                                httpExchange.sendResponseHeaders(200,0);

                            }  else {
                                System.out.println("NO EPICTASK id:" + id + "!");
                                httpExchange.sendResponseHeaders(400,0);
                            }
                        } else {
                            taskManager.deleteEpicTask();
                            System.out.println("deleted all epicTasks complete");
                            httpExchange.sendResponseHeaders(200,0);

                        }

                        break;
                    }
                }
                    default: {
                        System.out.println("не верный метод запроса");
                        httpExchange.sendResponseHeaders(405, 0);
                    }
                }


            } catch(Exception exception){
                exception.getStackTrace();
            } finally{
                httpExchange.close();
            }

        }



    private int parsePath (String path) {
        try {
            return Integer.parseInt(path);
        } catch (NumberFormatException exception) {
            return -1;
        }
    }

}
