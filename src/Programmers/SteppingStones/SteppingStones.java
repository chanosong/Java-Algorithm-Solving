package Programmers.SteppingStones;

import java.util.*;

public class SteppingStones {
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        int answer = 0;

        /*
        효율성 테스트 실패
        boolean flag = true;
        int idx = -1;

        while (flag) {
            for (int i = 1; i <= k; i++) {
                if (idx + i >= stones.length) {
                    idx = -1;
                    answer++;
                    break;
                }
                if (stones[idx + i] > 0) {
                    idx += i;
                    stones[idx]--;
                    break;
                }

                if (i == k) {
                    flag = false;
                    break;
                }
            }
        }
        */
        Deque<Integer> deque = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < k; i++) {
            deque.addLast(stones[i]);
        }

        max = Collections.max(deque);

        for (int i = k; i < stones.length; i++) {
            deque.pollFirst();
            deque.addLast(stones[i]);
            max = Math.min(max, Collections.max(deque));
        }

        answer = max;

        System.out.println(answer);
    }
}
