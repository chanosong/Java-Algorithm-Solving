package Programmers.MemoryScore;
// https://school.programmers.co.kr/learn/courses/30/lessons/176963
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MemoryScore {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"may", "kein", "kain", "radi"}, new int[] {5, 10, 1, 3}
                , new String[][] {{"may", "kein", "kain", "radi"},{"may", "kein", "brin", "deny"}
        ,{"kon", "kain", "may", "coni"}})));
    }

    static int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < name.length; i++) {
            map.put(name[i], yearning[i]);
        }

        for (int i = 0; i < photo.length; i++) {
            int score = 0;

            String[] now = photo[i];

            for (int j = 0; j < now.length; j++) {
                if (!map.containsKey(now[j])) continue;
                score += map.get(now[j]);
            }

            answer[i] = score;
        }

        return answer;
    }
}
