package Programmers.TargetNumber;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/43165
 */
import java.util.LinkedList;
import java.util.Queue;

public class TargetNumber {

    static class prodecure {
        int depth;
        int sum;
        prodecure(int depth, int sum){
            this.depth = depth;
            this.sum = sum;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {4,1,2,1};
        int target = 4;
        int answer = 0;
        int d = 0;
        int s = 0;
        Queue<prodecure> queue = new LinkedList<>();
        queue.add(new prodecure(d, s + numbers[d]));
        queue.add(new prodecure(d, s - numbers[d]));

        while (!queue.isEmpty()) {
            prodecure now = queue.poll();
            // proper procedure
            if (now.depth == numbers.length - 1 && now.sum == target) answer++;

            // keep going
            if (now.depth < numbers.length - 1) {
                queue.add(new prodecure(now.depth + 1, now.sum + numbers[now.depth + 1]));
                queue.add(new prodecure(now.depth + 1, now.sum - numbers[now.depth + 1]));
            }
        }

        System.out.println(answer);
    }
}
