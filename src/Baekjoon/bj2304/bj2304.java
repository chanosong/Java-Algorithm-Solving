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

        int answer = 0;
        Pillar max = new Pillar(0, 0);

        for (int i = 0; i < n; i++) {
            if (pillar[i].h >= max.h) {
                answer += max.h * (pillar[i].p - max.p);
                max = pillar[i];
            }
        }

        Pillar min = pillar[n - 1];

        for (int i = n - 1; i >= 0; i--) {
            if (pillar[i].h > min.h) {
                answer += min.h * (min.p - pillar[i].p);
                min = pillar[i];
            }
        }

        answer += max.h;

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
