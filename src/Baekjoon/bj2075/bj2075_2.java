package Baekjoon.bj2075;

// https://www.acmicpc.net/problem/2075
// N번째 큰 수

import java.io.*;
import java.util.*;

public class bj2075_2 {

    static long[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();


        for (int i = 0; i < N; i++) {
            long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

            for (int j = 0; j < N; j++) {
                pq.add(arr[j]);
            }
        }

        for (int i = 0; i < N * N - N; i++) {
            pq.poll();
        }

        System.out.println(pq.poll());
    }
}
