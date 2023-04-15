package Baekjoon.bj1082;

import java.util.Arrays;
import java.util.Scanner;

public class bj1082 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 버퍼 초기화
        sc.nextLine();

        // 배열 입력
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = sc.nextInt();

        // 가장 가격이 싼
        int cheapest = Arrays.stream(arr).min().orElse(Integer.MAX_VALUE);
        int level = m / cheapest;
        /*
        // 각 숫자 마다 구매 가능한 최대 자리수
        int[] level = new int[n];

        for (int i = 0; i < n; i++) {
            level[i] = arr[i] / m;
        }
        */
        String answer = "";

        for (int i = n - 1; i >= 0; i--) {
            StringBuffer sb = new StringBuffer();
            int cost = m;
            // 시작점 설정
            sb.append(arr[i]);
            cost -= arr[i];
            
            // 음수이면 스킵
            if (cost < 0) continue;
            
            // 그 이후로 도전
            for (int j = i - 1; j >= 0; j--) {
                //if (cost - arr[j] > 0)
            }
            
            
            // 최대 자리 수라면 탈출
            if (sb.length() == level) {
                answer = sb.toString();
                break;
            }
        }

        System.out.println(answer);
    }
}
