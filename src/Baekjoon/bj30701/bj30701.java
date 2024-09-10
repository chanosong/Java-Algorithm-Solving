package Baekjoon.bj30701;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/30701
// 30701 돌아온 똥게임

public class bj30701 {

    static int n;
    static long d;

    public static void main(String[] args) throws IOException {

        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Long.parseLong(st.nextToken());

        List<Integer> monster = new ArrayList<>();
        List<Integer> gear = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if (a == 1) monster.add(x);
            else if (a == 2) gear.add(x);
        }

        Collections.sort(monster);
        Collections.sort(gear);

        int monsterIdx = 0;
        int gearIdx = 0;

        while (monsterIdx < monster.size()) {
            if (monster.get(monsterIdx) < d) {
                d += monster.get(monsterIdx);
                monsterIdx++;
            }
            else {
                if (gearIdx >= gear.size()) break;

                d *= gear.get(gearIdx);
                gearIdx++;
            }
        }

        System.out.println(monsterIdx + gear.size());
    }
}
