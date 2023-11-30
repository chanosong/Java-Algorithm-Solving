package Baekjoon.bj23300;

// https://www.acmicpc.net/problem/23300
// 23300 웹 브라우저 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class bj23300 {
    static int n;
    static int q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        q = Integer.parseInt(input[1]);

        Deque<Integer> backward = new ArrayDeque<>();
        Deque<Integer> forward = new ArrayDeque<>();

        int nowPage = 0;
        boolean isFirst = true;

        for (int i = 0; i < q; i++) {
            String[] line = br.readLine().split(" ");

            switch (line[0]) {
                case "B":
                    if (!backward.isEmpty()) {
                        forward.push(nowPage);
                        nowPage = backward.pop();
                    }
                    break;
                case "F":
                    if (!forward.isEmpty()) {
                        backward.push(nowPage);
                        nowPage = forward.pop();
                    }
                    break;
                case "A":
                    forward.clear();
                    if (!isFirst) {
                        backward.push(nowPage);
                    }
                    else {
                        isFirst = false;
                    }

                    nowPage = Integer.parseInt(line[1]);
                    break;
                case "C":
                    Deque<Integer> cache = new ArrayDeque<>();

                    while (!backward.isEmpty()) {
                        int now = backward.pollLast();

                        // 겹치는 경우 스킵
                        if (cache.isEmpty()) {
                            cache.addFirst(now);
                            continue;
                        }

                        if (now == cache.getFirst()) continue;
                        cache.addFirst(now);
                    }
                    // 스위치
                    backward = cache;
            }
        }

        System.out.println(nowPage);

        if (backward.isEmpty()) System.out.println(-1);
        else {
            while (!backward.isEmpty()) {
                System.out.print(backward.pop() + " ");
            }
            System.out.println();
        }

        if (forward.isEmpty()) System.out.println(-1);
        else {
            while (!forward.isEmpty()) {
                System.out.print(forward.poll() + " ");
            }
        }
    }
}
