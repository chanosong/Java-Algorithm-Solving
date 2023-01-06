package Programmers.PackageBox;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/131704
 */

import java.util.*;

public class PackageBox {
    public static void main(String[] args) {
        int[] order = {1,2,5,3,4};
        int answer = 0;
        // index of order
        int idx = 0;
        // actual number
        int cur = 1;
        // sub trailer
        Stack<Integer> sub = new Stack<>();
        while (true) {
            if (idx >= order.length) break;
            // exactly correct
            if (order[idx] == cur) {
                idx++;
                cur++;
                answer++;
                continue;
            }
            // top of sub is proper num
            if (!sub.isEmpty() && sub.peek() == order[idx]) {
                sub.pop();
                idx++;
                answer++;
                continue;
            }
            // exist on origin
            if (cur < order[idx]){
                sub.add(cur++);
                continue;
            }
            // no way
            break;
        }

        System.out.println(answer);
    }
}
