package Baekjoon.bj20044;
// https://www.acmicpc.net/problem/20044
import java.util.Arrays;
import java.util.Scanner;

public class bj20044 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] codeSkill = new int[n * 2];
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n * 2; i++) {
            codeSkill[i] = sc.nextInt();
        }

        Arrays.sort(codeSkill);

        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, codeSkill[i] + codeSkill[n * 2 - 1 - i]);
        }

        System.out.println(answer);
    }
}
