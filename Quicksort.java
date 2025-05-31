public class Quicksort{

    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        quickSort(array, 0, array.length - 1);
        for (int value : array)
            System.out.print(value + " ");
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i]; array[i] = array[j]; array[j] = temp;
            }
        }
        int temp = array[i + 1]; array[i + 1] = array[high]; array[high] = temp;
        return i + 1;
    }
}
