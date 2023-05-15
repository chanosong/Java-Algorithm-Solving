package Baekjoon.bj14888;

// https://www.acmicpc.net/problem/14888
// 연산자 끼워넣기

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class bj14888 {

    static long min = Long.MAX_VALUE;
    static long max = Long.MIN_VALUE;

    static int[] nums;

    static String[] op = new String[]{"+", "-", "*" , "/"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // n 입력
        int n = sc.nextInt();

        // 버퍼 초기화
        sc.nextLine();

        nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] operator = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] used = new int[operator.length];

        bfs(0, n - 1, new Stack<>(), used, operator);

        System.out.println(max);
        System.out.println(min);
    }

    static void bfs(int depth, int limit, Stack<Integer> order, int[] used, int[] operator) {
        // 모두 골랐다면
        if (depth == limit) {
            int[] orderArr = order.stream().mapToInt(Integer::intValue).toArray();
            long res = calculate(orderArr);

            min = Math.min(res, min);
            max = Math.max(res, max);
            return;
        }

        // 아직 고르지 못했다면
        for (int i = 0; i < used.length; i++) {
            // 사용이 불가능하다면 스킵
            if (used[i] == operator[i]) continue;

            // 사용이 가능하다면 사용
            used[i]++;
            order.push(i);
            bfs(depth + 1, limit, order, used, operator);
            order.pop();
            used[i]--;
        }
    }

    static long calculate(int[] order) {
        long res;

        Stack<String> st = new Stack<>();
        st.push(String.valueOf(nums[0]));

        int numCur = 1;
        int opCur = 0;

        while (true) {
            // 다 한 경우
            if (numCur == nums.length) break;

            switch (op[order[opCur]]) {
                case "+":
                    st.push(String.valueOf(Long.parseLong(st.pop()) + (long) (nums[numCur++])));
                    break;
                case "-":
                    st.push(String.valueOf(Long.parseLong(st.pop()) - (long) (nums[numCur++])));
                    break;
                case "*":
                    st.push(String.valueOf(Long.parseLong(st.pop()) * (long) (nums[numCur++])));
                    break;
                case "/":
                    st.push(String.valueOf(Long.parseLong(st.pop()) / (long) (nums[numCur++])));
            }
            // 커서 증가, 플래그 변경
            opCur++;
        }

        res = Long.parseLong(st.pop());

        return res;
    }
}
