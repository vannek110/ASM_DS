package org.example;

import java.util.Scanner;

class StudentManagement {
    private StudentStack studentStack = new StudentStack(); // Use StudentStack to manage students
    private Scanner scanner = new Scanner(System.in);

    // 1. Add student information
    public void addStudent() {
        String id;
        do {
            System.out.print("Enter student ID: ");
            id = scanner.nextLine();
            if (id.isEmpty()) {
                System.out.println("ID cannot be empty. Please enter a valid ID.");
            }
        } while (id.isEmpty());

        String name;
        do {
            System.out.print("Enter student name: ");
            name = scanner.nextLine();
            if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                System.out.println("Invalid name. Please enter a valid name (alphabetic characters only).");
            }
        } while (name.isEmpty() || !name.matches("[a-zA-Z ]+"));

        double marks;
        do {
            System.out.print("Enter student marks (0 to 10): ");
            marks = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (marks < 0 || marks > 10) {
                System.out.println("Marks must be between 0 and 10.");
            }
        } while (marks < 0 || marks > 10);

        studentStack.push(new Student(id, name, marks));
        System.out.println("Student added successfully.");
    }

    // 2. Edit student information
    public void editStudent() {
        System.out.print("Enter student ID to edit: ");
        String id = scanner.nextLine();
        Student student = searchStudentById(id);
        if (student != null) {
            String name;
            do {
                System.out.print("Enter new name: ");
                name = scanner.nextLine();
                if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                    System.out.println("Invalid name. Please enter a valid name (alphabetic characters only).");
                }
            } while (name.isEmpty() || !name.matches("[a-zA-Z ]+"));

            double marks;
            do {
                System.out.print("Enter new marks (0 to 10): ");
                marks = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (marks < 0 || marks > 10) {
                    System.out.println("Marks must be between 0 and 10.");
                }
            } while (marks < 0 || marks > 10);

            student.setName(name);
            student.setMarks(marks);
            System.out.println("Student information updated.");
        } else {
            System.out.println("Student not found!");
        }
    }

    // 3. Delete student information
    public void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        String id = scanner.nextLine();
        Student deletedStudent = removeStudentById(id);
        if (deletedStudent != null) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found!");
        }
    }

    // 4. Sort the list of students (needs to convert stack to a temporary array to sort)
    public void sortStudents(boolean ascending) {
        Student[] studentArray = toArray(); // Convert stack to array to sort
        if (ascending) {
            sortAscending(studentArray);
        } else {
            sortDescending(studentArray);
        }
        // Print the sorted list
        for (Student student : studentArray) {
            System.out.println(student);
        }
    }

    // 5. Search student information by ID
    public void searchStudentById() {
        System.out.print("Enter student ID to search: ");
        String id = scanner.nextLine();
        Student student = searchStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found!");
        }
    }

    // 6. Search students by marks range
    public void searchStudentsByMarksRange() {
        double minMarks;
        double maxMarks;

        do {
            System.out.print("Enter minimum marks (0 to 10): ");
            minMarks = scanner.nextDouble();
            if (minMarks < 0 || minMarks > 10) {
                System.out.println("Minimum marks must be between 0 and 10.");
            }
        } while (minMarks < 0 || minMarks > 10);

        do {
            System.out.print("Enter maximum marks (0 to 10): ");
            maxMarks = scanner.nextDouble();
            if (maxMarks < 0 || maxMarks > 10 || maxMarks < minMarks) {
                System.out.println("Maximum marks must be between 0 and 10 and greater than or equal to minimum marks.");
            }
        } while (maxMarks < 0 || maxMarks > 10 || maxMarks < minMarks);

        scanner.nextLine(); // Consume newline

        Node current = studentStack.peekNode(); // Start from the top of the stack
        boolean found = false;
        while (current != null) {
            Student student = current.student;
            if (student.getMarks() >= minMarks && student.getMarks() <= maxMarks) {
                System.out.println(student);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No students found in the given marks range.");
        }
    }

    // 7. Display all students
    public void displayAllStudents() {
        studentStack.displayStudents(); // Use the display function from StudentStack
    }

    // Convert stack to array
    private Student[] toArray() {
        int size = studentStack.size();
        Student[] studentArray = new Student[size];
        Node current = studentStack.peekNode();
        int index = 0;
        while (current != null) {
            studentArray[index++] = current.student;
            current = current.next;
        }
        return studentArray;
    }

    // Sort in ascending order
    private void sortAscending(Student[] students) {
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = i + 1; j < students.length; j++) {
                if (students[i].getMarks() > students[j].getMarks()) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }
    }

    // Sort in descending order
    private void sortDescending(Student[] students) {
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = i + 1; j < students.length; j++) {
                if (students[i].getMarks() < students[j].getMarks()) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }
    }

    // Search student by ID
    private Student searchStudentById(String id) {
        Node current = studentStack.peekNode(); // Traverse from the top of the stack
        while (current != null) {
            if (current.student.getId().equals(id)) {
                return current.student;
            }
            current = current.next;
        }
        return null;
    }

    // Remove student by ID
    private Student removeStudentById(String id) {
        Node prev = null;
        Node current = studentStack.peekNode();

        while (current != null) {
            if (current.student.getId().equals(id)) {
                if (prev == null) { // Remove the first student
                    studentStack.pop(); // Pop from the stack
                } else {
                    prev.next = current.next; // Skip the current node
                    studentStack.decrementSize(); // Decrease the size
                }
                return current.student;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }
}
