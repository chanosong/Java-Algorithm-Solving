package Baekjoon.bj10773;
// https://www.acmicpc.net/problem/10773
import java.util.Scanner;
import java.util.Stack;

public class bj10773 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            int input = sc.nextInt();

            if (input == 0) {
                st.pop();
                continue;
            }

            st.push(input);
        }

        while (!st.isEmpty()) {
            answer += st.pop();
        }

        System.out.println(answer);
    }
}
