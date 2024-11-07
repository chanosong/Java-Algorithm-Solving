package Baekjoon.bj12978;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/12978
// 스크루지 민호 2

public class bj12978 {

    static int n;
    static int[] arr;
    static Map<Integer, List<Integer>> map;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new HashMap<>();

        arr = new int[2];

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

        traverse(1, true);

        System.out.println(Math.min(arr[0], arr[1]));
    }

    static public void traverse (int i, boolean flag) {
        if (flag) arr[0]++;
        else arr[1]++;

        isVisited[i] = true;

        List<Integer> list = map.get(i);

        for (int j = 0; j < list.size(); j++) {
            if (isVisited[list.get(j)]) continue;
            traverse(list.get(j), !flag);
        }
    }
}
