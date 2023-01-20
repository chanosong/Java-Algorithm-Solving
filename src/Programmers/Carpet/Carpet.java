package Programmers.Carpet;
//https://school.programmers.co.kr/learn/courses/30/lessons/42842
import java.util.*;

public class Carpet {
    public static void main(String[] args) {
        int brown = 8;
        int yellow = 1;
        int[] answer = new int[2];
        int sum = brown + yellow;

        for (int i = 1; i <= yellow; i++) {
            if (yellow % i == 0 && (i + 2) * (yellow / i + 2) == sum) {
                answer[0] = yellow / i + 2;
                answer[1] = i + 2;
                break;
            }
        }
        System.out.println(Arrays.toString(answer));
    }   
}
