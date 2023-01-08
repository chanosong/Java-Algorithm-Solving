package Programmers.Fibonacci;
//https://school.programmers.co.kr/learn/courses/30/lessons/12945
public class Fibonacci {
    /*
    static int fibonacci(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        return fibonacci(x-2) + fibonacci(x-1);
    }
    */
    static long[] arr;
    static long fibonacci2(int x) {
        for (int i = 2; i <= x; i++) {
            arr[i] = arr[i-2] % 1234567L + arr[i-1] % 1234567L;
        }
        return arr[x];
    }
    public static void main(String[] args) {
        int n = 100000;
        int answer = 0;
        arr = new long[n+1];
        arr[0] = 0L;
        arr[1] = 1L;
        answer = (int) (fibonacci2(n) % 1234567L);

        //answer = fibonacci(n) % 1234567;

        System.out.println(answer);
    }
}
