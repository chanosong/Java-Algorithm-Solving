package Baekjoon.bj2512;

// https://www.acmicpc.net/problem/2512
// 예산

import java.io.*;
import java.util.*;

public class bj2512 {

    static int n, m;
    static int[] arr;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        m = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        bs(0, arr[n - 1]);

        System.out.println(answer);
    }

    static void bs(int min, int max) {
        if (min > max) return;
        int mid = (max + min) / 2;

        long sum = 0;
        boolean isBigger = false;

        for (int i = 0; i < n; i++) {
            // 이미 오버한 경우
            if (m < sum) {
                isBigger = true;
                break;
            }

            if (arr[i] > mid) {
                sum += (long) mid * (n - i);
                break;
            }
            else sum += arr[i];
        }

        // 마지막으로 한 번 더 체크
        if (m < sum) isBigger = true;

        if (isBigger) bs(min, mid - 1);
        else if (sum == m) answer = mid;
        else {
            answer = Math.max(answer, mid);
            bs(mid + 1, max);
        }
    }
}
