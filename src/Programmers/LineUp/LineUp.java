package Programmers.LineUp;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/12936
 */
import java.util.*;

public class LineUp {
    public static void main(String[] args) {
        int n = 20;
        long k = 2432902008176640000L;
        int[] answer = new int[n];
        long div = 1;
        int idx = 0;
        List<Integer> reference = new LinkedList<>();
        for (int i = 0; i < n; i++) reference.add(i + 1);

        for (int i = n; i > 1; i--) div *= i;

        for (int i = n; i > 1; i--) {
            div /= i;

            int tmp = (int) (((k - 1) / div) % i);
            answer[idx] = reference.get(tmp);
            reference.remove(tmp);
            idx++;
        }

        answer[idx] = reference.get(0);
        System.out.println(Arrays.toString(answer));
    }
}
