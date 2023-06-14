package Baekjoon.bj28107;

// https://www.acmicpc.net/problem/28107
// 28107 회전초밥

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj28107 {

    static int n;
    static int m;
    static Map<Integer, Queue<Integer>> order;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        answer = new int[n];

        order = new HashMap<>();

        // Map에 등록
        for (int i = 0; i < n; i++) {
            String[] info = br.readLine().split(" ");
            int total = Integer.parseInt(info[0]);

            for (int j = 0; j < total; j++) {
                int sushi = Integer.parseInt(info[j + 1]);

                // 이미 등록되어있는 것이라면 삽입 없다면 최초 생성 진행
                if (order.containsKey(sushi)) {
                    order.get(sushi).add(i);
                    continue;
                }

                Queue<Integer> newList = new LinkedList<>();
                newList.add(i);
                order.put(sushi, newList);
            }
        }

        // 스시 종류 확인
        int[] sushiNum = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 차례로 스시 만들어지고, 각 손님이 먹을 수 있는지 판단
        for (int i = 0; i < m; i++) {
            // 원하는 스시를 먹었다면 제거하고 카운트 증가
            if (order.containsKey(sushiNum[i])) {
                Queue<Integer> waitList = order.get(sushiNum[i]);
                
                // 대기열이 없는 경우 스킵
                if (waitList.isEmpty()) continue;
                
                // 대기열이 있는 경우 순서대로 처리
                answer[waitList.poll()]++;
            }
        }

        // 정답 출력
        for (int i = 0; i < n; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}