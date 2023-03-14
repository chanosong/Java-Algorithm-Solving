package Baekjoon.bj1965;
// https://www.acmicpc.net/problem/1965
import java.util.*;

public class bj1965 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr;
        int n = sc.nextInt();

        arr = new int[n];
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.fill(ans, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    ans[i] = Math.max(ans[i], ans[j] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.stream(ans).max().getAsInt());
    }
}
