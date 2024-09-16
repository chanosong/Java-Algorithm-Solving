package Baekjoon.bj19941;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/19941
// 19941 햄버거 분배

public class bj19941 {

    static int n;
    static int k;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        String[] arr = br.readLine().split("");


        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (!arr[i].equals("P")) continue;

            // 사람인 경우
            int start = Math.max(0, i - k);
            int end = Math.min(n - 1, i + k);

            while (start <= end) {
                if (arr[start].equals("H")) {
                    arr[start] = "X";
                    answer++;
                    break;
                }
                start++;
            }
        }

        System.out.println(answer);
    }
}
