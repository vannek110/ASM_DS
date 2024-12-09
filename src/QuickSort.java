public class QuickSort {

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1); // Recursively sort elements before partition
            quickSort(array, pivotIndex + 1, high); // Recursively sort elements after partition
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Choosing the last element as pivot
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) { // If current element is smaller than or equal to pivot
                i++;
                swap(array, i, j); // Swap if element is smaller
            }
        }
        swap(array, i + 1, high); // Swap the pivot element to the correct position
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {34, 7, 23, 32, 5, 62};
        System.out.println("Original array:");
        printArray(array);

        quickSort(array, 0, array.length - 1);

        System.out.println("Sorted array:");
        printArray(array);
    }

    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
