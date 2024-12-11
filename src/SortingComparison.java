import java.util.*;

public class SortingComparison {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số lượng sinh viên
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();

        // Tạo danh sách sinh viên ngẫu nhiên
        Student[] students = generateRandomStudents(n);


        // So sánh thời gian chạy của các thuật toán sắp xếp
        compareSortingAlgorithms(students);
    }

    // Hàm tạo danh sách sinh viên ngẫu nhiên
    public static Student[] generateRandomStudents(int n) {
        Student[] students = new Student[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            String id = "ID" + (i + 1);
            String name = "Student" + (i + 1);
            double marks = 5 + random.nextDouble() * 5; // Marks between 5.0 and 10.0
            students[i] = new Student(id, name, marks);
        }

        return students;
    }

    // Hàm so sánh thời gian chạy của các thuật toán sắp xếp
    public static void compareSortingAlgorithms(Student[] originalArray) {
        Student[] bubbleSortArray = Arrays.copyOf(originalArray, originalArray.length);
        Student[] mergeSortArray = Arrays.copyOf(originalArray, originalArray.length);
        Student[] quickSortArray = Arrays.copyOf(originalArray, originalArray.length);

        // Thực hiện sắp xếp Bubble Sort và đo thời gian
        long startTime = System.nanoTime();
        bubbleSort(bubbleSortArray);
        long endTime = System.nanoTime();
        System.out.println("Bubble Sort Time: " + (endTime - startTime) + " nanoseconds");

        // Thực hiện sắp xếp Merge Sort và đo thời gian
        startTime = System.nanoTime();
        mergeSort(mergeSortArray, 0, mergeSortArray.length - 1);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Time: " + (endTime - startTime) + " nanoseconds");

        // Thực hiện sắp xếp Quick Sort và đo thời gian
        startTime = System.nanoTime();
        quickSort(quickSortArray, 0, quickSortArray.length - 1, true);
        endTime = System.nanoTime();
        System.out.println("Quick Sort Time: " + (endTime - startTime) + " nanoseconds");
    }

    // Bubble Sort
    public static void bubbleSort(Student[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].getMarks() > array[j + 1].getMarks()) {
                    Student temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Merge Sort
    public static void mergeSort(Student[] array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    public static void merge(Student[] array, int left, int middle, int right) {
        Student[] leftArray = Arrays.copyOfRange(array, left, middle + 1);
        Student[] rightArray = Arrays.copyOfRange(array, middle + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i].getMarks() <= rightArray[j].getMarks()) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < leftArray.length) {
            array[k++] = leftArray[i++];
        }

        while (j < rightArray.length) {
            array[k++] = rightArray[j++];
        }
    }

    // Quick Sort
    public static void quickSort(Student[] array, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partition(array, low, high, ascending);
            quickSort(array, low, pi - 1, ascending);
            quickSort(array, pi + 1, high, ascending);
        }
    }

    public static int partition(Student[] array, int low, int high, boolean ascending) {
        Student pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition = ascending ? array[j].getMarks() < pivot.getMarks()
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

}
