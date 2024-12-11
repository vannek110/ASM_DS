import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            try {
                System.out.println("\n--- Student Manager ---");
                System.out.println("1. Add Student");
                System.out.println("2. Edit Student");
                System.out.println("3. Delete Student");
                System.out.println("4. Search Student");
                System.out.println("5. Print Students");
                System.out.println("6. Undo");
                System.out.println("7. Redo");
                System.out.println("8. Sort Students by Marks");
                System.out.println("9. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        // Add student
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine();
                        if (id.isEmpty()) throw new IllegalArgumentException("Student ID cannot be empty.");
                        if (isIdDuplicate(manager, id)) {
                            System.out.println("Student ID already exists. Please enter a unique ID.");
                            break;
                        }

                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        if (!isValidName(name)) {
                            System.out.println("Invalid name. Name cannot be empty.");
                            break;
                        }

                        System.out.print("Enter marks: ");
                        double marks = readValidMarks(scanner);

                        manager.addStudent(new Student(id, name, marks));
                        break;

                    case 2:
                        // Edit student
                        System.out.print("Enter student ID to edit: ");
                        String editId = scanner.nextLine();
                        if (editId.isEmpty()) throw new IllegalArgumentException("Student ID cannot be empty.");
                        if (manager.searchStudentById(editId) == null) {
                            System.out.println("Student not found.");
                            break;
                        }

                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        if (!isValidName(newName)) {
                            System.out.println("Invalid name. Name cannot be empty.");
                            break;
                        }

                        System.out.print("Enter new marks: ");
                        double newMarks = readValidMarks(scanner);

                        manager.editStudent(editId, newName, newMarks);
                        break;

                    case 3:
                        // Delete student
                        System.out.print("Enter student ID to delete: ");
                        String deleteId = scanner.nextLine();
                        if (deleteId.isEmpty()) throw new IllegalArgumentException("Student ID cannot be empty.");
                        if (manager.searchStudentById(deleteId) == null) {
                            System.out.println("Student not found.");
                            break;
                        }

                        manager.deleteStudent(deleteId);
                        break;

                    case 4:
                        // Search student
                        System.out.print("Enter student ID to search: ");
                        String searchId = scanner.nextLine();
                        if (searchId.isEmpty()) throw new IllegalArgumentException("Student ID cannot be empty.");

                        Student student = manager.searchStudentById(searchId);
                        if (student != null) {
                            System.out.println(student);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 5:
                        // Print all students
                        manager.printStudents();
                        break;

                    case 6:
                        // Undo
                        manager.undo();
                        break;

                    case 7:
                        // Redo
                        manager.redo();
                        break;

                    case 8:
                        // Sort students by marks
                        System.out.print("Sort in ascending order? (true/false): ");
                        boolean ascending = scanner.nextBoolean();
                        manager.sortStudentsByMarks(ascending);
                        break;

                    case 9:
                        // Exit
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice, try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter a valid number.");
                scanner.nextLine();  // Consume the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error occurred: " + e.getMessage());
            }
        }
    }

    // Helper method to read valid marks input
    private static double readValidMarks(Scanner scanner) {
        double marks = -1;
        while (marks < 0 || marks > 10) {
            try {
                marks = scanner.nextDouble();
                if (marks < 0 || marks > 10) {
                    System.out.println("Marks must be between 0 and 10. Please enter again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for marks.");
                scanner.nextLine();  // Consume the invalid input
            }
        }
        return marks;
    }

    // Method to check if the student ID already exists
    private static boolean isIdDuplicate(StudentManager manager, String id) {
        return manager.searchStudentById(id) != null;
    }

    // Method to validate the student's name (non-empty)
    private static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
