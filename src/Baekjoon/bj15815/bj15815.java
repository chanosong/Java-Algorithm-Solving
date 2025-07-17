package Baekjoon.bj15815;

// https://www.acmicpc.net/problem/15815
// 15815 천재 수학자 성필

import java.io.*;
import java.util.*;

public class bj15815 {

    static String[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine().split("");

        Stack<String> st = new Stack<>();

        for (int i = 0; i < input.length; i++) {
            int a = 0;
            int b = 0;
            switch (input[i]) {
                case "+":
                    a = Integer.parseInt(st.pop());
                    b = Integer.parseInt(st.pop());
                    st.push((Integer.toString(a + b)));
                    break;
                case "-":
                    a = Integer.parseInt(st.pop());
                    b = Integer.parseInt(st.pop());
                    st.push((Integer.toString(b - a)));
                    break;
                case "*":
                    a = Integer.parseInt(st.pop());
                    b = Integer.parseInt(st.pop());
                    st.push((Integer.toString(a * b)));
                    break;
                case "/":
                    a = Integer.parseInt(st.pop());
                    b = Integer.parseInt(st.pop());
                    st.push((Integer.toString(b / a)));
                    break;
                default:
                    st.push(input[i]);
            }
        }

        System.out.println(st.pop());
    }
}
