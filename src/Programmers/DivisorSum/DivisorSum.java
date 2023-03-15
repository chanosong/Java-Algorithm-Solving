package Programmers.DivisorSum;

// https://school.programmers.co.kr/learn/courses/30/lessons/77884
public class DivisorSum {
    public static void main(String[] args) {
        int left = 24;
        int right = 27;
        int answer = 0;

        for (int i = left; i <= right; i++) {
            if (getPrimeCnt(i) % 2 == 0) {
                answer += i;
                continue;
            }
            answer -= i;
        }

        System.out.println(answer);
    }

    static int getPrimeCnt(int n) {
        int cnt = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) cnt++;
        }

        return cnt;
    }
}
