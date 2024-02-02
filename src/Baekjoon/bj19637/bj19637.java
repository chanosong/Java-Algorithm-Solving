package Baekjoon.bj19637;

import java.util.*;
import java.io.*;

// 19637 IF문 좀 대신 써줘
// https://www.acmicpc.net/problem/19637

public class bj19637 {
    static int n;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        n = input[0];
        m = input[1];

        Title[] titleList = new Title[n];
        // 칭호 리스트 설정
        for (int i = 0; i < n; i++) {
            String[] example  = br.readLine().split(" ");

            titleList[i] = new Title(example[0], Integer.parseInt(example[1]));
        }

        StringBuffer sb = new StringBuffer();

        // 차례대로 진행
        for (int i = 0; i < m; i++) {
            int now = Integer.parseInt(br.readLine());

            // 이진탐색 시작
            int front = 0;
            int end = n - 1;

            while (front <= end) {
                int mid = (front + end) / 2;

                if (titleList[mid].power < now) {
                    front = mid + 1;
                }
                else {
                    end = mid - 1;
                }
            }

            sb.append(titleList[front].title + "\n");
        }

        System.out.println(sb.toString());

    }

    static class Title {
        String title;
        int power;

        Title (String title, int power) {
            this.title = title;
            this.power = power;
        }
    }
}
