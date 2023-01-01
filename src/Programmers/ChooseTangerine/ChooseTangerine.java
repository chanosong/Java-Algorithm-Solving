package Programmers.ChooseTangerine;
//https://school.programmers.co.kr/learn/courses/30/lessons/138476
import java.util.*;

public class ChooseTangerine {
    public static void main(String[] args) {
        int k = 2;
        int answer = 0;
        int [] tangerine = {1, 1, 1, 1, 2, 2, 2, 3};
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < tangerine.length; i++) {
            if (map.get(tangerine[i]) == null) {
                map.put(tangerine[i], 1);
                continue;
            }
            map.replace(tangerine[i], map.get(tangerine[i]) + 1);
        }

        List<Integer> value = new ArrayList<>(map.values());
        Collections.sort(value, Collections.reverseOrder());
        int vidx = 0;
        while (k != 0) {
            answer++;
            // done
            if (k < value.get(vidx)) break;
            /*
            for (int key : map.keySet()) {
                if (map.get(key) == max) {
                    kidx = key;
                    break;
                }
            }
             */
            k -= value.get(vidx);
            vidx++;
        }

        System.out.println(answer);
    }
}
