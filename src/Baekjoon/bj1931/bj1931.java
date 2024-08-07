package Baekjoon.bj1931;

// 1931 회의실 배정
// https://www.acmicpc.net/problem/1931

import java.util.*;
import java.io.*;

public class bj1931 {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        List<Time> timeList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");

            timeList.add(new Time(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        Collections.sort(timeList);

        Time last = timeList.get(0);
        int cnt = 1;

        for (int i = 1; i < timeList.size(); i++) {
            Time tmp = timeList.get(i);
            // 끝나는 시간과 시작 시간이 같거나 뒤인 경우 바로 넘겨 받기
            if (last.e <= tmp.s) {
                last = tmp;
                cnt++;
                continue;
            }

            // 그렇지 않은 경우 길이 비교 후 더 짧은 경우 바꾸기
            if (last.duration > tmp.duration && last.e > tmp.e) {
                last = tmp;
            }
        }


        System.out.println(cnt);
    }
}

class Time implements Comparable<Time> {
    int s, e;
    int duration;


    Time (int s, int e) {
        this.s = s;
        this.e = e;

        duration = e - s;
    }

    @Override
    public int compareTo(Time o) {
        if (this.s > o.s) return 1;
        else if (this.s == o.s) {
            if (this.e > o.e) return 1;
            else if (this.e == o.e) return 0;
            return -1;
        }
        return -1;
    }
}
