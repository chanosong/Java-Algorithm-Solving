package Baekjoon.bj23757;

// 아이들과 선물 상자
// https://www.acmicpc.net/problem/23757

import java.io.*;
import java.util.*;

public class bj23757 {

    static int n, m;
    static int[] boxArr;
    static int[] wishArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boxArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        wishArr =  Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int x : boxArr) pq.add(x);

        boolean isTrue = true;

        for (int x : wishArr) {
            if (pq.isEmpty()) {
                isTrue = false;
                break;
            }

            int now = pq.poll();

            if (x > now) {
                isTrue = false;
                break;
            }

            now -= x;

            if (now > 0) pq.add(now);
        }

        if(isTrue) System.out.println(1);
        else System.out.println(0);
    }
}
