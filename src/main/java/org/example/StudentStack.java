package org.example;

class StudentStack {
    private Node top; // Top of the stack
    private int size; // To keep track of the number of elements

    // Constructor
    public StudentStack() {
        top = null; // Stack is initially empty
        size = 0;   // Initial size is 0
    }

    // Push a student onto the stack
    public void push(Student student) {
        Node newNode = new Node(student);
        newNode.next = top; // Point new node to the previous top
        top = newNode;      // Update top to be the new node
        size++;
    }

    // Pop a student from the stack
    public Student pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! No students to remove.");
            return null;
        }
        Student poppedStudent = top.student; // Get the student from the top node
        top = top.next;                     // Move top to the next node
        size--;
        return poppedStudent;
    }

    // Peek at the top student
    public Student peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return top.student; // Return the student at the top node
    }

    // Peek at the top node (to iterate through stack)
    public Node peekNode() {
        return top; // Return the top node
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == null; // Stack is empty if top is null
    }

    // Get the size of the stack
    public int size() {
        return size; // Return current size of the stack
    }

    // Decrement size (used in delete operation)
    public void decrementSize() {
        size--; // Decrease the size of the stack by 1
    }

    // Display all students in the stack
    public void displayStudents() {
        if (isEmpty()) {
            System.out.println("No students in the stack.");
            return;
        }

        System.out.println("Students in Stack:");
        Node current = top;  // Start from the top node
        while (current != null) {
            System.out.println(current.student);
            current = current.next; // Move to the next node
        }
    }
}
