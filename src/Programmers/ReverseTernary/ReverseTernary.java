package Programmers.ReverseTernary;
// https://school.programmers.co.kr/learn/courses/30/lessons/68935

import java.util.Stack;

public class ReverseTernary {
    public static void main(String[] args) {
        System.out.println(solution(45));
        System.out.println(solution(125));
    }

    static int solution(int n) {
        int answer = 0;

        Stack<Integer> st = new Stack<>();

        while (n != 0) {
            st.push(n % 3);
            n /= 3;
        }

        int m = 1;

        while (!st.isEmpty()) {
            answer += st.pop() * m;
            m *= 3;
        }

        return answer;
    }
}
