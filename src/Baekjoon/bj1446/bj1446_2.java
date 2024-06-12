package Baekjoon.bj1446;

// https://www.acmicpc.net/problem/1446
// 1446 지름길

import java.io.*;
import java.util.*;

public class bj1446_2 {

    static int n;
    static int d;

    static List<Shortcut> list = new ArrayList<>();

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        answer = d;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 실질적으로 돌아가는 경우 스킵
            if (end - start <= cost) continue;

            // 종착점을 오버하는 경우 스킵
            if (end > d) continue;

            list.add(new Shortcut(start, end, cost));
        }

        recursive(0,0);

        System.out.println(answer);
    }

    static void recursive(int nowLoc, int nowCost) {

        // 이미 최소치 갱신이라면 종료
        if (nowCost >= answer) return;

        for (int i = 0; i < list.size(); i++) {
            // 이미 지난 경우 스킵
            if (nowLoc > list.get(i).start) continue;

            // 사용가능한 경우 사용
            recursive(list.get(i).end, nowCost + list.get(i).cost + list.get(i).start - nowLoc);
        }

        answer = Math.min(answer, d - nowLoc + nowCost);
    }
}

class Shortcut {
    int start;
    int end;
    int cost;

    Shortcut(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}
