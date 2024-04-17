package Baekjoon.bj20922;

// 20922 겹치는 건 싫어
// https://www.acmicpc.net/problem/20922

import java.io.*;
import java.util.*;

public class bj20922 {

    static int n;
    static int k;

    static int[] arr;

    static int[] count = new int[100001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        int start = 0;
        int end = 0;

        int answer = 0;

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();



        while (start < n && end < n) {

            // end가 가리키는 숫자를 추가하면 k개 초과가 되는 경우
            if (count[arr[end]] >= k) {
                count[arr[start++]]--;
                continue;
            }

            // 추가 가능한 경우
            count[arr[end++]]++;

            answer = Math.max(answer, end - start);
        }

        System.out.println(answer);
    }
}
