package Baekjoon.bj1446;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1446
// 1446 지름길

public class bj1446 {

    static int n;
    static int d;

    static List<Road> list = new LinkedList<>();

    static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        d = Integer.parseInt(input[1]);

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");

            int[] nums = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();

            // 지름길이 의미가 없는경우 넣지 않음
            if (nums[1] - nums[0] <= nums[2]) continue;

            // 지름길이 도착위치보다 뒤로 가도 넣지 않음
            if (nums[1] > d) continue;

            list.add(new Road(nums[0], nums[1], nums[2]));
        }

        // 길이 다시 설정
        n = list.size();

        int point = 0;
        int cost = 0;

        // 최소값 저장
        minCost = d;

        for (int i = 0; i < n; i++) {
            Road now = list.get(i);

            // 지름길 시작보다 뒤인 경우 스킵
            if (point > now.start) continue;

            // 지름길 탈 수 있는 경우
            drive(now.end, cost + now.cost + now.start - point);
        }

        System.out.println(minCost);
    }

    static void drive(int s, int c) {

        // 도착한 경우
        if(s == d) {
            minCost = Math.min(minCost, c);
            return;
        }

        // 도착한 경우가 아닌데 이미 코스트 오버한경우
        if (minCost <= c) return;

        // 다시 길들 확인
        for (int i = 0; i < n; i++) {
            Road now = list.get(i);

            // 지름길 시작보다 뒤인 경우 스킵
            if (s > now.start) continue;

            // 지름길 탈 수 있는 경우
            drive(now.end, c + now.cost + now.start - s);
        }

        // 종점까지 걸어가기
        minCost = Math.min(minCost, c + d - s);
    }

    static class Road {
        int start;
        int end;
        int cost;

        public Road (int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
