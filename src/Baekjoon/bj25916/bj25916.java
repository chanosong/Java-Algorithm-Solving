package Baekjoon.bj25916;

// https://www.acmicpc.net/problem/25916
// 25916 싫은데요

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj25916 {
    static int n;
    static int m;
    static int[] nums;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = 0;
        int end = 0;
        int holeSize = nums[0];

        while (true) {
            // 현재 막고 있는 구멍의 크기와 햄스터의 크기가 일치할 경우
            if (holeSize == m) {
                answer = m;
                break;
            }

            else  {
                // 현재 막고 있는 구멍의 크기가 햄스터의 크기보다 작은 경우
                if (holeSize < m) {
                    // 더 이상 추가할 구멍이 없는 경우 종료
                    if (end >= n - 1) break;
                    holeSize += nums[++end];
                }
                // 현재 막고 있는 구멍의 크기가 햄스터의 크기보다 큰 경우
                else {
                    // 더 이상 땡길 수 없는 경우 종료
                    if (start >= n - 1) break;
                    holeSize -= nums[start++];
                }

                //  햄스터가 커버 가능하다면 최대값 갱신
                if (holeSize <= m) {
                    answer = Math.max(answer, holeSize);
                }
            }
        }

        System.out.println(answer);
    }
}
