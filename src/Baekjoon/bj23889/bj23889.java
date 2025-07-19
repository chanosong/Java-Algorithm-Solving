package Baekjoon.bj23889;

// https://www.acmicpc.net/problem/23889
// 23889 돌 굴러가유

import java.io.*;
import java.util.*;

public class bj23889 {

    static int n, m, k;
    static int[] arr;
    static int[] rockIdx;

    static long min = Long.MAX_VALUE;
    static int[] castle;
    static int[] stone;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        castle = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        stone = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(stone);

        // 구간별 합 계산
        List<Pair> graph = new ArrayList<>();

        for (int i = 0; i < k - 1; i++) {
            int left = stone[i] - 1;
            int right = stone[i + 1] - 1;
            int sum = 0;
            for (int j = left; j < right; j++) {
                sum += castle[j];
            }
            graph.add(new Pair(stone[i], sum));
        }
        // 마지막 구간
        int left = stone[k - 1] - 1;
        int sum = 0;
        for (int j = left; j < n; j++) {
            sum += castle[j];
        }
        graph.add(new Pair(stone[k - 1], sum));

        // 합 기준 내림차순, 인덱스 기준 오름차순 정렬
        graph.sort((a, b) -> {
            if (b.sum != a.sum) return Integer.compare(b.sum, a.sum);
            return Integer.compare(a.idx, b.idx);
        });

        // m개만 남기고 idx만 오름차순 정렬
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < m && i < graph.size(); i++) {
            result.add(graph.get(i).idx);
        }
        Collections.sort(result);

        for (int x : result) {
            System.out.println(x);
        }
    }
}

class Pair implements Comparable<Pair> {
    int idx;
    int sum;

    Pair (int idx, int sum) {
        this.idx = idx;
        this.sum = sum;
    }

    @Override
    public int compareTo(Pair o) {
        return o.sum - this.sum;
    }
}
