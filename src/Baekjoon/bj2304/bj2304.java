package Baekjoon.bj2304;

import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/2304
// 2304 창고 다각형

public class bj2304 {

    static int n;
    static Pillar[] pillar;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        pillar = new Pillar[n];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");

            pillar[i] = new Pillar(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        Arrays.sort(pillar);

        Stack<Pillar> stack = new Stack<>();

        int answer = 0;
        int maxH = 0;

        for (int i = 0; i < n; i++) {
            // 스택이 빈경우 밀어넣기
            if(stack.isEmpty()) {
                stack.push(pillar[i]);
                maxH = pillar[i].h;
                continue;
            }

            // 여태까지 중 가장 큰 기둥인 경우
            if (maxH <= pillar[i].h) {
                Pillar now;
                while (true) {
                    now = stack.pop();

                    if (now.h == maxH) {
                        answer += (pillar[i].p - now.p) * now.h;
                        break;
                    }
                }
            }

            stack.push(pillar[i]);
            maxH = Math.max(maxH, pillar[i].h);
        }

        // 끝처리
        Pillar last = stack.pop();
        maxH = last.h;

        while (!stack.isEmpty()) {
            Pillar now = stack.pop();

            if (maxH < now.h) {
                answer += (last.p - now.p) * last.h;
                last = now;
            }
        }

        answer += last.h;

        System.out.println(answer);
    }
}

class Pillar implements Comparable<Pillar> {
    int p;
    int h;

    Pillar (int p, int h) {
        this.p = p;
        this.h = h;
    }

    @Override
    public int compareTo (Pillar p) {
        if (p.p < this.p) return 1;
        return -1;
    }
}
