package Baekjoon.bj16112;

// https://www.acmicpc.net/problem/16112
// 16112 5차 전직

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj16112 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");

        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        int[] stones = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(stones);

        long sum = 0;

        for (int i = n - 1; i >= 0; i--) {
            // 온전히 K번 할 수 있다면
            if (i > k) sum += (long) stones[i] * k;
            // 그렇지 못한 경우
            else {
                sum += (long) stones[i] * i;
            }
        }

        System.out.println(sum);
    }
}
