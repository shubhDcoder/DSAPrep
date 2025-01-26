package recursion;

import java.util.List;
import java.util.ArrayList;

public class Keypad {
    public static final String[] keyMap = {"YZ", "AB", "", "", "JKL", "", "", "", "WX", "PQ", "FL", "IL"};

    public static void main(String[] args) {
        List<String> resultHolder = new ArrayList<>();
        int result = zeroOneType("489", 0, "", resultHolder);
        System.out.println("total possibilities are zeroOneType          > " + result);
        System.out.println("total possibilities are zeroOneType          > " + resultHolder);

        resultHolder = new ArrayList<>();
        result = zeroOneTypeWithMultiCall("489", 0, new ArrayList<>(), resultHolder);
        System.out.println("total possibilities are zeroOneTypeMultiCall > " + result);
        System.out.println("total possibilities are zeroOneTypeMultiCall > " + resultHolder);

        System.out.println("total possibilities are startType            > " + startType("489", 0));


        result = zeroOneType("1011", 0, "", resultHolder);
        System.out.println("total possibilities are zeroOneType          > " + result);
        System.out.println("total possibilities are zeroOneType          > " + resultHolder);

        resultHolder = new ArrayList<>();
        result = zeroOneTypeWithMultiCall("1011", 0, new ArrayList<>(), resultHolder);
        System.out.println("total possibilities are zeroOneTypeMultiCall > " + result);
        System.out.println("total possibilities are zeroOneTypeMultiCall > " + resultHolder);
    }

    private static int zeroOneTypeWithMultiCall(String input, int index, List<Character> runningResult, List<String> resultHolder) {
        if (index == input.length()) {
            char[] result = new char[runningResult.size()];
            for (int i = 0; i < runningResult.size(); i++) result[i] = runningResult.get(i);
            resultHolder.add(new String(result));
            return 1;
        }

        int count = 0;
        String word = keyMap[input.charAt(index) - '0'];
        for (char c : word.toCharArray()) {
            runningResult.add(c);
            count += zeroOneTypeWithMultiCall(input, index + 1, runningResult, resultHolder);
            runningResult.remove(runningResult.size() - 1);
        }

        if (index + 1 < input.length()) {
            int number = ((input.charAt(index) - '0') * 10) + (input.charAt(index + 1) - '0');
            if (number > 9 && number < 12) {
                word = keyMap[number];
                for (char c : word.toCharArray()) {
                    runningResult.add(c);
                    count += zeroOneTypeWithMultiCall(input, index + 2, runningResult, resultHolder);
                    runningResult.remove(runningResult.size() - 1);
                }
            }
        }

        return count;
    }

    private static List<String> startType(String input, int index) {
        if (index == input.length()) {
            List<String> result = new ArrayList<>();
            result.add("");
            return result;
        }

        String possibilities = keyMap[input.charAt(index) - '0'];
        List<String> recurseResult = startType(input, index+ 1);
        List<String> result = new ArrayList<>();
        for (String str : recurseResult) for (char c : possibilities.toCharArray()) result.add(c + str);
        return result;
    }

    private static int zeroOneType(String input, int index, String combination, List<String> holder) {
        if (index == input.length()) {
            holder.add(combination);
            return 1;
        }

        int count = 0;
        String possibilities = keyMap[input.charAt(index) - '0'];
        for (char c : possibilities.toCharArray()) count += zeroOneType(input, index + 1, combination + c, holder);
        return count;
    }
}
