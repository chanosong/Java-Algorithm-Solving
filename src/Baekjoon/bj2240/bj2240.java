package Baekjoon.bj2240;

// https://www.acmicpc.net/problem/2240
// 2240 자두나무

import java.io.*;
import java.util.*;

public class bj2240 {

    static int t;
    static int w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        List<Integer> num = new ArrayList<>();

        int straight = 0;
        int beforeTree = -1;

        for (int i = 0; i < t; i++) {
            int x = Integer.parseInt(br.readLine());

            if (i == 0) {
                straight = 1;
                beforeTree = x;
            }
            else {
                if (beforeTree != x) {
                    list.add(straight);
                    num.add(beforeTree);
                    beforeTree = x;
                    straight = 1;
                }
                else {
                    straight++;
                }
            }
        }

        list.add(straight);
        num.add(beforeTree);

        for (int i : list) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i : num) {
            System.out.print(i + " ");
        }
    }
}
