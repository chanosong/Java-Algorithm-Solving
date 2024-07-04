package Baekjoon.bj13549;

// 13549 숨바꼭질 3
// https://www.acmicpc.net/problem/13549

import java.io.*;
import java.util.*;

public class bj13549 {

    static int[] time = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Status> q = new LinkedList<>();
        q.add(new Status(n, 0));

        Arrays.fill(time, Integer.MAX_VALUE);
        time[n] = 0;

        while (!q.isEmpty()) {
            Status s = q.poll();

//            if (s.idx == k) {
//                System.out.println(s.t);
//                return;
//            }

            if (s.idx - 1 >= 0 && time[s.idx - 1] > s.t + 1) {
                q.add(new Status(s.idx - 1, s.t + 1));
                time[s.idx - 1] = s.t + 1;
            }
            if (s.idx + 1 <= 100000 && time[s.idx + 1] > s.t + 1) {
                q.add(new Status(s.idx + 1, s.t + 1));
                time[s.idx + 1] = s.t + 1;
            }
            if (s.idx * 2 <= 100000 && time[s.idx * 2] > s.t) {
                q.add(new Status(s.idx * 2, s.t ));
                time[s.idx * 2] = s.t;
            }
        }

        System.out.println(time[k]);
    }
}

class Status {
    int idx;
    int t;

    Status(int idx, int t) {
        this.idx = idx;
        this.t = t;
    }
}