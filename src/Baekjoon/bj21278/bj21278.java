package Baekjoon.bj21278;

// https://www.acmicpc.net/problem/21278
// 21278 호석이 두 마리 치킨

import java.io.*;
import java.util.*;

public class bj21278 {

    static int n;
    static int m;

    static boolean[][] map;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new boolean[n][n];
        dist = new int[n][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            map[a][b] = true;
            map[b][a] = true;
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            Queue<Integer[]> q = new LinkedList<>();
            q.add(new Integer[]{i, 1});
            dist[i][i] = 0;

            while (!q.isEmpty()) {
                Integer[] cur = q.poll();
                int start = cur[0];
                int depth = cur[1];
                
                for (int j = 0; j < n; j++) {
                    // 시작점인 경우, 이어지지 않은 경우, 더 작은 뎁스를 갖는 경우 스킵
                    if (start == j || !map[start][j] || dist[i][j] < depth) continue;

                    dist[i][j] = Math.min(dist[i][j], depth);

                    q.add(new Integer[]{j, depth + 1});
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        int ans1 = 0;
        int ans2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int min = 0;

                for (int k = 0; k < n; k++) {
                    min += Math.min(dist[i][k], dist[j][k]);
                }

                if (min * 2 < answer) {
                    ans1 = i + 1;
                    ans2 = j + 1;
                    answer = min * 2;
                }
            }
        }

        System.out.println(ans1 + " " + ans2 + " " + answer);
    }
}
