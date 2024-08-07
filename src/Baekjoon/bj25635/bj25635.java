package Baekjoon.bj25635;

// https://www.acmicpc.net/problem/25635
// 25635 자유 이용권

import java.io.*;
import java.util.*;

public class bj25635 {

    static long n;

    static List<Long> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Long.parseLong(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new ArrayList<>();
        long sum = 0;

        while (st.hasMoreTokens()) {
            long tmp = Integer.parseInt(st.nextToken());
            arr.add(tmp);
            sum += tmp;
        }

        Collections.sort(arr, Collections.reverseOrder());

        if ((sum - arr.get(0)) >= arr.get(0) - 1) System.out.println(sum);
        else System.out.println((sum - arr.get(0)) * 2 + 1);
    }
}
