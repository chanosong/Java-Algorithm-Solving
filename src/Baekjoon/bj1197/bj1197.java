package Baekjoon.bj1197;
// https://www.acmicpc.net/board/search/all/problem/1197
// 최소 신장 트리
// with Kruskal
import java.util.*;

public class bj1197 {

    static int v;
    static int e;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static boolean[] used;
    static int[] parent;
    static int totalWeight = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();
        used = new boolean[e];
        parent = new int[v];

        // 버퍼 초기화
        sc.nextLine();

        for (int i = 0; i < e; i++) {
            int[] info = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            pq.add(new Edge(info[0], info[1], info[2]));
        }

        // 부모 배열 초기화
        for (int i = 0; i < v; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            tryToAdd();
        }

        System.out.println(totalWeight);
    }

    // 부모 노드 재귀로 찾기
    static int find(int idx) {
        // 더 이상 부모가 없는 경우
        if (parent[idx] == idx) return idx;
        parent[idx] = find(parent[idx]);
        return parent[idx];
    }

    static void tryToAdd() {

        Edge candidate = pq.poll();

        // 각 노드의 부모 확인
        int root1 = find(candidate.x - 1);
        int root2 = find(candidate.y - 1);

        // 이미 이어진 상황이라면 스킵
        if (root1 == root2) return;

        parent[root1] = root2;

        // 무게 증가
        totalWeight += candidate.w;
    }
}

class Edge implements Comparable<Edge> {
    int x;
    int y;
    int w;

    Edge(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    @Override
    public int compareTo(Edge e) {
        // 비교군의 무게가 더 큰 경우
        if (e.w > this.w) return -1;
        else if (e.w == this.w) return 0;
        return 1;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("v1:" + this.x + " ");
        sb.append("v2:" + this.y + " ");
        sb.append("weight:" + this.w);

        return sb.toString();
    }
}
