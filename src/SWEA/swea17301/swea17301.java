package SWEA.swea17301;

import java.util.Arrays;
import java.util.Scanner;

// 17301 평균 내림 수열
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AYffJQbab3wDFARP
public class swea17301 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++){
            int n = sc.nextInt();

            // 버퍼 초기화
            sc.nextLine();

            int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int q = sc.nextInt();
            // 버퍼 초기화
            sc.nextLine();

            long[] idx = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
            long[] answer = new long[q];

            long maxIdx = Arrays.stream(idx).max().getAsLong();

            // 기본 세팅
            for (long j = 0; j < n; j++) {
                answer[(int) j] = arr[(int) j];
            }

            if (n != q) {
                answer[n] = (long) Math.floor((double) Arrays.stream(arr).sum() / n);

                // 이후 계산
                for (long j = n + 1; j < maxIdx; j++) {
                    answer[(int) j] = (long) Math.floor((double) ((answer[(int) j - 1] * n) + answer[(int) j - 1] - answer[(int) j - n - 1]) / n);
                }
            }

            for (int j = 0; j < idx.length; j++) {
                System.out.print(answer[(int) idx[j] - 1] + " ");
            }
        }
    }
}
