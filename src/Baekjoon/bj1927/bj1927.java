package Baekjoon.bj1927;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1927
// 1927 최소 힙

public class bj1927 {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());

            if (input == 0) {
                if (pq.isEmpty()) System.out.println(0);
                else System.out.println(pq.poll());
            }
            else {
                pq.add(input);
            }
        }
    }
}
