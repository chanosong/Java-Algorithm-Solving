package Programmers.Spicier;

// https://school.programmers.co.kr/learn/courses/30/lessons/42626

import java.util.*;

public class Spicer {
    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // heap 생성
        for (int i : scoville) {
            pq.add(i);
        }

        while (true) {
            // 최소치를 넘은 경우 종료
            if (pq.peek() >= K) break;

            // 더이상 섞는 것이 불가능한 경우 -1
            if (pq.size() == 1) {
                answer = -1;
                break;
            }
            // 2개를 뽑아 섞고 다시 삽입
            pq.add(pq.poll() + pq.poll() * 2);
            answer++;
        }

        System.out.println(answer);

    }
}
