import java.io.*;
import java.util.*;

class DiffBetweenTwoStrings {
    static int maxLength = 0;
    static List<String> list = new ArrayList<>();

    static String[] diffBetweenTwoStrings(String source, String target) {
        maxLength = source.length() + target.length();
        recursion(source, target, 0, 0, new ArrayList<String>());

        String[] result = new String[list.size()];
        for (int i = 0; i < result.length; i++) result[i] = list.get(i);

        return result;
    }

    private static void recursion(String source, String target, int sIdx, int tIdx, List<String> temp) {
        if (tIdx == target.length() && sIdx == source.length()) {
            if (temp.size() < maxLength) {
                list.addAll(temp);
                maxLength = temp.size();
            }

            return;
        }

        if (sIdx == source.length()) {
            temp.add("+" + target.charAt(tIdx));
            recursion(source, target, sIdx, tIdx + 1, temp);
        } else if (tIdx == target.length()) {
            temp.add("-" + source.charAt(sIdx));
            recursion(source, target, sIdx + 1, tIdx, temp);
        } else if (target.charAt(tIdx) == source.charAt(sIdx)) {
            temp.add("" + target.charAt(tIdx));
            recursion(source, target, sIdx + 1, tIdx + 1, temp);
        } else {
            //remove char of source
            temp.add("-" + source.charAt(sIdx));
            recursion(source, target, sIdx + 1, tIdx, temp);
            temp.remove(temp.size() - 1);

            temp.add("+" + target.charAt(tIdx));
            recursion(source, target, sIdx, tIdx + 1, temp);

            //or add char of target
        }

        temp.remove(temp.size() - 1);
    }

    public static void main(String[] args) {
        String[] diffs = diffBetweenTwoStrings("ABCDEFG", "ABDFFGH");

        for (String s : diffs) {
            System.out.print(s + " ");
        }
    }
}
