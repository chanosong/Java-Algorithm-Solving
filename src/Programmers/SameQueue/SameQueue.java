package Programmers.SameQueue;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/118667
 */
import java.util.*;
import java.util.stream.Collectors;

public class SameQueue {
    public static void main(String[] args) {
        int[] queue1 = {3,2,7,2};
        int[] queue2 = {4,6,5,1};
        Queue<Double> primeQ1 = new LinkedList<>(Arrays.stream(queue1).mapToDouble(i -> (long)i).boxed().collect(Collectors.toList()));
        Queue<Double> primeQ2 = new LinkedList<>(Arrays.stream(queue2).mapToDouble(i -> (long)i).boxed().collect(Collectors.toList()));
        int answer = 0;
        int limit = (primeQ1.size() + primeQ2.size()) * 2;
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();

        while (sum1 != sum2) {
            Double front;
            if (sum1 > sum2) {
                front = primeQ1.poll();
                sum1 -= front;
                sum2 += front;
                primeQ2.add(front);
            }
            else {
                front = primeQ2.poll();
                sum1 += front;
                sum2 -= front;
                primeQ1.add(front);
            }
            answer++;

            if (answer > limit) {
                answer = -1;
                break;
            }
        }

        System.out.println(answer);
    }
}
