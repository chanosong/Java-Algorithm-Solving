package Baekjoon.bj1802;

// https://www.acmicpc.net/problem/1802
// 1802 종이 접기

import java.util.Scanner;

public class bj1802 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 버퍼 초기화
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String[] now = sc.nextLine().split("");


            if (canFold(now, 0, now.length - 1)) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    // mid 를 기준점으로 양 옆으로 서로 다른 수인지 확인, 분할 정복
    static boolean canFold(String[] arr, int start, int end) {

        // 중점 세팅
        int mid = (start + end) / 2;
        int dx = 1;

        // 시작점과 끝점이 같다면 더 이상 체킹할 것이 없는 경우
        if (start == end) return true;

        // 중점 기준으로 대칭인지 확인
        while (mid - dx >= start) {
            // 대칭이라면 접을 수 없다
            if (arr[mid - dx].equals(arr[mid + dx])) {
                return false;
            }
            dx++;
        }

        return canFold(arr, start, mid - 1)
                && canFold(arr, mid + 1, end);
    }
}
