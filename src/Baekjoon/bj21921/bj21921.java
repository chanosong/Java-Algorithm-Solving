package Baekjoon.bj21921;

// 21921 블로그
// https://www.acmicpc.net/problem/21921

import java.io.*;
import java.util.*;

public class bj21921 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = 0;
        int end = x - 1;

        int sum = 0;

        int max = 0;
        int maxCnt = 1;

        for (int i = 0; i < end + 1; i++) {
            sum += arr[i];
        }

        max = sum;

        while (end < n - 1) {
            sum = sum + arr[++end] - arr[start++];

            if (sum > max) {
                max = sum;
                maxCnt = 1;
            }
            else if (sum == max) maxCnt++;
        }

        if (max == 0) System.out.println("SAD");
        else {
            System.out.println(max);
            System.out.println(maxCnt);
        }
    }
}
