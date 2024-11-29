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

                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter marks: ");
                        double marks = scanner.nextDouble();
                        if (marks < 0 || marks > 10) {
                            throw new IllegalArgumentException("Marks must be between 0 and 10.");
                        }

                        manager.addStudent(new Student(id, name, marks));
                        break;

                    case 2:
                        // Edit student
                        System.out.print("Enter student ID to edit: ");
                        String editId = scanner.nextLine();
                        if (editId.isEmpty()) throw new IllegalArgumentException("Student ID cannot be empty.");

                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();

                        System.out.print("Enter new marks: ");
                        double newMarks = scanner.nextDouble();
                        if (newMarks < 0 || newMarks > 10) {
                            throw new IllegalArgumentException("Marks must be between 0 and 10.");
                        }

                        manager.editStudent(editId, newName, newMarks);
                        break;

                    case 3:
                        // Delete student
                        System.out.print("Enter student ID to delete: ");
                        String deleteId = scanner.nextLine();
                        if (deleteId.isEmpty()) throw new IllegalArgumentException("Student ID cannot be empty.");

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
}
