package Baekjoon.bj1138;

import java.util.*;
import java.io.*;

// 1138 한 줄로 서기
// https://www.acmicpc.net/problem/1138

public class bj1138 {

    static int n;

    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        answer = new int[n];

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 가장 작은 사람 자리 먼저 픽스
        answer[input[0]] = 1;

        // 이후 순서대로 확인 - i는 input 순회용
        for (int i = 1; i < n; i++) {
            int cnt = 0;

            int nowHeight = i + 1;
            int leftCnt = input[i];

            // 자기 맞는자리 찾기 - j는 자리 순회용
            for (int j = 0; j < n; j++) {
                // 자신보다 큰 사람의 명수가 다 채워진 경우, 해당 자리가 빈 경우
                if (cnt == leftCnt && answer[j] == 0) {
                    answer[j] = nowHeight;
                    break;
                }
                // 비어있다는 것은 나보다 더 큰 사람이 들어올 수 있다는 것
                if (answer[j] == 0) {
                    cnt++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(answer[i] + " ");
        }

    }
}
