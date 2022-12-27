package Programmers.RollCake;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/132265
 */

import java.util.*;

public class RollCake {
    public static void main(String args[]) {
        int answer = 0;
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};

        HashMap<Integer, Integer> rightMap = new HashMap<>();
        Set<Integer> left = new HashSet<>();

        for (int i = 0; i <topping.length; i++) {
            Integer cursor = topping[i];
            if (rightMap.containsKey(cursor)) {
                rightMap.replace(cursor, rightMap.get(cursor) + 1);
            }
            else {
                rightMap.put(cursor, 1);
            }
        }

        for (int i = 0; i < topping.length - 1; i++) {
            Integer cursor = topping[i];
            left.add(cursor);

            rightMap.replace(cursor, rightMap.get(cursor) - 1);
            if (rightMap.get(cursor) == 0) {
                rightMap.remove(cursor);
            }

            if (rightMap.size() == left.size()) answer++;
        }
        System.out.println(answer);
    }
}
