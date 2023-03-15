package Baekjoon.bj2606;
// https://www.acmicpc.net/problem/2606
import java.util.*;

public class bj2606 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int answer = 0;
        int n = sc.nextInt();
        int e = sc.nextInt();

        List<Edge> list = new ArrayList<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < e; i++) {
            list.add(new Edge(sc.nextInt(), sc.nextInt()));
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (Edge edge: list) {
                if (edge.isConnected(now)) {
                    int next = edge.getConnectedVertex(now);
                    if (visited[next - 1] == true) continue;

                    queue.add(next);
                    visited[next - 1] = true;
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}

class Edge {
    int start;
    int end;

    Edge(int start, int end) {
        this.start = start;
        this.end = end;
    }

    boolean isConnected (int n) {
        return n == start || n == end;
    }

    int getConnectedVertex (int n) {
        if (n == start) return end;
        return start;
    }
}
