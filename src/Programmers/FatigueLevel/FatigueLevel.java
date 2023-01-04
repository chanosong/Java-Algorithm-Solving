package Programmers.FatigueLevel;
/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/87946
 */
import java.util.*;

public class FatigueLevel {
    static int length;
    static List<int[]> order = new ArrayList<>();
    static boolean[] visited;

    // factorial
    static int fact(int n) {
        if (n <= 1) return n;
        else return fact(n-1) * n;
    }

    // permutation
    static void perm(int[] output, int cnt) {
        if(cnt == length) {
            int[] cases = new int[length];
            for (int i = 0; i < length; i++) {
                cases[i] = output[i];
            }
            order.add(cases);
            return;
        }
        for (int i = 0; i < length; i++) {
            if (visited[i]) continue;
            output[cnt] = i + 1;
            visited[i] = true;
            perm(output, cnt + 1);
            visited[i] = false;
        }
    }
    public static void main(String[] args) {
        int answer = 0;
        int k = 80;
        int[][] dungeons = {{80,20},{50,40},{30,10}};

        length = dungeons.length;
        int[] output = new int[length];
        visited = new boolean[length];
        
        // permutataion
        perm(output, 0);

        for (int[] arr : order) {
            int kprime = k;
            int ans = 0;
            for (int i = 0; i < arr.length; i++) {
                if (kprime < dungeons[arr[i] - 1][0]) {
                    answer = Math.max(ans, answer);
                    break;
                }
                kprime -= dungeons[arr[i] - 1][1];
                ans++;
                if (i == arr.length - 1) answer = Math.max(ans, answer);
            }
        }
        System.out.println(answer);
    }
}
