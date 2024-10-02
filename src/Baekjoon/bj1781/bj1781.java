package Baekjoon.bj1781;

// https://www.acmicpc.net/problem/1781
// 1781 컵라면

import java.io.*;
import java.util.*;

public class bj1781 {
    static int n;
    static PriorityQueue<Assignment> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int maxDeadline = 0;

        for (int i = 0; i < n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            maxDeadline = Math.max(maxDeadline, a);

            pq.add(new Assignment(a,b));
        }

        PriorityQueue<Integer> solved = new PriorityQueue<>();

        while (!pq.isEmpty()) {
            Assignment a = pq.poll();

            // 여유 시간이 남는 경우
            if (solved.size() < a.deadline) {
                solved.add(a.ramen);
            }
            // 여유 시간이 남지 않지만 기존 보다 더 높은 이득일때
            else if (solved.peek() < a.ramen){
                solved.poll();
                solved.add(a.ramen);
            }
        }

        long ans = 0;

        while (!solved.isEmpty()) {
            ans += solved.poll();
        }

        System.out.println(ans);
    }
}

class Assignment implements Comparable<Assignment> {
    int num;
    int deadline;
    int ramen;

    Assignment(int deadline, int ramen) {
        this.deadline = deadline;
        this.ramen = ramen;
    }

    @Override
    public int compareTo(Assignment o) {
        if (this.deadline < o.deadline) return -1;
        else if (this.deadline == o.deadline) {
            if (this.ramen > o.ramen) return -1;
            else return 1;
        }
        return 1;
    }
}
