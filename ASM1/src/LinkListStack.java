// Stack implementation using a linked list
class Stack1<T> {
    private Node<T> top; // Top of the stack

    // Node class to represent each element in the stack
    private static class Node<T> {
        T data; // Data stored in the node
        Node<T> next; // Reference to the next node

        // Constructor to create a new node
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Stack constructor
    public Stack1() {
        this.top = null; // Stack is initially empty, so top is null
    }

    // Push operation - adds an element to the top of the stack
    public void push(T data) {
        Node<T> newNode = new Node<>(data); // Create a new node with the provided data
        newNode.next = top; // Link the new node to the current top
        top = newNode; // Update the top to be the new node
        System.out.println(data + " pushed to stack");
    }

    // Pop operation - removes and returns the top element of the stack
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty, cannot pop");
            return null;
        }
        T data = top.data; // Get data from the top node
        top = top.next; // Update the top to the next node
        System.out.println(data + " popped from stack");
        return data; // Return the popped data
    }

    // Peek operation - returns the top element without removing it
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return top.data; // Return data from the top node without removing it
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == null; // Stack is empty if top is null
    }

    // Main method to demonstrate stack operations
    public static void main(String[] args) {
        Stack1<Integer> stack1 = new Stack1<>(); // Create a stack

        // Push elements onto the stack
        stack1.push(10);
        stack1.push(20);
        stack1.push(30);
        stack1.push(40);
        stack1.push(50);

        // Peek at the top element
        System.out.println("Top element is: " + stack1.peek());

        // Pop elements from the stack
        stack1.pop();
        stack1.pop();

        // Peek again after popping
        System.out.println("Top element is: " + stack1.peek());

        // Check if the stack is empty
        System.out.println("Is stack empty? " + stack1.isEmpty());

        // Pop remaining elements
        stack1.pop();
        stack1.pop();
        stack1.pop(); // Stack should be empty after this

        // Check if the stack is empty after all pops
        System.out.println("Is stack empty? " + stack1.isEmpty());
    }
}
