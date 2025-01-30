package Baekjoon.bj21735;

// https://www.acmicpc.net/problem/21735
// 눈덩이 굴리기

import java.io.*;
import java.util.*;

public class bj21735 {

    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] scoreboard = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Stack<Status> stack = new Stack<>();
        stack.add(new Status(-1, 1, 0));

        long answer = 0;

        while (!stack.isEmpty()) {
            Status s = stack.pop();

            if (s.time >= m || s.idx >= n - 1) {
                answer = Math.max(answer, s.score);
                continue;
            }

            // Roll
            if (s.idx + 1 < n) stack.push(new Status(s.idx + 1, s.score + scoreboard[s.idx + 1], s.time + 1));
            // Throw
            if (s.idx + 2 < n) stack.push(new Status(s.idx + 2, (s.score / 2) + scoreboard[s.idx + 2], s.time + 1));
        }

        System.out.println(answer);
    }
}

class Status {
    int idx;
    long score;
    int time;

    Status (int idx, long score, int time) {
        this.idx = idx;
        this.score = score;
        this.time = time;
    }
}
