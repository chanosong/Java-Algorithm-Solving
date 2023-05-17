package Programmers.OJT;

// https://school.programmers.co.kr/learn/courses/15009/lessons/121688?language=java
// PCCP 모의고사 #2 신입사원 교육

import java.util.PriorityQueue;

public class OJT {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{10,3,7,2}, 2));
        System.out.println(solution(new int[]{1,2,3,4}, 3));
    }

    static int solution(int[] ability, int number) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for (int stat : ability) {
            pq.add(stat);
        }

        for (int i = 0; i < number; i++) {
            int person1 = pq.poll();
            int person2 = pq.poll();

            for (int j = 0; j < 2; j++) {
                pq.add(person1 + person2);
            }
        }

        while (!pq.isEmpty()) {
            answer += pq.poll();
        }

        return answer;
    }
}
