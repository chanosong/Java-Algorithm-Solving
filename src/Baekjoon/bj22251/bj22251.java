package Baekjoon.bj22251;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/22251
// 22251 빌런 호석

public class bj22251 {

    static int n;
    static int k;
    static int p;
    static int x;

    // 0 ~ 9 숫자 표현 방식, 위 > 아래, 좌 > 우, 0 소등 1 점등
    static int[][] numbers = new int[][]{
            {1,1,1,0,1,1,1}, // 0
            {0,0,1,0,0,1,0}, // 1
            {1,0,1,1,1,0,1}, // 2
            {1,0,1,1,0,1,1}, // 3
            {0,1,1,1,0,1,0}, // 4
            {1,1,0,1,0,1,1}, // 5
            {1,1,0,1,1,1,1}, // 6
            {1,0,1,0,0,1,0}, // 7
            {1,1,1,1,1,1,1}, // 8
            {1,1,1,1,0,1,1} // 9
        };

    // 변경 가능한 숫자 저장소
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        p = Integer.parseInt(input[2]);
        x = Integer.parseInt(input[3]);

        recursive(n, 0, k, p, 0, x, new StringBuffer());

        System.out.println(set.size());
    }

    // K의 자리수인 원래의 숫자 X에서 P개 까지 반전하는 최대 N까지의 숫자를 구하는 재귀 함수
    static void recursive(int n, int step, int k, int p, int pCnt, int x, StringBuffer sb) {
        // 전부 고른 경우 종료
        if (step == k) {
            int newNum = Integer.parseInt(sb.toString());
            // 1개라도 반전된 경우 추가, 0인 경우 제외
            if (pCnt > 0 && newNum > 0) set.add(newNum);
            return;
        }

        // 숫자를 더 골라야할 경우
        for (int i = 0; i <= 9; i++) {
            sb.append(i);

            // 만약 이미 최대 수인 N을 넘은 경우 진행 x
            if ((int) (n / Math.pow(10, k - (step + 1))) < Integer.parseInt(sb.toString())) return;

            // 현재 층수와 자리 수 필요 반전 수
            int toReverse = ledGap((int) ((x / Math.pow(10, k - (step + 1)))) % 10, i);

            // 만약 이미 반전 개수 이내인 경우 다음 단계 진행
            if (p >= pCnt + toReverse) recursive(n, step + 1, k, p, pCnt + toReverse, x , sb);

            // StringBuffer 원복
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // a > b 로 바꾸기 위해 반전해야하는 소자의 수 반환
    static int ledGap(int a, int b) {
        int ret = 0;

        for (int i = 0; i < 7; i++) {
            ret += Math.abs(numbers[a][i] - numbers[b][i]);
        }

        return ret;
    }
}
