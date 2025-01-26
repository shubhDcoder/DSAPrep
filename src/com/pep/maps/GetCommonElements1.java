package maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GetCommonElements1 {
    private static final int[] arr1 = {1, 1, 3, 4, 5, 1, 5, 3};
    private static final int[] arr2 = {1, 2, 5, 7, 8, 9, 2 ,3, 1, 5, 1, 1, 1};

    public static void main(String[] args) {
//        getCommonElements1();
        getCommonElements2();
    }

    private static void getCommonElements2() {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int elem : arr1) freqMap.put(elem, freqMap.getOrDefault(elem, 0) + 1);
        for (int elem : arr2) {
            if (freqMap.getOrDefault(elem, 0) > 0) {
                System.out.println(elem);
                freqMap.put(elem, freqMap.get(elem) - 1);
            }
        }
    }

    private static void getCommonElements1() {
        Set<Integer> uniques = new HashSet<>();
        for (int elem : arr1) uniques.add(elem);
        for (int elem : arr2)
            if (uniques.contains(elem)) {
                System.out.println(elem);
                uniques.remove(elem);
            }
    }
}
