package heaps;

import java.util.PriorityQueue;

public class KLargestElements {
    private static final int[] elements = {96, 10, 91, 1, 11, 92, 2, 12, 93, 3, 94, 71, 4, 95, 77};

    public static void main(String[] args) {
        kLargestElements(3);
    }

    private static void kLargestElements(int size) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        for (int elem : elements)
            if (pQueue.size() >= size) {
                if (pQueue.peek() < elem) {
                    pQueue.remove();
                    pQueue.add(elem);
                }
            } else pQueue.add(elem);

        while (!pQueue.isEmpty()) System.out.println(pQueue.remove());
    }
}
