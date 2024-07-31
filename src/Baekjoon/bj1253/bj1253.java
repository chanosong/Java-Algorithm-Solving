package Baekjoon.bj1253;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1253
// 1253 좋다

public class bj1253 {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int answer = 0;

        // 선 정렬
        Arrays.sort(arr);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;

            while (left < right) {
                if (left == i || right == i) {
                    if (left == i) left++;
                    else right--;
                }
                else {
                    int now = arr[left] + arr[right];

                    if (arr[i] == now) {
                        cnt++;
                        break;
                    }
                    else if (now < arr[i]) left++;
                    else right--;
                }
            }
        }

        System.out.println(cnt);
    }
}
