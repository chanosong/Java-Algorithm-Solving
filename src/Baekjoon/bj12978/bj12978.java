package Baekjoon.bj12978;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/12978
// 스크루지 민호 2

public class bj12978 {

    static int n;
    static int[] dp1;
    static int[] dp2;
    static Map<Integer, List<Integer>> map;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new HashMap<>();

        dp1 = new int[n + 1];
        dp2 = new int[n + 1];

        isVisited = new boolean[n + 1];

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer s = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(s.nextToken());
            int b = Integer.parseInt(s.nextToken());

            if (!map.containsKey(a)) {
                map.put(a, new ArrayList<>());
            }

            map.get(a).add(b);

            if (!map.containsKey(b)) {
                map.put(b, new ArrayList<>());
            }

            map.get(b).add(a);
        }

        traverse(1);

        int answer = Math.min(dp1[1], dp2[1]);

        System.out.println(answer);
    }

    static public void traverse (int i) {
        isVisited[i] = true;
        dp1[i] = 1;

        for (int idx : map.get(i)) {
            if (isVisited[idx]) continue;

            traverse(idx);

            dp1[i] += Math.min(dp1[idx], dp2[idx]);
            dp2[i] += dp1[idx];
        }
    }
}
