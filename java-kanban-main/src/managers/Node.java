package managers;

class Node<Task> {
    public Task data;
    public Node<Task> next;
    public Node<Task> prev;
    public Task item;

    public Node(Node<Task> prev, Task data, Node<Task> next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}
