package Baekjoon.bj1713;

// https://www.acmicpc.net/problem/1713
// 1713 후보 추천하기

import java.io.*;
import java.util.*;

public class bj1713 {

    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Student[] students = new Student[101];
        List<Student> pictures = new ArrayList<>();

        // 순서대로 추천 학생 확인
        for (int i = 0; i < k; i++) {
            // 처음 추천 받은 경우
            int idx = arr[i];
            if (students[idx] == null) {
                Collections.sort(pictures);
                // 사진 삭제가 필요한 경우
                if (pictures.size() == n) {
                    Student del = pictures.remove(n - 1);
                    students[del.num] = null;
                }

                // 새 학생 추가
                students[idx] = new Student(idx, 1, i);
                pictures.add(students[idx]);
            }
            // 사진 있는 경우
            else students[idx].recommendCnt++;
        }

        List<Integer> res = new ArrayList<>();
        for (Student s : pictures) {
            res.add(s.num);
        }
        Collections.sort(res);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i) + " ");
        }

        System.out.println(sb);
    }
}

class Student implements Comparable<Student> {
    int num;
    int recommendCnt;
    int lastRecommended;

    Student (int num, int recommendCnt, int lastRecommended) {
        this.num = num;
        this.recommendCnt = recommendCnt;
        this.lastRecommended = lastRecommended;
    }

    @Override
    public int compareTo (Student s) {
        // 추천 수가 더 많은 경우 후순위
        int res = s.recommendCnt - this.recommendCnt;
        if (res == 0) return s.lastRecommended - this.lastRecommended;
        return res;
    }
}
