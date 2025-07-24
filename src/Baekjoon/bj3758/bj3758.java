package Baekjoon.bj3758;

// https://www.acmicpc.net/problem/3758
// 3758 KCPC

import java.io.*;
import java.util.*;

public class bj3758 {

    static int t;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<Map<Integer, Integer>> teamList = new LinkedList<>();
            int[] cntArr = new int[n];
            int[] lastSubmit = new int[n];

            for (int j = 0; j < n; j++){
                teamList.add(new HashMap<>());
            }

            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());

                int teamId = Integer.parseInt(st.nextToken());
                int problemNum = Integer.parseInt(st.nextToken());
                int score = Integer.parseInt(st.nextToken());

                // 점수가 없거나 더 큰 경우에만 삽입
                if (teamList.get(teamId - 1).get(problemNum) == null || teamList.get(teamId - 1).get(problemNum) < score) {
                    teamList.get(teamId - 1).put(problemNum, score);
                }

                // 제출카운트 증가
                cntArr[teamId - 1]++;

                // 마지막 제출 시간 갱신
                lastSubmit[teamId - 1] = Math.max(lastSubmit[teamId - 1], j + 1);
            }

            PriorityQueue<Team> pq = new PriorityQueue<>();

            for (int j = 0; j < n; j++) {
                pq.add(new Team(j + 1, teamList.get(j).values().stream().mapToInt(Integer::intValue).sum(), cntArr[j], lastSubmit[j]));
            }

            int rank = 1;

            while (!pq.isEmpty()) {
                Team now = pq.poll();

                if (now.teamId == t) {
                    System.out.println(rank);
                    break;
                }
                rank++;
            }
        }
    }
}

class Team implements Comparable<Team> {
    int teamId;
    int score;
    int submitCnt;
    int lastSubmit;

    Team (int teamId, int score, int submitCnt, int lastSubmit) {
        this.teamId = teamId;
        this.score = score;
        this.submitCnt = submitCnt;
        this.lastSubmit = lastSubmit;
    }

    @Override
    public int compareTo(Team o) {
        if (this.score == o.score) {
            // 점수가 같은 경우 횟수가 더 작은
            if (this.submitCnt == o.submitCnt) {
                // 제출 시간이 더 빠른 경우 우선순위
                return this.lastSubmit - o.lastSubmit;
            }
            return this.submitCnt - o.submitCnt;
        }
        // 더 큰 경우 앞섬
        return o.score- this.score;
    }
}