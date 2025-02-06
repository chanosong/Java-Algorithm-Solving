package Baekjoon.bj11725;

// https://www.acmicpc.net/problem/11725
// 트리의 부모 찾기

import java.util.*;
import java.io.*;

public class bj11725 {

    static int n;
    static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];
        int[] parent = new int[n + 1];


        Stack<Trace> st = new Stack<>();
        visited[1] = true;

        st.add(new Trace(0, 1));

        while (!st.isEmpty()) {
            Trace t = st.pop();

            parent[t.num] = t.parent;

            List<Integer> tlist = list.get(t.num);

            for (int i = 0; i < tlist.size(); i++) {
                if(visited[tlist.get(i)]) continue;

                st.add(new Trace(t.num, tlist.get(i)));
                visited[tlist.get(i)] = true;
            }
        }

        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }
}

class Trace {
    int parent, num;

    Trace (int parent, int num) {
        this.parent = parent;
        this.num = num;
    }
}
