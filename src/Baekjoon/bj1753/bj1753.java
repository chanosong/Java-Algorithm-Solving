package Baekjoon.bj1753;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1753
// 최단경로

public class bj1753 {

    static int v, e, k;

    static List<List<Vertex>> list;
    static int[] minCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        k = Integer.parseInt(br.readLine());

        list = new ArrayList<>();

        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }

        minCost = new int[v + 1];

        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[k] = 0;

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Vertex newVertex = new Vertex(b, c);
            list.get(a).add(newVertex);
        }

        Vertex start = new Vertex(k, 0);

        PriorityQueue<Vertex> q = new PriorityQueue<>();
        q.add(start);

        while (!q.isEmpty()) {
            Vertex p = q.poll();

            for (int i = 0; i < list.get(p.idx).size(); i++) {
                int newIdx = list.get(p.idx).get(i).idx;
                // 자기 자신인 경우 스킵
                if (p.idx == newIdx) continue;

                int newCost = p.cost + list.get(p.idx).get(i).cost;

                if (newCost >= minCost[newIdx]) continue;
                minCost[newIdx] = newCost;
                q.add(new Vertex(newIdx, newCost));
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= v; i++) {
            if (minCost[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
            else sb.append(minCost[i]).append("\n");
        }

        System.out.println(sb);
    }
}

class Vertex implements Comparable<Vertex> {
    int idx, cost;

    Vertex(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Vertex o) {
        return cost - o.cost;
    }
}
