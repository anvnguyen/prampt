import java.util.*;

public class ShortestWordEditPath {
    static int shortestWordEditPath(String source, String target, String[] words) {
        Map<String, List<String>> map = buildGraph(source, words);

        return BFS(source, target, map);
    }

    static int BFS(String source, String target, Map<String, List<String>> map) {
        //use BFS to find the path from source -> target
        List<String> queue = new ArrayList<>(map.get(source));
        Set<String> visited = new HashSet<>();

        int length = 0;
        while (queue.size() > 0) {
            length++;

            List<String> tmpQueue = new ArrayList<>();
            for (String s : queue) {
                visited.add(s);
                if (s.equals(target)) {
                    return length;
                }

                for (String adj : map.getOrDefault(s, new ArrayList<>())) {
                    if (!visited.contains(adj)) {
                        tmpQueue.add(adj);
                    }
                }
            }

            queue = tmpQueue;
        }

        return -1;
    }

    static Map<String, List<String>> buildGraph(String source, String[] words) {
        Map<String, List<String>> map = new HashMap<>();

        for (int k = 0; k <= words.length; k++) {
            for (int i = 0; i < words.length; i++) {
                int count = diff(source, words[i]);
                if (count == 1) {
                    List<String> adj = map.getOrDefault(source, new ArrayList<>());
                    adj.add(words[i]);
                    map.put(source, adj);
                }
            }

            if (k == words.length) {
                break;
            }
            source = words[k];
        }

        return map;
    }

    static int diff(String w1, String w2) {
        if (w1.length() != w2.length()) {
            return -1;
        }

        int count = 0;
        for (int i = 0; i < w1.length(); i++) {
            if(w1.charAt(i) != w2.charAt(i)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String source = "bit";
        String target = "pog";

        String[] words = {"but","put","big","pot","pog","pig","dog","lot"};

        //System.out.println(words);

        System.out.println(shortestWordEditPath(source, target, words));
    }
}
