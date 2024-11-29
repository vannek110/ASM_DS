import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortingComparison {

    // Generate a list of 1000 random students
    public static List<Student> generateRandomStudents(int count) {
        List<Student> students = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String id = "S" + (i + 1);  // Simple ID generation (S1, S2, ...)
            String name = "Student " + (i + 1);
            double marks = 50 + (random.nextDouble() * 50);  // Random marks between 50 and 100
            students.add(new Student(id, name, marks));
        }

        return students;
    }

    // Bubble Sort Algorithm
    public static void bubbleSort(List<Student> students, boolean ascending) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean condition = ascending
                        ? students.get(j).getMarks() > students.get(j + 1).getMarks()
                        : students.get(j).getMarks() < students.get(j + 1).getMarks();
                if (condition) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    // Merge Sort Algorithm
    public static void mergeSort(List<Student> students, boolean ascending) {
        if (students.size() <= 1) {
            return;
        }

        int mid = students.size() / 2;
        List<Student> left = new ArrayList<>(students.subList(0, mid));
        List<Student> right = new ArrayList<>(students.subList(mid, students.size()));

        mergeSort(left, ascending);
        mergeSort(right, ascending);

        merge(students, left, right, ascending);
    }

    private static void merge(List<Student> students, List<Student> left, List<Student> right, boolean ascending) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            boolean condition = ascending
                    ? left.get(i).getMarks() <= right.get(j).getMarks()
                    : left.get(i).getMarks() >= right.get(j).getMarks();
            if (condition) {
                students.set(k++, left.get(i++));
            } else {
                students.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            students.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            students.set(k++, right.get(j++));
        }
    }

    // Quick Sort Algorithm
    public static void quickSort(List<Student> students, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partition(students, low, high, ascending);
            quickSort(students, low, pi - 1, ascending);  // Before pivot
            quickSort(students, pi + 1, high, ascending); // After pivot
        }
    }

    private static int partition(List<Student> students, int low, int high, boolean ascending) {
        Student pivot = students.get(high); // Taking last element as pivot
        int i = (low - 1);  // Index of smaller element

        for (int j = low; j < high; j++) {
            boolean condition = ascending
                    ? students.get(j).getMarks() < pivot.getMarks()
                    : students.get(j).getMarks() > pivot.getMarks();

            if (condition) {
                i++;
                // Swap students[i] and students[j]
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }

        // Swap students[i + 1] and pivot
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1; // Return pivot index
    }

    // Main method to test the sorting algorithms
    public static void main(String[] args) {
        int studentCount = 1000; // Number of students
        List<Student> students = generateRandomStudents(studentCount);

        // Test Bubble Sort
        List<Student> bubbleSortedStudents = new ArrayList<>(students);
        long startTime = System.nanoTime();
        bubbleSort(bubbleSortedStudents, true);
        long endTime = System.nanoTime();
        System.out.println("Bubble Sort Time: " + (endTime - startTime) + " nanoseconds");

        // Test Merge Sort
        List<Student> mergeSortedStudents = new ArrayList<>(students);
        startTime = System.nanoTime();
        mergeSort(mergeSortedStudents, true);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Time: " + (endTime - startTime) + " nanoseconds");

        // Test Quick Sort
        List<Student> quickSortedStudents = new ArrayList<>(students);
        startTime = System.nanoTime();
        quickSort(quickSortedStudents, 0, quickSortedStudents.size() - 1, true);
        endTime = System.nanoTime();
        System.out.println("Quick Sort Time: " + (endTime - startTime) + " nanoseconds");
    }
}