package Baekjoon.bj2531;

// https://www.acmicpc.net/problem/2531
// 2531 회전 초밥

import java.io.*;
import java.util.*;

public class bj2531_2 {

    static int n;
    static int d;
    static int k;
    static int c;

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int start = 0;
        int end = k - 1;

        Map<Integer, Integer> map = new HashMap<>();

        // 초기 세팅
        for (int i = 0; i < k; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
            else map.put(arr[i], 1);
        }

        int answer = map.keySet().size();
        if (!map.containsKey(c)) answer++;

        while (true) {
            start++;
            end = (end + 1) % n;

            if (start > n) break;

            // 이전 start 제외
            if (map.get(arr[start - 1]) == 1) map.remove(arr[start - 1]);
            else map.put(arr[start - 1], map.get(arr[start - 1]) - 1);

            // end 추가
            if (map.containsKey(arr[end])) map.put(arr[end], map.get(arr[end]) + 1);
            else map.put(arr[end], 1);

            if (map.containsKey(c)) answer = Math.max(answer, map.keySet().size());
            else answer = Math.max(answer, map.keySet().size() + 1);
        }

        System.out.println(answer);
    }
}
