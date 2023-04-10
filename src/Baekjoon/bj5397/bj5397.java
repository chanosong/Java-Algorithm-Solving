package Baekjoon.bj5397;
// https://www.acmicpc.net/problem/5397
import java.util.*;

public class bj5397 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        Stack<String> left = new Stack<>();
        Stack<String> right = new Stack<>();

        for (int i = 0; i < n; i++) {
            String[] str = sc.nextLine().split("");

            for (String s: str) {
                switch (s) {
                    case "<":
                        if (left.isEmpty()) break;
                        right.push(left.pop());
                        break;
                    case ">":
                        if (right.isEmpty()) break;
                        left.push(right.pop());
                        break;
                    case "-":
                        if (left.isEmpty()) break;
                        left.pop();
                        break;
                    default:
                        left.push(s);
                }
            }
            StringBuffer sb = new StringBuffer();
            while (!left.isEmpty()) right.push(left.pop());
            while (!right.isEmpty()) sb.append(right.pop());

            System.out.println(sb);
        }
    }
}
