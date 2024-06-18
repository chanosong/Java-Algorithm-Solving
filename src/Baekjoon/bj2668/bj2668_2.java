package Baekjoon.bj2668;

// https://www.acmicpc.net/problem/2668
// 2668 숫자고르기

import java.io.*;
import java.util.*;

public class bj2668_2 {

    static int n;

    static int[] arr1;
    static int[] arr2;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       n = Integer.parseInt(br.readLine());

       arr1 = new int[n];
       arr2 = new int[n];

       for (int i = 0; i < n; i++) {
           arr1[i] = i + 1;
           arr2[i] = Integer.parseInt(br.readLine());
       }

       Set<Integer> tmp = new HashSet<>();
       Set<Integer> answer = new HashSet<>();

       // 순회 시작
       for (int i = 0; i < n; i++) {
           int idx = i;

           if (answer.contains(arr1[idx])) continue;

           Set<Integer> trace = new HashSet<>();
           boolean isNormal = false;

           while (true) {

               // 이미 체킹한 숫자면 탈출
               if (trace.contains(arr1[idx])) break;

               if (tmp.contains(arr1[idx])) {
                   tmp.remove(arr1[idx]);
                   trace.add(arr1[idx]);
               }
               else tmp.add(arr1[idx]);

               // 이미 체킹한 숫자면 탈출
               if (trace.contains(arr2[idx])) break;

               if (tmp.contains(arr2[idx])) {
                   tmp.remove(arr2[idx]);
                   trace.add(arr2[idx]);
               }
               else tmp.add(arr2[idx]);

               idx = arr2[idx] - 1;

               if (tmp.isEmpty()) {
                   isNormal = true;
                   break;
               }
           }

           if (isNormal) answer.addAll(trace);
           tmp.clear();
       }

        System.out.println(answer.size());

       List<Integer> list = new ArrayList<>(answer);
       Collections.sort(list);

       for (int i : list) {
           System.out.println(i);
       }
    }
}
