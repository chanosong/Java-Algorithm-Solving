package Programmers.MostClosestChar;
// https://school.programmers.co.kr/learn/courses/30/lessons/142086

import java.util.*;

public class MostClosestChar {
    public static void main(String[] args) {
        String s = "banana";
        int[] answer = new int[s.length()];
        int len = s.length();
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            char now = s.charAt(i);

            if (!map.containsKey(now)) {
                answer[i] = -1;
                map.put(now, i);
                continue;
            }

            answer[i] = i - map.get(now);
            map.put(now, i);
        }

        System.out.println(Arrays.toString(answer));
    }
    
}
