package Baekjoon.bj2230;

import java.io.*;
import java.util.*;

// 수 고르기
// https://www.acmicpc.net/problem/2230

public class bj2230 {

    static int n, m;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list);

        int left = 0;
        int right = 0;

        int answer = Integer.MAX_VALUE;

        while (right < n) {
            int gap = list.get(right) - list.get(left);
            if (gap < m) {
                right ++;
            }
            else if (gap > m) {
                answer = Math.min(answer, gap);
                left++;
            }
            else {
                answer = m;
                break;
            }
        }

        System.out.println(answer);
    }
}
