package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Student Management System:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Search Students by Marks Range");
            System.out.println("7. Display All Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    management.addStudent();
                    break;
                case 2:
                    management.editStudent();
                    break;
                case 3:
                    management.deleteStudent();
                    break;
                case 4:
                    System.out.print("Sort in ascending order? (yes/no): ");
                    String order = scanner.nextLine();
                    boolean ascending = order.equalsIgnoreCase("yes");
                    management.sortStudents(ascending);
                    break;
                case 5:
                    management.searchStudentById();
                    break;
                case 6:
                    management.searchStudentsByMarksRange();
                    break;
                case 7:
                    management.displayAllStudents();
                    break;
                case 0:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }

        } while (choice != 0);
    }
}
