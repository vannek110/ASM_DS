// LinkedListQueue class for linked list-based queue implementation
class LinkedList_Queue<T> {
    // Node class to represent each element in the queue
    private static class Node<T> {
        T data; // Data stored in the node
        Node<T> next; // Reference to the next node

        // Constructor to initialize the node
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front; // Front of the queue
    private Node<T> rear;  // Rear of the queue
    private int count;     // Number of elements in the queue

    // Constructor to initialize the queue
    public LinkedList_Queue() {
        this.front = null;
        this.rear = null;
        this.count = 0;
    }

    // Enqueue operation to add elements to the queue
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            front = rear = newNode; // If queue is empty, both front and rear point to the new node
        } else {
            rear.next = newNode; // Link the new node after the rear
            rear = newNode; // Update rear to the new node
        }

        count++;
        System.out.println(data + " enqueued to the queue");
    }

    // Dequeue operation to remove elements from the queue
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty, cannot dequeue");
            return null;
        }

        T data = front.data; // Get data from the front node
        front = front.next;  // Move front to the next node

        if (front == null) { // If the queue becomes empty after dequeue
            rear = null; // Set rear to null as well
        }

        count--;
        System.out.println(data + " dequeued from the queue");
        return data; // Return the dequeued data
    }

    // Peek operation to view the first element without removing it
    public T peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return front.data; // Return data from the front node
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Get the size of the queue
    public int size() {
        return count;
    }
}

// Main class to test the LinkedListQueue


