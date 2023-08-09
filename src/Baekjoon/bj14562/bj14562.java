package Baekjoon.bj14562;

// 14562 태권왕
// https://www.acmicpc.net/problem/14562

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj14562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for (int _tc = 0; _tc < tc; _tc++) {
            String[] input = br.readLine().split(" ");

            int s = Integer.parseInt(input[0]);
            int t = Integer.parseInt(input[1]);

            Queue<Procedure> queue = new LinkedList<>();

            Procedure start = new Procedure(s,t,0);
            queue.add(start);

            while (!queue.isEmpty()) {
                Procedure now = queue.poll();

                // 끝난 경우
                if (now.s == now.t) {
                    System.out.println(now.step);
                    break;
                }

                // 내 점수가 넘어가는 경우
                if (now.s > now.t) continue;

                queue.add(new Procedure(now.s * 2, now.t + 3, now.step + 1));
                queue.add(new Procedure(now.s + 1, now.t, now.step + 1));
            }
        }
    }
}

class Procedure {
    int s;
    int t;
    int step;

    Procedure(int s, int t, int step) {
        this.s = s;
        this.t = t;
        this.step = step;
    }
}