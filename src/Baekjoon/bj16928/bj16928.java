package Baekjoon.bj16928;

// 뱀과 사다리 게임
// https://www.acmicpc.net/problem/16928

import java.io.*;
import java.util.*;

public class bj16928 {

    static Map<Integer,Integer> ladder;
    static Map<Integer,Integer> snake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ladder = new HashMap<>();
        snake = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            ladder.put(x,y);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            snake.put(x,y);
        }

        int[] cnt = new int[101];
        boolean[] visited = new boolean[101];

        visited[1] = true;

        Queue<Procedure> q = new LinkedList<>();

        q.add(new Procedure(1,0));

        while (!q.isEmpty()) {
            Procedure p = q.poll();

            if (p.idx == 100) {
                System.out.println(p.cnt);
                return;
            }

            for (int i = 1; i <= 6; i++) {
                int nIdx = p.idx + i;
                int nCnt = p.cnt + 1;

                if (nIdx > 100) continue;

                if (ladder.containsKey(nIdx)) nIdx = ladder.get(nIdx);
                else if (snake.containsKey(nIdx)) nIdx = snake.get(nIdx);

                if (visited[nIdx]) continue;

                cnt[nIdx] = Math.min(cnt[nIdx], nCnt);
                q.add(new Procedure(nIdx, nCnt));
                visited[nIdx] = true;
            }
        }
    }
}

class Procedure {
    int idx;
    int cnt;

    Procedure (int idx, int cnt) {
        this.idx = idx;
        this.cnt = cnt;
    }
}
