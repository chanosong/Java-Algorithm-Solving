package Programmers.FatigueLevel;

import java.util.*;

public class FatigueLevel {
    static int length;
    static int[] output;
    static List<int[]> order = new ArrayList<>();
    static int[] visited;
    static int fact(int n) {
        if (n <= 1) return n;
        else return fact(n-1) * n;
    }

    static void perm(int cnt) {
        if(cnt == length) {
            order.add(output);
        }
        for (int i = 0; i < length; i++) {
            if (visited[i] == 1) continue;
            output[cnt] = i;
            visited[i] = 1;
            perm(cnt + 1);
            visited[i] = 0;
        }
    }
    public static void main(String[] args) {
        int answer = -1;
        int k = 80;
        int[][] dungeons = {{80,20},{50,40},{30,10}};

        length = dungeons.length;
        output = new int[length];
        visited = new int[length];

        perm(0);
        for (int[] arr : order) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println(answer);
    }
}
