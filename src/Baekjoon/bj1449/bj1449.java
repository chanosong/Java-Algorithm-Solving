package Baekjoon.bj1449;

// https://www.acmicpc.net/problem/1449
// 1449 수리공 항승

import java.io.*;
import java.util.*;

public class bj1449 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        int start = arr[0];
        int cnt = 1;
        int remain = l - 1;

        for (int i = 1; i < arr.length; i++) {
            int now = arr[i];

            boolean flag = true;

            if (now - start <= remain) {
                remain -= now - start;
                flag = false;
            }

            if (flag) {
                cnt++;
                remain = l - 1;
            }

            start = now;
        }

        System.out.println(cnt);
    }
}
