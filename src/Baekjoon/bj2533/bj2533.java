package Baekjoon.bj2533;

// https://www.acmicpc.net/problem/2533
// 사회망 서비스(SNS)

import java.io.*;
import java.util.*;

public class bj2533 {

    static int n;

    static Map<Integer, List<Integer>> map;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (!map.containsKey(a)) {
                map.put(a, new ArrayList<>());
            }
            map.get(a).add(b);

            if (!map.containsKey(b)) {
                map.put(b, new ArrayList<>());
            }
            map.get(b).add(a);
        }

        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int idx) {
        visited[idx] = true;
        // idx가 얼리어답터가 아닌 경우
        dp[idx][0] = 0;
        // idx가 얼리어답터인 경우
        dp[idx][1] = 1;

        for (int i : map.get(idx)) {
            if (visited[i]) continue;
            
            // 자식노드 값 선 계산
            dfs(i);
            // 자식이 얼리어답터일수도 아닐 수도 있다
            dp[idx][0] += dp[i][1];
            // 자식이 무조건 얼리어답터
            dp[idx][1] += Math.min(dp[i][0], dp[i][1]);
        }
    }
}
