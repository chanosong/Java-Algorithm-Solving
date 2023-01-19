package Programmers.LongJump;
//https://school.programmers.co.kr/learn/courses/30/lessons/12914
public class LongJump {
    /*
    static int limit;
    static long calculate(long x) {
        if (x > limit) return 0;
        if (x == limit) return 1;
        return calculate(x + 1) % 1234567 + calculate(x + 2) % 1234567;
    }

     */
    public static void main(String[] args) {
        int n = 4;
        long[] list = new long[2000];
        list[0] = 1;
        list[1] = 2;

        for (int i = 2; i < n; i++) {
            list[i] = (list[i-2] + list[i-1]) % 1234567;
        }

        long answer = list[n-1];

        System.out.println(answer);
    }
}
