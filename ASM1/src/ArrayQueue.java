// ArrayQueue class for array-based queue implementation
class ArrayQueue<T> {
    private T[] queue; // Array to store data in the queue
    private int front; // Front of the queue
    private int rear;  // Rear of the queue
    private int count; // Number of elements in the queue
    private int capacity; // Capacity of the queue

    // Constructor to initialize the queue with an initial capacity
    @SuppressWarnings("unchecked")
    public ArrayQueue(int initialCapacity) {
        capacity = initialCapacity;
        queue = (T[]) new Object[capacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    // Default constructor with an initial capacity of 4
    public ArrayQueue() {
        this(4);
    }

    // Enqueue operation to add elements to the queue
    public void enqueue(T data) {
        if (isFull()) {
            resize(); // Resize the array when the queue is full
        }
        rear = (rear + 1) % capacity; // Adjust rear in a circular fashion
        queue[rear] = data; // Add the element at the rear position
        count++;
        System.out.println(data + " enqueued to the queue");
    }

    // Dequeue operation to remove elements from the queue
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty, cannot dequeue");
            return null;
        }
        T data = queue[front]; // Get the element at the front
        front = (front + 1) % capacity; // Adjust front in a circular fashion
        count--;
        System.out.println(data + " dequeued from the queue");
        return data;
    }

    // Peek operation to view the first element without removing it
    public T peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return queue[front]; // Return the element at the front
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return count == 0;
    }

    // Check if the queue is full
    public boolean isFull() {
        return count == capacity;
    }

    // Resize the array when the queue is full
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        T[] newQueue = (T[]) new Object[newCapacity];

        for (int i = 0; i < count; i++) {
            newQueue[i] = queue[(front + i) % capacity]; // Copy data in circular order
        }

        queue = newQueue;
        front = 0;
        rear = count - 1;
        capacity = newCapacity;

        System.out.println("Queue resized to " + newCapacity);
    }
}
