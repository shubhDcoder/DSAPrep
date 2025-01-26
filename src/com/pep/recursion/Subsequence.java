package recursion;

import java.util.List;
import java.util.ArrayList;

public class Subsequence {

    public static void main(String[] args) {
//        showSubsequencesDriver("abc");
//        zeroOneCombinationDriver("abc");
//
        zeroOnePermutationWdtDuplicateDriver("abc");
//        zeroOnePermutationWdDuplicateDriver("abc");
//        showPermutationsDriver("abc");

//        zeroOnePermutationWdtDuplicateDriver("aba");
//        zeroOnePermutationWdDuplicateDriver("aba");
    }

    public static void zeroOnePermutationWdDuplicateDriver(String input) {
        if (input.isEmpty()) return;
        List<String> result = new ArrayList<>();
        int totalCombination = zeroOnePermutationWdDuplicateRunner(input, 0, result, "", new int[input.length()]);
        System.out.println("total permutation > " + totalCombination);
        System.out.println(result);
    }

    private static int zeroOnePermutationWdDuplicateRunner(String input, int index, List<String> holder, String permutation, int[] visited) {
        if (index == input.length()) {
            holder.add(permutation);
            return 1;
        }

        int count = 0;
        if (visited[index] == 0) {
            visited[index] = 1;
            count += zeroOnePermutationWdDuplicateRunner(input, 0, holder, permutation + input.charAt(index), visited);
            visited[index] = 0;
        }
        count += zeroOnePermutationWdDuplicateRunner(input, index + 1, holder, permutation, visited);

        return count;
    }

    public static void zeroOnePermutationWdtDuplicateDriver(String input) {
        if (input.isEmpty()) return;
        List<String> result = new ArrayList<>();
        int totalCombination = zeroOnePermutationWdtDuplicateRunner(input, result, "", new boolean[input.length()]);
        System.out.println("total permutation > " + totalCombination);
        System.out.println(result);
    }

    private static int zeroOnePermutationWdtDuplicateRunner(String input, List<String> holder, String permutation, boolean[] taken) {
        if (permutation.length() == input.length()) {
            holder.add(permutation);
            return 1;
        }

        int count = 0;
        boolean[] inStackTracker = new boolean[26];

        for (int i = 0; i < input.length(); i++) {
            int charMapped = input.charAt(i) - 'a';
            if (!taken[i] && !inStackTracker[charMapped]) {
                taken[i] = true;
                inStackTracker[charMapped] = true;
                count += zeroOnePermutationWdtDuplicateRunner(input, holder, permutation + input.charAt(i), taken);
                taken[i] = false;
            }
        }

        return count;
    }

    public static void zeroOneCombinationDriver(String input) {
        if (input.isEmpty()) return;
        List<String> result = new ArrayList<>();
        int totalCombination = zeroOneCombinationRunner(input, 0, result, "");
        System.out.println("total combination > " + totalCombination);
        System.out.println(result);
    }

    private static int zeroOneCombinationRunner(String input, int index, List<String> holder, String combination) {
        if (index == input.length()) {
            holder.add(combination);
            return 1;
        }

        int count = 0;
        count += zeroOneCombinationRunner(input, index + 1, holder, combination + input.charAt(index));
        count += zeroOneCombinationRunner(input, index + 1, holder, combination);

        return count;
    }

    public static void showSubsequencesDriver(String input) {
        if (input.isEmpty()) return;
        System.out.println(showSubsequencesRunner(input, 0));
    }

    private static List<String> showSubsequencesRunner(String input, int index) {
        if (index == input.length()) {
            List<String> result = new ArrayList<>();
            result.add("");
            return result;
        }

        List<String> result = showSubsequencesRunner(input, index + 1);
        char c = input.charAt(index);
        int size = result.size();
        while (--size >= 0) result.add(c + result.get(size));
        return result;
    }

    public static void showPermutationsDriver(String input) {
        if (input.isEmpty()) return;
        System.out.println(showPermutationsRunner(input, 0));
    }

    private static List<String> showPermutationsRunner(String input, int index) {
        if (index == input.length()) {
            List<String> result = new ArrayList<>();
            result.add("");
            return result;
        }

        List<String> result = showPermutationsRunner(input, index + 1);
        char c = input.charAt(index);
        int size = result.size();

        while (--size >= 0) {
            String permutation = result.get(size);
            int len = permutation.length();
            for (int i = 0; i <= len; i++) result.add(permutation.substring(0, i) + c + permutation.substring(i));
        }

        return result;
    }
}
