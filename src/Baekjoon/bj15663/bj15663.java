package Baekjoon.bj15663;
//https://www.acmicpc.net/problem/15663
import java.util.*;


public class bj15663 {
    static int n;
    static int m;
    static int[] num;
    static int[] fragment;
    static boolean[] visited;

    static Set<String> set;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        num = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(num);

        fragment = new int[m];
        visited = new boolean[n];

        set = new LinkedHashSet<>();

        permutation(0);

        for (String s : set.toArray(String[]::new)) {
            System.out.println(s);
        }
    }

    static void permutation(int cnt) {
        if (cnt == m) {
            StringBuffer sb = new StringBuffer();
            for (int i : fragment) {
                sb.append(i).append(" ");
            }
            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            fragment[cnt] = num[i];
            permutation(cnt + 1);
            visited[i] = false;
        }
    }
}
