package recursion;

import java.util.List;
import java.util.ArrayList;

public class KeypadReverse {

    public static final char[] CHAR_MAPPING = {'-', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) {
        List<String> resultHolder = new ArrayList<>();
        int result = reverseKeymapZeroOne("1213", 0, new ArrayList<>(), resultHolder);
        System.out.println("total possibilities are zeroOneType          > " + result);
        System.out.println("total possibilities are zeroOneType          > " + resultHolder);

        resultHolder = new ArrayList<>();
        result = reverseKeymapZeroOne("10112", 0, new ArrayList<>(), resultHolder);
        System.out.println("total possibilities are zeroOneType          > " + result);
        System.out.println("total possibilities are zeroOneType          > " + resultHolder);
    }

    private static int reverseKeymapZeroOne(String input, int index, List<Character> runningResult, List<String> resultHolder) {
        if (index == input.length()) {
            char[] result = new char[runningResult.size()];
            for (int i = 0; i < runningResult.size(); i++) result[i] = runningResult.get(i);
            resultHolder.add(new String(result));
            return 1;
        }

        int count = 0;
        int singleDigit = (input.charAt(index) - '0');

        if (singleDigit == 0) return 0;

        runningResult.add((char) (singleDigit + 'a' - 1));
        count += reverseKeymapZeroOne(input, index + 1, runningResult, resultHolder);
        runningResult.remove(runningResult.size() - 1);

        if (index + 1 < input.length()) {
            int doubleDigit = (singleDigit * 10) + (input.charAt(index + 1) - '0');
            if (doubleDigit > 9 && doubleDigit < 27) {
                runningResult.add((char) (doubleDigit + 'a' - 1));
                count += reverseKeymapZeroOne(input, index + 2, runningResult, resultHolder);
                runningResult.remove(runningResult.size() - 1);
            }
        }

        return count;
    }
}
