package Programmers.HateSameNum;
// https://school.programmers.co.kr/learn/courses/30/lessons/12906
import java.util.Arrays;
import java.util.Stack;

public class HateSameNum {
    public static void main(String[] args) {
        int[] arr = {1,1,3,3,0,1,1};
        int[] answer = {};

        Stack<Integer> st = new Stack<>();

        for (int i : arr) {
            // 스택이 빈 경우 그냥 넣기
            if (st.isEmpty()) {
                st.push(i);
                continue;
            }

            // 스택의 top이 다른 수라면 넣는다
            if (st.peek() != i) {
                st.push(i);
            }
        }

        answer = Arrays.stream(st.toArray(Integer[]::new)).mapToInt(Integer::intValue).toArray();
        System.out.println(answer);
    }
}
