package Baekjoon.bj2003;
// https://www.acmicpc.net/problem/2003
import java.util.Scanner;

public class bj2003 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int answer = 0;

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int front = 0;
        int back = 0;
        int sum = arr[front];

        while(back < n) {
            if (sum == m) {
                answer++;
                sum -= arr[front];
                front++;
                continue;
            }
            if (sum < m) {
                back++;
                if (back == n) break;
                sum += arr[back];
                continue;
            }
            if (sum > m) {
                sum -= arr[front];
                front++;
            }
        }

        System.out.println(answer);
    }
}
