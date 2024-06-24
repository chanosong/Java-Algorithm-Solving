package Baekjoon.bj2493;

// https://www.acmicpc.net/problem/2493
// 2493 탑

import java.io.*;
import java.util.*;

public class bj2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] height = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] answer = new int[n];

        Stack<Tower> st = new Stack<>();

        st.push(new Tower(height[0], 0));

        for (int i = 1; i < n; i++) {
            // stack이 빈 경우 삽입
            while (!st.isEmpty()) {
                if (st.peek().height > height[i]) {
                    answer[i] = st.peek().index + 1;
                    break;
                }
                st.pop();
            }

            st.push(new Tower(height[i], i));
        }


        for (int h : answer) {
            System.out.print(h + " ");
        }
        System.out.println();
    }
}

class Tower {
    int height;
    int index;

    Tower (int height, int index) {
        this.height = height;
        this.index = index;
    }
}