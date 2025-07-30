package Baekjoon.bj1205;

// https://www.acmicpc.net/problem/1205
// 1205 등수 구하기

import java.io.*;
import java.util.*;

public class bj1205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n =  Integer.parseInt(st.nextToken());
        int score =  Integer.parseInt(st.nextToken());
        int p =  Integer.parseInt(st.nextToken());

        if (n == 0) System.out.println(1);
        else {
            Integer[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            Arrays.sort(arr, Collections.reverseOrder());

            if (n == p && score <= arr[arr.length-1]) System.out.println(-1);
            else {
                int rank = 1;

                for (int i = 0; i < arr.length; i++) {
                    if (score < arr[i]) rank++;
                    else break;
                }

                System.out.println(rank);
            }

        }
    }
}