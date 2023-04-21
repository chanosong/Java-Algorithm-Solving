package Baekjoon.bj5014;

import java.util.*;

// https://www.acmicpc.net/problem/5014
// 스타트링크
public class bj5014 {

    static int f;
    static int s;
    static int g;
    static int u;
    static int d;

    static int[] floor;

    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 총 층수
        f = sc.nextInt();
        // 출발 층수
        s = sc.nextInt();
        // 목표 층수
        g = sc.nextInt();
        // 위로 올라갈 수 있는
        u = sc.nextInt();
        // 아래로 내려갈 수 있는
        d = sc.nextInt();
        
        // floor[i] -> i층에 도달 할 수 있는 최소 엘베 사용 횟수
        floor = new int[f + 1];
        visited = new boolean[f + 1];
        // 최대치로 채우기
        // Arrays.fill(floor, Integer.MAX_VALUE);
        // 시작지점
        // floor[s] = 0;

        String answer;
        // 시작과 목표가 같은 경우
        if (s == g) {
            answer = "0";
        }
        // 다른 경우 탐색
        else {
            // bfs 진행
            Queue<Procedure> q = new LinkedList<>();
            q.add(new Procedure(s, 0));

            while(!q.isEmpty()) {
                Procedure now = q.poll();
                // 방문
                /*
                floor[now.floor] = now.trial;
                visited[now.floor] = true;
                */

                // 목표 층수라면 탈출
                if (now.floor == g) break;
                
                int uf = now.floor + u;
                int df = now.floor - d;
                int nt = now.trial + 1;

                // 올라갈 때 층을 넘어가지 않으며 아직 가지 않은 경우 진행
                if (uf <= f && !visited[uf]) {
                    q.add(new Procedure(uf, nt));
                    visited[uf] = true;
                    floor[uf] = nt;
                }

                // 내려갈 때 층을 넘어가지 않으며 아직 가지 않은 경우 진행
                if (df > 0 && !visited[df]) {
                    q.add(new Procedure(df, nt));
                    visited[df] = true;
                    floor[df] = nt;
                }
            }

            // 가지 못하는 곳이라면
            if (!visited[g]) {
                answer = "use the stairs";
            }
            // 갈 수 있는 곳이라면
            else {
                answer = Integer.toString(floor[g]);
            }
        }

        System.out.println(answer);
    }
}

class Procedure {
    int floor;
    int trial;

    Procedure(int floor, int trial) {
        this.floor = floor;
        this.trial = trial;
    }
}
