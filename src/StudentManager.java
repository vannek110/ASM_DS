import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private MyStack<Student> studentStack;
    private MyStack<MyStack<Student>> undoStack;
    private MyStack<MyStack<Student>> redoStack;

    public StudentManager() {
        studentStack = new MyStack<>();
        undoStack = new MyStack<>();
        redoStack = new MyStack<>();
    }

    // Save the current state to the undo stack
    private void saveState() {
        undoStack.push(studentStack.cloneStack());
    }

    // Add a new student
    public void addStudent(Student student) {
        saveState();
        studentStack.push(student);
        redoStack = new MyStack<>();  // Clear redo stack after a new action
    }

    // Edit student information
    public void editStudent(String id, String newName, double newMarks) {
        saveState();
        MyStack<Student> tempStack = new MyStack<>();
        boolean found = false;

        // Search for the student and update information
        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                student.setName(newName);
                student.setMarks(newMarks);
                found = true;
            }
            tempStack.push(student);
        }

        // Restore the stack
        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        if (!found) {
            System.out.println("Student not found!");
        }
    }

    // Delete a student by ID
    public void deleteStudent(String id) {
        saveState();
        MyStack<Student> tempStack = new MyStack<>();
        boolean found = false;

        // Search for the student to delete
        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                found = true;
                break;
            }
            tempStack.push(student);
        }

        // Restore the stack
        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        if (!found) {
            System.out.println("Student not found to delete!");
        }
    }

    // Search for a student by ID
    public Student searchStudentById(String id) {
        MyStack<Student> tempStack = new MyStack<>();
        Student result = null;

        // Traverse the stack to find the student
        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                result = student;
            }
            tempStack.push(student);
        }

        // Restore the stack
        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        return result;
    }

    // Sort students by marks (Bubble Sort algorithm)
    public void sortStudentsByMarks(boolean ascending) {
        saveState(); // Save the current state before sorting

        // Transfer data from stack to a temporary list
        List<Student> studentList = new ArrayList<>();
        while (!studentStack.isEmpty()) {
            studentList.add(studentStack.pop());
        }

        // Call Quick Sort on the list
        quickSort(studentList, 0, studentList.size() - 1, ascending);

        // Transfer sorted data back to the stack
        for (int i = studentList.size() - 1; i >= 0; i--) {
            studentStack.push(studentList.get(i));
        }

        // Print the sorted list
        System.out.println("Students sorted by marks " + (ascending ? "(Ascending):" : "(Descending):"));
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    // Helper method to perform Quick Sort
    private void quickSort(List<Student> list, int low, int high, boolean ascending) {
        if (low < high) {
            // Find pivot index such that elements smaller than pivot are on the left and greater on the right
            int pivotIndex = partition(list, low, high, ascending);

            // Recursively sort elements before and after the pivot
            quickSort(list, low, pivotIndex - 1, ascending);
            quickSort(list, pivotIndex + 1, high, ascending);
        }
    }

    // Partition method for Quick Sort
    private int partition(List<Student> list, int low, int high, boolean ascending) {
        // Choose the pivot element (last element in the list)
        Student pivot = list.get(high);
        int i = (low - 1); // Index of smaller element

        // Compare each element with the pivot
        for (int j = low; j < high; j++) {
            boolean condition = ascending
                    ? list.get(j).getMarks() < pivot.getMarks()  // Ascending order
                    : list.get(j).getMarks() > pivot.getMarks(); // Descending order

            if (condition) {
                i++;
                // Swap list[i] and list[j]
                Student temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // Swap the pivot element with the element at i+1
        Student temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1; // Return the pivot index
    }


    // Undo the last action
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(studentStack.cloneStack());
            studentStack = undoStack.pop();
        } else {
            System.out.println("No actions to undo.");
        }
    }

    // Redo the last undone action
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(studentStack.cloneStack());
            studentStack = redoStack.pop();
        } else {
            System.out.println("No actions to redo.");
        }
    }

    // Print the list of students
    public void printStudents() {
        MyStack<Student> tempStack = new MyStack<>();
        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            System.out.println(student);
            tempStack.push(student);
        }

        // Restore the stack
        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }
    }
}
