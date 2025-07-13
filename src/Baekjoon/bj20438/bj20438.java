package Baekjoon.bj20438;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/20438
// 20438 출석체크

public class bj20438 {

    static int n,k,q,m;
    static int[] sleepNum;
    static int[] attendanceNum;

    static int[] getCode;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sleepNum = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        attendanceNum = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        getCode = new int[n];

        for (int i = 0; i < k; i++) {
            getCode[sleepNum[i] - 3] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        Arrays.sort(attendanceNum);

        for (int i = 0; i < q; i++) {
            queue.add(attendanceNum[i]);
        }

        // 배수로 겹치는 경우 스킵
        List<Integer> attendance = new ArrayList<>();

        while (!queue.isEmpty()) {
            int now = queue.poll();

            int size = queue.size();

            boolean isSleeping = false;
            // 출석체크 뿌릴 인원이 자고있는 경우
            if (getCode[now - 3] == -1) isSleeping = true;

            for (int i = 0; i < size; i++) {
                int tmp = queue.poll();
                if (isSleeping || tmp % now != 0) queue.add(tmp);
            }

            if (!isSleeping) attendance.add(now);
        }


        // 출석체크 하는 인원 카운팅
        for (int i = 0; i < attendance.size(); i++) {
            int num = attendance.get(i);
            int times = num;
            if (getCode[num - 3] == -1) continue;

            while (num < n + 3) {
                if (getCode[num - 3] != -1) getCode[num - 3] = 1;
                num += times;
            }
        }

        int[] accSum = new int[n];
        if (getCode[0] != 1) accSum[0] = 1;

        // 누적합 진행
        for (int i = 1; i < n; i++) {
            if (getCode[i] < 1) accSum[i] += accSum[i - 1] + 1;
            else accSum[i] = accSum[i - 1];
        }

        // 해당 구간에 대해서 합산
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 3;
            int end = Integer.parseInt(st.nextToken()) - 3;

            int answer = 0;
            if (start == 0) answer = accSum[end];
            else answer = accSum[end] - accSum[start - 1];

            System.out.println(answer);
        }
    }
}
