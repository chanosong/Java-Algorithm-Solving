package Baekjoon.bj10828;
// https://www.acmicpc.net/problem/10828
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.valueOf(br.readLine());

        Stack<String> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            String[] cmd = br.readLine().split(" ");

            switch(cmd[0]) {
                case "push":
                    st.push(cmd[1]);
                    break;
                case "pop":
                    if (st.isEmpty()) System.out.println(-1);
                    else System.out.println(st.pop());
                    break;
                case "size":
                    System.out.println(st.size());
                    break;
                case "empty":
                    if (st.isEmpty()) System.out.println(1);
                    else System.out.println(0);
                    break;
                case "top":
                    if(st.isEmpty()) System.out.println(-1);
                    else System.out.println(st.peek());
            }
        }
    }
}
