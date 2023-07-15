package Baekjoon.bj2531;

// https://www.acmicpc.net/problem/2531
// 2531 회전초밥

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class bj2531 {

    static int answer = 0;
    static int n;
    static int d;
    static int k;
    static int coupon;

    static int[] dishes;
    static Map<Integer, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        d = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);
        coupon = Integer.parseInt(input[3]);

        dishes = new int[n];
        map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }

        // 연속으로 먹는 갯수가 길이보다 큰 경우 처리
        k = Math.min(k, n);

        // 투 포인터 실시
        int start = 0;
        int end = k - 1;

        // 초기 셋 확인
        for (int i = start; i <= end; i++) {
            if (map.containsKey(dishes[i])) {
                map.put(dishes[i], map.get(dishes[i]) + 1);
                continue;
            }
            map.put(dishes[i], 1);
        }

        // 초기 초밥 가짓수 갱신
        answer = map.keySet().size();
        if (!map.containsKey(coupon)) answer++;

        while (start < n) {

            // 시작 삭제
            if (map.get(dishes[start]) > 1) {
                map.put(dishes[start], map.get(dishes[start]) - 1);
            }
            else {
                map.remove(dishes[start]);
            }

            end++;
            // 회전 생각
            end %= n;

            // 추가
            if (map.containsKey(dishes[end])) {
                map.put(dishes[end], map.get(dishes[end]) + 1);
            }
            else {
                map.put(dishes[end], 1);
            }

            int typeCnt = map.keySet().size();
            if (!map.containsKey(coupon)) typeCnt += 1;
            answer = Math.max(answer, typeCnt);

            start++;
        }

        System.out.println(answer);
    }
}