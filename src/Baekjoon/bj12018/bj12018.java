package Baekjoon.bj12018;

// https://www.acmicpc.net/problem/12018
// Yonsei TOTO

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class bj12018 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int ans = 0;


        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            // 신청 인원
            int p = sc.nextInt();
            // 정원
            int l = sc.nextInt();

            // 버퍼 초기화
            sc.nextLine();

            Integer[] arr = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

            // 정원보다 더 적게 신청한 경우
            if (p < l) {
                pq.add(1);
                continue;
            }

            // 정원과 같거나 더 많이 신청한 경우
            // 역순정렬 후 새치기
            Arrays.sort(arr, Collections.reverseOrder());

            pq.add(arr[l - 1]);
        }

        while(!pq.isEmpty()) {
            m -= pq.poll();
            if (m < 0) break;
            ans++;
        }

        System.out.println(ans);
    }
}
