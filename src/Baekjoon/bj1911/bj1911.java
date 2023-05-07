package Baekjoon.bj1911;

import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1911
// 1911 흙길 보수하기

public class bj1911 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();

        // 웅덩이의 정보를 저장할 배열
        Pond[] ponds = new Pond[n];

        for (int i = 0; i < n ; i++) {
            ponds[i] = new Pond(sc.nextInt(), sc.nextInt());
        }

        // 정렬
        Arrays.sort(ponds);

        // 커서
        int cursor = 0;
        int cnt = 0;

        for (Pond p : ponds) {
            if (cursor < p.start) {
                cursor = p.start;
            }

            while (cursor < p.end) {
                cursor += l;
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}

class Pond implements Comparable<Pond> {
    int start;
    int end;

    Pond(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Pond pond) {
        if (this.start < pond.start) return -1;
        else return 1;
    }
}
