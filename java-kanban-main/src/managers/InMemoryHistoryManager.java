package managers;

import tasks.Task;

import java.util.*;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    public InMemoryHistoryManager() {
    }

    public Map<Integer, Node<Task>> getNodeMap() {
        return nodeMap;
    }

    private static final Map<Integer, Node<Task>> nodeMap = new HashMap<>();
    List <Task> listGetHistory = new ArrayList<>();


    private Node<Task> head;

    private Node<Task> tail;

    public  List historyToString() {

        List<Integer> number = new ArrayList<>();
        for (Map.Entry<Integer, Node<tasks.Task>> entry : nodeMap.entrySet()) {
            number.add(entry.getKey()) ;
        }
        return  number;
    }
    @Override
    public void add(int id,Task task) {
        Integer taskId = id;
            if (nodeMap.containsKey(taskId)) {
                removeNode (nodeMap.get(taskId));
            }
            Node<Task> newNode = linkLast(task);
            nodeMap.put(taskId, newNode);
        }


//    @Override
//    public List () {
//        return getTasks();
//    }

    @Override
    public void remove(int id) {
        listGetHistory.remove(id);
    }

         public static void removeNode(Node<Task> node) {
             final Task task = node.item;
             final Node<Task> next = node.next;
             final Node<Task> prev = node.prev;

             if (prev == null) {
                 Node<Task> first = next;
             } else {
                 prev.next = next;
                 node.prev = null;
             }

             if (next == null) {
                 Node<Task> last = prev;
             } else {
                 next.prev = prev;
                 node.next = null;
             }

             node.item = null;

         }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryHistoryManager that = (InMemoryHistoryManager) o;
        return Objects.equals(nodeMap, that.nodeMap) && Objects.equals(listGetHistory, that.listGetHistory) && Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeMap, listGetHistory, head, tail);
    }

    public  Node<Task> linkLast(Task task) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(tail, task, null);
        tail = newNode;
        if (oldTail == null)
            tail = newNode;
        else
            oldTail.prev = newNode;
        return newNode;
    }

    public List getHistory () {

        for (Map.Entry<Integer, Node<Task>> nodes: nodeMap.entrySet()) {
            listGetHistory.add(nodes.getValue().data);
        }
return listGetHistory;
    }
    public void clear() {
        listGetHistory.clear();
        nodeMap.clear();

    }
}
