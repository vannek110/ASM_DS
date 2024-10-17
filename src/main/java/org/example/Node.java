package org.example;

class Node {
    Student student; // Student data
    Node next;       // Next node reference

    // Constructor
    public Node(Student student) {
        this.student = student;
        this.next = null; // Default next is null
    }
}
