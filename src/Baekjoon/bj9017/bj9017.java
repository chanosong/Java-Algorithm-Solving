package Baekjoon.bj9017;

// https://www.acmicpc.net/problem/9017
// 9017 크로스 컨트리

import java.io.*;
import java.util.*;

public class bj9017 {

    static int t;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] cnt = new int[1000];


            for (int j = 0; j < n; j++) {
                // 개수 증가
                cnt[arr[j]]++;
            }

            int[] cnt4 = new int[1000];
            int[] score = new int[1000];
            int[] fifth = new int[1000];

            int rank = 1;
            for (int j = 0; j < n; j++) {
                // 6명보다 적으면 스킵
                if (cnt[arr[j]] < 6) continue;
                cnt4[arr[j]]++;
                // 상윗 4명 전까지만 점수 증가
                if (cnt4[arr[j]] <= 4) score[arr[j]] += rank;
                else if (cnt4[arr[j]] == 5) fifth[arr[j]] = rank;
                rank++;
            }

            int answer = 0;
            int min = Integer.MAX_VALUE;
            int minFifth = Integer.MAX_VALUE;

            for (int j = 1; j < 1000; j++) {
                // 아무도 없는 팀이 나오는 순간 종료
                if (cnt[j] == 0) break;

                // 6인 이상인지 확인, 아니면 스킵
                if (cnt[j] < 6) continue;

                // 점수 비교
                if (min > score[j]) {
                    min = score[j];
                    minFifth = fifth[j];
                    answer = j;
                }
                // 점수가 같은 경우
                else if (min == score[j]) {
                    if (minFifth > fifth[j]) {
                        min = score[j];
                        minFifth = fifth[j];
                        answer = j;
                    }
                }
            }
            System.out.println(answer);
        }
    }
}
