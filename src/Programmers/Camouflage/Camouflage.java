package Programmers.Camouflage;
//https://school.programmers.co.kr/learn/courses/30/lessons/42578
import java.util.*;

public class Camouflage {
    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"}
                , {"blue_sunglasses", "eyewear"}
                , {"green_turban", "headgear"}};
        HashMap<String, Integer> map = new HashMap<>();
        int answer = 1;

        for (int i = 0; i < clothes.length; i++) {
            String now = clothes[i][1];
            if (map.containsKey(now)) {
                map.replace(now, map.get(now) + 1);
                continue;
            }
            map.put(now, 1);
        }

        for (int i : map.values()) {
            answer *= i + 1;
        }

        answer--;
        System.out.println(answer);
    }
}
