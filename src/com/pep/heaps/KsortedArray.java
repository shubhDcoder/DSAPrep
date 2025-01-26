package heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KsortedArray {
    private static final int[] elements = {2, 3, 1, 4, 6, 7, 5, 8, 9};

    public static void main(String[] args) {
        kSortedArray(2);
    }

    private static void kSortedArray(int dist) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        List<Integer> sorted = new ArrayList<>();
        for (int elem : elements) {
            if (pQueue.size() > dist) sorted.add(pQueue.remove());
            pQueue.add(elem);
        }
        while (!pQueue.isEmpty()) sorted.add(pQueue.remove());

        System.out.println(sorted);
    }
}
