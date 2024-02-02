package Baekjoon.bj1874;

import java.io.*;
import java.util.*;

// 1874 스택 수열
// https://www.acmicpc.net/problem/1874

public class bj1874 {

    static int n;

    static Stack<Integer> st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int cursor = 1;

        StringBuilder sb = new StringBuilder();

        st = new Stack<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            // 커서와 원하는 숫자가 같은 경우
            if (cursor == num) {
                cursor++;
                sb.append("+\n-\n");
            }
            // 커서가 원하는 숫자보다 작은 경우
            else if (cursor < num) {
                while (cursor < num) {
                    st.push(cursor++);
                    sb.append("+\n");
                }
                cursor++;
                sb.append("+\n-\n");
            }
            // 커서가 원하는 숫자보다 큰 경우
            else {
                // 순서가 뒤집힌 경우 불가능
                if (st.peek() != num) {
                    sb = new StringBuilder("NO");
                    break;
                }
                // 순서가 맞는 경우 pop
                st.pop();
                sb.append("-\n");
            }
        }

        System.out.println(sb.toString());
    }
}
