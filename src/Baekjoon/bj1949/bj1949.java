package Baekjoon.bj1949;

// 1949 우수마을
// https://www.acmicpc.net/problem/1949

import java.io.*;
import java.util.*;

public class bj1949 {

    static int n;
    static int[] nums;
    static Map<Integer, List<Integer>> linked;

    static int[] dp1;
    static int[] dp2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        linked = new HashMap<>();

        dp1 = new int[n];
        dp2 = new int[n];

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            if (!linked.containsKey(a)) linked.put(a, new ArrayList<>());
            linked.get(a).add(b);

            if (!linked.containsKey(b)) linked.put(b, new ArrayList<>());
            linked.get(b).add(a);
        }

        recursive(0, 0);

        System.out.println(Math.max(dp1[0], dp2[0]));
    }

    static void recursive(int n, int root) {

        for (int k : linked.get(n)) {
            if (k == root) continue;

            recursive(k, n);
            dp1[n] += Math.max(dp1[k], dp2[k]);
            dp2[n] += dp1[k];
        }

        dp2[n] += nums[n];
    }
}

