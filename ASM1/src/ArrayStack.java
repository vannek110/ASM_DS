// Stack implementation using an array
class Stack<T> {
    private T[] stackArray; // Array to hold stack elements
    private int top; // Index to track the top of the stack
    private int capacity; // Maximum capacity of the stack

    // Constructor to initialize stack
    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        this.capacity = capacity; // Set the capacity of the stack
        stackArray = (T[]) new Object[capacity]; // Create an array to hold elements
        top = -1; // Stack is initially empty, so top is -1
    }

    // Push operation - adds an element to the top of the stack
    public void push(T data) {
        if (isFull()) {
            System.out.println("Stack is full, cannot push " + data);
            return;
        }
        stackArray[++top] = data; // Increment top and add the new element
        System.out.println(data + " pushed to stack");
    }

    // Pop operation - removes and returns the top element of the stack
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty, cannot pop");
            return null;
        }
        System.out.println(stackArray[top] + " popped from stack");
        return stackArray[top--]; // Return top element and decrement top
    }

    // Peek operation - returns the top element without removing it
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return stackArray[top]; // Return top element
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1; // Stack is empty if top is -1
    }

    // Check if the stack is full
    public boolean isFull() {
        return top == capacity - 1; // Stack is full if top is at capacity - 1
    }

    // Main method to demonstrate stack operations
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(5); // Create a stack of capacity 5

        // Push elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

        // Trying to push to a full stack
        stack.push(60); // This should display a "Stack is full" message

        // Peek at the top element
        System.out.println("Top element is: " + stack.peek());

        // Pop elements from the stack
        stack.pop();
        stack.pop();

        // Peek again after popping
        System.out.println("Top element is: " + stack.peek());

        // Check if the stack is empty
        System.out.println("Is stack empty? " + stack.isEmpty());

        // Pop remaining elements
        stack.pop();
        stack.pop();
        stack.pop(); // Stack should be empty after this

        // Check if the stack is empty after all pops
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
