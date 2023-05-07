package Baekjoon.bj2785;

// https://www.acmicpc.net/problem/2785
// 2785 체인

import java.util.Arrays;
import java.util.Scanner;

public class bj2785 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        sc.nextLine();

        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 최대로 많이 만들어야 하는 고리의 수는 n - 1
        int needed = n - 1;
        int used = 0;

        // 오름차순 정렬
        Arrays.sort(arr);

        // 작은 고리부터 확인
        for (int i = 0; i < arr.length; i++) {
            // 이미 다 채운 경우 탈출
            if (needed <= 0) break;
            // 예상 사용 갯수보다 현재 고리 개수가 많다면
            if (needed < arr[i]) {
                used += needed;
                break;
            }

            // 예상 사용 개수보다 현재 고리 개수가 적거나 같다면
            needed -= (arr[i] + 1);
            used += arr[i];
        }

        System.out.println(used);
    }
}
