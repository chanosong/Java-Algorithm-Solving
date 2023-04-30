package Programmers.RoughlyMadeKeyboard;

// https://school.programmers.co.kr/learn/courses/30/lessons/160586
// 대충 만든 자판

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RoughlyMadeKeyboard {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"ABACD", "BCEFD"}, new String[] {"ABCD", "AABB"})));
        System.out.println(Arrays.toString(solution(new String[]{"AA"}, new String[] {"B"})));
        System.out.println(Arrays.toString(solution(new String[]{"AGZ", "BSSS"}, new String[] {"ASA", "BGZ"})));
        System.out.println(Arrays.toString(solution(new String[]{"FFF", "FFF"}, new String[] {"CCC", "CCC"})));
        System.out.println(Arrays.toString(solution(new String[]{"AGZ", "BSSS"}, new String[] {"AGY", "BSSS"})));
        System.out.println(Arrays.toString(solution(new String[]{"ABCDEFGHIJK"}, new String[] {"J"})));
    }

    static int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];

        Map<String, Integer> map = new HashMap<>();

        for (String s : keymap) {
            String[] key = s.split("");

            for (int i = 0; i < key.length; i++) {
                // 없다면
                if (!map.containsKey(key[i])) {
                    map.put(key[i], i);
                    continue;
                }
                // 있다면 개수 체킹
                // 더 적다면 갱신
                if (map.get(key[i]) > i) {
                    map.replace(key[i], i);
                }
            }
        }

        for (int i = 0; i < targets.length; i++) {
            String[] now = targets[i].split("");
            int cnt = 0;

            for (String s : now) {
                // 만들 수 없다면 -1
                if (!map.containsKey(s)) {
                    cnt = -1;
                    break;
                }
                // 만들 수 있다면 cnt 더하기
                cnt += map.get(s) + 1;
            }

            answer[i] = cnt;
        }

        return answer;
    }
}
