//package managers;
//
//import com.sun.net.httpserver.HttpServer;
//
//import com.google.gson.Gson;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.HttpCookie;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.text.DecimalFormat;
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
//import com.sun.net.httpserver.HttpServer;
//import tasks.EpicTask;
//import tasks.SubTask;
//import tasks.Task;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.InetSocketAddress;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//public class HttpTaskServer extends FileBackedTasksManager {
//
//
//
//    private static final int PORT = 8080;
//
//
//    public HttpTaskServer() throws IOException {
//
//        HttpServer httpServer = HttpServer.create();
//        httpServer.bind(new InetSocketAddress(PORT), 0);
//        httpServer.createContext("/tasks", new TasksHandler());
//        httpServer.start();
//        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
//    }
//       ; // запускаем сервер
//
//
//}
//
////    class TasksHandler implements HttpHandler {
////        FileBackedTasksManager tm = new FileBackedTasksManager();
////        @Override
////        public void handle(HttpExchange httpExchange) throws IOException {
////            System.out.println("Началась обработка запроса от клиента.");
////
////            switch (httpExchange.getRequestMethod()) {
////                case "GET":
////                    handleGetRequest(httpExchange);
////                case "POST":
////                    handlePostrequest(httpExchange);
////                case "DELETE":
////                    handleDelRequest(httpExchange);
////                    break;
////            }
////        }
//
//    @Override
//    public void handle(HttpExchange exchange) throws IOException {
//
//    }
//}
////        private void handleDelRequest(HttpExchange httpExchange) {
////
////
////
////            if ("/tasks/task".equals(httpExchange.getRequestURI())) {
////
////                tm.deleteTask();
////
////            } else if ("/tasks/subTask".equals(httpExchange.getRequestURI())) {
////                tm.deleteSubTask();
////
////            } else if ("/tasks/epicTask".equals(httpExchange.getRequestURI())) {
////                tm.deleteEpicTask();
////            }
////
////        }
////
////        private void handlePostrequest(HttpExchange httpExchange) {
////            if ("/tasks/task".equals(httpExchange.getRequestURI())) {
////
////            } else if ("/tasks/subTask".equals(httpExchange.getRequestURI())) {
////
////            } else if ("/tasks/epicTask".equals(httpExchange.getRequestURI())) {
////
////            }
////        }
////
////        private void handleGetRequest(HttpExchange httpExchange) {
////            if ("/tasks/task".equals(httpExchange.getRequestURI())) {
////
////            } else if ("/tasks/subTask".equals(httpExchange.getRequestURI())) {
////
////            } else if ("/tasks/epicTask".equals(httpExchange.getRequestURI())) {
////
////            }
////
////        }
//
//
//
//}
