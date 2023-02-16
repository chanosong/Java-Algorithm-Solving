package Programmers.GroupPhoto;
// https://school.programmers.co.kr/learn/courses/30/lessons/1835

import java.util.*;

public class GroupPhoto {

    static int ans = 0;
    static String[] alpha = {"A", "C", "F", "J", "M", "N", "R", "T"};

    static List<Condition> list;
    static void dfs(Stack<String> st, boolean[] visited){
        if (st.size() == alpha.length) {
            boolean fulfill = true;
            for (Condition c : list){
                int posA = st.indexOf(c.A);
                int posB = st.indexOf(c.B);

                if (!c.dist.contains(Math.abs(posA - posB) - 1)) {
                    fulfill = false;
                    break;
                }
            }
            if (fulfill) ans++;
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                st.add(alpha[i]);
                dfs(st, visited);
                st.pop();
                visited[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        int answer = 0;

        list = new ArrayList<>();

        for (String now : data) {
            String former = now.substring(0,3);
            String last = now.substring(3);
            list.add(new Condition(former, last));
        }

        Stack<String> stack = new Stack<>();
        boolean[] visited = new boolean[alpha.length];

        dfs(stack, visited);

        answer = ans;
        System.out.println(answer);
    }
}

class Condition {
    String A;
    String B;
    Set<Integer> dist;

    Condition(String alpha, String cond) {
        dist = new HashSet<>();

        String[] al = alpha.split("~");
        A = al[0];
        B = al[1];

        String comparator = cond.substring(0,1);
        int lim = Integer.valueOf(cond.substring(1));

        switch (comparator) {
            case "=":
                dist.add(lim);
                break;
            case "<" :
                for (int i = 0; i < lim; i++) dist.add(i);
                break;
            case ">":
                for (int i = lim + 1; i < 7; i++) dist.add(i);
        }
    }
}