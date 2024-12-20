package Baekjoon.bj18870;

// https://www.acmicpc.net/problem/18870
// 18870 좌표 압축

import java.io.*;
import java.util.*;

public class bj18870 {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int[] x = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < n; i++) {
            pq.add(x[i]);
        }

        Map<Integer, Integer> map = new HashMap<>();

        int idx = 0;

        while (!pq.isEmpty()) {
            int num = pq.poll();

            if (map.containsKey(num)) continue;
            map.put(num, idx);
            idx++;
        }

        StringJoiner sj = new StringJoiner(" ");

        for (int i = 0; i < n; i++) {
            sj.add(String.valueOf(map.get(x[i])));
        }

        System.out.println(sj.toString());
    }
}
