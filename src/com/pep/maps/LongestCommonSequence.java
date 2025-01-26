package maps;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSequence {
    private static final int[] jumbledSeq = {96, 10, 91, 1, 11, 92, 2, 12, 93, 3, 94, 71, 4, 95, 77};

    public static void main(String[] args) {
        longestCommonSeq();
    }

    private static void longestCommonSeq() {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int elem : jumbledSeq) map.put(elem, true);

        for (int key : map.keySet()) if (map.containsKey(key - 1)) map.put(key, false);

        int max_sequence_length = 0;
        int max_sequence_key = 0;

        for (int key : map.keySet()) {
            if (map.get(key)) {
                int tempKey = key;
                while (map.containsKey(tempKey + 1)) tempKey += 1;
                int sequence_length = tempKey - key + 1;
                if (sequence_length > max_sequence_length) {
                    max_sequence_length = sequence_length;
                    max_sequence_key = key;
                }
            }
        }

        System.out.println("sequence starts with " + max_sequence_key + " having length " + max_sequence_length);
    }
}
