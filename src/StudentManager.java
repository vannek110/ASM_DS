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

    // Sort students by marks (Quick Sort algorithm)
    public void sortStudentsByMarks(boolean ascending) {
        // Convert stack to an array
        int stackSize = studentStack.size();
        Student[] studentArray = new Student[stackSize];
        int index = 0;

        // Pop all elements from the stack into the array
        while (!studentStack.isEmpty()) {
            studentArray[index++] = studentStack.pop();
        }

        // Apply QuickSort on the array
        quickSort(studentArray, 0, studentArray.length - 1, ascending);

        // Push the sorted elements back into the stack
        for (int i = studentArray.length - 1; i >= 0; i--) {
            studentStack.push(studentArray[i]);
        }

        // Print sorted students
        System.out.println("Students sorted by marks " + (ascending ? "(Ascending):" : "(Descending):"));
        for (Student student : studentArray) {
            System.out.println(student);
        }
    }

    // QuickSort implementation
    private void quickSort(Student[] array, int low, int high, boolean ascending) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, ascending);
            quickSort(array, low, pivotIndex - 1, ascending);
            quickSort(array, pivotIndex + 1, high, ascending);
        }
    }

    // Partition method for QuickSort
    private int partition(Student[] array, int low, int high, boolean ascending) {
        Student pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            boolean condition = ascending
                    ? array[j].getMarks() < pivot.getMarks()
                    : array[j].getMarks() > pivot.getMarks();

            if (condition) {
                i++;
                Student temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        Student temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
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
