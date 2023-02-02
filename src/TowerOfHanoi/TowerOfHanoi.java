package TowerOfHanoi;
// https://school.programmers.co.kr/learn/courses/30/lessons/12946

import java.util.*;

public class TowerOfHanoi {

    static List<List<Integer>> list;

    static void hanoi(int n, int start, int sub, int dest) {
        if (n == 1) {
            list.add(new ArrayList<>(Arrays.asList(start, dest)));
            return;
        }
        hanoi(n - 1, start, dest, sub);
        list.add(new ArrayList<>(Arrays.asList(start, dest)));
        hanoi(n - 1, sub, start, dest);
    }
    public static void main(String[] args) {
        int n = 3;
        int[][] answer;
        list = new ArrayList<>();
        hanoi(n, 1,2,3);
        answer = list.stream().map(i -> i.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
        System.out.println(Arrays.toString(answer));
    }
}
