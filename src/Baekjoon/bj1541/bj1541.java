package Baekjoon.bj1541;

// 1541 잃어버린 괄호
// https://www.acmicpc.net/problem/1541

import java.io.*;
import java.util.*;

public class bj1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // - 기준으로 자르기
        String[] tk = br.readLine().split("-");

        for (int i = 0; i < tk.length; i++) {

            // + 가 없는 경우 스킵
            if (!tk[i].contains("+")) continue;

            // + 가 있는 경우 계산
            int[] num = Arrays.stream(tk[i].split("\\+")).mapToInt(Integer::parseInt).toArray();

            int sum = 0;

            for (int j = 0; j < num.length; j++) {
                sum += num[j];
            }

            // 바꿔치기
            tk[i] = String.valueOf(sum);
        }

        // 뺄셈 계산 진행

        int answer = Integer.parseInt(tk[0]);

        for (int i = 1; i < tk.length; i++) {
            answer -= Integer.parseInt(tk[i]);
        }

        System.out.println(answer);
    }
}
