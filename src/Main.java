import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Main {
    // Реализовать сортировку подсчетом
// Она заключается в подсчете количества вхождений элементов в массив и восстановление по этой информации.
// [1, 3, 1, 5, 7, 7, 3, 2, 5, 7]
//        1 -> 2
//        2 -> 1
//        3 -> 2
//        5 -> 2
//        7 -> 3
// [1, 1, 2, 3, 3, 5, 5, 7, 7, 7];
    public static void main(String[] args) {
        int[] array = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(10)).limit(10).toArray();
        System.out.println("Изначальный массив:\n" + Arrays.toString(array));
        System.out.println();
        countSort(array);
    }

    private static void countSort(int[] array) {
        Map<Integer, Integer> listPairs = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int count = 1;
            if (checkElement(array, array[i], i)) {
                continue;
            }
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] == array[i]) {
                    count++;
                }
            }
            listPairs.put(array[i], count);
        }
        ArrayList<Integer> listValues = new ArrayList<>();
        ArrayList<Integer> listCounts = new ArrayList<>();
        ArrayList<Integer> sortedArray = new ArrayList<>();
        for (int key : listPairs.keySet()) {
            listValues.add(key);
        }
        for (int value : listPairs.values()) {
            listCounts.add(value);
        }
        for (int i = 0; i < listValues.size(); i++) {
            int countdown = listCounts.get(i);
            while (countdown > 0) {
                sortedArray.add(listValues.get(i));
                countdown--;
            }
        }
        System.out.println("Отсортированный массив:\n" + sortedArray);

    }

    private static boolean checkElement(int[] array, int element, int endIndex) {
        boolean check = false;
        for (int i = 0; i < endIndex; i++) {
            if (array[i] == element) {
                check = true;
                break;
            }
        }
        return check;
    }
}